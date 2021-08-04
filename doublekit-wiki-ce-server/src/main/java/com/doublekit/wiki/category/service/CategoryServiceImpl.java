package com.doublekit.wiki.category.service;

import com.doublekit.wiki.category.dao.CategoryDao;
import com.doublekit.wiki.category.entity.CategoryPo;
import com.doublekit.wiki.category.model.Category;
import com.doublekit.wiki.category.model.CategoryQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
import com.doublekit.wiki.repository.dao.RepositoryDetailsDao;
import com.doublekit.wiki.repository.entity.RepositoryDetailsPo;
import com.doublekit.wiki.repository.model.RepositoryDetails;
import com.doublekit.wiki.repository.model.RepositoryDetailsQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

/**
* CategoryServiceImpl
*/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JoinQuery joinQuery;

    @Autowired
    RepositoryDetailsDao repositoryDetailsDao;

    @Override
    public String createCategory(@NotNull @Valid Category category) {
        CategoryPo categoryPo = BeanMapper.map(category, CategoryPo.class);

        return categoryDao.createCategory(categoryPo);
    }

    @Override
    public void updateCategory(@NotNull @Valid Category category) {
        CategoryPo categoryPo = BeanMapper.map(category, CategoryPo.class);

        categoryDao.updateCategory(categoryPo);
    }

    @Override
    public void deleteCategory(@NotNull String id) {
        categoryDao.deleteCategory(id);
    }

    @Override
    public Category findOne(String id) {
        CategoryPo categoryPo = categoryDao.findCategory(id);

        Category category = BeanMapper.map(categoryPo, Category.class);
        return category;
    }

    @Override
    public List<Category> findList(List<String> idList) {
        List<CategoryPo> categoryPoList =  categoryDao.findCategoryList(idList);

        List<Category> categoryList =  BeanMapper.mapList(categoryPoList,Category.class);
        return categoryList;
    }

    @Override
    public Category findCategory(@NotNull String id) {
        Category category = findOne(id);

        joinQuery.queryOne(category);
        return category;
    }

    @Override
    public List<Category> findAllCategory() {
        List<CategoryPo> categoryPoList =  categoryDao.findAllCategory();

        List<Category> categoryList =  BeanMapper.mapList(categoryPoList,Category.class);

        joinQuery.queryList(categoryList);
        return categoryList;
    }

    @Override
    public List<Category> findCategoryList(CategoryQuery categoryQuery) {
        List<CategoryPo> categoryPoList = categoryDao.findCategoryList(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(categoryPoList,Category.class);

        joinQuery.queryList(categoryList);

        return categoryList;
    }

    @Override
    public Pagination<Category> findCategoryPage(CategoryQuery categoryQuery) {
        Pagination<Category> pg = new Pagination<>();

        Pagination<CategoryPo>  pagination = categoryDao.findCategoryPage(categoryQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Category> categoryList = BeanMapper.mapList(pagination.getDataList(),Category.class);

        joinQuery.queryList(categoryList);

        pg.setDataList(categoryList);
        return pg;
    }

    @Override
    public Map findCategoryListTree(CategoryQuery categoryQuery) {

        Map<String, Object> repostMap = new HashMap<>();
        //查询符合条件的所有目录
        List<Category> categoryList = this.findCategoryList(categoryQuery);

        //查找并设置分类下面的接口
        findCategoryMethodList(categoryList,repostMap);

        //查询以及目录
        List<Category> topCategoryList = findTopCategoryList(categoryList);

        //查找并设置子分类列表
        if(topCategoryList != null){
            for(Category topCategory:topCategoryList){
                setChildren(categoryList,topCategory);
            }
        }
        repostMap.put("topCategoryList",topCategoryList);
        return repostMap;
    }


    /**
     * 查找第一级分类目录
     * @param categoryList
     * @return
     */
    List<Category> findTopCategoryList(List<Category> categoryList){
        return categoryList.stream()
                .filter(category -> (category.getParentCategory() == null || ObjectUtils.isEmpty(category.getParentCategory().getId())))
                .collect(Collectors.toList());
    }

    /**
     * 查找并设置下级分类列表
     * @param matchCategoryList
     * @param parentCaegory
     */
    void setChildren(List<Category> matchCategoryList,Category parentCaegory){
        List<Category> childCategoryList = matchCategoryList.stream()
                .filter(category -> (category.getParentCategory() != null && category.getParentCategory().getId() != null && category.getParentCategory().getId().equals(parentCaegory.getId())))
                .collect(Collectors.toList());

        if(childCategoryList != null && childCategoryList.size() > 0){
            parentCaegory.setChildren(childCategoryList);

            for(Category category:childCategoryList){
                setChildren(matchCategoryList,category);
            }
        }
    }
    /**
     * 查询 知识库相关的内容
     * @param categoryList
     * @return
     */
    List<Category> findCategoryMethodList(List<Category> categoryList,Map<String, Object> repostoryMap){

        List<Category> categorys = categoryList.stream().map(category -> {
            RepositoryDetailsQuery repositoryDetails = new RepositoryDetailsQuery();
            repositoryDetails.setCategoryId(category.getId());
            List<RepositoryDetailsPo> repositoryDetailsList = repositoryDetailsDao.findRepositoryDetailsList(repositoryDetails);
            List<RepositoryDetails> repositoryDetails1 = BeanMapper.mapList(repositoryDetailsList, RepositoryDetails.class);
            //内容直接在知识库下面没有放入目录下面
            if (CollectionUtils.isNotEmpty(repositoryDetails1)){
                List<RepositoryDetails> collect = repositoryDetails1.stream().filter(a -> ObjectUtils.isEmpty(a.getCategoryId())).collect(Collectors.toList());
                repostoryMap.put("repositoryDetails",collect) ;
            }
            category.setRepositoryDetails(repositoryDetails1);
            return category;
        }).collect(Collectors.toList());

        return  categorys;
    }


}