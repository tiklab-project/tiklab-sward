package com.doublekit.wiki.repository.dao;

import com.doublekit.common.Pagination;
import com.doublekit.dal.jpa.criterial.model.DeleteCondition;
import com.doublekit.wiki.repository.entity.RepositoryEntity;
import com.doublekit.wiki.repository.model.RepositoryQuery;
import com.doublekit.dal.jpa.JpaTemplate;
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
     * @param repositoryEntity
     * @return
     */
    public String createRepository(RepositoryEntity repositoryEntity) {
        return jpaTemplate.save(repositoryEntity,String.class);
    }

    /**
     * 更新
     * @param repositoryEntity
     */
    public void updateRepository(RepositoryEntity repositoryEntity){
        jpaTemplate.update(repositoryEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepository(String id){
        jpaTemplate.delete(RepositoryEntity.class,id);
    }

    public void deleteRepository(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RepositoryEntity findRepository(String id){
        return jpaTemplate.findOne(RepositoryEntity.class,id);
    }

    /**
    * findAllRepository
    * @return
    */
    public List<RepositoryEntity> findAllRepository() {
        return jpaTemplate.findAll(RepositoryEntity.class);
    }

    public List<RepositoryEntity> findRepositoryList(List<String> idList) {
        return jpaTemplate.findList(RepositoryEntity.class,idList);
    }

    public List<RepositoryEntity> findRepositoryList(RepositoryQuery repositoryQuery) {
        return jpaTemplate.findList(repositoryQuery, RepositoryEntity.class);
    }

    public Pagination<RepositoryEntity> findRepositoryPage(RepositoryQuery repositoryQuery) {
        return jpaTemplate.findPage(repositoryQuery, RepositoryEntity.class);
    }
}