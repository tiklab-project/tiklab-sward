package io.thoughtware.sward.document.dao;

import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.sward.document.entity.CommentEntity;
import io.thoughtware.sward.document.entity.DocumentFocusEntity;
import io.thoughtware.sward.document.model.CommentQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
        commentEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return jpaTemplate.save(commentEntity,String.class);
    }

    /**
     * 更新
     * @param commentEntity
     */
    public void updateComment(CommentEntity commentEntity){
        commentEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
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


    public void deleteCommentCondition(CommentQuery commentQuery){
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(CommentEntity.class)
                .eq("documentId", commentQuery.getDocumentId())
                .get();
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
                .eq("firstOneCommentId", commentQuery.getFirstOneCommentId())
                .eq("parentCommentId", commentQuery.getParentCommentId())
                .orders(commentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, CommentEntity.class);
    }

    public Pagination<CommentEntity> findCommentPage(CommentQuery commentQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(CommentEntity.class)
                .eq("documentId", commentQuery.getDocumentId())
                .eq("firstOneCommentId", commentQuery.getFirstOneCommentId())
                .eq("parentCommentId", commentQuery.getParentCommentId())
                .orders(commentQuery.getOrderParams())
                .pagination(commentQuery.getPageParam());
//        if(commentQuery.getFirstCommentNull()){
//            queryBuilders.isNull("firstOneCommentId");
//        }
        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findPage(queryCondition, CommentEntity.class);
    }

    public List<CommentEntity> findCommentChildren(String commentIds) {
        String sql = "select * from wiki_comment t where t.parent_comment_id in "+ commentIds;
//        List<CommentEntity> commentEntitiyList = this.jpaTemplate.getJdbcTemplate().queryForList(sql, CommentEntity.class);

        List<CommentEntity> commentEntitiyList = this.jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(CommentEntity.class));

        return commentEntitiyList;
    }
}