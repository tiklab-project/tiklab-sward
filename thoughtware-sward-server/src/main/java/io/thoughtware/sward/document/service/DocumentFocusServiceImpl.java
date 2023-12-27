package io.thoughtware.sward.document.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.sward.document.model.DocumentFocus;
import io.thoughtware.sward.document.model.DocumentFocusQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.sward.document.dao.DocumentFocusDao;
import io.thoughtware.sward.document.entity.DocumentFocusEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DocumentFocusServiceImpl
 */
@Service
public class DocumentFocusServiceImpl implements DocumentFocusService {

    @Autowired
    DocumentFocusDao wikiDocumentFocusDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createDocumentFocus(@NotNull @Valid DocumentFocus wikiDocumentFocus) {
        String userId = LoginContext.getLoginId();
        wikiDocumentFocus.setMasterId(userId);
        DocumentFocusEntity wikiDocumentFocusEntity = BeanMapper.map(wikiDocumentFocus, DocumentFocusEntity.class);

        return wikiDocumentFocusDao.createDocumentFocus(wikiDocumentFocusEntity);
    }

    @Override
    public void updateDocumentFocus(@NotNull @Valid DocumentFocus wikiDocumentFocus) {
        DocumentFocusEntity wikiDocumentFocusEntity = BeanMapper.map(wikiDocumentFocus, DocumentFocusEntity.class);

        wikiDocumentFocusDao.updateDocumentFocus(wikiDocumentFocusEntity);
    }

    @Override
    public void deleteDocumentFocus(@NotNull String id) {
        wikiDocumentFocusDao.deleteDocumentFocus(id);
    }

    @Override
    public void deleteDocumentFocusByCondition(DocumentFocusQuery wikiDocumentFocusQuery) {
        List<DocumentFocusEntity> documentFocusList = wikiDocumentFocusDao.findDocumentFocusList(wikiDocumentFocusQuery);
        if(documentFocusList.size() > 0){
            for (DocumentFocusEntity documentFocus : documentFocusList) {
                deleteDocumentFocus(documentFocus.getId());
            }
        }
    }

    @Override
    public DocumentFocus findOne(String id) {
        DocumentFocusEntity wikiDocumentFocusEntity = wikiDocumentFocusDao.findDocumentFocus(id);

        DocumentFocus wikiDocumentFocus = BeanMapper.map(wikiDocumentFocusEntity, DocumentFocus.class);
        return wikiDocumentFocus;
    }

    @Override
    public List<DocumentFocus> findList(List<String> idList) {
        List<DocumentFocusEntity> wikiDocumentFocusEntityList =  wikiDocumentFocusDao.findDocumentFocusList(idList);

        List<DocumentFocus> wikiDocumentFocusList =  BeanMapper.mapList(wikiDocumentFocusEntityList, DocumentFocus.class);
        return wikiDocumentFocusList;
    }

    @Override
    public DocumentFocus findDocumentFocus(@NotNull String id) {
        DocumentFocus wikiDocumentFocus = findOne(id);

        joinTemplate.joinQuery(wikiDocumentFocus);

        return wikiDocumentFocus;
    }

    @Override
    public List<DocumentFocus> findAllDocumentFocus() {
        List<DocumentFocusEntity> wikiDocumentFocusEntityList =  wikiDocumentFocusDao.findAllDocumentFocus();

        List<DocumentFocus> wikiDocumentFocusList =  BeanMapper.mapList(wikiDocumentFocusEntityList, DocumentFocus.class);

        joinTemplate.joinQuery(wikiDocumentFocusList);

        return wikiDocumentFocusList;
    }

    @Override
    public List<DocumentFocus> findDocumentFocusList(DocumentFocusQuery wikiDocumentFocusQuery) {
        List<DocumentFocusEntity> wikiDocumentFocusEntityList = wikiDocumentFocusDao.findDocumentFocusList(wikiDocumentFocusQuery);

        List<DocumentFocus> wikiDocumentFocusList = BeanMapper.mapList(wikiDocumentFocusEntityList, DocumentFocus.class);

        joinTemplate.joinQuery(wikiDocumentFocusList);

        return wikiDocumentFocusList;
    }

    @Override
    public Pagination<DocumentFocus> findDocumentFocusPage(DocumentFocusQuery wikiDocumentFocusQuery) {
        Pagination<DocumentFocusEntity>  pagination = wikiDocumentFocusDao.findDocumentFocusPage(wikiDocumentFocusQuery);

        List<DocumentFocus> wikiDocumentFocusList = BeanMapper.mapList(pagination.getDataList(), DocumentFocus.class);

        joinTemplate.joinQuery(wikiDocumentFocusList);

        return PaginationBuilder.build(pagination, wikiDocumentFocusList);
    }
}