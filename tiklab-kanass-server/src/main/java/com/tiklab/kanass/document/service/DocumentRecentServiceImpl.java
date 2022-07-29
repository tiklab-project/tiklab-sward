package com.tiklab.kanass.document.service;

import com.tiklab.core.page.Pagination;
import com.tiklab.core.page.PaginationBuilder;
import com.tiklab.kanass.document.dao.DocumentRecentDao;
import com.tiklab.kanass.document.entity.DocumentRecentEntity;
import com.tiklab.kanass.document.model.DocumentRecent;
import com.tiklab.kanass.document.model.DocumentRecentQuery;

import com.tiklab.beans.BeanMapper;
import com.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DocumentRecentServiceImpl
*/
@Service
public class DocumentRecentServiceImpl implements DocumentRecentService {

    @Autowired
    DocumentRecentDao documentRecentDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createDocumentRecent(@NotNull @Valid DocumentRecent documentRecent) {
        DocumentRecentEntity documentRecentEntity = BeanMapper.map(documentRecent, DocumentRecentEntity.class);

        return documentRecentDao.createDocumentRecent(documentRecentEntity);
    }

    @Override
    public void updateDocumentRecent(@NotNull @Valid DocumentRecent documentRecent) {
        DocumentRecentEntity documentRecentEntity = BeanMapper.map(documentRecent, DocumentRecentEntity.class);

        documentRecentDao.updateDocumentRecent(documentRecentEntity);
    }

    @Override
    public void deleteDocumentRecent(@NotNull String id) {
        documentRecentDao.deleteDocumentRecent(id);
    }

    @Override
    public DocumentRecent findOne(String id) {
        DocumentRecentEntity documentRecentEntity = documentRecentDao.findDocumentRecent(id);

        DocumentRecent documentRecent = BeanMapper.map(documentRecentEntity, DocumentRecent.class);
        return documentRecent;
    }

    @Override
    public List<DocumentRecent> findList(List<String> idList) {
        List<DocumentRecentEntity> documentRecentEntityList =  documentRecentDao.findDocumentRecentList(idList);

        List<DocumentRecent> documentRecentList =  BeanMapper.mapList(documentRecentEntityList,DocumentRecent.class);
        return documentRecentList;
    }

    @Override
    public DocumentRecent findDocumentRecent(@NotNull String id) {
        DocumentRecent documentRecent = findOne(id);

        joinTemplate.joinQuery(documentRecent);

        return documentRecent;
    }

    @Override
    public List<DocumentRecent> findAllDocumentRecent() {
        List<DocumentRecentEntity> documentRecentEntityList =  documentRecentDao.findAllDocumentRecent();

        List<DocumentRecent> documentRecentList =  BeanMapper.mapList(documentRecentEntityList,DocumentRecent.class);

        joinTemplate.joinQuery(documentRecentList);

        return documentRecentList;
    }

    @Override
    public List<DocumentRecent> findDocumentRecentList(DocumentRecentQuery documentRecentQuery) {
        List<DocumentRecentEntity> documentRecentEntityList = documentRecentDao.findDocumentRecentList(documentRecentQuery);

        List<DocumentRecent> documentRecentList = BeanMapper.mapList(documentRecentEntityList,DocumentRecent.class);

        joinTemplate.joinQuery(documentRecentList);

        return documentRecentList;
    }

    @Override
    public Pagination<DocumentRecent> findDocumentRecentPage(DocumentRecentQuery documentRecentQuery) {
        Pagination<DocumentRecentEntity>  pagination = documentRecentDao.findDocumentRecentPage(documentRecentQuery);

        List<DocumentRecent> documentRecentList = BeanMapper.mapList(pagination.getDataList(),DocumentRecent.class);

        joinTemplate.joinQuery(documentRecentList);

        return PaginationBuilder.build(pagination,documentRecentList);
    }
}