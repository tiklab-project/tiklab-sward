package io.tiklab.sward.document.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.sward.document.model.DocumentFocus;
import io.tiklab.sward.document.model.DocumentFocusQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.sward.document.dao.DocumentFocusDao;
import io.tiklab.sward.document.entity.DocumentFocusEntity;


import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 收藏文档
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
        User user = new User();
        user.setId(userId);
        wikiDocumentFocus.setMaster(user);

        // 收藏时间
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        wikiDocumentFocus.setFocusTime(format);

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
    public void deleteDocumentFocusByCondition(DocumentFocusQuery documentFocusQuery) {
        wikiDocumentFocusDao.deleteDocumentFocusByCondition(documentFocusQuery);
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
        Pagination<DocumentFocusEntity> pagination = wikiDocumentFocusDao.findDocumentFocusPage(wikiDocumentFocusQuery);

        List<DocumentFocus> wikiDocumentFocusList = BeanMapper.mapList(pagination.getDataList(), DocumentFocus.class);

        joinTemplate.joinQuery(wikiDocumentFocusList);

        return PaginationBuilder.build(pagination, wikiDocumentFocusList);
    }
}