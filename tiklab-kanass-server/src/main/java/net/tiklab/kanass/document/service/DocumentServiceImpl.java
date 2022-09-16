package net.tiklab.kanass.document.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
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
    CommentDao commentDao;

    @Autowired
    LikeDao likeDao;
/*
    @Autowired
    private WorkItemDocumentController workItemDocumentController;*/

    @Override
    public String createDocument(@NotNull @Valid Document document) {
        DocumentEntity documentEntity = BeanMapper.map(document, DocumentEntity.class);

        return documentDao.createDocument(documentEntity);
    }

    @Override
    public void updateDocument(@NotNull @Valid Document document) {

        DocumentEntity documentEntity = BeanMapper.map(document, DocumentEntity.class);
//        if (ObjectUtils.isEmpty(document.getCategory())){
//            documentEntity.setCategoryId("");
//        }
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