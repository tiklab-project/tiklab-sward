package io.thoughtware.sward.document.service;

import io.thoughtware.sward.document.dao.DocumentTemplateDao;
import io.thoughtware.sward.document.entity.DocumentTemplateEntity;
import io.thoughtware.sward.document.model.DocumentTemplate;
import io.thoughtware.sward.document.model.DocumentTemplateQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* DocumentTemplateServiceImpl
*/
@Service
@Exporter
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

        joinTemplate.joinQuery(documentTemplate);
        return documentTemplate;
    }

    @Override
    public List<DocumentTemplate> findAllDocumentTemplate() {
        List<DocumentTemplateEntity> documentTemplateEntityList =  documentTemplateDao.findAllDocumentTemplate();

        List<DocumentTemplate> documentTemplateList =  BeanMapper.mapList(documentTemplateEntityList,DocumentTemplate.class);

        joinTemplate.joinQuery(documentTemplateList);
        return documentTemplateList;
    }

    @Override
    public List<DocumentTemplate> findDocumentTemplateList(DocumentTemplateQuery documentTemplateQuery) {
        List<DocumentTemplateEntity> documentTemplateEntityList = documentTemplateDao.findDocumentTemplateList(documentTemplateQuery);

        List<DocumentTemplate> documentTemplateList = BeanMapper.mapList(documentTemplateEntityList,DocumentTemplate.class);

        joinTemplate.joinQuery(documentTemplateList);

        return documentTemplateList;
    }

    @Override
    public Pagination<DocumentTemplate> findDocumentTemplatePage(DocumentTemplateQuery documentTemplateQuery) {

        Pagination<DocumentTemplateEntity>  pagination = documentTemplateDao.findDocumentTemplatePage(documentTemplateQuery);

        List<DocumentTemplate> documentTemplateList = BeanMapper.mapList(pagination.getDataList(),DocumentTemplate.class);

        joinTemplate.joinQuery(documentTemplateList);

        return PaginationBuilder.build(pagination,documentTemplateList);
    }
}