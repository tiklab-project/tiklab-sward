package io.tiklab.sward.statistics.dao;

import io.tiklab.core.exception.SystemException;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderTypeEnum;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.node.entity.NodeEntity;
import io.tiklab.sward.statistics.entity.StatisticsDocEntity;
import io.tiklab.sward.statistics.model.StatisticsByGroup;
import io.tiklab.sward.statistics.model.StatisticsDocQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;


@Repository
public class StatisticsDocDao {
    
    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建统计
     * @param StatisticsDocEntity 详情
     * @return 详情Id
     */
    public String createStatisticsDocEntity(StatisticsDocEntity StatisticsDocEntity){
      return jpaTemplate.save(StatisticsDocEntity,String.class);
    }


    /**
     * 更新统计
     * @param StatisticsDocEntity 详情
     */
    public void updateStatisticsDocEntity(StatisticsDocEntity StatisticsDocEntity){
        jpaTemplate.update(StatisticsDocEntity);
    }


    /**
     * 删除统计
     * @param StatisticsId 详情Id
     */
    public void deleteStatisticsDocEntity(String StatisticsId){
        jpaTemplate.delete(StatisticsDocEntity.class, StatisticsId);
    }


    /**
     * 根据统计Id获取统计
     * @param StatisticsId 统计Id
     * @return 统计
     */
    @FindOne
    public StatisticsDocEntity findStatisticsDocEntity(String StatisticsId){
       return jpaTemplate.findOne(StatisticsDocEntity.class, StatisticsId);
    }

    /**
     * 获取所有统计
     * @return 统计列表
     */
    @FindAll
    public List<StatisticsDocEntity> findAllStatisticsDocEntity(){
        List<StatisticsDocEntity> entityList = jpaTemplate.findAll(StatisticsDocEntity.class);
        if (Objects.isNull(entityList)){
            return Collections.emptyList();
        }
        return entityList;
    }

    /**
     * 根据id列表获取统计
     * @param idList id列表
     * @return 统计列表
     */
    @FindList
    public List<StatisticsDocEntity> findStatisticsDocEntityList(List<String> idList){
        List<StatisticsDocEntity> entityList = jpaTemplate.findList(StatisticsDocEntity.class, idList);
        if (Objects.isNull(entityList)){
            return Collections.emptyList();
        }
        return entityList;
    }

    /**
     * 根据查询条件获取统计
     * @param query 查询条件
     * @return 统计列表
     */
    public List<StatisticsDocEntity> findStatisticsDocEntityList(StatisticsDocQuery query){
        StatisticsCondition queryCondition = findQueryCondition(query);
        String sql = queryCondition.getSql();
        return jpaTemplate.getJdbcTemplate().query(sql, queryCondition.getObjects(),
                new BeanPropertyRowMapper<>(StatisticsDocEntity.class));
    }


    /**
     * 分组统计查询
     * @param query 查询条件
     * @return 统计列表
     */
    public List<StatisticsByGroup> findStatisticsByGroup(StatisticsDocQuery query){
        StatisticsCondition queryCondition = findQueryConditionByGroup(query);
        String sql = queryCondition.getSql();
        return jpaTemplate.getJdbcTemplate().query(sql, queryCondition.getObjects(),
                new BeanPropertyRowMapper<>(StatisticsByGroup.class));
    }

    public Pagination<StatisticsByGroup> findStatisticsByGroupPage(StatisticsDocQuery query){
        StatisticsCondition queryCondition = findQueryConditionByGroup(query);
        String sql = queryCondition.getSql();
        return jpaTemplate.getJdbcTemplate().findPage(sql, queryCondition.getObjects(),query.getPageParam(),
                new BeanPropertyRowMapper<>(StatisticsByGroup.class));
    }

    public Integer findStatisticsDocCount(StatisticsDocQuery query){
        StatisticsCondition queryCondition = findQueryConditionCount(query);
        String sql = queryCondition.getSql();
        Object[] objects = queryCondition.getObjects();
        // 使用 JdbcTemplate 执行查询
        return jpaTemplate.getJdbcTemplate()
                .queryForObject(sql, objects, Integer.class);
    }

    /**
     * 分页查询统计
     * @param query 查询条件
     * @return 分页查询结果
     */
    public Pagination<StatisticsDocEntity> findStatisticsDocEntityPage(StatisticsDocQuery query){

        StatisticsCondition queryCondition = findQueryCondition(query);

        return jpaTemplate.getJdbcTemplate().findPage(queryCondition.getSql(),queryCondition.getObjects(),
                query.getPageParam(),
                new BeanPropertyRowMapper<>(StatisticsDocEntity.class));
    }

