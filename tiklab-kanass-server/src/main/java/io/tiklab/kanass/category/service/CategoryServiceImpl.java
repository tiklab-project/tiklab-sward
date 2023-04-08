package io.tiklab.kanass.category.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.category.entity.CategoryEntity;
import io.tiklab.kanass.category.model.Category;
import io.tiklab.kanass.category.model.CategoryQuery;
import io.tiklab.kanass.category.support.OpLogTemplateCategory;
import io.tiklab.kanass.document.entity.DocumentEntity;
import io.tiklab.kanass.document.model.Document;
import io.tiklab.kanass.document.model.DocumentQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.kanass.category.dao.CategoryDao;
import io.tiklab.kanass.document.service.DocumentService;
import io.tiklab.security.logging.model.Logging;
import io.tiklab.security.logging.model.LoggingType;
import io.tiklab.security.logging.service.LoggingByTemplService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
* CategoryServiceImpl
*/
@Service
@Exporter
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    UserService userService;

    @Autowired
    LoggingByTemplService loggingByTemplService;


    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DocumentService documentService;

    @Value("${base.url:null")
    String baseUrl;

    void creatDynamic( Map<String, String> content){
        Logging log = new Logging();
        log.setBgroup("kanass");

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);
        content.put("master", user.getName());
        content.put("updateTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        log.setLoggingTemplateId(OpLogTemplateCategory.TEAMWIRE_LOGTEMPLATE_CATEGORYADD);

        LoggingType opLogType = new LoggingType();
        opLogType.setId(OpLogTemplateCategory.TEAMWIRE_LOGTYPE_CATEGORYADD);
        log.setActionType(opLogType);

        log.setModule("category");
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
        content.put("createUserIcon",user.getName().substring( 0, 1));
        log.setContent(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        loggingByTemplService.createLog(log);
    }
    
    @Override
    public String createCategory(@NotNull @Valid Category category) {
        category.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        category.setMaster(user);

        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);
        String categoryId = categoryDao.createCategory(categoryEntity);

        Category category1 = findCategory(categoryId);
        Map<String, String> content = new HashMap<>();
        content.put("categoryId", category1.getId());
        content.put("categoryName", category1.getName());
        content.put("repositoryId", category1.getRepository().getId());
        creatDynamic(content);
        return categoryId;
    }

    @Override
    public void updateCategory(@NotNull @Valid Category category) {
        category.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        category.setMaster(user);

        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);
        if (category.getParentCategory() != null && category.getParentCategory().getId() == "nullString"){
            categoryEntity.setParentCategoryId(null);
            categoryDao.updateCategory(categoryEntity);
        } else {
            categoryDao.updateCategory(categoryEntity);
        }
        String id = category.getId();

        Category category1 = findCategory(id);
        Map<String, String> content = new HashMap<>();
        content.put("categoryId", category1.getId());
        content.put("categoryName", category1.getName());
        content.put("repositoryId", category1.getRepository().getId());

        creatDynamic(content);
    }

    @Override
    public void deleteCategory(@NotNull String id) {
        //删除下面相关联的目录和文档
        
        categoryDao.deleteCategory(id);
    }

    @Override
    public Category findOne(String id) {
        CategoryEntity categoryEntity = categoryDao.findCategory(id);

        Category category = BeanMapper.map(categoryEntity, Category.class);
        return category;
    }

    @Override
    public List<Category> findList(List<String> idList) {
        List<CategoryEntity> categoryEntityList =  categoryDao.findCategoryList(idList);

        List<Category> categoryList =  BeanMapper.mapList(categoryEntityList,Category.class);
        return categoryList;
    }

    @Override
    public Category findCategory(@NotNull String id) {
        Category category = findOne(id);

        joinTemplate.joinQuery(category);
        return category;
    }

    public List<Category> findCategoryList(@NotNull String id) {
        List<CategoryEntity> categoryEntityList = categoryDao.findCategoryDocument(id);
        List<Category> categoryList =  BeanMapper.mapList(categoryEntityList,Category.class);

        joinTemplate.joinQuery(categoryList);
        for (Category category : categoryList) {
            String id1 = category.getId();
            List<Category> categoryList1 = findCategoryList(id1);
            joinTemplate.joinQuery(categoryList);
            ArrayList<Object> categoryObject = new ArrayList<>();
            categoryObject.add(categoryList1);
            if(categoryList1.size() > 0){
                category.setChildren(categoryObject);
            }

            List<DocumentEntity> documentEntityList = categoryDao.findDocumentDocument(id1);
            List<Document> documentList =  BeanMapper.mapList(documentEntityList,Document.class);

            joinTemplate.joinQuery(documentList);
            if(documentList.size() > 0){
                category.setDocuments(documentList);
            }
        }
        return categoryList;
    }
    @Override
    public List<Object> findCategoryDocument(@NotNull String id) {
        List<Object> objects = new ArrayList<>();
//        List<CategoryEntity> categoryEntityList = categoryDao.findCategoryDocument(id);
//        List<Category> categoryList =  BeanMapper.mapList(categoryEntityList,Category.class);
//        joinTemplate.joinQuery(categoryList);
        List<Category> categoryList = findCategoryList(id);
        objects.addAll(categoryList);

        List<DocumentEntity> documentEntityList = categoryDao.findDocumentDocument(id);
        List<Document> documentList =  BeanMapper.mapList(documentEntityList,Document.class);

        joinTemplate.joinQuery(documentList);
        objects.addAll(documentList);

        return objects;
    }

    @Override
    public List<Category> findAllCategory() {
        List<CategoryEntity> categoryEntityList =  categoryDao.findAllCategory();

        List<Category> categoryList =  BeanMapper.mapList(categoryEntityList,Category.class);

        joinTemplate.joinQuery(categoryList);
        return categoryList;
    }

    @Override
    public List<Category> findCategoryList(CategoryQuery categoryQuery) {
        List<CategoryEntity> categoryEntityList = categoryDao.findCategoryList(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(categoryEntityList,Category.class);

        joinTemplate.joinQuery(categoryList);

        return categoryList;
    }

    @Override
    public Pagination<Category> findCategoryPage(CategoryQuery categoryQuery) {

        Pagination<CategoryEntity>  pagination = categoryDao.findCategoryPage(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(pagination.getDataList(),Category.class);

        joinTemplate.joinQuery(categoryList);

        return PaginationBuilder.build(pagination,categoryList);
    }

    @Override
    public List<Object> findCategoryListTree(CategoryQuery categoryQuery) {
        ArrayList<Object> objects = new ArrayList<>();
        //查询符合条件的所有目录
        List<Category> categoryList = this.findCategoryList(categoryQuery);

        List<Document> documentList = documentService.findDocumentList(new DocumentQuery().setRepositoryId(categoryQuery.getRepositoryId()));
        //查找并设置分类下面的文档
        List<Category> categoryMethodList = findCategoryMethodList(categoryList);

        //查询没在目录下main的文档
        List<Document> collect = documentList.stream().filter(a -> ObjectUtils.isEmpty(a.getCategory())).collect(Collectors.toList());
        //查询一级目录
        List<Category> topCategoryList = findTopCategoryList(categoryMethodList);
        //查找并设置子分类列表
        if(topCategoryList != null){
            for(Category topCategory:topCategoryList){
                setChildren(categoryList,topCategory);
            }
        }

        if (!topCategoryList.isEmpty()){
            for(Category topCategory:topCategoryList){
                objects.add(topCategory);
            }

        }
        if(!collect.isEmpty()){
            for(Document document:collect){
                objects.add(document);
            }
        }
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
     * @param parentCategory
     */
    void setChildren(List<Category> matchCategoryList,Category parentCategory){
        List<Category> childCategoryList = matchCategoryList.stream()
                .filter(category -> (category.getParentCategory() != null && category.getParentCategory().getId() != null && category.getParentCategory().getId().equals(parentCategory.getId())))
                .collect(Collectors.toList());

        if(childCategoryList != null && childCategoryList.size() > 0){
            ArrayList<Object> objects = new ArrayList<>();
//            objects.add(childCategoryList);
            if(!parentCategory.getChildren().isEmpty() && parentCategory.getChildren().size() > 0){
                ArrayList<Object> children = parentCategory.getChildren();
                objects.addAll(children);
                objects.addAll(childCategoryList);
            }
            parentCategory.setChildren(objects);

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
            List<Document> repositoryDetailsList = documentService.findDocumentList(documentQuery);

            ArrayList<Object> objects = new ArrayList<>();
            objects.addAll(repositoryDetailsList);
//            category.setDocuments(repositoryDetailsList);
            category.setChildren(objects);
            return category;
        }).collect(Collectors.toList());

        return  categorys;
    }


}