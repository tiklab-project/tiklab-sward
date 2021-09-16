package com.doublekit.wiki.category.dao;

import com.doublekit.common.Pagination;
import com.doublekit.wiki.category.entity.CategoryPo;
import com.doublekit.wiki.category.model.CategoryQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
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
     * @param categoryPo
     * @return
     */
    public String createCategory(CategoryPo categoryPo) {
        return jpaTemplate.save(categoryPo,String.class);
    }

    /**
     * 更新
     * @param categoryPo
     */
    public void updateCategory(CategoryPo categoryPo){
        jpaTemplate.update(categoryPo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteCategory(String id){
        jpaTemplate.delete(CategoryPo.class,id);
    }

    public void deleteCategory(DeleteCondition deleteCondition){
        jpaTemplate.delete(CategoryPo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public CategoryPo findCategory(String id){
        return jpaTemplate.findOne(CategoryPo.class,id);
    }

    /**
    * findAllCategory
    * @return
    */
    public List<CategoryPo> findAllCategory() {
        return jpaTemplate.findAll(CategoryPo.class);
    }

    public List<CategoryPo> findCategoryList(List<String> idList) {
        return jpaTemplate.findList(CategoryPo.class,idList);
    }

    public List<CategoryPo> findCategoryList(CategoryQuery categoryQuery) {
        return jpaTemplate.findList(CategoryPo.class,categoryQuery);
    }

    public Pagination<CategoryPo> findCategoryPage(CategoryQuery categoryQuery) {
        return jpaTemplate.findPage(CategoryPo.class,categoryQuery);
    }
}