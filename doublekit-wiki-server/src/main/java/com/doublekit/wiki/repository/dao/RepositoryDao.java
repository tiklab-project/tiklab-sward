package com.doublekit.wiki.repository.dao;

import com.doublekit.common.Pagination;
import com.doublekit.wiki.repository.entity.RepositoryPo;
import com.doublekit.wiki.repository.model.RepositoryQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RepositoryDao
 */
@Repository
public class RepositoryDao{

    private static Logger logger = LoggerFactory.getLogger(RepositoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param repositoryPo
     * @return
     */
    public String createRepository(RepositoryPo repositoryPo) {
        return jpaTemplate.save(repositoryPo,String.class);
    }

    /**
     * 更新
     * @param repositoryPo
     */
    public void updateRepository(RepositoryPo repositoryPo){
        jpaTemplate.update(repositoryPo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepository(String id){
        jpaTemplate.delete(RepositoryPo.class,id);
    }

    public void deleteRepository(DeleteCondition deleteCondition){
        jpaTemplate.delete(RepositoryPo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RepositoryPo findRepository(String id){
        return jpaTemplate.findOne(RepositoryPo.class,id);
    }

    /**
    * findAllRepository
    * @return
    */
    public List<RepositoryPo> findAllRepository() {
        return jpaTemplate.findAll(RepositoryPo.class);
    }

    public List<RepositoryPo> findRepositoryList(List<String> idList) {
        return jpaTemplate.findList(RepositoryPo.class,idList);
    }

    public List<RepositoryPo> findRepositoryList(RepositoryQuery repositoryQuery) {
        return jpaTemplate.findList(RepositoryPo.class,repositoryQuery);
    }

    public Pagination<RepositoryPo> findRepositoryPage(RepositoryQuery repositoryQuery) {
        return jpaTemplate.findPage(RepositoryPo.class,repositoryQuery);
    }
}