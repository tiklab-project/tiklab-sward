package com.doublekit.wiki.repository.dao;

import com.doublekit.common.Pagination;
import com.doublekit.wiki.repository.entity.RepositoryDetailsPo;
import com.doublekit.wiki.repository.model.RepositoryDetailsQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RepositoryDetailsDao
 */
@Repository
public class RepositoryDetailsDao{

    private static Logger logger = LoggerFactory.getLogger(RepositoryDetailsDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param repositoryDetailsPo
     * @return
     */
    public String createRepositoryDetails(RepositoryDetailsPo repositoryDetailsPo) {
        return jpaTemplate.save(repositoryDetailsPo,String.class);
    }

    /**
     * 更新
     * @param repositoryDetailsPo
     */
    public void updateRepositoryDetails(RepositoryDetailsPo repositoryDetailsPo){
        jpaTemplate.update(repositoryDetailsPo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepositoryDetails(String id){
        jpaTemplate.delete(RepositoryDetailsPo.class,id);
    }

    public void deleteRepositoryDetails(DeleteCondition deleteCondition){
        jpaTemplate.delete(RepositoryDetailsPo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RepositoryDetailsPo findRepositoryDetails(String id){
        return jpaTemplate.findOne(RepositoryDetailsPo.class,id);
    }

    /**
    * findAllRepositoryDetails
    * @return
    */
    public List<RepositoryDetailsPo> findAllRepositoryDetails() {
        return jpaTemplate.findAll(RepositoryDetailsPo.class);
    }

    public List<RepositoryDetailsPo> findRepositoryDetailsList(List<String> idList) {
        return jpaTemplate.findList(RepositoryDetailsPo.class,idList);
    }

    public List<RepositoryDetailsPo> findRepositoryDetailsList(RepositoryDetailsQuery repositoryDetailsQuery) {
        return jpaTemplate.findList(RepositoryDetailsPo.class,repositoryDetailsQuery);
    }

    public Pagination<RepositoryDetailsPo> findRepositoryDetailsPage(RepositoryDetailsQuery repositoryDetailsQuery) {
        return jpaTemplate.findPage(RepositoryDetailsPo.class,repositoryDetailsQuery);
    }
}