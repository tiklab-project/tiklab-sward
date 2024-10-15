package io.tiklab.sward.document.dao;

import io.tiklab.sward.support.entity.RecentEntity;
import io.tiklab.sward.document.entity.WikiDocumentEntity;
import io.tiklab.sward.document.model.DocumentQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.sward.support.model.RecentQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DocumentDao
 */
@Repository
public class DocumentDao{

    private static Logger logger = LoggerFactory.getLogger(DocumentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param wikiDocumentEntity
     * @return
     */
    public String createDocument(WikiDocumentEntity wikiDocumentEntity) {

        return jpaTemplate.save(wikiDocumentEntity,String.class);
    }

    // 获取父级所有的文档和目录的个数
    public Integer getBrotherNum(String repositoryId, String categoryId){
        String sql = "";
        Integer num = 0;
        if(categoryId == null){
            sql = "select count(1) as totalCount from wiki_document where category_id is null and repository_id = '" + repositoryId + "'";
            Integer totalDocumentCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);

            sql = "select count(1) as totalCount from wiki_category where parent_category_id is null and repository_id = '" + repositoryId + "'";
            Integer totalCategoryCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);
            num = totalDocumentCount + totalCategoryCount;
        }else {
            sql = "select count(1) as totalCount from wiki_document where category_id = '" + categoryId + "'";
            Integer totalDocumentCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);

