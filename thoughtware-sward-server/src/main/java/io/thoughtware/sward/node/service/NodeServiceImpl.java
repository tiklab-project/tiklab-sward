package io.thoughtware.sward.node.service;

import io.thoughtware.sward.category.service.WikiCategoryService;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
            }else {
                Integer brotherNum = nodeDao.getBrotherNum(repositoryId, null);
                node.setSort(brotherNum);
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
    public void deleteNode(@NotNull String id) {
        //删除最近的文档,或者目录
        RecentQuery recentQuery = new RecentQuery();
        recentQuery.setModelId(id);
        recentService.deleteRecnetByCondition(recentQuery);

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

        // 如果是目录，需要删除下级的所有文档和目录
        if(node.getType().equals("category")){
            NodeQuery nodeQuery = new NodeQuery();
            nodeQuery.setParentId(id);
            // 删除公共表
            List<Node> nodeList = findNodeList(nodeQuery);
            Object[] ids = nodeList.stream().map(Node::getId).toArray();
            nodeDao.deleteNodeCondition(ids);

            // 删除目录表
            wikiCategoryService.deleteCategoryByIds(ids);
        }

        nodeDao.deleteNode(id);
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
        List<Node> nodeList = findNodeList(nodeQuery);
        // 一般深度是[1, 2] 或者 [3, 4]
        Integer[] dimensions = nodeQuery.getDimensions();
        Arrays.sort(dimensions);
        int minDimensions = dimensions[0];
        List<Node> firstLeavelNodeList = nodeList.stream().filter(node -> node.getDimension().equals(minDimensions)).collect(Collectors.toList());

        for (Node firstLeavelNode : firstLeavelNodeList) {
            List<Node> childrenList = nodeList.stream().filter(node -> node.getParent() != null &&
                    node.getParent().getId().equals(firstLeavelNode.getId())).collect(Collectors.toList());
            firstLeavelNode.setChildren(childrenList);
        }
        return firstLeavelNodeList;
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

}