    // 列表查询
    StatisticsCondition findQueryCondition(StatisticsDocQuery query) {

        List<Object> params = new ArrayList<>();
        String sql = "select wiki_document_statistics.* from wiki_document_statistics where 1=1";

        if (!Objects.isNull(query.getType())){
            sql = sql.concat(" and type = ?");
            params.add(query.getType());
        }

        if (!Objects.isNull(query.getUserId())){
            sql = sql.concat(" and user_id = ?");
            params.add(query.getUserId());
        }

        if (!Objects.isNull(query.getRpyId())){
            sql = sql.concat(" and rpy_id = ?");
            params.add(query.getRpyId());
        }

        if (!Objects.isNull(query.getDocumentId())){
            sql = sql.concat(" and document_id = ?");
            params.add(query.getDocumentId());
        }

        if (!query.getTimeList().isEmpty()){
            sql = sql.concat(" and create_time > ? and create_time < ?");
            params.add(query.getTimeList().get(0));
            params.add(query.getTimeList().get(1));
        }

        if (query.getOrderParams() != null && !query.getOrderParams().isEmpty()) {
            sql += " order by " + orderBy(query.getOrderParams(), NodeEntity.class);
        }

        return new StatisticsCondition(sql,params.toArray());

    }

    // 统计查询
    StatisticsCondition findQueryConditionCount(StatisticsDocQuery query) {

        List<Object> params = new ArrayList<>();
        String sql = "select count(*) as number from wiki_document_statistics where 1=1";

        if (!Objects.isNull(query.getType())){
            sql = sql.concat(" and type = ?");
            params.add(query.getType());
        }

        if (!Objects.isNull(query.getUserId())){
            sql = sql.concat(" and user_id = ?");
            params.add(query.getUserId());
        }

        if (!Objects.isNull(query.getRpyId())){
            sql = sql.concat(" and rpy_id = ?");
            params.add(query.getRpyId());
        }

        if (!Objects.isNull(query.getDocumentId())){
            sql = sql.concat(" and document_id = ?");
            params.add(query.getDocumentId());
        }

        if (!query.getTimeList().isEmpty()){
            sql = sql.concat(" and create_time > ? and create_time < ?");
            params.add(query.getTimeList().get(0));
            params.add(query.getTimeList().get(1));
        }
        return new StatisticsCondition(sql,params.toArray());

    }

    // 分组查询
    StatisticsCondition findQueryConditionByGroup(StatisticsDocQuery query) {

        List<Object> params = new ArrayList<>();
        String sql = "select "+query.getGroupBy()+", count(*) as number from wiki_document_statistics where 1=1";

        if (!Objects.isNull(query.getType())){
            sql = sql.concat(" and type = ?");
            params.add(query.getType());
        }

        if (!Objects.isNull(query.getUserId())){
            sql = sql.concat(" and user_id = ?");
            params.add(query.getUserId());
        }

        if (!Objects.isNull(query.getRpyId())){
            sql = sql.concat(" and rpy_id = ?");
            params.add(query.getRpyId());
        }

        if (!Objects.isNull(query.getDocumentId())){
            sql = sql.concat(" and document_id = ?");
            params.add(query.getDocumentId());
        }

        if (!query.getTimeList().isEmpty()){
            sql = sql.concat(" and create_time > ? and create_time < ?");
            params.add(query.getTimeList().get(0));
            params.add(query.getTimeList().get(1));
        }
        sql = sql.concat(" group by ").concat(query.getGroupBy());
        return new StatisticsCondition(sql,params.toArray());

    }

    /**
     * 排序
     */
    public <T> String orderBy (List<Order> orders, Class<T> clazz, String... alias) {
        StringBuilder sqlDesc = new StringBuilder();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            String key = order.getName();
            OrderTypeEnum orderType = order.getOrderType();

            String columnName = null;
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                if (name.equals(key)){
                    Column column = field.getAnnotation(Column.class);
                    columnName = column.name();
                }
            }
            if (org.apache.commons.lang3.StringUtils.isEmpty(columnName)){
                throw new SystemException("字段"+key+"实体字段不存在！");
            }

            if (alias.length == 0){
                if ( i + 1 == orders.size()) {
                    sqlDesc.append(columnName).append(" ").append(orderType);
                } else {
                    sqlDesc.append(columnName).append(" ").append(orderType).append(" ").append(',');
                }
            }else {
                if ( i + 1 == orders.size()) {
                    sqlDesc.append(alias[0]).append(".").append(columnName).append(" ").append(orderType);
                } else {
                    sqlDesc.append(alias[0]).append(".").append(columnName).append(" ").append(orderType).append(" ").append(',');
                }
            }
        }
        return sqlDesc.toString();
    }


}
class StatisticsCondition{

    private String sql;

    private Object[] objects;

    public StatisticsCondition(String sql, Object[] objects) {
        this.sql = sql;
        this.objects = objects;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
}