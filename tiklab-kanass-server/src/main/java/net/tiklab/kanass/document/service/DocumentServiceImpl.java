package net.tiklab.kanass.document.service;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.kanass.category.model.Category;
import net.tiklab.kanass.category.support.OpLogTemplateCategory;
import net.tiklab.kanass.document.support.OpLogTemplateDocument;
import net.tiklab.logging.modal.Logging;
import net.tiklab.logging.modal.LoggingType;
import net.tiklab.logging.service.LoggingByTemplService;
import net.tiklab.rpc.annotation.Exporter;
import net.tiklab.user.user.model.User;
import net.tiklab.kanass.document.dao.CommentDao;
import net.tiklab.kanass.document.dao.DocumentDao;
import net.tiklab.kanass.document.dao.LikeDao;
import net.tiklab.kanass.document.entity.CommentEntity;
import net.tiklab.kanass.document.entity.DocumentEntity;
import net.tiklab.kanass.document.entity.LikeEntity;
import net.tiklab.kanass.document.model.*;
import net.tiklab.kanass.document.model.*;
import net.tiklab.user.user.service.UserService;
import net.tiklab.utils.context.LoginContext;
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
    CommentDao commentDao;

    @Autowired
    LikeDao likeDao;

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

        return documentDao.createDocument(documentEntity);
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
    public Document findOne(String id,String type) {
        DocumentEntity documentEntity = documentDao.findDocument(id);
        //查询该文档的所有评论
        List<CommentEntity> commentList = commentDao.findCommentList(new CommentQuery().setDocumentId(id));

        Document document = BeanMapper.map(documentEntity, Document.class);
        if (!commentList.isEmpty()){
            //添加评论数
            document.setCommentNumber(commentList.size());
        }
        findLike(document,type);

        return document;
    }

    @Override
    public List<Document> findList(List<String> idList) {
        List<DocumentEntity> documentEntityList =  documentDao.findDocumentList(idList);

        List<Document> documentList =  BeanMapper.mapList(documentEntityList,Document.class);
        return documentList;
    }

    @Override
    public Document findDocument(@NotNull String id,String type) {
        Document document = findOne(id,type);

        joinTemplate.joinQuery(document);
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
    public void findLike( Document document,String type){
        LikeQuery likeQuery = new LikeQuery();
        likeQuery.setToWhomId(document.getId());
        likeQuery.setLikeType("doc");
        List<LikeEntity> likeList = likeDao.findLikeList(likeQuery);
        if (!likeList.isEmpty()){
            //view  是分享出去后访问的
            if ("view".equals(type)){
                document.setIsLike("false");
            }else {
                //根据用户id判断该用户是否点赞了
//                List<LikeEntity> collect1 = likeList.stream().filter(a -> findCreatUser().equals(a.getLikeUser())).collect(Collectors.toList());
//                if (CollectionUtils.isNotEmpty(collect1)){
//                    document.setIsLike("true");
//                }else {
//                    document.setIsLike("false");
//                }
            }
            List<Like> likes = BeanMapper.mapList(likeList, Like.class);
            joinTemplate.joinQuery(likes);
            List<User> userList = likes.stream().map(Like::getLikeUser).collect(Collectors.toList());
            if(!userList.isEmpty()){
                //取点赞人名字
                List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
                document.setLikeUserList(collect);
            }
            document.setLikenumInt(likeList.size());

        }else {
            document.setIsLike("false");
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