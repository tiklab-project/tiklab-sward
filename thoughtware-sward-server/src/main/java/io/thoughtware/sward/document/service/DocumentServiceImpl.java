package io.thoughtware.sward.document.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.security.logging.logging.model.Logging;
import io.thoughtware.security.logging.logging.model.LoggingType;
import io.thoughtware.security.logging.logging.service.LoggingByTempService;
import io.thoughtware.security.logging.logging.service.LoggingService;
import io.thoughtware.sward.document.model.*;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dss.client.DssClient;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.sward.document.entity.WikiDocumentEntity;
import io.thoughtware.sward.node.entity.NodeEntity;
import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.node.model.NodeQuery;
import io.thoughtware.sward.node.service.NodeService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.sward.document.dao.DocumentDao;
import io.thoughtware.sward.support.model.RecentQuery;
import io.thoughtware.sward.support.service.RecentService;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
* DocumentServiceImpl
*/
@Exporter
@Service
public class DocumentServiceImpl implements DocumentService {
    private static Logger logger = LoggerFactory.getLogger(DocumentService.class);
    // 新建锁
    private final Lock lock = new ReentrantLock();
    @Autowired
    JpaTemplate jpaTemplate;
    @Autowired
    DocumentDao documentDao;

    @Autowired
    DocumentFocusService documentFocusService;
    @Autowired
    DssClient dssClient;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    UserService userService;

    @Autowired
    RecentService recentService;

    @Autowired
    LoggingByTempService loggingByTemplService;

    @Autowired
    CommentService commentService;

    @Autowired
    DocumentAttachService documentAttachService;

    @Autowired
    LikeService likeService;

    @Autowired
    NodeService nodeService;

    @Autowired
    ShareRelationService shareRelationService;

    @Autowired
    LoggingService loggingService;

    @Value("${base.url:null}")
    String baseUrl;

    void createDynamic(Node node){
        Logging log = new Logging();
        Map<String, String> content = new HashMap<>();

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);

        LoggingType opLogType = new LoggingType();
        opLogType.setId("SWARD_LOGTYPE_DOCUMENTADD");

        log.setBgroup("sward");
        log.setUser(user);
        log.setActionType(opLogType);
        log.setModule("document");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        log.setBaseUrl(baseUrl);
        String documentType = node.getDocumentType();
        if(documentType.equals("document")){
            log.setLink("/repositorydetail/${repositoryId}/doc/${documentId}");
        }
        if(documentType.equals("markdown")){
            log.setLink("/repositorydetail/${repositoryId}/markdownView/${documentId}");
        }

        content.put("documentId", node.getId());
        content.put("documentName", node.getName());
        content.put("documentType", node.getDocumentType());
        content.put("repositoryId", node.getWikiRepository().getId());
        content.put("createUser", user.getName());
        content.put("updateTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        content.put("createUserIcon",user.getName().substring( 0, 1));

        log.setData(JSONObject.toJSONString(content));
        log.setAction(node.getName());
        loggingByTemplService.createLog(log);
    }

    void creatUpdateOplog(Node newNode, Node oldNode){
        Map<String, String> logContent = new HashMap<>();

        Logging log = new Logging();
        log.setBgroup("sward");
        log.setModule("document");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);

        logContent.put("documentId", oldNode.getId());
        logContent.put("documentType", oldNode.getDocumentType());
        logContent.put("repositoryId", oldNode.getWikiRepository().getId());
        logContent.put("createUserName", user.getName());
        logContent.put("createUserId", createUserId);
        logContent.put("createTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        logContent.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());

        logContent.put("oldValue", oldNode.getName());
        logContent.put("newValue", newNode.getName());


        LoggingType opLogType = new LoggingType();
        opLogType.setId("SWARD_LOGTYPE_DOCUMENTUPDATENAME");
        log.setActionType(opLogType);

        log.setBaseUrl(baseUrl);
        log.setAction(newNode.getName());

        String documentType = oldNode.getType();
        if(documentType.equals("document")){
            log.setLink("/repositorydetail/${repositoryId}/doc/${documentId}");
        }
        if(documentType.equals("markdown")){
            log.setLink("/repositorydetail/${repositoryId}/markdownView/${documentId}");
        }
        log.setData(JSON.toJSONString(logContent));
        loggingByTemplService.createLog(log);
    }

