package io.tiklab.kanass.document.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.kanass.document.entity.DocumentEntity;
import io.tiklab.kanass.document.model.*;
import io.tiklab.kanass.document.support.OpLogTemplateDocument;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.kanass.document.dao.DocumentDao;
import io.tiklab.kanass.document.model.*;
import io.tiklab.security.logging.model.Logging;
import io.tiklab.security.logging.model.LoggingType;
import io.tiklab.security.logging.service.LoggingByTemplService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import io.tiklab.utils.context.LoginContext;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    JoinTemplate joinTemplate;

    @Autowired
    UserService userService;

    @Autowired
    LoggingByTemplService loggingByTemplService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @Value("${base.url:null")
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
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
        content.put("createUserIcon",user.getName().substring( 0, 1));
        log.setContent(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        loggingByTemplService.createLog(log);
    }


    @Override
    public String createDocument(@NotNull @Valid Document document) {
        DocumentEntity documentEntity = BeanMapper.map(document, DocumentEntity.class);
        String documentId = documentDao.createDocument(documentEntity);

        Document document1 = findDocumentById(documentId);
        Map<String, String> content = new HashMap<>();
        content.put("documentId", document1.getId());
        content.put("documentName", document1.getName());
        content.put("repositoryId", document1.getRepository().getId());
        String typeId = document1.getTypeId();
        if(typeId.equals("document")){
            content.put("iconUrl", "/images/mindMap.png");
        }else {
            content.put("iconUrl", "/images/document.png");
        }
        creatDynamic(content);

        return documentId;
    }

    @Override
    public void updateDocument(@NotNull @Valid Document document) {
        DocumentEntity documentEntity = BeanMapper.map(document, DocumentEntity.class);

        Document document1 = findDocumentById(documentEntity.getId());
        Map<String, String> content = new HashMap<>();
        content.put("documentId", document1.getId());
        content.put("documentName", document1.getName());
        content.put("repositoryId", document1.getRepository().getId());
        String typeId = document1.getTypeId();
        if(typeId.equals("document")){
            content.put("iconUrl", "/images/mindMap.png");
        }else {
            content.put("iconUrl", "/images/document.png");
        }
        creatDynamic(content);

        documentDao.updateDocument(documentEntity);
    }


    @Override
    public void deleteDocument(@NotNull String id) {
        //删除事项的关联
   //     workItemDocumentController.delete(id);
        documentDao.deleteDocument(id);
    }

    @Override
    public Document findOne(String id) {

        DocumentEntity documentEntity = documentDao.findDocument(id);

        Document document = BeanMapper.map(documentEntity, Document.class);

        return document;
    }

    @Override
    public List<Document> findList(List<String> idList) {
        List<DocumentEntity> documentEntityList =  documentDao.findDocumentList(idList);

        List<Document> documentList =  BeanMapper.mapList(documentEntityList,Document.class);
        return documentList;
    }

    @Override
    public Document findDocument(@NotNull String id) {
        Document document = findOne(id);

        joinTemplate.joinQuery(document);

        //查询该文档的所有评论
        CommentQuery commentQuery = new CommentQuery();
        commentQuery.setDocumentId(id);
        List<Comment> commentList = commentService.findCommentList(commentQuery);

        if (!commentList.isEmpty()){
            //添加评论数
            document.setCommentNumber(commentList.size());
        }else {
            document.setCommentNumber(0);
        }
        findLike(document);

        return document;
    }

    @Override
    public Document findDocumentById(@NotNull String id) {
        DocumentEntity documentEntity = documentDao.findDocument(id);
        Document document = BeanMapper.map(documentEntity, Document.class);


        joinTemplate.joinQuery(document);
        return document ;
    }

    @Override
    public List<Document> findAllDocument() {
        List<DocumentEntity> documentEntityList =  documentDao.findAllDocument();

        List<Document> documentList =  BeanMapper.mapList(documentEntityList,Document.class);

        joinTemplate.joinQuery(documentList);
        return documentList;
    }

    @Override
    public List<Document> findDocumentList(DocumentQuery documentQuery) {
        List<DocumentEntity> documentEntityList = documentDao.findDocumentList(documentQuery);

        List<Document> documentList = BeanMapper.mapList(documentEntityList,Document.class);

        joinTemplate.joinQuery(documentList);

        return documentList;
    }

    @Override
    public Pagination<Document> findDocumentPage(DocumentQuery documentQuery) {

        Pagination<DocumentEntity>  pagination = documentDao.findDocumentPage(documentQuery);

        List<Document> documentList = BeanMapper.mapList(pagination.getDataList(),Document.class);

        joinTemplate.joinQuery(documentList);

        return PaginationBuilder.build(pagination,documentList);
    }



    /**
     *查询点赞
     * @param
     */
    public void findLike( Document document){
        LikeQuery likeQuery = new LikeQuery();
        likeQuery.setToWhomId(document.getId());
        likeQuery.setLikeType("doc");
        List<Like> likeList = likeService.findLikeList(likeQuery);

        if (!likeList.isEmpty()){
            String createUserId = LoginContext.getLoginId();
            List<Like> collect1 = likeList.stream().filter(a -> createUserId.equals(a.getLikeUser().getId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collect1)){
                document.setLike(true);
            }else {
                document.setLike(false);
            }
            List<User> userList = likeList.stream().map(Like::getLikeUser).collect(Collectors.toList());
            if(!userList.isEmpty()){
                //取点赞人名字
                List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
                document.setLikeUserList(collect);
            }
            document.setLikenumInt(likeList.size());

        }else {
            document.setLike(false);
            document.setLikenumInt(0);
        }
    }

    /**
     * 查询登录用户（创建人）id
     * @param
     */
//    public String findCreatUser(){
//        String ticketId = TicketHolder.get();
//        Ticket ticket = TicketContext.get(ticketId);
//        return ticket.getUserId();
//    }
}