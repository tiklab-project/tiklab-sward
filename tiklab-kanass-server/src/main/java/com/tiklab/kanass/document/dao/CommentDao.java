package com.tiklab.kanass.document.dao;

import com.tiklab.core.page.Pagination;
import com.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import com.tiklab.dal.jpa.criterial.condition.QueryCondition;
import com.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import com.tiklab.kanass.document.entity.CommentEntity;
import com.tiklab.kanass.document.model.CommentQuery;
import com.tiklab.dal.jpa.JpaTemplate;
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
     * @param commentEntity
     * @return
     */
    public String createComment(CommentEntity commentEntity) {
        return jpaTemplate.save(commentEntity,String.class);
    }

    /**
     * 更新
     * @param commentEntity
     */
    public void updateComment(CommentEntity commentEntity){
        jpaTemplate.update(commentEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteComment(String id){
        jpaTemplate.delete(CommentEntity.class,id);
    }

    public void deleteComment(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public CommentEntity findComment(String id){
        return jpaTemplate.findOne(CommentEntity.class,id);
    }

    /**
    * findAllComment
    * @return
    */
    public List<CommentEntity> findAllComment() {
        return jpaTemplate.findAll(CommentEntity.class);
    }

    public List<CommentEntity> findCommentList(List<String> idList) {
        return jpaTemplate.findList(CommentEntity.class,idList);
    }

    public List<CommentEntity> findCommentList(CommentQuery commentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CommentEntity.class)
                .eq("documentId", commentQuery.getDocumentId())
                .orders(commentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, CommentEntity.class);
    }

    public Pagination<CommentEntity> findCommentPage(CommentQuery commentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CommentEntity.class)
                .eq("documentId", commentQuery.getDocumentId())
                .orders(commentQuery.getOrderParams())
                .pagination(commentQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, CommentEntity.class);
    }
}