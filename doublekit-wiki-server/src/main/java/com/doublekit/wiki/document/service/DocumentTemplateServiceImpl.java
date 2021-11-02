package com.doublekit.wiki.document.service;

import com.doublekit.beans.BeanMapper;
import com.doublekit.common.Pagination;
import com.doublekit.join.JoinTemplate;
import com.doublekit.wiki.document.dao.DocumentTemplateDao;
import com.doublekit.wiki.document.entity.DocumentTemplateEntity;
import com.doublekit.wiki.document.model.DocumentTemplate;
import com.doublekit.wiki.document.model.DocumentTemplateQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DocumentTemplateServiceImpl
*/
@Service
public class DocumentTemplateServiceImpl implements DocumentTemplateService {

    @Autowired
    DocumentTemplateDao documentTemplateDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createDocumentTemplate(@NotNull @Valid DocumentTemplate documentTemplate) {
        DocumentTemplateEntity documentTemplateEntity = BeanMapper.map(documentTemplate, DocumentTemplateEntity.class);

        return documentTemplateDao.createDocumentTemplate(documentTemplateEntity);
    }

    @Override
    public void updateDocumentTemplate(@NotNull @Valid DocumentTemplate documentTemplate) {
        DocumentTemplateEntity documentTemplateEntity = BeanMapper.map(documentTemplate, DocumentTemplateEntity.class);

        documentTemplateDao.updateDocumentTemplate(documentTemplateEntity);
    }

    @Override
    public void deleteDocumentTemplate(@NotNull String id) {
        documentTemplateDao.deleteDocumentTemplate(id);
    }

    @Override
    public DocumentTemplate findOne(String id) {
        DocumentTemplateEntity documentTemplateEntity = documentTemplateDao.findDocumentTemplate(id);

        DocumentTemplate documentTemplate = BeanMapper.map(documentTemplateEntity, DocumentTemplate.class);
        return documentTemplate;
    }

    @Override
    public List<DocumentTemplate> findList(List<String> idList) {
        List<DocumentTemplateEntity> documentTemplateEntityList =  documentTemplateDao.findDocumentTemplateList(idList);

        List<DocumentTemplate> documentTemplateList =  BeanMapper.mapList(documentTemplateEntityList,DocumentTemplate.class);
        return documentTemplateList;
    }

    @Override
    public DocumentTemplate findDocumentTemplate(@NotNull String id) {
        DocumentTemplate documentTemplate = findOne(id);

        joinTemplate.queryOne(documentTemplate);
        return documentTemplate;
    }

    @Override
    public List<DocumentTemplate> findAllDocumentTemplate() {
        List<DocumentTemplateEntity> documentTemplateEntityList =  documentTemplateDao.findAllDocumentTemplate();

        List<DocumentTemplate> documentTemplateList =  BeanMapper.mapList(documentTemplateEntityList,DocumentTemplate.class);

        joinTemplate.queryList(documentTemplateList);
        return documentTemplateList;
    }

    @Override
    public List<DocumentTemplate> findDocumentTemplateList(DocumentTemplateQuery documentTemplateQuery) {
        List<DocumentTemplateEntity> documentTemplateEntityList = documentTemplateDao.findDocumentTemplateList(documentTemplateQuery);

        List<DocumentTemplate> documentTemplateList = BeanMapper.mapList(documentTemplateEntityList,DocumentTemplate.class);

        joinTemplate.queryList(documentTemplateList);

        return documentTemplateList;
    }

    @Override
    public Pagination<DocumentTemplate> findDocumentTemplatePage(DocumentTemplateQuery documentTemplateQuery) {
        Pagination<DocumentTemplate> pg = new Pagination<>();

        Pagination<DocumentTemplateEntity>  pagination = documentTemplateDao.findDocumentTemplatePage(documentTemplateQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<DocumentTemplate> documentTemplateList = BeanMapper.mapList(pagination.getDataList(),DocumentTemplate.class);

        joinTemplate.queryList(documentTemplateList);

        pg.setDataList(documentTemplateList);
        return pg;
    }
}