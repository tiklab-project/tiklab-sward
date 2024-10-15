package io.tiklab.sward.support.dao;

import io.tiklab.sward.support.entity.RecentEntity;
import io.tiklab.sward.support.model.RecentQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * RecentDao
 */
@Repository
public class RecentDao {

    private static Logger logger = LoggerFactory.getLogger(RecentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param recentEntity
     * @return
     */
    public String createRecent(RecentEntity recentEntity) {
        RecentQuery recentQuery = new RecentQuery();
        recentQuery.setModelId(recentEntity.getModelId());
        List<RecentEntity> documentRecentList = findRecentList(recentQuery);
        if(documentRecentList.size() > 0){
            deleteRecent(documentRecentList.get(0).getId());
        }
        recentEntity.setRecentTime(new Timestamp(System.currentTimeMillis()));
        return jpaTemplate.save(recentEntity,String.class);
    }

    /**
     * 更新
     * @param recentEntity
     */
    public void updateRecent(RecentEntity recentEntity){
        jpaTemplate.update(recentEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRecent(String id){
        jpaTemplate.delete(RecentEntity.class,id);
    }
    public void deleteRecnetByCondition(DeleteCondition deleteCondition){

        jpaTemplate.delete(deleteCondition);
    }
    public void deleteRecentByIds(String ids){
        String sql = "DELETE FROM wiki_recent where id in " + ids ;
        int update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除多于的数据成功");
        }else {
            logger.info("删除多于的数据失败");
        }
    }
    public void deleteRecent(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RecentEntity findRecent(String id){
        return jpaTemplate.findOne(RecentEntity.class,id);
    }

    /**
    * findAllRecent
    * @return
    */
    public List<RecentEntity> findAllRecent() {
        return jpaTemplate.findAll(RecentEntity.class);
    }

    public List<RecentEntity> findRecentList(List<String> idList) {
        return jpaTemplate.findList(RecentEntity.class,idList);
    }

    public List<RecentEntity> findRecentList(RecentQuery recentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RecentEntity.class)
                .eq("modelId", recentQuery.getModelId())
                .eq("masterId", recentQuery.getMasterId())
                .eq("repositoryId", recentQuery.getRepositoryId())
                .eq("model", recentQuery.getModel())
                .in("model", recentQuery.getModelIds())
                .orders(recentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RecentEntity.class);
    }

    public Pagination<RecentEntity> findRecentPage(RecentQuery recentQuery) {
        return jpaTemplate.findPage(recentQuery, RecentEntity.class);
    }
}