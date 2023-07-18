package io.tiklab.kanass.document.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.dss.client.DssClient;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.document.entity.WikiDocumentEntity;
import io.tiklab.kanass.document.model.*;
import io.tiklab.kanass.document.support.OpLogTemplateDocument;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.kanass.document.dao.DocumentDao;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.security.logging.model.Logging;
import io.tiklab.security.logging.model.LoggingType;
import io.tiklab.security.logging.service.LoggingByTemplService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
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
    DocumentDao documentDao;
    @Autowired
    DssClient dssClient;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    UserService userService;

    @Autowired
    LoggingByTemplService loggingByTemplService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @Value("${base.url:null}")
    String baseUrl;

    void creatDynamic( Map<String, String> content){
        Logging log = new Logging();
        log.setBgroup("kanass");

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);
        content.put("master", user.getName());
        content.put("updateTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        log.setLoggingTemplateId(OpLogTemplateDocument.TEAMWIRE_LOGTEMPLATE_DOCUMENTADD);

        LoggingType opLogType = new LoggingType();
        opLogType.setId(OpLogTemplateDocument.TEAMWIRE_LOGTYPE_DOCUMENTADD);
        log.setActionType(opLogType);

        log.setModule("document");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        content.put("createUserIcon",user.getName().substring( 0, 1));
        log.setContent(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        loggingByTemplService.createLog(log);
    }


    @Override
    public String createDocument(@NotNull @Valid WikiDocument wikiDocument) {
        WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
        String documentId = documentDao.createDocument(wikiDocumentEntity);

        WikiDocument wikiDocument1 = findDocumentById(documentId);
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

        WikiDocument wikiDocument2 = findDocument(documentId);
        dssClient.save(wikiDocument2);
        return documentId;
    }

    @Override
    public void updateDocument(@NotNull @Valid WikiDocument wikiDocument) {
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
    public void deleteDocument(@NotNull String id) {
        //删除事项的关联
   //     workItemDocumentController.delete(id);
//        WikiDocumentEntity wikiDocumentEntity = documentDao.findDocument(id);
        dssClient.delete(WikiDocument.class, id);
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

}