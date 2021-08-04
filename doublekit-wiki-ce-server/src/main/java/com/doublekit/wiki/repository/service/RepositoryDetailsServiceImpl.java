package com.doublekit.wiki.repository.service;

import com.doublekit.wiki.repository.dao.RepositoryDetailsDao;
import com.doublekit.wiki.repository.entity.RepositoryDetailsPo;
import com.doublekit.wiki.repository.model.RepositoryDetails;
import com.doublekit.wiki.repository.model.RepositoryDetailsQuery;

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
* RepositoryDetailsServiceImpl
*/
@Service
public class RepositoryDetailsServiceImpl implements RepositoryDetailsService {

    @Autowired
    RepositoryDetailsDao repositoryDetailsDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createRepositoryDetails(@NotNull @Valid RepositoryDetails repositoryDetails) {
        RepositoryDetailsPo repositoryDetailsPo = BeanMapper.map(repositoryDetails, RepositoryDetailsPo.class);

        return repositoryDetailsDao.createRepositoryDetails(repositoryDetailsPo);
    }

    @Override
    public void updateRepositoryDetails(@NotNull @Valid RepositoryDetails repositoryDetails) {
        RepositoryDetailsPo repositoryDetailsPo = BeanMapper.map(repositoryDetails, RepositoryDetailsPo.class);

        repositoryDetailsDao.updateRepositoryDetails(repositoryDetailsPo);
    }

    @Override
    public void deleteRepositoryDetails(@NotNull String id) {
        repositoryDetailsDao.deleteRepositoryDetails(id);
    }

    @Override
    public RepositoryDetails findOne(String id) {
        RepositoryDetailsPo repositoryDetailsPo = repositoryDetailsDao.findRepositoryDetails(id);

        RepositoryDetails repositoryDetails = BeanMapper.map(repositoryDetailsPo, RepositoryDetails.class);
        return repositoryDetails;
    }

    @Override
    public List<RepositoryDetails> findList(List<String> idList) {
        List<RepositoryDetailsPo> repositoryDetailsPoList =  repositoryDetailsDao.findRepositoryDetailsList(idList);

        List<RepositoryDetails> repositoryDetailsList =  BeanMapper.mapList(repositoryDetailsPoList,RepositoryDetails.class);
        return repositoryDetailsList;
    }

    @Override
    public RepositoryDetails findRepositoryDetails(@NotNull String id) {
        RepositoryDetails repositoryDetails = findOne(id);

        joinQuery.queryOne(repositoryDetails);
        return repositoryDetails;
    }

    @Override
    public List<RepositoryDetails> findAllRepositoryDetails() {
        List<RepositoryDetailsPo> repositoryDetailsPoList =  repositoryDetailsDao.findAllRepositoryDetails();

        List<RepositoryDetails> repositoryDetailsList =  BeanMapper.mapList(repositoryDetailsPoList,RepositoryDetails.class);

        joinQuery.queryList(repositoryDetailsList);
        return repositoryDetailsList;
    }

    @Override
    public List<RepositoryDetails> findRepositoryDetailsList(RepositoryDetailsQuery repositoryDetailsQuery) {
        List<RepositoryDetailsPo> repositoryDetailsPoList = repositoryDetailsDao.findRepositoryDetailsList(repositoryDetailsQuery);

        List<RepositoryDetails> repositoryDetailsList = BeanMapper.mapList(repositoryDetailsPoList,RepositoryDetails.class);

        joinQuery.queryList(repositoryDetailsList);

        return repositoryDetailsList;
    }

    @Override
    public Pagination<RepositoryDetails> findRepositoryDetailsPage(RepositoryDetailsQuery repositoryDetailsQuery) {
        Pagination<RepositoryDetails> pg = new Pagination<>();

        Pagination<RepositoryDetailsPo>  pagination = repositoryDetailsDao.findRepositoryDetailsPage(repositoryDetailsQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RepositoryDetails> repositoryDetailsList = BeanMapper.mapList(pagination.getDataList(),RepositoryDetails.class);

        joinQuery.queryList(repositoryDetailsList);

        pg.setDataList(repositoryDetailsList);
        return pg;
    }
}