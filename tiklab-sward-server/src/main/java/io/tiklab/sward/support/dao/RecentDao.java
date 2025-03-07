package io.tiklab.sward.support.dao;

import io.tiklab.core.order.Order;
import io.tiklab.dal.jdbc.JdbcTemplate;
import io.tiklab.sward.node.entity.NodeEntity;
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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

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
//        QueryCondition queryCondition = QueryBuilders.createQuery(RecentEntity.class, "re")
//                .leftJoin(NodeEntity.class, "node", "node.id=re.modelId")
//                .eq("re.modelId", recentQuery.getModelId())
//                .eq("re.masterId", recentQuery.getMasterId())
//                .eq("re.repositoryId", recentQuery.getRepositoryId())
//                .eq("re.model", recentQuery.getModel())
//                .in("re.model", recentQuery.getModelIds())
//                .eq("node.recycle", "0")
//                .eq("node.status", "nomal")
//                .orders(recentQuery.getOrderParams())
//                .get();

        String sql = "SELECT re.* FROM wiki_recent AS re LEFT JOIN wiki_node AS node ON node.id = re.model_id WHERE 1=1";
        Map<String, Object> paramMap = new HashMap<String, Object>();


        if(recentQuery.getModelId() != null ){
            sql = sql.concat(" and re.model_id = '" + recentQuery.getModelId() + "'");
        }

        if(recentQuery.getMasterId() != null ){
            sql = sql.concat(" and re.master_id = '" + recentQuery.getMasterId() + "'");
        }

        if(recentQuery.getRepositoryId() != null ){
            sql = sql.concat(" and re.repository_id = '" + recentQuery.getRepositoryId()+ "'");
        }
        if(recentQuery.getModel() != null ){
            sql = sql.concat(" and re.model = '" + recentQuery.getModel()+ "'");
        }
        if(recentQuery.getModelIds() != null ){
            String[] modelIds = recentQuery.getModelIds();
            List<String> modelIdList = new ArrayList<>(Arrays.asList(modelIds));
            String modelIdLists = modelIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(", "));

            sql = sql.concat(" and re.model in " + modelIdLists + " ");
        }
        if(recentQuery.getRecycle() != null){
            sql = sql.concat(" and node.recycle = '" + recentQuery.getRecycle() + "'");
        }

        if(recentQuery.getStatus() != null){
            sql = sql.concat(" and node.status = '" + recentQuery.getStatus() + "'");
        }
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<RecentEntity> query = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(RecentEntity.class));
        return query;
    }

    public Pagination<RecentEntity> findRecentPage(RecentQuery recentQuery) {
        return jpaTemplate.findPage(recentQuery, RecentEntity.class);
    }
}