package net.tiklab.kanass.repository.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.kanass.repository.dao.RepositoryFocusDao;
import net.tiklab.kanass.repository.entity.RepositoryEntity;
import net.tiklab.kanass.repository.entity.RepositoryFocusEntity;
import net.tiklab.kanass.repository.model.RepositoryFocus;
import net.tiklab.kanass.repository.model.RepositoryFocusQuery;

import net.tiklab.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* RepositoryFocusServiceImpl
*/
@Service
public class RepositoryFocusServiceImpl implements RepositoryFocusService {

    @Autowired
    RepositoryFocusDao repositoryFocusDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRepositoryFocus(@NotNull @Valid RepositoryFocus repositoryFocus) {
        String userId = LoginContext.getLoginId();
        repositoryFocus.setMasterId(userId);
        RepositoryFocusEntity repositoryFocusEntity = BeanMapper.map(repositoryFocus, RepositoryFocusEntity.class);

        return repositoryFocusDao.createRepositoryFocus(repositoryFocusEntity);
    }

    @Override
    public void updateRepositoryFocus(@NotNull @Valid RepositoryFocus repositoryFocus) {
        RepositoryFocusEntity repositoryFocusEntity = BeanMapper.map(repositoryFocus, RepositoryFocusEntity.class);

        repositoryFocusDao.updateRepositoryFocus(repositoryFocusEntity);
    }

    @Override
    public void deleteRepositoryFocus(@NotNull String id) {
        repositoryFocusDao.deleteRepositoryFocus(id);
    }

    @Override
    public void deleteRepositoryFocusByCondition(RepositoryFocusQuery repositoryFocusQuery) {
        List<RepositoryFocusEntity> repositoryFocusList = repositoryFocusDao.findRepositoryFocusList(repositoryFocusQuery);
        if(repositoryFocusList.size() > 0){
            for (RepositoryFocusEntity repositoryFocus : repositoryFocusList) {
                deleteRepositoryFocus(repositoryFocus.getId());
            }
        }
    }

    @Override
    public RepositoryFocus findOne(String id) {
        RepositoryFocusEntity repositoryFocusEntity = repositoryFocusDao.findRepositoryFocus(id);

        RepositoryFocus repositoryFocus = BeanMapper.map(repositoryFocusEntity, RepositoryFocus.class);
        return repositoryFocus;
    }

    @Override
    public List<RepositoryFocus> findList(List<String> idList) {
        List<RepositoryFocusEntity> repositoryFocusEntityList =  repositoryFocusDao.findRepositoryFocusList(idList);

        List<RepositoryFocus> repositoryFocusList =  BeanMapper.mapList(repositoryFocusEntityList,RepositoryFocus.class);
        return repositoryFocusList;
    }

    @Override
    public RepositoryFocus findRepositoryFocus(@NotNull String id) {
        RepositoryFocus repositoryFocus = findOne(id);

        joinTemplate.joinQuery(repositoryFocus);

        return repositoryFocus;
    }

    @Override
    public List<RepositoryFocus> findAllRepositoryFocus() {
        List<RepositoryFocusEntity> repositoryFocusEntityList =  repositoryFocusDao.findAllRepositoryFocus();

        List<RepositoryFocus> repositoryFocusList =  BeanMapper.mapList(repositoryFocusEntityList,RepositoryFocus.class);

        joinTemplate.joinQuery(repositoryFocusList);

        return repositoryFocusList;
    }

    @Override
    public List<RepositoryFocus> findRepositoryFocusList(RepositoryFocusQuery repositoryFocusQuery) {
        List<RepositoryFocusEntity> repositoryFocusEntityList = repositoryFocusDao.findRepositoryFocusList(repositoryFocusQuery);

        List<RepositoryFocus> repositoryFocusList = BeanMapper.mapList(repositoryFocusEntityList,RepositoryFocus.class);

        joinTemplate.joinQuery(repositoryFocusList);

        return repositoryFocusList;
    }

    @Override
    public Pagination<RepositoryFocus> findRepositoryFocusPage(RepositoryFocusQuery repositoryFocusQuery) {
        Pagination<RepositoryFocusEntity>  pagination = repositoryFocusDao.findRepositoryFocusPage(repositoryFocusQuery);

        List<RepositoryFocus> repositoryFocusList = BeanMapper.mapList(pagination.getDataList(),RepositoryFocus.class);

        joinTemplate.joinQuery(repositoryFocusList);

        return PaginationBuilder.build(pagination,repositoryFocusList);
    }
}