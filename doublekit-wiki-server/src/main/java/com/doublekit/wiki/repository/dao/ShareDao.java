package com.doublekit.wiki.repository.dao;

import com.doublekit.common.Pagination;
import com.doublekit.wiki.repository.entity.SharePo;
import com.doublekit.wiki.repository.model.ShareQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShareDao
 */
@Repository
public class ShareDao{

    private static Logger logger = LoggerFactory.getLogger(ShareDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param sharePo
     * @return
     */
    public String createShare(SharePo sharePo) {
        return jpaTemplate.save(sharePo,String.class);
    }

    /**
     * 更新
     * @param sharePo
     */
    public void updateShare(SharePo sharePo){
        jpaTemplate.update(sharePo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteShare(String id){
        jpaTemplate.delete(SharePo.class,id);
    }

    public void deleteShare(DeleteCondition deleteCondition){
        jpaTemplate.delete(SharePo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public SharePo findShare(String id){
        return jpaTemplate.findOne(SharePo.class,id);
    }

    /**
    * findAllShare
    * @return
    */
    public List<SharePo> findAllShare() {
        return jpaTemplate.findAll(SharePo.class);
    }

    public List<SharePo> findShareList(List<String> idList) {
        return jpaTemplate.findList(SharePo.class,idList);
    }

    public List<SharePo> findShareList(ShareQuery shareQuery) {
        return jpaTemplate.findList(SharePo.class,shareQuery);
    }

    public Pagination<SharePo> findSharePage(ShareQuery shareQuery) {
        return jpaTemplate.findPage(SharePo.class,shareQuery);
    }
}