    @Override
    public String createDocument(@NotNull @Valid WikiDocument wikiDocument) {
        // 设置顺序
        String documentId = new String();
        Node node = wikiDocument.getNode();
        String nodeId = new String();

        try {
            nodeId = nodeService.createNode(node);

            wikiDocument.setId(nodeId);
            WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
            documentId = documentDao.createDocument(wikiDocumentEntity);
        } catch (Exception e) {
            throw new ApplicationException(2000, "文档添加失败" + e.getMessage());
        }
        node = nodeService.findNode(nodeId);

        dssClient.save(node);
        dssClient.save(wikiDocument);
        createDynamic(node);
        return documentId;
    }

    @Override
    public String createConfluDocument(@NotNull @Valid WikiDocument wikiDocument){
        String documentId = new String();
        try {
            Node node = wikiDocument.getNode();
            String nodeId = nodeService.createConfluNode(node);

            wikiDocument.setId(nodeId);
            WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
            documentId = documentDao.createDocument(wikiDocumentEntity);
        } catch (Exception e) {
            throw new ApplicationException(2000, "文档添加失败" + e.getMessage());
        }
        return documentId;
    }
    @Override
    public Integer getBrotherNum(String repositoryId, String categoryId) {
        Integer brotherNum = documentDao.getBrotherNum(repositoryId, categoryId);
        return brotherNum;
    }

    @Override
    public Integer getMaxSort(String repositoryId, String categoryId) {
        Integer maxSort = documentDao.getMaxSort(repositoryId, categoryId);
        return maxSort;
    }

    @Override
    public void updateDocument(@NotNull @Valid WikiDocument wikiDocument) {
        String id = wikiDocument.getId();
        Node oldNode = nodeService.findNode(id);

        Node node = new Node();
        if(wikiDocument.getNode() != null){
            node = wikiDocument.getNode();
        }else{
            node.setId(wikiDocument.getId());
        }
        nodeService.updateNode(node);

        if(wikiDocument.getDetails() != null){
            WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
            documentDao.updateDocument(wikiDocumentEntity);
        }
        if(node.getName() != null){
            creatUpdateOplog(node, oldNode);
        }
        dssClient.update(node);
        dssClient.update(wikiDocument);
    }


    @Override
    public void updateDocumentInit(WikiDocument wikiDocument) {
        WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
        documentDao.updateDocument(wikiDocumentEntity);
    }


    @Override
    public void deleteDocument(@NotNull String id) {

        dssClient.delete(WikiDocument.class, id);
        documentDao.deleteDocument(id);
        nodeService.deleteNodeById(id);
        // 删除最近查看的
        RecentQuery recentQuery = new RecentQuery();
        recentQuery.setModelId(id);
        recentService.deleteRecnetByCondition(recentQuery);

        // 删除文档关联的关注数据
        DocumentFocusQuery documentFocusQuery = new DocumentFocusQuery();
        documentFocusQuery.setDocumentId(id);
        documentFocusService.deleteDocumentFocusByCondition(documentFocusQuery);

        // 删除关联的评论
        CommentQuery commentQuery = new CommentQuery();
        commentQuery.setDocumentId(id);
        commentService.deleteCommentCondition(commentQuery);

        LikeQuery likeQuery = new LikeQuery();
        likeQuery.setToWhomId(id);
        likeService.deleteLikeCondition(likeQuery);

        // 删除关联的附件
        DocumentAttachQuery documentAttachQuery = new DocumentAttachQuery();
        documentAttachQuery.setDocumentId(id);
        documentAttachService.deleteDocumentAttachCondition(documentAttachQuery);

        // 删除分享的文档记录
        ShareRelationQuery shareRelationQuery = new ShareRelationQuery();
        shareRelationQuery.setNodeId(id);
        shareRelationService.deleteShareRelationCondition(shareRelationQuery);


    }

