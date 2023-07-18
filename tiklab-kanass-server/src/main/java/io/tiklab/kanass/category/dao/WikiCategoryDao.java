package io.tiklab.kanass.category.dao;

import io.tiklab.kanass.category.entity.WikiCategoryEntity;
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
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiCategoryEntity.class)
                .eq("repositoryId", wikiCategoryQuery.getRepositoryId())
                .orders(wikiCategoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WikiCategoryEntity.class);
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