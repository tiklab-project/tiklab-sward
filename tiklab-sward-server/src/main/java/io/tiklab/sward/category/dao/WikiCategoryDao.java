package io.tiklab.sward.category.dao;

import io.tiklab.sward.category.entity.WikiCategoryEntity;
import io.tiklab.sward.category.model.WikiCategoryQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

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

    public void deleteCategoryByIds(DeleteCondition deleteCondition){

        jpaTemplate.delete(deleteCondition);
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
                .orders(wikiCategoryQuery.getOrderParams());
        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findList(queryCondition, WikiCategoryEntity.class);
    }


    public Pagination<WikiCategoryEntity> findCategoryPage(WikiCategoryQuery wikiCategoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiCategoryEntity.class)
                .orders(wikiCategoryQuery.getOrderParams())
                .pagination(wikiCategoryQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WikiCategoryEntity.class);
    }

}