package com.doublekit.wiki.repository.service;

import com.doublekit.user.auth.passport.context.TicketContext;
import com.doublekit.user.auth.passport.context.TicketHolder;
import com.doublekit.user.auth.passport.model.Ticket;
import com.doublekit.user.user.model.User;
import com.doublekit.wiki.repository.dao.CommentDao;
import com.doublekit.wiki.repository.dao.DocumentDao;
import com.doublekit.wiki.repository.dao.LikeDao;
import com.doublekit.wiki.repository.entity.CommentPo;
import com.doublekit.wiki.repository.entity.DocumentPo;
import com.doublekit.wiki.repository.entity.LikePo;
import com.doublekit.wiki.repository.model.*;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

/**
* DocumentServiceImpl
*/
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentDao documentDao;

    @Autowired
    JoinQuery joinQuery;

    @Autowired
    CommentDao commentDao;

    @Autowired
    LikeDao likeDao;

    @Override
    public String createDocument(@NotNull @Valid Document document) {
        DocumentPo documentPo = BeanMapper.map(document, DocumentPo.class);

        return documentDao.createDocument(documentPo);
    }

    @Override
    public void updateDocument(@NotNull @Valid Document document) {

        DocumentPo documentPo = BeanMapper.map(document, DocumentPo.class);
        if (ObjectUtils.isEmpty(document.getCategory())){
            documentPo.setCategoryId("");
        }
        documentDao.updateDocument(documentPo);
    }

    @Override
    public void deleteDocument(@NotNull String id) {
        documentDao.deleteDocument(id);
    }

    @Override
    public Document findOne(String id,String type) {
        DocumentPo documentPo = documentDao.findDocument(id);
        //查询该文档的所有评论
        List<CommentPo> commentList = commentDao.findCommentList(new CommentQuery().setDocumentId(id));

        Document document = BeanMapper.map(documentPo, Document.class);
        if (CollectionUtils.isNotEmpty(commentList)){
            //添加评论数
            document.setCommentNumber(commentList.size());
        }
        findLike(document,type);

        return document;
    }

    @Override
    public List<Document> findList(List<String> idList) {
        List<DocumentPo> documentPoList =  documentDao.findDocumentList(idList);

        List<Document> documentList =  BeanMapper.mapList(documentPoList,Document.class);
        return documentList;
    }

    @Override
    public Document findDocument(@NotNull String id,String type) {
        Document document = findOne(id,type);

        joinQuery.queryOne(document);
        return document;
    }

    @Override
    public List<Document> findAllDocument() {
        List<DocumentPo> documentPoList =  documentDao.findAllDocument();

        List<Document> documentList =  BeanMapper.mapList(documentPoList,Document.class);

        joinQuery.queryList(documentList);
        return documentList;
    }

    @Override
    public List<Document> findDocumentList(DocumentQuery documentQuery) {
        List<DocumentPo> documentPoList = documentDao.findDocumentList(documentQuery);

        List<Document> documentList = BeanMapper.mapList(documentPoList,Document.class);

        joinQuery.queryList(documentList);

        return documentList;
    }

    @Override
    public Pagination<Document> findDocumentPage(DocumentQuery documentQuery) {
        Pagination<Document> pg = new Pagination<>();

        Pagination<DocumentPo>  pagination = documentDao.findDocumentPage(documentQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Document> documentList = BeanMapper.mapList(pagination.getDataList(),Document.class);

        joinQuery.queryList(documentList);

        pg.setDataList(documentList);
        return pg;
    }

    /**
     *查询点赞
     * @param
     */
    public void findLike( Document document,String type){
        LikeQuery likeQuery = new LikeQuery();
        likeQuery.setToWhomId(document.getId());
        likeQuery.setLikeType("doc");
        List<LikePo> likeList = likeDao.findLikeList(likeQuery);
        if (CollectionUtils.isNotEmpty(likeList)){
            //view  是分享出去后访问的
            if ("view".equals(type)){
                document.setIsLike("false");
            }else {
                //根据用户id判断该用户是否点赞了
                List<LikePo> collect1 = likeList.stream().filter(a -> findCreatUser().equals(a.getLikeUser())).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(collect1)){
                    document.setIsLike("true");
                }else {
                    document.setIsLike("false");
                }
            }
            List<Like> likes = BeanMapper.mapList(likeList, Like.class);
            joinQuery.queryList(likes);
            List<User> userList = likes.stream().map(Like::getLikeUser).collect(Collectors.toList());
            //取点赞人名字
            List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
            document.setLikenumInt(likeList.size());
            document.setLikeUserList(collect);
        }else {
            document.setIsLike("false");
        }
    }

    /**
     * 查询用户（创建人）id
     * @param
     */
    public String findCreatUser(){
        String ticketId = TicketHolder.get();
        Ticket ticket = TicketContext.get(ticketId);
        return ticket.getUserId();
    }
}