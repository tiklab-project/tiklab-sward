package io.tiklab.sward.document.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.document.entity.ShareRelationEntity;
import io.tiklab.sward.document.model.ShareRelationQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShareRelationDao
 */
@Repository
public class ShareRelationDao {

    private static Logger logger = LoggerFactory.getLogger(ShareRelationDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param shareRelationEntity
     * @return
     */
    public String createShareRelation(ShareRelationEntity shareRelationEntity) {
        return jpaTemplate.save(shareRelationEntity,String.class);
    }

    /**
     * 更新
     * @param shareRelationEntity
     */
    public void updateShareRelation(ShareRelationEntity shareRelationEntity){
        jpaTemplate.update(shareRelationEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteShareRelation(String id){
        jpaTemplate.delete(ShareRelationEntity.class,id);
    }

    public void deleteShareRelation(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ShareRelationEntity findShareRelation(String id){
        return jpaTemplate.findOne(ShareRelationEntity.class,id);
    }

    /**
    * findAllShareRelation
    * @return
    */
    public List<ShareRelationEntity> findAllShareRelation() {
        return jpaTemplate.findAll(ShareRelationEntity.class);
    }

    public List<ShareRelationEntity> findShareRelationList(List<String> idList) {
        return jpaTemplate.findList(ShareRelationEntity.class,idList);
    }

    public List<ShareRelationEntity> findShareRelationList(ShareRelationQuery shareRelationQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ShareRelationEntity.class)
                .eq("documentId", shareRelationQuery.getDocumentId())
                .eq("shareId", shareRelationQuery.getShareId())
                .eq("type", shareRelationQuery.getType())
                .orders(shareRelationQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ShareRelationEntity.class);
    }

    public Pagination<ShareRelationEntity> findShareRelationPage(ShareRelationQuery shareRelationQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ShareRelationEntity.class)
                .eq("documentId", shareRelationQuery.getDocumentId())
                .eq("shareId", shareRelationQuery.getShareId())
                .orders(shareRelationQuery.getOrderParams())
                .pagination(shareRelationQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ShareRelationEntity.class);
    }
}