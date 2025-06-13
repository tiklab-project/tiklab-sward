package io.tiklab.sward.node.dao;


import io.tiklab.core.exception.SystemException;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderTypeEnum;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.node.entity.NodeEntity;
import io.tiklab.sward.node.model.NodeQuery;
import io.tiklab.sward.support.entity.RecentEntity;
import io.tiklab.sward.support.model.RecentQuery;
import io.tiklab.sward.support.util.DatabaseVersionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * NodeDao
 */
@Repository
public class NodeDao {

    private static Logger logger = LoggerFactory.getLogger(NodeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param nodeEntity
     * @return
     */
    public String createNode(NodeEntity nodeEntity) {
        return jpaTemplate.save(nodeEntity,String.class);
    }

    /**
     * 更新
     * @param nodeEntity
     */
    public void updateNode(NodeEntity nodeEntity){
        jpaTemplate.update(nodeEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteNode(String id){
        jpaTemplate.delete(NodeEntity.class,id);
    }

    public void deleteNode(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    public void deleteNodeCondition(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public NodeEntity findNode(String id){
        return jpaTemplate.findOne(NodeEntity.class,id);
    }

    /**
     * findAllNode
     * @return
     */
    public List<NodeEntity> findAllNode() {
        return jpaTemplate.findAll(NodeEntity.class);
    }

    public List<NodeEntity> findNodeList(List<String> idList) {
        return jpaTemplate.findList(NodeEntity.class,idList);
    }

    public List<NodeEntity> findNodeList(NodeQuery nodeQuery) {
        NodeCondition condition = findQueryCondition(nodeQuery);
        return jpaTemplate.getJdbcTemplate().query(condition.getSql(), condition.getObjects(),new BeanPropertyRowMapper<>(NodeEntity.class));
    }

    public List<NodeEntity> findNodeListByName(NodeQuery nodeQuery) {

        QueryCondition queryCondition = QueryBuilders.createQuery(NodeEntity.class)
                .eq("name", nodeQuery.getName())
                .eq("repositoryId", nodeQuery.getRepositoryId())
                .orders(nodeQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, NodeEntity.class);
    }

    public Pagination<NodeEntity> findNodePage(NodeQuery nodeQuery) {
        NodeCondition condition = findQueryCondition(nodeQuery);
        return jpaTemplate.getJdbcTemplate().findPage(condition.getSql(), condition.getObjects(),
                nodeQuery.getPageParam(),new BeanPropertyRowMapper<>(NodeEntity.class));
    }

    public List<NodeEntity> findNodeRecentList(RecentQuery recentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(NodeEntity.class,"wd")
                .leftJoin(RecentEntity.class, "dr","wd.id=dr.modelId")
                .eq("dr.modelId", recentQuery.getModelId())
                .eq("dr.masterId", recentQuery.getMasterId())
                .eq("dr.repositoryId", recentQuery.getRepositoryId())
                .eq("dr.model", recentQuery.getModel())
                .eq("wd.status", recentQuery.getStatus())
                .eq("wd.recycle", recentQuery.getRecycle())
                .orders(recentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, NodeEntity.class);
    }


    public void updateSortAfterDelete(String repositoryId, String parentId, Integer sort){
        String sql = "";
        // 更新库下的文档和目录
        if(StringUtils.isEmpty(parentId) || parentId.equals("nullString")){
            sql = " UPDATE wiki_node SET sort = sort - 1 where repository_id = '" +repositoryId
                    + "' and parent_id is null and sort > '" + sort + "'";
            this.jpaTemplate.getJdbcTemplate().execute(sql);
        }else {
            // 更新目录下的文档和目录
            sql = " UPDATE wiki_node SET sort = sort - 1 where repository_id = '" +repositoryId
                    + "' and parent_id = '" + parentId +"' and sort > '" +sort + "'";
            this.jpaTemplate.getJdbcTemplate().execute(sql);
        }
    }

    public void reduceSortInCategory(String wikiCategoryId, Integer sort){
        try {
            String sql = "UPDATE wiki_node SET sort = sort - 1 WHERE parent_id ='" + wikiCategoryId + "' and sort > " + sort;
            this.jpaTemplate.getJdbcTemplate().execute(sql);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<NodeEntity> findChildrenNodeList(String repositoryIds){
        String sql =  "SELECT * FROM wiki_node WHERE repository_id in " + repositoryIds;
        List<NodeEntity> nodeEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(NodeEntity.class));
        return nodeEntityList;
    }

    public void reduceSortInRepository(String repositoryId, Integer sort){
        try {
            String sql = "UPDATE wiki_node SET sort = sort - 1 WHERE repository_id ='" + repositoryId + "' and sort > " + sort + " and parent_id IS NULL";
            this.jpaTemplate.getJdbcTemplate().execute(sql);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSortInCategory(String wikiCategoryId, Integer sort){
        try {
            String sql = "UPDATE wiki_node SET sort = sort + 1 WHERE parent_id ='" + wikiCategoryId + "' and sort >= " + sort;
            this.jpaTemplate.getJdbcTemplate().execute(sql);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSortInRepository(String repositoryId, Integer sort){
        try {
            String sql = "UPDATE wiki_node SET sort = sort + 1 WHERE repository_id ='" + repositoryId + "' and sort >= " + sort + " and parent_id IS NULL";
            this.jpaTemplate.getJdbcTemplate().execute(sql);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getMaxSort(String repositoryId, String parentId){
        String sql = "";
        Integer num = 0;
        if(parentId == null){
            sql = "select coalesce(max(sort),0) from wiki_node where parent_id is null and repository_id = '" + repositoryId + "'";
            num = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);
        }else {
            sql = "select coalesce(max(sort),0) from wiki_node where parent_id = '" + parentId + "'";
            num = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);
        }
        return num;
    }

    public List<NodeEntity> findAllChildrenNodeList(String id){
        // 找出所有下级
        String sql = "SELECT * from wiki_node WHERE tree_path like '%" + id + "%';";
        List<NodeEntity> nodeEntityList = this.jpaTemplate.getJdbcTemplate().
                query(sql, new BeanPropertyRowMapper(NodeEntity.class));
        return nodeEntityList;
    }

    // 获取父级所有的文档和目录的个数
    public Integer getBrotherNum(String repositoryId, String categoryId){
        String sql = "";
        Integer num = 0;
        if(categoryId == null){
            sql = "select count(1) as totalCount from wiki_node where parent_id is null and repository_id = '" + repositoryId + "'";
            num = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);


        }else {
            sql = "select count(1) as totalCount from wiki_node where parent_id = '" + categoryId + "'";
            num = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);
        }
        return num;
    }

    NodeCondition findQueryPgsqlCondition(NodeQuery query) {

        List<Object> params = new ArrayList<>();
        String sql = "select wiki_node.* from wiki_node where 1=1";

        if (!StringUtils.isBlank(query.getName())){
            sql = sql.concat(" and name ILIKE '%"+query.getName()+"%'");
        }

        if (!Objects.isNull(query.getIds()) && query.getIds().length !=0 ){
            String ids = Arrays.stream(query.getIds())
                    .map(id -> "'" + id + "'")
                    .collect(Collectors.joining(","));
            sql = sql.concat(" and id in (" + ids + ") ");
        }

        if (!Objects.isNull(query.getNotIds()) && query.getNotIds().length !=0 ){
            String ids = Arrays.stream(query.getNotIds())
                    .map(id -> "'" + id + "'")
                    .collect(Collectors.joining(","));
            sql = sql.concat(" and not id in (" + ids + ") ");
        }

        if (!Objects.isNull(query.getRecycle())){
            sql = sql.concat(" and recycle = ?");
            params.add(query.getRecycle());
        }

        if (!Objects.isNull(query.getDimensions())){
            String ids = Arrays.stream(query.getDimensions())
                    .map(id -> "'" + id + "'")
                    .collect(Collectors.joining(","));
            sql = sql.concat(" and dimension in (" + ids + ") ");
        }

        if (!Objects.isNull(query.getType())){
            sql = sql.concat(" and type = ?");
            params.add(query.getType());
        }

        if (!Objects.isNull(query.getParentId())){
            sql = sql.concat(" and parent_id = ?");
            params.add(query.getParentId());
        }

        if (!Objects.isNull(query.getStatus())){
            sql = sql.concat(" and status = ?");
            params.add(query.getStatus());
        }

        if (!Objects.isNull(query.getMasterId())){
            sql = sql.concat(" and master_id = ?");
            params.add(query.getMasterId());
        }

        if (!Objects.isNull(query.getRepositoryId())){
            sql = sql.concat(" and repository_id = ?");
            params.add(query.getRepositoryId());
        }

        if (!Objects.isNull(query.getRepositoryIds()) && query.getRepositoryIds().length !=0 ){
            String ids = String.join("','", query.getRepositoryIds());
            sql = sql.concat(" and repository_id in ('" + ids + "') ");
        }

        if (!query.getTimeList().isEmpty()){
            sql = sql.concat(" and create_time > ? and create_time < ?");
            params.add(query.getTimeList().get(0));
            params.add(query.getTimeList().get(1));
        }

        if (!StringUtils.isEmpty(query.getApprove())){
            sql = sql.concat(" and ( approve = ?  or ( approve = 'review' and master = ? ))");
            params.add(query.getApprove());
            params.add(query.getApproveUserId());
        }

        if (!StringUtils.isEmpty(query.getTreePath())){
            sql = sql.concat(" and (tree_path like '%" + query.getTreePath() + "%' or id = ?)");
            params.add(query.getId());
        }

        if (query.getOrderParams() != null && !query.getOrderParams().isEmpty()) {
            sql += " order by " + orderBy(query.getOrderParams(), NodeEntity.class);
        }

        return new NodeCondition(sql,params.toArray());

    }

    NodeCondition findMysqlPgsqlCondition(NodeQuery query) {

        List<Object> params = new ArrayList<>();
        String sql = "select wiki_node.* from wiki_node where 1=1";

        if (!StringUtils.isBlank(query.getName())){
            sql = sql.concat(" and LOWER(name) LIKE '%"+query.getName()+"%'");
        }

        if (!Objects.isNull(query.getIds()) && query.getIds().length !=0 ){
            String ids = Arrays.stream(query.getIds())
                    .map(id -> "'" + id + "'")
                    .collect(Collectors.joining(","));
            sql = sql.concat(" and id in (" + ids + ") ");
        }

        if (!Objects.isNull(query.getNotIds()) && query.getNotIds().length !=0 ){
            String ids = Arrays.stream(query.getNotIds())
                    .map(id -> "'" + id + "'")
                    .collect(Collectors.joining(","));
            sql = sql.concat(" and not id in (" + ids + ") ");
        }

        if (!Objects.isNull(query.getRecycle())){
            sql = sql.concat(" and recycle = ?");
            params.add(query.getRecycle());
        }

        if (!Objects.isNull(query.getDimensions())){
            String ids = Arrays.stream(query.getDimensions())
                    .map(id -> "'" + id + "'")
                    .collect(Collectors.joining(","));
            sql = sql.concat(" and dimension in (" + ids + ") ");
        }

        if (!Objects.isNull(query.getType())){
            sql = sql.concat(" and type = ?");
            params.add(query.getType());
        }

        if (!Objects.isNull(query.getParentId())){
            sql = sql.concat(" and parent_id = ?");
            params.add(query.getParentId());
        }

        if (!Objects.isNull(query.getStatus())){
            sql = sql.concat(" and status = ?");
            params.add(query.getStatus());
        }

        if (!Objects.isNull(query.getMasterId())){
            sql = sql.concat(" and master_id = ?");
            params.add(query.getMasterId());
        }

        if (!Objects.isNull(query.getRepositoryId())){
            sql = sql.concat(" and repository_id = ?");
            params.add(query.getRepositoryId());
        }

        if (!Objects.isNull(query.getRepositoryIds()) && query.getRepositoryIds().length !=0 ){
            String ids = String.join(",", query.getRepositoryIds());
            sql = sql.concat(" and repository_id in (" + ids + ") ");
        }

        if (!query.getTimeList().isEmpty()){
            sql = sql.concat(" and create_time > ? and create_time < ?");
            params.add(query.getTimeList().get(0));
            params.add(query.getTimeList().get(1));
        }

        if (!StringUtils.isEmpty(query.getApprove())){
            sql = sql.concat(" and ( approve = ?  or ( approve = 'review' and master = ? ))");
            params.add(query.getApprove());
            params.add(query.getApproveUserId());
        }

        if (!StringUtils.isEmpty(query.getTreePath())){
            sql = sql.concat(" and (tree_path like '%" + query.getTreePath() + "%' or id = ?)");
            params.add(query.getId());
        }

        if (query.getOrderParams() != null && !query.getOrderParams().isEmpty()) {
            sql += " order by " + orderBy(query.getOrderParams(), NodeEntity.class);
        }

        return new NodeCondition(sql,params.toArray());

    }

    NodeCondition findQueryCondition(NodeQuery query) {
        if (DatabaseVersionUtil.isMysql()) {
            return findMysqlPgsqlCondition(query);
        }
        return findQueryPgsqlCondition(query);
    }

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

class NodeCondition{

    private String sql;

    private Object[] objects;

    public NodeCondition(String sql, Object[] objects) {
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