    @Override
    public void deleteDocumentCondition(DocumentQuery documentQuery ) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WikiDocumentEntity.class)
                .in("id", documentQuery.getIds())
                .eq("id", documentQuery.getId())
                .get();
        jpaTemplate.delete(deleteCondition);
    }

    @Override
    public void batchDeleteDocument(DocumentQuery documentQuery){
        String[] ids = documentQuery.getIds();
        if(ids.length > 0){
            // 删除文档本身和node
            deleteDocumentCondition(documentQuery);


            NodeQuery nodeQuery = new NodeQuery();
            nodeQuery.setIds(ids);
            nodeService.deleteNodeCondition(nodeQuery);

            // 删除最近查看的
            RecentQuery recentQuery = new RecentQuery();
            recentQuery.setModelIds(ids);
            recentService.deleteRecnetByCondition(recentQuery);


            // 删除文档关联的关注数据
            DocumentFocusQuery documentFocusQuery = new DocumentFocusQuery();
            documentFocusQuery.setDocumentIds(ids);
            documentFocusService.deleteDocumentFocusByCondition(documentFocusQuery);

            // 删除关联的评论
            CommentQuery commentQuery = new CommentQuery();
            commentQuery.setDocumentIds(ids);
            commentService.deleteCommentCondition(commentQuery);

            // 删除关联的附件
            DocumentAttachQuery documentAttachQuery = new DocumentAttachQuery();
            documentAttachQuery.setDocumentIds(ids);
            documentAttachService.deleteDocumentAttachCondition(documentAttachQuery);

            LikeQuery likeQuery = new LikeQuery();
            likeQuery.setToWhomIds(ids);
            likeService.deleteLikeCondition(likeQuery);

            // 删除分享的文档记录
            ShareRelationQuery shareRelationQuery = new ShareRelationQuery();
            shareRelationQuery.setNodeIds(ids);
            shareRelationService.deleteShareRelationCondition(shareRelationQuery);
        }

    }


    @Override
    public WikiDocument findOne(String id) {

        WikiDocumentEntity wikiDocumentEntity = documentDao.findDocument(id);

        WikiDocument wikiDocument = BeanMapper.map(wikiDocumentEntity, WikiDocument.class);

        return wikiDocument;
    }

    @Override
    public List<WikiDocument> findList(List<String> idList) {
        List<WikiDocumentEntity> wikiDocumentEntityList =  documentDao.findDocumentList(idList);

        List<WikiDocument> wikiDocumentList =  BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);
        return wikiDocumentList;
    }

    @Override
    public WikiDocument findDocument(@NotNull String id) {
        WikiDocument wikiDocument = findOne(id);


        if(!ObjectUtils.isEmpty(wikiDocument)){
            Node node = nodeService.findNode(id);
            wikiDocument.setNode(node);
            joinTemplate.joinQuery(wikiDocument);
            CommentQuery commentQuery = new CommentQuery();
            commentQuery.setDocumentId(id);
            List<Comment> commentList = commentService.findCommentList(commentQuery);

            if (!commentList.isEmpty()){
                //添加评论数
                wikiDocument.setCommentNumber(commentList.size());
            }else {
                wikiDocument.setCommentNumber(0);
            }
            findLike(wikiDocument);
            findFocus(wikiDocument);

        }
        return wikiDocument;
    }

    @Override
    public WikiDocument findDocumentById(@NotNull String id) {
        WikiDocumentEntity wikiDocumentEntity = documentDao.findDocument(id);
        WikiDocument wikiDocument = BeanMapper.map(wikiDocumentEntity, WikiDocument.class);
        Node node = nodeService.findNode(id);
        wikiDocument.setNode(node);

        CommentQuery commentQuery = new CommentQuery();
        commentQuery.setDocumentId(id);
        List<Comment> commentList = commentService.findCommentList(commentQuery);

        if (!commentList.isEmpty()){
            //添加评论数
            wikiDocument.setCommentNumber(commentList.size());
        }else {
            wikiDocument.setCommentNumber(0);
        }
        joinTemplate.joinQuery(wikiDocument);
        return wikiDocument;
    }

    @Override
    public List<Map<String, Object>> findDocumentByRepositoryIds(String repositoryIds) {
        String sql = "select repository_id from wiki_document t where t.repository_id in "+ repositoryIds;
        List<Map<String, Object>> documentList = this.jpaTemplate.getJdbcTemplate().queryForList(sql);
        return documentList;
    }

    @Override
    public List<WikiDocument> findAllDocument() {
        List<WikiDocumentEntity> wikiDocumentEntityList =  documentDao.findAllDocument();

        List<WikiDocument> wikiDocumentList =  BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);
        return wikiDocumentList;
    }

    @Override
    public List<WikiDocument> findDocumentList(DocumentQuery documentQuery) {
        List<WikiDocumentEntity> wikiDocumentEntityList = documentDao.findDocumentList(documentQuery);

        List<WikiDocument> wikiDocumentList = BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);

        return wikiDocumentList;
    }

    @Override
    public Integer findDocumentCount(DocumentQuery documentQuery) {
        List<WikiDocumentEntity> documentList = documentDao.findDocumentList(documentQuery);
        int size = documentList.size();
        return size;
    }


    @Override
    public Pagination<WikiDocument> findDocumentPage(DocumentQuery documentQuery) {

        Pagination<WikiDocumentEntity>  pagination = documentDao.findDocumentPage(documentQuery);

        List<WikiDocument> wikiDocumentList = BeanMapper.mapList(pagination.getDataList(), WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);

        return PaginationBuilder.build(pagination, wikiDocumentList);
    }

    @Override
    public List<WikiDocument> findDocuementByKeyWork(String keyWork) {
        List<WikiDocumentEntity> wikiDocumentEntityList = documentDao.findDocuementByKeyWork(keyWork);

        List<WikiDocument> wikiDocumentList = BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);

        return wikiDocumentList;
    }


    /**
     *查询点赞
     * @param
     */
    public void findLike( WikiDocument wikiDocument){
        LikeQuery likeQuery = new LikeQuery();
        likeQuery.setToWhomId(wikiDocument.getId());
        likeQuery.setLikeType("doc");
        List<Like> likeList = likeService.findLikeList(likeQuery);

        if (!likeList.isEmpty()){
            String createUserId = LoginContext.getLoginId();
            List<Like> collect1 = likeList.stream().filter(a -> createUserId.equals(a.getLikeUser().getId())).collect(Collectors.toList());
            if (!collect1.isEmpty()){
                wikiDocument.setLike(true);
            }else {
                wikiDocument.setLike(false);
            }
            List<User> userList = likeList.stream().map(Like::getLikeUser).collect(Collectors.toList());
            if(!userList.isEmpty()){
                //取点赞人名字
                List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
                wikiDocument.setLikeUserList(collect);
            }
            wikiDocument.setLikenumInt(likeList.size());

        }else {
            wikiDocument.setLike(false);
            wikiDocument.setLikenumInt(0);
        }
    }

    public void findFocus( WikiDocument wikiDocument){
        DocumentFocusQuery documentFocusQuery = new DocumentFocusQuery();
        documentFocusQuery.setDocumentId(wikiDocument.getId());
        String createUserId = LoginContext.getLoginId();
        documentFocusQuery.setMasterId(createUserId);
        List<DocumentFocus> documentFocusList = documentFocusService.findDocumentFocusList(documentFocusQuery);

        if (!documentFocusList.isEmpty()){
            wikiDocument.setFocus(true);
        }else {
            wikiDocument.setFocus(false);
        }
    }

    @Override
    public List<WikiDocument> findRecentDocumentList(RecentQuery recentQuery) {
        List<WikiDocumentEntity> wikiDocumentEntityList = documentDao.findRecentDocumentList(recentQuery);

        List<WikiDocument> wikiDocumentList = BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);

        return wikiDocumentList;
    }

    @Override
    public void reduceSortInCategory(String wikiCategoryId, Integer sort) {
        documentDao.reduceSortInCategory(wikiCategoryId, sort);
    }

    @Override
    public void reduceSortInRepository(String repositoryId, Integer sort) {
        documentDao.reduceSortInRepository(repositoryId, sort);
    }


    @Override
    public void addSortInCategory(String wikiCategoryId, Integer sort) {
        documentDao.addSortInCategory(wikiCategoryId, sort);
    }

    @Override
    public void addSortInRepository(String repositoryId, Integer sort) {
        documentDao.addSortInRepository(repositoryId, sort);
    }

    /**
     * 查找所有目录所有下级的文件
     * @param categoryId
     * @return
     */
    public List<WikiDocument> findAllChildrenDocumentList(String categoryId) {
        List<WikiDocumentEntity> documentListEntity = documentDao.findAllChildrenDocumentList(categoryId);

        List<WikiDocument> wikiDocumentList = BeanMapper.mapList(documentListEntity, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);

        return wikiDocumentList;
    }

}