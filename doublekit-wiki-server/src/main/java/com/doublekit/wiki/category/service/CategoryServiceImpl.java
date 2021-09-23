package com.doublekit.wiki.category.service;

import com.doublekit.beans.BeanMapper;
import com.doublekit.common.Pagination;
import com.doublekit.join.JoinTemplate;
import com.doublekit.wiki.category.dao.CategoryDao;
import com.doublekit.wiki.category.entity.CategoryPo;
import com.doublekit.wiki.category.model.Category;
import com.doublekit.wiki.category.model.CategoryQuery;
import com.doublekit.wiki.document.dao.DocumentDao;
import com.doublekit.wiki.document.entity.DocumentPo;
import com.doublekit.wiki.document.model.Document;
import com.doublekit.wiki.document.model.DocumentQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* CategoryServiceImpl
*/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DocumentDao documentDao;


    @Override
    public String createCategory(@NotNull @Valid Category category) {
        CategoryPo categoryPo = BeanMapper.map(category, CategoryPo.class);

        return categoryDao.createCategory(categoryPo);
    }

    @Override
    public void updateCategory(@NotNull @Valid Category category) {
        CategoryPo categoryPo = BeanMapper.map(category, CategoryPo.class);
        if (ObjectUtils.isEmpty(category.getParentCategory())){
            categoryPo.setParentCategoryId("");
        }
        categoryDao.updateCategory(categoryPo);
    }

    @Override
    public void deleteCategory(@NotNull String id) {
        //删除下面相关联的目录和文档
        
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

        joinTemplate.queryOne(category);
        return category;
    }

    @Override
    public List<Category> findAllCategory() {
        List<CategoryPo> categoryPoList =  categoryDao.findAllCategory();

        List<Category> categoryList =  BeanMapper.mapList(categoryPoList,Category.class);

        joinTemplate.queryList(categoryList);
        return categoryList;
    }

    @Override
    public List<Category> findCategoryList(CategoryQuery categoryQuery) {
        List<CategoryPo> categoryPoList = categoryDao.findCategoryList(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(categoryPoList,Category.class);

        joinTemplate.queryList(categoryList);

        return categoryList;
    }

    @Override
    public Pagination<Category> findCategoryPage(CategoryQuery categoryQuery) {
        Pagination<Category> pg = new Pagination<>();

        Pagination<CategoryPo>  pagination = categoryDao.findCategoryPage(categoryQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Category> categoryList = BeanMapper.mapList(pagination.getDataList(),Category.class);

        joinTemplate.queryList(categoryList);

        pg.setDataList(categoryList);
        return pg;
    }

    @Override
    public List<Object> findCategoryListTree(CategoryQuery categoryQuery) {
        ArrayList<Object> objects = new ArrayList<>();
        //查询符合条件的所有目录
        List<Category> categoryList = this.findCategoryList(categoryQuery);

        List<DocumentPo> documentList = documentDao.findDocumentList(new DocumentQuery().setRepositoryId(categoryQuery.getRepositoryId()));
        //查找并设置分类下面的接口
        List<Category> categoryMethodList = findCategoryMethodList(categoryList);

        //查询没在目录下main的文档
        List<DocumentPo> collect = documentList.stream().filter(a -> ObjectUtils.isEmpty(a.getCategoryId())).collect(Collectors.toList());
        //查询以及目录
        List<Category> topCategoryList = findTopCategoryList(categoryMethodList);

        //查找并设置子分类列表
        if(topCategoryList != null){
            for(Category topCategory:topCategoryList){
                setChildren(categoryList,topCategory);
            }
        }
        objects.add(topCategoryList);
        objects.add(collect);
        return objects;
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
    List<Category> findCategoryMethodList(List<Category> categoryList){

        List<Category> categorys = categoryList.stream().map(category -> {
            DocumentQuery documentQuery = new DocumentQuery();
            documentQuery.setCategoryId(category.getId());
            List<DocumentPo> repositoryDetailsList = documentDao.findDocumentList(documentQuery);
            List<Document> documents = BeanMapper.mapList(repositoryDetailsList, Document.class);
            category.setDocuments(documents);
            return category;
        }).collect(Collectors.toList());

        return  categorys;
    }


}