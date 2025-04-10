package io.tiklab.sward.document.service;

import io.tiklab.sward.document.dao.DocumentAttachDao;
import io.tiklab.sward.document.entity.DocumentAttachEntity;
import io.tiklab.sward.document.model.DocumentAttach;
import io.tiklab.sward.document.model.DocumentAttachQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 文档附件，暂时不用
*/
@Service
@Exporter
public class DocumentAttachServiceImpl implements DocumentAttachService {

    @Autowired
    DocumentAttachDao documentAttachDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createDocumentAttach(@NotNull @Valid DocumentAttach documentAttach) {
        DocumentAttachEntity documentAttachEntity = BeanMapper.map(documentAttach, DocumentAttachEntity.class);

        return documentAttachDao.createDocumentAttach(documentAttachEntity);
    }

    @Override
    public void updateDocumentAttach(@NotNull @Valid DocumentAttach documentAttach) {
        DocumentAttachEntity documentAttachEntity = BeanMapper.map(documentAttach, DocumentAttachEntity.class);

        documentAttachDao.updateDocumentAttach(documentAttachEntity);
    }

    @Override
    public void deleteDocumentAttach(@NotNull String id) {
        documentAttachDao.deleteDocumentAttach(id);
    }

    @Override
    public void deleteDocumentAttachCondition(DocumentAttachQuery documentAttachQuery) {
        documentAttachDao.deleteDocumentAttachCondition(documentAttachQuery);
    }

    @Override
    public DocumentAttach findOne(String id) {
        DocumentAttachEntity documentAttachEntity = documentAttachDao.findDocumentAttach(id);

        DocumentAttach documentAttach = BeanMapper.map(documentAttachEntity, DocumentAttach.class);
        return documentAttach;
    }

    @Override
    public List<DocumentAttach> findList(List<String> idList) {
        List<DocumentAttachEntity> documentAttachEntityList =  documentAttachDao.findDocumentAttachList(idList);

        List<DocumentAttach> documentAttachList =  BeanMapper.mapList(documentAttachEntityList,DocumentAttach.class);
        return documentAttachList;
    }

    @Override
    public DocumentAttach findDocumentAttach(@NotNull String id) {
        DocumentAttach documentAttach = findOne(id);

        joinTemplate.joinQuery(documentAttach);
        return documentAttach;
    }

    @Override
    public List<DocumentAttach> findAllDocumentAttach() {
        List<DocumentAttachEntity> documentAttachEntityList =  documentAttachDao.findAllDocumentAttach();

        List<DocumentAttach> documentAttachList =  BeanMapper.mapList(documentAttachEntityList,DocumentAttach.class);

        joinTemplate.joinQuery(documentAttachList);
        return documentAttachList;
    }

    @Override
    public List<DocumentAttach> findDocumentAttachList(DocumentAttachQuery documentAttachQuery) {
        List<DocumentAttachEntity> documentAttachEntityList = documentAttachDao.findDocumentAttachList(documentAttachQuery);

        List<DocumentAttach> documentAttachList = BeanMapper.mapList(documentAttachEntityList,DocumentAttach.class);

        joinTemplate.joinQuery(documentAttachList);

        return documentAttachList;
    }

    @Override
    public Pagination<DocumentAttach> findDocumentAttachPage(DocumentAttachQuery documentAttachQuery) {

        Pagination<DocumentAttachEntity>  pagination = documentAttachDao.findDocumentAttachPage(documentAttachQuery);

        List<DocumentAttach> documentAttachList = BeanMapper.mapList(pagination.getDataList(),DocumentAttach.class);

        joinTemplate.joinQuery(documentAttachList);

        return PaginationBuilder.build(pagination,documentAttachList);
    }
}