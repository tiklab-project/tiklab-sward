package io.thoughtware.sward.node.service;

import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.sward.category.model.WikiCategoryQuery;
import io.thoughtware.sward.category.service.WikiCategoryService;
import io.thoughtware.sward.document.model.DocumentQuery;
import io.thoughtware.sward.document.service.DocumentService;
import io.thoughtware.sward.node.dao.NodeDao;
import io.thoughtware.sward.node.entity.NodeEntity;
import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.node.model.NodeQuery;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.sward.support.model.RecentQuery;
import io.thoughtware.sward.support.service.RecentService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;


import io.thoughtware.user.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * NodeServiceImpl
 */
@Service
public class NodeServiceImpl implements NodeService {
    private static Logger logger = LoggerFactory.getLogger(DocumentService.class);

    private final Lock lock = new ReentrantLock();
    @Autowired
    NodeDao nodeDao;

    @Autowired
    UserService userService;
    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DocumentService documentService;

    @Autowired
    RecentService recentService;

    @Autowired
    WikiCategoryService wikiCategoryService;

    @Override
    public String createNode(@NotNull @Valid Node node) {
        lock.lock();
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = formater.format(new Date());
            node.setCreateTime(format);

            WikiRepository wikiRepository = node.getWikiRepository();
            String repositoryId = wikiRepository.getId();

            // 设置顺序
            Node parent = node.getParent();
            if(parent != null){
                Integer brotherNum = nodeDao.getBrotherNum(repositoryId, parent.getId());
                node.setSort(brotherNum);

                String parentId = parent.getId();
                NodeEntity parentNode = nodeDao.findNode(parentId);
                String treePath = parentNode.getTreePath();
                if(!StringUtils.isEmpty(treePath)){
                    treePath = treePath + parentId + ";";
                }else {
                    treePath = parentId + ";";
                }

                node.setTreePath(treePath);

                Integer dimension = parentNode.getDimension();
                node.setDimension(dimension + 1);

            }else {
                Integer brotherNum = nodeDao.getBrotherNum(repositoryId, null);
                node.setSort(brotherNum);
                node.setDimension(1);
            }

            NodeEntity nodeEntity = BeanMapper.map(node, NodeEntity.class);
            return nodeDao.createNode(nodeEntity);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String createConfluNode(@NotNull @Valid Node node){
        NodeEntity nodeEntity = BeanMapper.map(node, NodeEntity.class);
        return nodeDao.createNode(nodeEntity);
    }
    @Override
    public void updateNode(@NotNull @Valid Node node) {
        String moveToId = node.getMoveToId();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        node.setUpdateTime(format);

        if(moveToId != null){
            updateSort(node);

        }else {
            NodeEntity nodeEntity = BeanMapper.map(node, NodeEntity.class);
            nodeDao.updateNode(nodeEntity);
        }

    }

    public Node updateSort(@NotNull @Valid Node node){
        String moveType = node.getMoveType();
        String moveToId = node.getMoveToId();
        String moveToType = node.getMoveToType();
        // 更新
        // 被移动的id
        String id = node.getId();
        node = findNode(id);
        Integer sort = node.getSort();
        Integer dimension = node.getDimension();
        Node parent = node.getParent();

        // 第一步,先把大于被移动着的顺序的文档，目录的sort减一
        // 判断被移动的文档有没有上级目录
        if(parent != null){
            String wikiParentCategoryId = parent.getId();
            reduceSortInCategory(wikiParentCategoryId, sort);
        }else {
            // 如果没有上级目录
            WikiRepository wikiRepository = node.getWikiRepository();
            String repositoryId = wikiRepository.getId();
            reduceSortInRepository(repositoryId, sort);
        }

        // 第二步, 把移动到的位置的， 大于移动位置的文档，目录排序都加一
        // moveId 不为空的话，表示文档移动到moveToId的位置
        // 拖动类型1，文档被托放在id 为moveToId 的目录或者文档存在的位置
        if(moveType.equals("1")){
            // 如果被插入位置原来是文档
            // 由于先执行sort - 1 导致在 if(moveType.equals("1")) 中获取的时候获取的是减去1之后的sort,
            // 同级移动有问题，所以就提到外面
            Node toNode = findNode(moveToId);
            Integer toSort = toNode.getSort();
            // 在同一个级别移动， 同目录或者同数据库
            Node toNodeParent = toNode.getParent();
            // 如果移动到的位置有上级
            if(toNodeParent != null){
                // 如果插入位置与之前的位置都在同一个目录下面
                addSortInCategory(toNodeParent.getId(), toSort);
                // 更新被拖动的文档的顺序，
                node.setSort(toSort);
                node.setDimension(toNode.getDimension());
                node.setTreePath(toNode.getTreePath() != null ? toNode.getTreePath(): "nullstring");
                node.setParent(toNodeParent);
            }
            // 如果移动到的位置没有上级, 就是在知识库下面
            if(ObjectUtils.isEmpty(toNodeParent)){
                // 如果插入位置与之前的位置都在知识库下
                WikiRepository wikiRepository = toNode.getWikiRepository();
                String repositoryId = wikiRepository.getId();
                addSortInRepository(repositoryId, toSort);

                // 更新被拖动的文档的顺序，
                node.setSort(toSort);
                node.setDimension(toNode.getDimension());
                node.setTreePath("nullstring");
                Node parentNode = new Node();
                parentNode.setId("nullstring");
                node.setParent(parentNode);
            }
        }

        //  拖动类型2，文档被托放在id 为moveToId 的目录下的最后一个位置
        if(moveType.equals("2")){
            Node toNode1 = findNode(moveToId);
            WikiRepository wikiRepository = toNode1.getWikiRepository();
            // 获取移动到的目录有多少个下级
            Integer maxSort = nodeDao.getMaxSort(wikiRepository.getId(), moveToId);
            node.setSort(maxSort + 1);
            node.setDimension(toNode1.getDimension() + 1);
            if(toNode1.getTreePath() != null){
                node.setTreePath(toNode1.getTreePath() + moveToId + ";");
            }else {
                node.setTreePath(moveToId + ";");
            }
            node.setParent(toNode1);
        }

        //  拖动类型3，文档被托放知识库最后
        if(moveType.equals("3")){
            // 获取移动到的目录有多少个下级
            WikiRepository wikiRepository = node.getWikiRepository();
            Integer maxSort = nodeDao.getMaxSort(wikiRepository.getId(), null);
            // 查看被插入的位置与被移动的目录是否都在知识库下
            node.setSort(maxSort + 1);
            node.setDimension(1);
            node.setTreePath("nullstring");
            Node nodeParent = new Node();
            nodeParent.setId("nullstring");
            node.setParent(nodeParent);
        }
        NodeEntity nodeEntity = BeanMapper.map(node, NodeEntity.class);
        nodeDao.updateNode(nodeEntity);
        // 如果是目录， 更新下级
        String treePath = node.getTreePath();
        Integer distance = node.getDimension() - dimension;
        if(node.getType().equals("category")){
            updateChildrenDimensionAndTreePath(id, distance, treePath);
        }
        return node;
    }

    // 更新下级
    public void updateChildrenDimensionAndTreePath(String id, Integer distance, String treePath){
        List<NodeEntity> allChildrenNodeList = nodeDao.findAllChildrenNodeList(id);
        if(allChildrenNodeList.size() > 0){
            for (NodeEntity nodeEntity : allChildrenNodeList) {
                // wikiCategory 下
                Integer dimension = nodeEntity.getDimension();
                nodeEntity.setDimension(dimension + distance);

                String treePath1 = nodeEntity.getTreePath();
                int index = treePath1.indexOf(id);
                treePath1 = treePath1.substring(index);
                if(treePath != null && treePath != "nullstring"){
                    treePath1 = treePath + treePath1;
                }
                nodeEntity.setTreePath(treePath1);

                nodeDao.updateNode(nodeEntity);
            }
        }
    }

    @Override
    public List<Node> findAllChildrenNodeList(String id){
        List<NodeEntity> allChildrenNodeListEntity = nodeDao.findAllChildrenNodeList(id);
        List<Node> allChildrenNodeList = BeanMapper.mapList(allChildrenNodeListEntity, Node.class);
        return allChildrenNodeList;
    }

    @Override
    public void deleteNode(@NotNull String id) {
        //删除之后排序改变
        Node node = findNode(id);
        WikiRepository wikiRepository = node.getWikiRepository();
        Node parent = node.getParent();
        Integer sort = node.getSort();
        if(parent != null){
            nodeDao.updateSortAfterDelete(wikiRepository.getId(), parent.getId(), sort);
        }else {
            nodeDao.updateSortAfterDelete(wikiRepository.getId(), null, sort);
        }
        String type = node.getType();
        if(type.equals("category")){
            wikiCategoryService.deleteCategory(id);
        }else {
            documentService.deleteDocument(id);
        }
    }

    @Override
    public void deleteNodeById(String id){
        nodeDao.deleteNode(id);
    }
    public void deleteNodeCondition(NodeQuery nodeQuery){
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(NodeEntity.class)
                .in("id", nodeQuery.getIds())
                .eq("id", nodeQuery.getId())
                .eq("repositoryId", nodeQuery.getRepositoryId())
                .get();
        nodeDao.deleteNodeCondition(deleteCondition);
    }


    @Override
    public void deleteRepositoryNodeCondition(String repositoryId) {
        // 删除所有下级文档, 以及文档关联的数据
        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setRepositoryId(repositoryId);

        List<Node> nodeList = findNodeList(nodeQuery);
        if(nodeList.size() > 0){
            List<Node> categoryList = nodeList.stream().filter(item -> item.getType().equals("category")).collect(Collectors.toList());
            if(categoryList.size() > 0){
                List<String> categoryIdList = categoryList.stream().map(item -> item.getId()).collect(Collectors.toList());
                String[] categoryIds = categoryIdList.toArray(new String[categoryIdList.size()]);
                WikiCategoryQuery wikiCategoryQuery = new WikiCategoryQuery();
                wikiCategoryQuery.setIds(categoryIds);
                wikiCategoryQuery.setRepositoryId(repositoryId);
                // 删除包括当前目录在内的所有目录
                wikiCategoryService.batchDeleteCategory(wikiCategoryQuery);
                nodeList.removeAll(categoryIdList);
            }

            if(nodeList.size() > 0){
                // 删除所有文档
                List<String> documentIdList = nodeList.stream().map(item -> item.getId()).collect(Collectors.toList());
                String[] documentIds = documentIdList.toArray(new String[documentIdList.size()]);
                DocumentQuery documentQuery = new DocumentQuery();
                documentQuery.setIds(documentIds);
                documentQuery.setRepositoryId(repositoryId);
                documentService.batchDeleteDocument(documentQuery);
            }
        }

    }

    @Override
    public Node findOne(String id) {
        NodeEntity nodeEntity = nodeDao.findNode(id);
        Node node = BeanMapper.map(nodeEntity, Node.class);
        return node;
    }

    @Override
    public List<Node> findList(List<String> idList) {
        List<NodeEntity> nodeEntityList =  nodeDao.findNodeList(idList);

        List<Node> nodeList =  BeanMapper.mapList(nodeEntityList, Node.class);
        return nodeList;
    }

    @Override
    public Node findNode(@NotNull String id) {
        Node node = findOne(id);

        joinTemplate.joinQuery(node);

        return node;
    }

    @Override
    public List<Node> findAllNode() {
        List<NodeEntity> nodeEntityList =  nodeDao.findAllNode();

        List<Node> nodeList =  BeanMapper.mapList(nodeEntityList, Node.class);

        joinTemplate.joinQuery(nodeList);

        return nodeList;
    }

    @Override
    public List<Node> findNodeList(NodeQuery nodeQuery) {
        List<NodeEntity> nodeEntityList = nodeDao.findNodeList(nodeQuery);

        List<Node> nodeList = BeanMapper.mapList(nodeEntityList, Node.class);

        joinTemplate.joinQuery(nodeList);

        return nodeList;
    }

    @Override
    public Pagination<Node> findNodePage(NodeQuery nodeQuery) {
        Pagination<NodeEntity>  pagination = nodeDao.findNodePage(nodeQuery);

        List<Node> nodeList = BeanMapper.mapList(pagination.getDataList(), Node.class);

        joinTemplate.joinQuery(nodeList);

        return PaginationBuilder.build(pagination, nodeList);
    }

    @Override
    public List<Node> findNodePageTree(NodeQuery nodeQuery) {
        List<Node> nodeList = new ArrayList<>();
        List<Node> allNodeList = findNodeList(nodeQuery);
        // 一般深度是[1, 2] 或者 [3, 4]
        Integer[] dimensions = nodeQuery.getDimensions();
        Arrays.sort(dimensions);
        List<Integer> dimensionList = new ArrayList<Integer>(Arrays.asList(dimensions));
        Integer minDimensions = dimensionList.get(0);
        nodeList = allNodeList.stream().filter(node -> node.getDimension().equals(minDimensions)).collect(Collectors.toList());
        //删除已被使用的node
        if(nodeList.size() > 0){
            allNodeList.removeAll(nodeList);
            List<Node> nodes = setChildrenNode(allNodeList, nodeList, dimensionList);
            nodeList.addAll(nodes);
        }
        return nodeList;
    }

    List<Node> setChildrenNode(List<Node> allNodeList, List<Node> nodeList, List<Integer> dimensionList){
        List<Node> residueNodeList = new ArrayList<>();
        if(allNodeList.size() > 0){
            for (Node firstLeavelNode : nodeList) {
                List<Node> childrenList = allNodeList.stream().filter(node -> node.getParent() != null &&
                        node.getParent().getId().equals(firstLeavelNode.getId())).collect(Collectors.toList());
                if(childrenList.size() > 0){
                    allNodeList.removeAll(childrenList);
                    residueNodeList = allNodeList;
                    firstLeavelNode.setChildren(childrenList);
                    if(allNodeList.size() > 0){
                        setChildrenNode(allNodeList, childrenList, dimensionList);
                    }
                }else {
                    firstLeavelNode.setChildren(null);
                }
            }
        }
        return residueNodeList;
    }

    @Override
    public List<Node> findChildrenNodeList(String repositoryIds) {
        List<NodeEntity> nodeEntityList = nodeDao.findChildrenNodeList(repositoryIds);

        List<Node> nodeList = BeanMapper.mapList(nodeEntityList, Node.class);

        joinTemplate.joinQuery(nodeList);

        return nodeList;
    }

    @Override
    public List<Node> findNodeRecentList(RecentQuery recentQuery){
        List<NodeEntity> nodeEntityList = nodeDao.findNodeRecentList(recentQuery);

        List<Node> nodeList = BeanMapper.mapList(nodeEntityList, Node.class);

        joinTemplate.joinQuery(nodeList);

        return nodeList;
    }

    public void reduceSortInRepository(String repostioryId, Integer sort) {
        nodeDao.reduceSortInRepository(repostioryId, sort);
    }

    public void reduceSortInCategory(String wikiCategoryId, Integer sort) {
        nodeDao.reduceSortInCategory(wikiCategoryId, sort);
    }


    public void addSortInCategory(String wikiCategoryId, Integer sort){
        nodeDao.addSortInCategory(wikiCategoryId, sort);
    }

    public void addSortInRepository(String repositoryId, Integer sort){
        nodeDao.addSortInRepository(repositoryId, sort);
    }

    public List<Node>  findAllHigherNode(String id){
        List<Node> nodePageTree = new ArrayList<>();
        Node node = new Node();
        node = findNode(id);
        Node parent = node.getParent();
        if(parent != null) {
            String treePath = node.getTreePath();
            Integer dimension = node.getDimension();
            Integer[] dimensionList = new Integer[dimension];
            for (int i = 0; i < dimension; i++) {
                dimensionList[i] = i + 1;
            }

            String[] nodeIds = treePath.split(";");
            List<String> nodeIdList = Arrays.asList(nodeIds);

            NodeQuery nodeQuery = new NodeQuery();
            nodeQuery.setTreePath(nodeIdList.get(0));
            nodeQuery.setDimensions(dimensionList);
            nodeQuery.setId(nodeIdList.get(0));
            nodePageTree = findNodePageTree(nodeQuery);
        }
        return nodePageTree;
    }

    /**
     * 根据获取的list 拼装成树形
     */
    public Node assembleTree(List<Node> nodeList, Node node, String parentId){
        Node parentNode = new Node();
        List<Node> parentList = nodeList.stream().filter(item -> item.getId().equals(parentId)).collect(Collectors.toList());
        parentNode = parentList.get(0);
        List<Node> childrenList = new ArrayList<>();
        childrenList.add(node);
        parentNode.setChildren(childrenList);
        Node parent = parentNode.getParent();
        if(parent != null){
            Node node1 = assembleTree(nodeList, parentNode, parent.getId());
            if(node1 != null){
                parentNode = node1;
            }
        }
        return parentNode;
    }
}