            sql = "select count(1) as totalCount from wiki_category where parent_category_id = '" + categoryId + "'";
            Integer totalCategoryCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);
            num = totalDocumentCount + totalCategoryCount;
        }
        return num;
    }

    public Integer getMaxSort(String repositoryId, String categoryId){
        String sql = "";
        Integer num = 0;
        if(categoryId == null){
            sql = "select coalesce(max(sort),0) from wiki_document where category_id is null and repository_id = '" + repositoryId + "'";
            Integer totalDocumentCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);

            sql = "select coalesce(max(sort),0) from wiki_category where parent_category_id is null and repository_id = '" + repositoryId + "'";
            Integer totalCategoryCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);
            num = (totalDocumentCount > totalCategoryCount ? totalDocumentCount : totalCategoryCount);
        }else {
            sql = "select coalesce(max(sort),0) from wiki_document where category_id = '" + categoryId + "'";
            Integer totalDocumentCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);

            sql = "select coalesce(max(sort),0) from wiki_category where parent_category_id = '" + categoryId + "'";
            Integer totalCategoryCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);
            num = (totalDocumentCount > totalCategoryCount ? totalDocumentCount : totalCategoryCount);
        }
        return num;
    }

    /**
     * 更新
     * @param wikiDocumentEntity
     */
    public void updateDocument(WikiDocumentEntity wikiDocumentEntity){
        jpaTemplate.update(wikiDocumentEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDocument(String id){
        jpaTemplate.delete(WikiDocumentEntity.class,id);
    }

    public void deleteDocument(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WikiDocumentEntity findDocument(String id){
        return jpaTemplate.findOne(WikiDocumentEntity.class,id);
    }

    /**
    * findAllDocument
    * @return
    */
    public List<WikiDocumentEntity> findAllDocument() {
        return jpaTemplate.findAll(WikiDocumentEntity.class);
    }

    public List<WikiDocumentEntity> findDocumentList(List<String> idList) {
        return jpaTemplate.findList(WikiDocumentEntity.class,idList);
    }

    public List<WikiDocumentEntity> findDocumentList(DocumentQuery documentQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(WikiDocumentEntity.class)
                .eq("id", documentQuery.getId())
                .like("name", documentQuery.getName())
                .eq("repositoryId", documentQuery.getRepositoryId())
                .in("repositoryId", documentQuery.getRepositoryIds())
                .eq("categoryId", documentQuery.getCategoryId())
                .in("categoryId", documentQuery.getCategoryIds())
                .eq("dimension", documentQuery.getDimension())
                .in("dimension", documentQuery.getDimensions())
                .orders(documentQuery.getOrderParams());
        if(documentQuery.getParentWikiCategoryIsNull() != null && documentQuery.getParentWikiCategoryIsNull() == true){
            queryBuilders.isNull("categoryId");
        }
        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findList(queryCondition, WikiDocumentEntity.class);
    }

    public Pagination<WikiDocumentEntity> findDocumentPage(DocumentQuery documentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiDocumentEntity.class)
                .eq("id", documentQuery.getId())
                .like("name", documentQuery.getName())
                .eq("repositoryId", documentQuery.getRepositoryId())
                .eq("categoryId", documentQuery.getCategoryId())
                .in("repositoryId", documentQuery.getRepositoryIds())
                .notIn("id", documentQuery.getIds())
                .orders(documentQuery.getOrderParams())
                .pagination(documentQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WikiDocumentEntity.class);
    }
    public List<WikiDocumentEntity> findDocuementByKeyWork(String keyWork) {
        String sql = "WITH RECURSIVE recursive_extract AS (\n" +
                "    SELECT \n" +
                "          wiki_document.id,\n" +
                "      wiki_document.name,\n" +
                "      wiki_document.details,\n" +
                "      wiki_document.type_id,\n" +
                "      wiki_document.repository_id,\n" +
                "      wiki_document.category_id,\n" +
                "      wiki_document.master,\n" +
                "      wiki_document.update_time,\n" +
                "          jsonb_array_elements(details) AS element\n" +
                "    FROM wiki_document\n" +
                "    UNION ALL\n" +
                "    SELECT \n" +
                "          recursive_extract.id,\n" +
                "      recursive_extract.name,\n" +
                "      recursive_extract.details,\n" +
                "      recursive_extract.type_id,\n" +
                "      recursive_extract.repository_id,\n" +
                "      recursive_extract.category_id,\n" +
                "      recursive_extract.master,\n" +
                "      recursive_extract.update_time,\n" +
                "          jsonb_array_elements(element->'children') AS element\n" +
                "    FROM recursive_extract\n" +
                "    WHERE jsonb_typeof(element) = 'object' AND element->'children' IS NOT NULL\n" +
                "  )\n" +
                "  \n" +
                "  SELECT *\n" +
                "  FROM \n" +
                "      recursive_extract,\n" +
                "      to_tsvector(recursive_extract.name || recursive_extract.element) document,\n" +
                "    to_tsquery( ? ) query\n" +
                "  WHERE element->>'text' IS NOT NULL AND query @@ document;";
        List<WikiDocumentEntity> wikiDocumentEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{keyWork}, new BeanPropertyRowMapper(WikiDocumentEntity.class));
        return wikiDocumentEntityList;
    }

    public List<WikiDocumentEntity> findRecentDocumentList(RecentQuery recentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiDocumentEntity.class,"wd")
                .leftJoin(RecentEntity.class, "dr","dr.modelId=wd.id")
                .eq("dr.modelId", recentQuery.getModelId())
                .eq("dr.masterId", recentQuery.getMasterId())
                .eq("dr.repositoryId", recentQuery.getRepositoryId())
                .eq("dr.model", recentQuery.getModel())
                .orders(recentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WikiDocumentEntity.class);
    }

    /**
     * 用于更新文档排序，大于被拖动的文档的sort的文档都减1
     */
    public void reduceSortInCategory(String wikiCategoryId, Integer sort){
        try {
            String sql = "UPDATE wiki_document SET sort = sort - 1 WHERE category_id = '" + wikiCategoryId + "' and sort > " + sort;
            this.jpaTemplate.getJdbcTemplate().execute(sql);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void reduceSortInRepository(String repositoryId, Integer sort){
        try {
            String sql = "UPDATE wiki_document SET sort = sort - 1 WHERE repository_id = '" +
                    repositoryId + "' and sort > " + sort + " and category_id IS NULL";
            this.jpaTemplate.getJdbcTemplate().execute(sql);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用于更新文档排序，大于被插入位置的文档的sort的文档都加1
     */
    public void addSortInCategory(String wikiCategoryId, Integer sort){
        try {
            String sql = "UPDATE wiki_document SET sort = sort + 1 WHERE category_id = '" + wikiCategoryId + "' and sort >= " + sort;
            this.jpaTemplate.getJdbcTemplate().execute(sql);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSortInRepository(String repositoryId, Integer sort){

        try {
            String sql = "UPDATE wiki_document SET sort = sort + 1 WHERE repository_id = '" + repositoryId + "' and sort >= " + sort + " and category_id IS NULL";
            this.jpaTemplate.getJdbcTemplate().execute(sql);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

//    public void findAllChildrenDocumentList(Integer dimension, String treePath, String categoryId){
//        // 找出所有下级
//        String sql = "UPDATE wiki_document SET dimension = " + dimension + ", tree_path = '" + treePath
//                + "' WHERE category_id = '" + categoryId + "';";
//        this.jpaTemplate.getJdbcTemplate().execute(sql);
//    }

    public List<WikiDocumentEntity> findAllChildrenDocumentList(String id){
        // 找出所有下级
        String sql = "SELECT * from wiki_document WHERE tree_path like '%" + id + "%';";
        List<WikiDocumentEntity> wikiDocumentList = this.jpaTemplate.getJdbcTemplate().
                query(sql, new BeanPropertyRowMapper(WikiDocumentEntity.class));
        return wikiDocumentList;
    }

}