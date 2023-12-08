package io.thoughtware.sward.repository.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.sward.repository.model.WikiRepositoryFocus;
import io.thoughtware.sward.repository.model.WikiRepositoryFocusQuery;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.sward.repository.dao.WikiRepositoryFocusDao;
import io.thoughtware.sward.repository.entity.WikiRepositoryFocusEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryFocusServiceImpl
*/
@Service
public class WikiRepositoryFocusServiceImpl implements WikiRepositoryFocusService {

    @Autowired
    WikiRepositoryFocusDao wikiRepositoryFocusDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRepositoryFocus(@NotNull @Valid WikiRepositoryFocus wikiRepositoryFocus) {
        String userId = LoginContext.getLoginId();
        wikiRepositoryFocus.setMasterId(userId);
        WikiRepositoryFocusEntity wikiRepositoryFocusEntity = BeanMapper.map(wikiRepositoryFocus, WikiRepositoryFocusEntity.class);

        return wikiRepositoryFocusDao.createRepositoryFocus(wikiRepositoryFocusEntity);
    }

    @Override
    public void updateRepositoryFocus(@NotNull @Valid WikiRepositoryFocus wikiRepositoryFocus) {
        WikiRepositoryFocusEntity wikiRepositoryFocusEntity = BeanMapper.map(wikiRepositoryFocus, WikiRepositoryFocusEntity.class);

        wikiRepositoryFocusDao.updateRepositoryFocus(wikiRepositoryFocusEntity);
    }

    @Override
    public void deleteRepositoryFocus(@NotNull String id) {
        wikiRepositoryFocusDao.deleteRepositoryFocus(id);
    }

    @Override
    public void deleteRepositoryFocusByCondition(WikiRepositoryFocusQuery wikiRepositoryFocusQuery) {
        List<WikiRepositoryFocusEntity> repositoryFocusList = wikiRepositoryFocusDao.findRepositoryFocusList(wikiRepositoryFocusQuery);
        if(repositoryFocusList.size() > 0){
            for (WikiRepositoryFocusEntity repositoryFocus : repositoryFocusList) {
                deleteRepositoryFocus(repositoryFocus.getId());
            }
        }
    }

    @Override
    public WikiRepositoryFocus findOne(String id) {
        WikiRepositoryFocusEntity wikiRepositoryFocusEntity = wikiRepositoryFocusDao.findRepositoryFocus(id);

        WikiRepositoryFocus wikiRepositoryFocus = BeanMapper.map(wikiRepositoryFocusEntity, WikiRepositoryFocus.class);
        return wikiRepositoryFocus;
    }

    @Override
    public List<WikiRepositoryFocus> findList(List<String> idList) {
        List<WikiRepositoryFocusEntity> wikiRepositoryFocusEntityList =  wikiRepositoryFocusDao.findRepositoryFocusList(idList);

        List<WikiRepositoryFocus> wikiRepositoryFocusList =  BeanMapper.mapList(wikiRepositoryFocusEntityList, WikiRepositoryFocus.class);
        return wikiRepositoryFocusList;
    }

    @Override
    public WikiRepositoryFocus findRepositoryFocus(@NotNull String id) {
        WikiRepositoryFocus wikiRepositoryFocus = findOne(id);

        joinTemplate.joinQuery(wikiRepositoryFocus);

        return wikiRepositoryFocus;
    }

    @Override
    public List<WikiRepositoryFocus> findAllRepositoryFocus() {
        List<WikiRepositoryFocusEntity> wikiRepositoryFocusEntityList =  wikiRepositoryFocusDao.findAllRepositoryFocus();

        List<WikiRepositoryFocus> wikiRepositoryFocusList =  BeanMapper.mapList(wikiRepositoryFocusEntityList, WikiRepositoryFocus.class);

        joinTemplate.joinQuery(wikiRepositoryFocusList);

        return wikiRepositoryFocusList;
    }

    @Override
    public List<WikiRepositoryFocus> findRepositoryFocusList(WikiRepositoryFocusQuery wikiRepositoryFocusQuery) {
        List<WikiRepositoryFocusEntity> wikiRepositoryFocusEntityList = wikiRepositoryFocusDao.findRepositoryFocusList(wikiRepositoryFocusQuery);

        List<WikiRepositoryFocus> wikiRepositoryFocusList = BeanMapper.mapList(wikiRepositoryFocusEntityList, WikiRepositoryFocus.class);

        joinTemplate.joinQuery(wikiRepositoryFocusList);

        return wikiRepositoryFocusList;
    }

    @Override
    public Pagination<WikiRepositoryFocus> findRepositoryFocusPage(WikiRepositoryFocusQuery wikiRepositoryFocusQuery) {
        Pagination<WikiRepositoryFocusEntity>  pagination = wikiRepositoryFocusDao.findRepositoryFocusPage(wikiRepositoryFocusQuery);

        List<WikiRepositoryFocus> wikiRepositoryFocusList = BeanMapper.mapList(pagination.getDataList(), WikiRepositoryFocus.class);

        joinTemplate.joinQuery(wikiRepositoryFocusList);

        return PaginationBuilder.build(pagination, wikiRepositoryFocusList);
    }
}