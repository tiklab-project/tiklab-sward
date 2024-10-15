package io.thoughtware.sward.node.dao;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.OrQueryCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.OrQueryBuilders;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.sward.category.entity.WikiCategoryEntity;
import io.thoughtware.sward.document.entity.WikiDocumentEntity;
import io.thoughtware.sward.node.entity.NodeEntity;
import io.thoughtware.sward.node.model.NodeQuery;
import io.thoughtware.sward.support.entity.RecentEntity;
import io.thoughtware.sward.support.model.RecentQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

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

    public void deleteNodeCondition(Object[] ids){
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(NodeEntity.class)
                .in("id", ids)
                .get();
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
        QueryCondition queryCondition = new QueryCondition();

        QueryBuilders queryBuilders = QueryBuilders.createQuery(NodeEntity.class)
                .like("name", nodeQuery.getName())
                .eq("repositoryId", nodeQuery.getRepositoryId())
                .eq("masterId", nodeQuery.getMasterId())
                .eq("parentId", nodeQuery.getParentId())
                .eq("type", nodeQuery.getType())
                .eq("status", nodeQuery.getStatus())
                .eq("recycle", nodeQuery.getRecycle())
                .in("dimension", nodeQuery.getDimensions())
                .in("id", nodeQuery.getIds())
                .orders(nodeQuery.getOrderParams());

        if( nodeQuery.getTreePath() != null){
            OrQueryCondition orQueryCondition = OrQueryBuilders.instance()
                    .like("treePath", nodeQuery.getTreePath())
                    .eq("id", nodeQuery.getId())
                    .get();
            queryCondition = queryBuilders.or(orQueryCondition).get();
        }else {
            queryCondition = queryBuilders.get();
        }

        return jpaTemplate.findList(queryCondition, NodeEntity.class);
    }

    public Pagination<NodeEntity> findNodePage(NodeQuery nodeQuery) {
        QueryCondition queryCondition = new QueryCondition();

        QueryBuilders queryBuilders = QueryBuilders.createQuery(NodeEntity.class)
                .like("name", nodeQuery.getName())
                .eq("repositoryId", nodeQuery.getRepositoryId())
                .eq("masterId", nodeQuery.getMasterId())
                .eq("parentId", nodeQuery.getParentId())
                .eq("type", nodeQuery.getType())
                .eq("status", nodeQuery.getStatus())
                .eq("recycle", nodeQuery.getRecycle())
                .in("dimension", nodeQuery.getDimensions())
                .in("id", nodeQuery.getIds())
                .notIn("id", nodeQuery.getNotIds())
                .orders(nodeQuery.getOrderParams())
                .pagination(nodeQuery.getPageParam());

        if( nodeQuery.getTreePath() != null){
            OrQueryCondition orQueryCondition = OrQueryBuilders.instance()
                    .like("treePath", nodeQuery.getTreePath())
                    .eq("id", nodeQuery.getId())
                    .get();
            queryCondition = queryBuilders.or(orQueryCondition).get();
        }else {
            queryCondition = queryBuilders.get();
        }
        return jpaTemplate.findPage(queryCondition, NodeEntity.class);
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
}