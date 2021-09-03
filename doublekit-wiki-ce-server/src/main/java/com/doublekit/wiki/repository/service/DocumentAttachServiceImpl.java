package com.doublekit.wiki.repository.service;

import com.doublekit.wiki.repository.dao.DocumentAttachDao;
import com.doublekit.wiki.repository.entity.DocumentAttachPo;
import com.doublekit.wiki.repository.model.DocumentAttach;
import com.doublekit.wiki.repository.model.DocumentAttachQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* DocumentAttachServiceImpl
*/
@Service
public class DocumentAttachServiceImpl implements DocumentAttachService {

    @Autowired
    DocumentAttachDao documentAttachDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createDocumentAttach(@NotNull @Valid DocumentAttach documentAttach) {
        DocumentAttachPo documentAttachPo = BeanMapper.map(documentAttach, DocumentAttachPo.class);

        return documentAttachDao.createDocumentAttach(documentAttachPo);
    }

    @Override
    public void updateDocumentAttach(@NotNull @Valid DocumentAttach documentAttach) {
        DocumentAttachPo documentAttachPo = BeanMapper.map(documentAttach, DocumentAttachPo.class);

        documentAttachDao.updateDocumentAttach(documentAttachPo);
    }

    @Override
    public void deleteDocumentAttach(@NotNull String id) {
        documentAttachDao.deleteDocumentAttach(id);
    }

    @Override
    public DocumentAttach findOne(String id) {
        DocumentAttachPo documentAttachPo = documentAttachDao.findDocumentAttach(id);

        DocumentAttach documentAttach = BeanMapper.map(documentAttachPo, DocumentAttach.class);
        return documentAttach;
    }

    @Override
    public List<DocumentAttach> findList(List<String> idList) {
        List<DocumentAttachPo> documentAttachPoList =  documentAttachDao.findDocumentAttachList(idList);

        List<DocumentAttach> documentAttachList =  BeanMapper.mapList(documentAttachPoList,DocumentAttach.class);
        return documentAttachList;
    }

    @Override
    public DocumentAttach findDocumentAttach(@NotNull String id) {
        DocumentAttach documentAttach = findOne(id);

        joinQuery.queryOne(documentAttach);
        return documentAttach;
    }

    @Override
    public List<DocumentAttach> findAllDocumentAttach() {
        List<DocumentAttachPo> documentAttachPoList =  documentAttachDao.findAllDocumentAttach();

        List<DocumentAttach> documentAttachList =  BeanMapper.mapList(documentAttachPoList,DocumentAttach.class);

        joinQuery.queryList(documentAttachList);
        return documentAttachList;
    }

    @Override
    public List<DocumentAttach> findDocumentAttachList(DocumentAttachQuery documentAttachQuery) {
        List<DocumentAttachPo> documentAttachPoList = documentAttachDao.findDocumentAttachList(documentAttachQuery);

        List<DocumentAttach> documentAttachList = BeanMapper.mapList(documentAttachPoList,DocumentAttach.class);

        joinQuery.queryList(documentAttachList);

        return documentAttachList;
    }

    @Override
    public Pagination<DocumentAttach> findDocumentAttachPage(DocumentAttachQuery documentAttachQuery) {
        Pagination<DocumentAttach> pg = new Pagination<>();

        Pagination<DocumentAttachPo>  pagination = documentAttachDao.findDocumentAttachPage(documentAttachQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<DocumentAttach> documentAttachList = BeanMapper.mapList(pagination.getDataList(),DocumentAttach.class);

        joinQuery.queryList(documentAttachList);

        pg.setDataList(documentAttachList);
        return pg;
    }
}