package com.doublekit.wiki.repository.dao;

import com.doublekit.common.Pagination;
import com.doublekit.wiki.repository.entity.LikePo;
import com.doublekit.wiki.repository.model.LikeQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * LikeDao
 */
@Repository
public class LikeDao{

    private static Logger logger = LoggerFactory.getLogger(LikeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param likePo
     * @return
     */
    public String createLike(LikePo likePo) {
        return jpaTemplate.save(likePo,String.class);
    }

    /**
     * 更新
     * @param likePo
     */
    public void updateLike(LikePo likePo){
        jpaTemplate.update(likePo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteLike(String id){
        jpaTemplate.delete(LikePo.class,id);
    }

    public void deleteLike(DeleteCondition deleteCondition){
        jpaTemplate.delete(LikePo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public LikePo findLike(String id){
        return jpaTemplate.findOne(LikePo.class,id);
    }

    /**
    * findAllLike
    * @return
    */
    public List<LikePo> findAllLike() {
        return jpaTemplate.findAll(LikePo.class);
    }

    public List<LikePo> findLikeList(List<String> idList) {
        return jpaTemplate.findList(LikePo.class,idList);
    }

    public List<LikePo> findLikeList(LikeQuery likeQuery) {
        return jpaTemplate.findList(LikePo.class,likeQuery);
    }

    public Pagination<LikePo> findLikePage(LikeQuery likeQuery) {
        return jpaTemplate.findPage(LikePo.class,likeQuery);
    }
}