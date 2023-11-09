package io.tiklab.kanass.category.dao;

import io.tiklab.kanass.category.entity.WikiCategoryEntity;
import io.tiklab.kanass.category.model.WikiCategory;
import io.tiklab.kanass.category.model.WikiCategoryQuery;
import io.tiklab.kanass.document.entity.WikiDocumentEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * CategoryDao
 */
@Repository
public class WikiCategoryDao {

    private static Logger logger = LoggerFactory.getLogger(WikiCategoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param wikiCategoryEntity
     * @return
     */
    public String createCategory(WikiCategoryEntity wikiCategoryEntity) {
        return jpaTemplate.save(wikiCategoryEntity,String.class);
    }

    /**
     * 更新
     * @param wikiCategoryEntity
     */
    public void updateCategory(WikiCategoryEntity wikiCategoryEntity){
        jpaTemplate.update(wikiCategoryEntity);
    }

    public void updateOnRepository(String repository, Integer oldSort, Integer sort ){
        String sql = "";
        if(oldSort > sort){
            sql = " UPDATE kanass_category SET sort = sort + 1 where repository_id = '" +repository
                    + "' and parent_category_id is null and sort >= '" +sort + "' and sort < '" + oldSort + "'";
            this.jpaTemplate.getJdbcTemplate().update(sql);

            sql = " UPDATE kanass_document SET sort = sort + 1 where repository_id = '" +repository
                    + "' and category_id is null and sort >= '" +sort + "' and sort < '" + oldSort + "'";
            this.jpaTemplate.getJdbcTemplate().update(sql);

        }else {
            sql = " UPDATE kanass_category SET sort = sort - 1 where repository_id = '" +repository
                    + "' and parent_category_id is null and sort <= '" + sort + "' and sort > '" + oldSort + "'";
            this.jpaTemplate.getJdbcTemplate().update(sql);

            sql = " UPDATE kanass_document SET sort = sort - 1 where repository_id = '" +repository
                    + "' and category_id is null and sort <= '" + sort + "' and sort > '" + oldSort + "'";
            this.jpaTemplate.getJdbcTemplate().update(sql);
        }

    }
    public void updateRepositoryToCategory(String repository, String parentWikiCategoryId,Integer oldSort, Integer sort ){
        String sql = "";
        // 更新库下的文档和目录
        sql = " UPDATE kanass_category SET sort = sort - 1 where repository_id = '" +repository
                + "' and parent_category_id is null and sort >= '" +oldSort + "'";
        this.jpaTemplate.getJdbcTemplate().execute(sql);
//        this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Void.class);
        sql = " UPDATE kanass_document SET sort = sort - 1 where repository_id = '" +repository
                + "' and category_id is null and sort >'" + oldSort + "'";
        this.jpaTemplate.getJdbcTemplate().execute(sql);

        // 更新目录下的文档和目录
        sql = " UPDATE kanass_category SET sort = sort + 1 where repository_id = '" +repository
                + "' and parent_category_id = '" + parentWikiCategoryId +"' and sort >= '" +sort + "'";
        this.jpaTemplate.getJdbcTemplate().execute(sql);
        sql = " UPDATE kanass_document SET sort = sort + 1 where repository_id = '" +repository
                + "' and category_id  = '" + parentWikiCategoryId + "' and sort >='" + sort + "'";
        this.jpaTemplate.getJdbcTemplate().execute(sql);
    }

    public void  updateCategoryToRepository(String repository, String parentWikiCategoryId,Integer oldSort, Integer sort ){
        String sql = "";
        // 更新库下的文档和目录
        sql = " UPDATE kanass_category SET sort = sort + 1 where repository_id = '" +repository
                + "' and parent_category_id is null and sort >= '" + sort + "'";
        this.jpaTemplate.getJdbcTemplate().update(sql);
        sql = " UPDATE kanass_document SET sort = sort + 1 where repository_id = '" +repository
                + "' and category_id is null and sort >='" + sort + "'";
        this.jpaTemplate.getJdbcTemplate().update(sql);

        // 更新目录下的文档和目录
        sql = " UPDATE kanass_category SET sort = sort - 1 where repository_id = '" +repository
                + "' and parent_category_id = '" + parentWikiCategoryId +"' and sort > '" +oldSort + "'";
        this.jpaTemplate.getJdbcTemplate().update(sql);
        sql = " UPDATE kanass_document SET sort = sort - 1 where repository_id = '" +repository
                + "' and category_id  = '" + parentWikiCategoryId + "' and sort >'" + oldSort + "'";
        this.jpaTemplate.getJdbcTemplate().update(sql);
    }

    public void  updateOnCategory(String oldParentId, String parentWikiCategoryId,Integer oldSort, Integer sort ){
        String sql = "";
        // 更新旧目录下的文档和目录
        sql = " UPDATE kanass_category SET sort = sort - 1 where parent_category_id = '" + oldParentId
                + "' and sort > '" +oldSort + "'";
        this.jpaTemplate.getJdbcTemplate().update(sql);
        sql = " UPDATE kanass_document SET sort = sort - 1 where category_id = '" +oldParentId
                + "' and sort >'" + oldSort + "'";
        this.jpaTemplate.getJdbcTemplate().update(sql);

        // 更新新目录下的文档和目录
        sql = " UPDATE kanass_category SET sort = sort + 1 where parent_category_id = '" + parentWikiCategoryId
                + "' and sort >= '" +sort + "'";
        this.jpaTemplate.getJdbcTemplate().update(sql);
        sql = " UPDATE kanass_document SET sort = sort + 1 where category_id = '" + parentWikiCategoryId
                + "' and sort >='" + sort + "'";
        this.jpaTemplate.getJdbcTemplate().update(sql);
    }

    public void  updateCurrentCategory(String parentWikiCategoryId,Integer oldSort, Integer sort ){
        String sql = "";
        if(oldSort > sort){
            sql = " UPDATE kanass_category SET sort = sort + 1 where parent_category_id = '" +
                    parentWikiCategoryId  + "' and sort >= '" +sort + "' and sort < '" + oldSort + "'";
            this.jpaTemplate.getJdbcTemplate().update(sql);

            sql = " UPDATE kanass_document SET sort = sort + 1 category_id = '" +
                    parentWikiCategoryId + "' and sort >= '" +sort + "' and sort < '" + oldSort + "'";
            this.jpaTemplate.getJdbcTemplate().update(sql);

        }else {
            sql = " UPDATE kanass_category SET sort = sort - 1 where parent_category_id = '" +
                    parentWikiCategoryId  + "' and sort <= '" + sort + "' and sort > '" + oldSort + "'";
            this.jpaTemplate.getJdbcTemplate().update(sql);

            sql = " UPDATE kanass_document SET sort = sort - 1 where category_id = '" +
                    parentWikiCategoryId + "' and sort <= '" + sort + "' and sort > '" + oldSort + "'";
            this.jpaTemplate.getJdbcTemplate().update(sql);
        }
    }
    /**
     * 删除
     * @param id
     */
    public void deleteCategory(String id){
        jpaTemplate.delete(WikiCategoryEntity.class,id);
    }

    public void deleteCategory(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WikiCategoryEntity findCategory(String id){
        return jpaTemplate.findOne(WikiCategoryEntity.class,id);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public List<WikiCategoryEntity> findCategoryDocument(String id){
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiCategoryEntity.class,"ce").eq("ce.parentCategoryId",id).get();
        List<WikiCategoryEntity> wikiCategoryEntityList = jpaTemplate.findList(queryCondition, WikiCategoryEntity.class);
        return wikiCategoryEntityList;
    }

    public List<WikiDocumentEntity> findDocumentDocument(String id){
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiDocumentEntity.class,"de").eq("de.categoryId",id).get();
        List<WikiDocumentEntity> wikiDocumentEntityList = jpaTemplate.findList(queryCondition, WikiDocumentEntity.class);

        return wikiDocumentEntityList;
    }

    /**
    * findAllCategory
    * @return
    */
    public List<WikiCategoryEntity> findAllCategory() {
        return jpaTemplate.findAll(WikiCategoryEntity.class);
    }

    public List<WikiCategoryEntity> findCategoryList(List<String> idList) {
        return jpaTemplate.findList(WikiCategoryEntity.class,idList);
    }

    public List<WikiCategoryEntity> findCategoryList(WikiCategoryQuery wikiCategoryQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(WikiCategoryEntity.class)
                .eq("repositoryId", wikiCategoryQuery.getRepositoryId())
                .eq("parentCategoryId", wikiCategoryQuery.getParentWikiCategory())
                .in("parentCategoryId", wikiCategoryQuery.getParentWikiCategorys())
                .eq("dimension", wikiCategoryQuery.getDimension())
                .in("dimension", wikiCategoryQuery.getDimensions())
                .orders(wikiCategoryQuery.getOrderParams());
//        if(wikiCategoryQuery.getParentWikiCategoryIsNull() != null && wikiCategoryQuery.getParentWikiCategoryIsNull() == true){
//            queryBuilders.isNull("parentCategoryId");
//        }

        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findList(queryCondition, WikiCategoryEntity.class);
    }

    public List<Map<String, Object>> findCategoryChidrenList(String wikiCategoryIds) {
        String sql = "SELECT id, parent_category_id from kanass_category WHERE parent_category_id in " + wikiCategoryIds;
        List<Map<String, Object>> categoryIds = this.jpaTemplate.getJdbcTemplate().queryForList(sql);
        return categoryIds;
    }

    public List<Map<String, Object>> findDocumentChidrenList(String wikiCategoryIds) {
        String sql = "SELECT id, category_id from kanass_document WHERE category_id in " + wikiCategoryIds;
        List<Map<String, Object>> documentIds = this.jpaTemplate.getJdbcTemplate().queryForList(sql);
        return documentIds;
    }

    public Pagination<WikiCategoryEntity> findCategoryPage(WikiCategoryQuery wikiCategoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiCategoryEntity.class)
                .eq("repositoryId", wikiCategoryQuery.getRepositoryId())
                .orders(wikiCategoryQuery.getOrderParams())
                .pagination(wikiCategoryQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WikiCategoryEntity.class);
    }
}