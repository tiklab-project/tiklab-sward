package com.doublekit.wiki.repository.service;

import com.doublekit.wiki.repository.dao.RepositoryPageDao;
import com.doublekit.wiki.repository.entity.RepositoryPagePo;
import com.doublekit.wiki.repository.model.RepositoryPage;
import com.doublekit.wiki.repository.model.RepositoryPageQuery;

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
* RepositoryPageServiceImpl
*/
@Service
public class RepositoryPageServiceImpl implements RepositoryPageService {

    @Autowired
    RepositoryPageDao repositoryPageDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createRepositoryPage(@NotNull @Valid RepositoryPage repositoryPage) {
        RepositoryPagePo repositoryPagePo = BeanMapper.map(repositoryPage, RepositoryPagePo.class);

        return repositoryPageDao.createRepositoryPage(repositoryPagePo);
    }

    @Override
    public void updateRepositoryPage(@NotNull @Valid RepositoryPage repositoryPage) {
        RepositoryPagePo repositoryPagePo = BeanMapper.map(repositoryPage, RepositoryPagePo.class);

        repositoryPageDao.updateRepositoryPage(repositoryPagePo);
    }

    @Override
    public void deleteRepositoryPage(@NotNull String id) {
        repositoryPageDao.deleteRepositoryPage(id);
    }

    @Override
    public RepositoryPage findOne(String id) {
        RepositoryPagePo repositoryPagePo = repositoryPageDao.findRepositoryPage(id);

        RepositoryPage repositoryPage = BeanMapper.map(repositoryPagePo, RepositoryPage.class);
        return repositoryPage;
    }

    @Override
    public List<RepositoryPage> findList(List<String> idList) {
        List<RepositoryPagePo> repositoryPagePoList =  repositoryPageDao.findRepositoryPageList(idList);

        List<RepositoryPage> repositoryPageList =  BeanMapper.mapList(repositoryPagePoList,RepositoryPage.class);
        return repositoryPageList;
    }

    @Override
    public RepositoryPage findRepositoryPage(@NotNull String id) {
        RepositoryPage repositoryPage = findOne(id);

        joinQuery.queryOne(repositoryPage);
        return repositoryPage;
    }

    @Override
    public List<RepositoryPage> findAllRepositoryPage() {
        List<RepositoryPagePo> repositoryPagePoList =  repositoryPageDao.findAllRepositoryPage();

        List<RepositoryPage> repositoryPageList =  BeanMapper.mapList(repositoryPagePoList,RepositoryPage.class);

        joinQuery.queryList(repositoryPageList);
        return repositoryPageList;
    }

    @Override
    public List<RepositoryPage> findRepositoryPageList(RepositoryPageQuery repositoryPageQuery) {
        List<RepositoryPagePo> repositoryPagePoList = repositoryPageDao.findRepositoryPageList(repositoryPageQuery);

        List<RepositoryPage> repositoryPageList = BeanMapper.mapList(repositoryPagePoList,RepositoryPage.class);

        joinQuery.queryList(repositoryPageList);

        return repositoryPageList;
    }

    @Override
    public Pagination<RepositoryPage> findRepositoryPagePage(RepositoryPageQuery repositoryPageQuery) {
        Pagination<RepositoryPage> pg = new Pagination<>();

        Pagination<RepositoryPagePo>  pagination = repositoryPageDao.findRepositoryPagePage(repositoryPageQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RepositoryPage> repositoryPageList = BeanMapper.mapList(pagination.getDataList(),RepositoryPage.class);

        joinQuery.queryList(repositoryPageList);

        pg.setDataList(repositoryPageList);
        return pg;
    }
}