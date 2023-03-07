package io.tiklab.kanass.category.dao;

import io.tiklab.kanass.category.entity.CategoryEntity;
import io.tiklab.kanass.category.model.CategoryQuery;
import io.tiklab.kanass.document.entity.DocumentEntity;
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
public class CategoryDao{

    private static Logger logger = LoggerFactory.getLogger(CategoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param categoryEntity
     * @return
     */
    public String createCategory(CategoryEntity categoryEntity) {
        return jpaTemplate.save(categoryEntity,String.class);
    }

    /**
     * 更新
     * @param categoryEntity
     */
    public void updateCategory(CategoryEntity categoryEntity){
        jpaTemplate.update(categoryEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteCategory(String id){
        jpaTemplate.delete(CategoryEntity.class,id);
    }

    public void deleteCategory(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public CategoryEntity findCategory(String id){
        return jpaTemplate.findOne(CategoryEntity.class,id);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public List<CategoryEntity> findCategoryDocument(String id){
        QueryCondition queryCondition = QueryBuilders.createQuery(CategoryEntity.class,"ce").eq("ce.parentCategoryId",id).get();
        List<CategoryEntity> categoryEntityList = jpaTemplate.findList(queryCondition,CategoryEntity.class);
        return categoryEntityList;
    }

    public List<DocumentEntity> findDocumentDocument(String id){
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentEntity.class,"de").eq("de.categoryId",id).get();
        List<DocumentEntity> documentEntityList = jpaTemplate.findList(queryCondition,DocumentEntity.class);

        return documentEntityList;
    }

    /**
    * findAllCategory
    * @return
    */
    public List<CategoryEntity> findAllCategory() {
        return jpaTemplate.findAll(CategoryEntity.class);
    }

    public List<CategoryEntity> findCategoryList(List<String> idList) {
        return jpaTemplate.findList(CategoryEntity.class,idList);
    }

    public List<CategoryEntity> findCategoryList(CategoryQuery categoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CategoryEntity.class)
                .eq("repositoryId", categoryQuery.getRepositoryId())
                .orders(categoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, CategoryEntity.class);
    }

    public Pagination<CategoryEntity> findCategoryPage(CategoryQuery categoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CategoryEntity.class)
                .eq("repositoryId", categoryQuery.getRepositoryId())
                .orders(categoryQuery.getOrderParams())
                .pagination(categoryQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, CategoryEntity.class);
    }
}