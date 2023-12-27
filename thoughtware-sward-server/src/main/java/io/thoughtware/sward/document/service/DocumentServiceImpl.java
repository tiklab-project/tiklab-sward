package io.thoughtware.sward.document.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.security.logging.service.LoggingByTempService;
import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.document.model.*;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dss.client.DssClient;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.sward.category.service.WikiCategoryService;
import io.thoughtware.sward.document.entity.WikiDocumentEntity;
import io.thoughtware.sward.document.support.OpLogTemplateDocument;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.sward.document.dao.DocumentDao;
import io.thoughtware.sward.support.model.Recent;
import io.thoughtware.sward.support.model.RecentQuery;
import io.thoughtware.sward.support.service.RecentService;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.security.logging.model.Logging;
import io.thoughtware.security.logging.model.LoggingType;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* DocumentServiceImpl
*/
@Exporter
@Service

public class DocumentServiceImpl implements DocumentService {
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
    LikeService likeService;

    @Autowired
    WikiCategoryService wikiCategoryService;

    @Value("${base.url:null}")
    String baseUrl;

    void creatDynamic( Map<String, String> content){
        Logging log = new Logging();
        log.setBgroup("sward");

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);
        content.put("createUser", user.getName());
        content.put("updateTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        LoggingType opLogType = new LoggingType();
        opLogType.setId("SWARD_LOGTYPE_DOCUMENTADD");
        log.setActionType(opLogType);

        log.setModule("document");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        content.put("createUserIcon",user.getName().substring( 0, 1));
        log.setData(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        log.setAction(content.get("documentName"));
        log.setLink("/repositorydetail/${repositoryId}/doc/${documentId}");
        loggingByTemplService.createLog(log);
    }


    @Override
    public String createDocument(@NotNull @Valid WikiDocument wikiDocument) {
        // 设置顺序
        String categoryId = wikiDocument.getWikiCategory().getId();
        String repositoryId = wikiDocument.getWikiRepository().getId();
        Integer brotherNum = documentDao.getBrotherNum(repositoryId, categoryId);
        wikiDocument.setSort(brotherNum);

        WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
        String documentId = documentDao.createDocument(wikiDocumentEntity);
        WikiDocument wikiDocument1 = findDocumentById(documentId);

        Map<String, String> content = new HashMap<>();
        content.put("documentId", wikiDocument1.getId());
        content.put("documentName", wikiDocument1.getName());
        content.put("repositoryId", wikiDocument1.getWikiRepository().getId());

        WikiDocument wikiDocument2 = findDocument(documentId);
        dssClient.save(wikiDocument2);
        creatDynamic(content);
        return documentId;
    }

    @Override
    public Integer getBrotherNum(String repositoryId, String categoryId) {
        Integer brotherNum = documentDao.getBrotherNum(repositoryId, categoryId);
        return brotherNum;
    }

    @Override
    public void updateDocument(@NotNull @Valid WikiDocument wikiDocument) {
        Integer sort = wikiDocument.getSort();
        if(sort != null){
            WikiCategory wikiCategory = new WikiCategory();
            wikiCategory.setWikiRepository(wikiDocument.getWikiRepository());
            wikiCategory.setParentWikiCategory(wikiDocument.getWikiCategory());
            wikiCategory.setSort(wikiDocument.getSort());
            wikiCategory.setOldParentId(wikiDocument.getOldParentId());
            wikiCategory.setOldSort(wikiDocument.getOldSort());
            wikiCategoryService.updateSort(wikiCategory, "document");
        }
        WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
        documentDao.updateDocument(wikiDocumentEntity);
        WikiDocument wikiDocument1 = findDocumentById(wikiDocumentEntity.getId());
        Map<String, String> content = new HashMap<>();
        content.put("documentId", wikiDocument1.getId());
        content.put("documentName", wikiDocument1.getName());
        content.put("repositoryId", wikiDocument1.getWikiRepository().getId());
        String typeId = wikiDocument1.getTypeId();

        if(typeId.equals("document")){
            content.put("iconUrl", "/images/mindMap.png");
        }else {
            content.put("iconUrl", "/images/document.png");
        }

        dssClient.update(wikiDocument1);
    }

    @Override
    public void updateDocumentInit(WikiDocument wikiDocument) {
        WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
        documentDao.updateDocument(wikiDocumentEntity);
    }


    @Override
    public void deleteDocument(@NotNull String id) {
        //删除事项的关联
    //    workItemDocumentController.delete(id);
    //    WikiDocumentEntity wikiDocumentEntity = documentDao.findDocument(id);
        RecentQuery recentQuery = new RecentQuery();
        recentQuery.setModelId(id);
        List<Recent> recentList = recentService.findRecentList(recentQuery);
        for (Recent recent : recentList) {
            recentService.deleteRecent(recent.getId());
        }

        dssClient.delete(WikiDocument.class, id);
        documentDao.deleteDocument(id);

    }
    @Override
    public void deleteDocumentAndSort(@NotNull @Valid WikiDocument wikiDocument) {
        //删除下面相关联的目录和文档
        String id = wikiDocument.getId();
        String repositoryId = wikiDocument.getWikiRepository().getId();
        WikiCategory wikiCategory = wikiDocument.getWikiCategory();
        Integer sort = wikiDocument.getSort();
        String parentWikiCategoryId = new String();
        if(wikiCategory != null){
            parentWikiCategoryId = wikiCategory.getId();
        }
        wikiCategoryService.updateSortAfterDelete(repositoryId, parentWikiCategoryId, sort);
        documentDao.deleteDocument(id);
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

        joinTemplate.joinQuery(wikiDocument);

        if(!ObjectUtils.isEmpty(wikiDocument)){
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
}