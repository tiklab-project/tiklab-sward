package io.tiklab.sward.document.dao;

import io.tiklab.sward.document.entity.ShareEntity;
import io.tiklab.sward.document.model.ShareQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
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
     * @param shareEntity
     * @return
     */
    public String createShare(ShareEntity shareEntity) {
        return jpaTemplate.save(shareEntity,String.class);
    }

    /**
     * 更新
     * @param shareEntity
     */
    public void updateShare(ShareEntity shareEntity){
        jpaTemplate.update(shareEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteShare(String id){
        jpaTemplate.delete(ShareEntity.class,id);
    }

    public void deleteShare(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ShareEntity findShare(String id){
        return jpaTemplate.findOne(ShareEntity.class,id);
    }

    /**
    * findAllShare
    * @return
    */
    public List<ShareEntity> findAllShare() {
        return jpaTemplate.findAll(ShareEntity.class);
    }

    public List<ShareEntity> findShareList(List<String> idList) {
        return jpaTemplate.findList(ShareEntity.class,idList);
    }

    public List<ShareEntity> findShareList(ShareQuery shareQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ShareEntity.class)
                .eq("type", shareQuery.getType())
                .eq("authCode", shareQuery.getAuthCode())
                .orders(shareQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ShareEntity.class);
    }

    public Pagination<ShareEntity> findSharePage(ShareQuery shareQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ShareEntity.class)
                .eq("type", shareQuery.getType())
                .eq("authCode", shareQuery.getAuthCode())
                .isNull("authCode")
                .orders(shareQuery.getOrderParams())
                .pagination(shareQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ShareEntity.class);
    }
}