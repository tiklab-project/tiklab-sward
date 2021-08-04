package com.doublekit.wiki.repository.dao;

import com.doublekit.common.Pagination;
import com.doublekit.wiki.repository.entity.RepositoryPagePo;
import com.doublekit.wiki.repository.model.RepositoryPageQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RepositoryPageDao
 */
@Repository
public class RepositoryPageDao{

    private static Logger logger = LoggerFactory.getLogger(RepositoryPageDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param repositoryPagePo
     * @return
     */
    public String createRepositoryPage(RepositoryPagePo repositoryPagePo) {
        return jpaTemplate.save(repositoryPagePo,String.class);
    }

    /**
     * 更新
     * @param repositoryPagePo
     */
    public void updateRepositoryPage(RepositoryPagePo repositoryPagePo){
        jpaTemplate.update(repositoryPagePo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepositoryPage(String id){
        jpaTemplate.delete(RepositoryPagePo.class,id);
    }

    public void deleteRepositoryPage(DeleteCondition deleteCondition){
        jpaTemplate.delete(RepositoryPagePo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RepositoryPagePo findRepositoryPage(String id){
        return jpaTemplate.findOne(RepositoryPagePo.class,id);
    }

    /**
    * findAllRepositoryPage
    * @return
    */
    public List<RepositoryPagePo> findAllRepositoryPage() {
        return jpaTemplate.findAll(RepositoryPagePo.class);
    }

    public List<RepositoryPagePo> findRepositoryPageList(List<String> idList) {
        return jpaTemplate.findList(RepositoryPagePo.class,idList);
    }

    public List<RepositoryPagePo> findRepositoryPageList(RepositoryPageQuery repositoryPageQuery) {
        return jpaTemplate.findList(RepositoryPagePo.class,repositoryPageQuery);
    }

    public Pagination<RepositoryPagePo> findRepositoryPagePage(RepositoryPageQuery repositoryPageQuery) {
        return jpaTemplate.findPage(RepositoryPagePo.class,repositoryPageQuery);
    }
}