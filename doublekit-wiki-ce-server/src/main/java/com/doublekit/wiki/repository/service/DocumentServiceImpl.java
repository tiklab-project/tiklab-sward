package com.doublekit.wiki.repository.service;

import com.doublekit.wiki.repository.dao.CommentDao;
import com.doublekit.wiki.repository.dao.DocumentDao;
import com.doublekit.wiki.repository.entity.CommentPo;
import com.doublekit.wiki.repository.entity.DocumentPo;
import com.doublekit.wiki.repository.model.CommentQuery;
import com.doublekit.wiki.repository.model.Document;
import com.doublekit.wiki.repository.model.DocumentQuery;

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
    public Document findOne(String id) {
        DocumentPo documentPo = documentDao.findDocument(id);
        List<CommentPo> commentList = commentDao.findCommentList(new CommentQuery().setDocumentId(id));
        Document document = BeanMapper.map(documentPo, Document.class);
        if (CollectionUtils.isNotEmpty(commentList)){
            document.setCommentNumber(commentList.size());
        }
        return document;
    }

    @Override
    public List<Document> findList(List<String> idList) {
        List<DocumentPo> documentPoList =  documentDao.findDocumentList(idList);

        List<Document> documentList =  BeanMapper.mapList(documentPoList,Document.class);
        return documentList;
    }

    @Override
    public Document findDocument(@NotNull String id) {
        Document document = findOne(id);

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
}