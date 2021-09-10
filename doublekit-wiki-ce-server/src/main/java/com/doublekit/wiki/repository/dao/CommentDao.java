package com.doublekit.wiki.repository.dao;

import com.doublekit.common.Pagination;
import com.doublekit.wiki.repository.entity.CommentPo;
import com.doublekit.wiki.repository.model.CommentQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CommentDao
 */
@Repository
public class CommentDao{

    private static Logger logger = LoggerFactory.getLogger(CommentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param commentPo
     * @return
     */
    public String createComment(CommentPo commentPo) {
        return jpaTemplate.save(commentPo,String.class);
    }

    /**
     * 更新
     * @param commentPo
     */
    public void updateComment(CommentPo commentPo){
        jpaTemplate.update(commentPo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteComment(String id){
        jpaTemplate.delete(CommentPo.class,id);
    }

    public void deleteComment(DeleteCondition deleteCondition){
        jpaTemplate.delete(CommentPo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public CommentPo findComment(String id){
        return jpaTemplate.findOne(CommentPo.class,id);
    }

    /**
    * findAllComment
    * @return
    */
    public List<CommentPo> findAllComment() {
        return jpaTemplate.findAll(CommentPo.class);
    }

    public List<CommentPo> findCommentList(List<String> idList) {
        return jpaTemplate.findList(CommentPo.class,idList);
    }

    public List<CommentPo> findCommentList(CommentQuery commentQuery) {
        return jpaTemplate.findList(CommentPo.class,commentQuery);
    }

    public Pagination<CommentPo> findCommentPage(CommentQuery commentQuery) {
        return jpaTemplate.findPage(CommentPo.class,commentQuery);
    }
}