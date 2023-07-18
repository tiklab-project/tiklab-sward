package io.tiklab.kanass.category.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.category.entity.WikiCategoryEntity;
import io.tiklab.kanass.category.model.WikiCategory;
import io.tiklab.kanass.category.model.WikiCategoryQuery;
import io.tiklab.kanass.category.support.OpLogTemplateCategory;
import io.tiklab.kanass.document.entity.WikiDocumentEntity;
import io.tiklab.kanass.document.model.WikiDocument;
import io.tiklab.kanass.document.model.DocumentQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.kanass.category.dao.WikiCategoryDao;
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
public class WikiWikiCategoryServiceImpl implements WikiCategoryService {

    @Autowired
    WikiCategoryDao wikiCategoryDao;

    @Autowired
    UserService userService;

    @Autowired
    LoggingByTemplService loggingByTemplService;


    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DocumentService documentService;

    @Value("${base.url:null}")
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
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        content.put("createUserIcon",user.getName().substring( 0, 1));
        log.setContent(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        loggingByTemplService.createLog(log);
    }
    
    @Override
    public String createCategory(@NotNull @Valid WikiCategory wikiCategory) {
        wikiCategory.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        wikiCategory.setMaster(user);

        WikiCategoryEntity wikiCategoryEntity = BeanMapper.map(wikiCategory, WikiCategoryEntity.class);
        String categoryId = wikiCategoryDao.createCategory(wikiCategoryEntity);

        WikiCategory wikiCategory1 = findCategory(categoryId);
        Map<String, String> content = new HashMap<>();
        content.put("categoryId", wikiCategory1.getId());
        content.put("categoryName", wikiCategory1.getName());
        content.put("repositoryId", wikiCategory1.getWikiRepository().getId());
        creatDynamic(content);
        return categoryId;
    }

    @Override
    public void updateCategory(@NotNull @Valid WikiCategory wikiCategory) {
        wikiCategory.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        wikiCategory.setMaster(user);

        WikiCategoryEntity wikiCategoryEntity = BeanMapper.map(wikiCategory, WikiCategoryEntity.class);
        if (wikiCategory.getParentWikiCategory() != null && wikiCategory.getParentWikiCategory().getId() == "nullString"){
            wikiCategoryEntity.setParentCategoryId(null);
            wikiCategoryDao.updateCategory(wikiCategoryEntity);
        } else {
            wikiCategoryDao.updateCategory(wikiCategoryEntity);
        }
        String id = wikiCategory.getId();

        WikiCategory wikiCategory1 = findCategory(id);
        Map<String, String> content = new HashMap<>();
        content.put("categoryId", wikiCategory1.getId());
        content.put("categoryName", wikiCategory1.getName());
        content.put("repositoryId", wikiCategory1.getParentWikiCategory().getId());

        creatDynamic(content);
    }

    @Override
    public void deleteCategory(@NotNull String id) {
        //删除下面相关联的目录和文档
        
        wikiCategoryDao.deleteCategory(id);
    }

    @Override
    public WikiCategory findOne(String id) {
        WikiCategoryEntity wikiCategoryEntity = wikiCategoryDao.findCategory(id);

        WikiCategory wikiCategory = BeanMapper.map(wikiCategoryEntity, WikiCategory.class);
        return wikiCategory;
    }

    @Override
    public List<WikiCategory> findList(List<String> idList) {
        List<WikiCategoryEntity> wikiCategoryEntityList =  wikiCategoryDao.findCategoryList(idList);

        List<WikiCategory> wikiCategoryList =  BeanMapper.mapList(wikiCategoryEntityList, WikiCategory.class);
        return wikiCategoryList;
    }

    @Override
    public WikiCategory findCategory(@NotNull String id) {
        WikiCategory wikiCategory = findOne(id);

        joinTemplate.joinQuery(wikiCategory);
        return wikiCategory;
    }

    public List<WikiCategory> findCategoryList(@NotNull String id) {
        List<WikiCategoryEntity> wikiCategoryEntityList = wikiCategoryDao.findCategoryDocument(id);
        List<WikiCategory> wikiCategoryList =  BeanMapper.mapList(wikiCategoryEntityList, WikiCategory.class);

        joinTemplate.joinQuery(wikiCategoryList);
        for (WikiCategory wikiCategory : wikiCategoryList) {
            String id1 = wikiCategory.getId();
            List<WikiCategory> wikiCategoryList1 = findCategoryList(id1);
            joinTemplate.joinQuery(wikiCategoryList);
            ArrayList<Object> categoryObject = new ArrayList<>();
            categoryObject.add(wikiCategoryList1);
            if(wikiCategoryList1.size() > 0){
                wikiCategory.setChildren(categoryObject);
            }

            List<WikiDocumentEntity> wikiDocumentEntityList = wikiCategoryDao.findDocumentDocument(id1);
            List<WikiDocument> wikiDocumentList =  BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);

            joinTemplate.joinQuery(wikiDocumentList);
            if(wikiDocumentList.size() > 0){
                wikiCategory.setDocuments(wikiDocumentList);
            }
        }
        return wikiCategoryList;
    }
    @Override
    public List<Object> findCategoryDocument(@NotNull String id) {
        List<Object> objects = new ArrayList<>();
//        List<CategoryEntity> categoryEntityList = categoryDao.findCategoryDocument(id);
//        List<Category> categoryList =  BeanMapper.mapList(categoryEntityList,Category.class);
//        joinTemplate.joinQuery(categoryList);
        List<WikiCategory> wikiCategoryList = findCategoryList(id);
        objects.addAll(wikiCategoryList);

        List<WikiDocumentEntity> wikiDocumentEntityList = wikiCategoryDao.findDocumentDocument(id);
        List<WikiDocument> wikiDocumentList =  BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);
        objects.addAll(wikiDocumentList);

        return objects;
    }

    @Override
    public List<WikiCategory> findAllCategory() {
        List<WikiCategoryEntity> wikiCategoryEntityList =  wikiCategoryDao.findAllCategory();

        List<WikiCategory> wikiCategoryList =  BeanMapper.mapList(wikiCategoryEntityList, WikiCategory.class);

        joinTemplate.joinQuery(wikiCategoryList);
        return wikiCategoryList;
    }

    @Override
    public List<WikiCategory> findCategoryList(WikiCategoryQuery wikiCategoryQuery) {
        List<WikiCategoryEntity> wikiCategoryEntityList = wikiCategoryDao.findCategoryList(wikiCategoryQuery);

        List<WikiCategory> wikiCategoryList = BeanMapper.mapList(wikiCategoryEntityList, WikiCategory.class);

        joinTemplate.joinQuery(wikiCategoryList);

        return wikiCategoryList;
    }

    @Override
    public Pagination<WikiCategory> findCategoryPage(WikiCategoryQuery wikiCategoryQuery) {

        Pagination<WikiCategoryEntity>  pagination = wikiCategoryDao.findCategoryPage(wikiCategoryQuery);

        List<WikiCategory> wikiCategoryList = BeanMapper.mapList(pagination.getDataList(), WikiCategory.class);

        joinTemplate.joinQuery(wikiCategoryList);

        return PaginationBuilder.build(pagination, wikiCategoryList);
    }

    @Override
    public List<Object> findCategoryListTree(WikiCategoryQuery wikiCategoryQuery) {
        ArrayList<Object> objects = new ArrayList<>();
        //查询符合条件的所有目录
        List<WikiCategory> wikiCategoryList = this.findCategoryList(wikiCategoryQuery);

        List<WikiDocument> wikiDocumentList = documentService.findDocumentList(new DocumentQuery().setRepositoryId(wikiCategoryQuery.getRepositoryId()));
        //查找并设置分类下面的文档
        List<WikiCategory> wikiCategoryMethodList = findCategoryMethodList(wikiCategoryList);

        //查询没在目录下main的文档
        List<WikiDocument> collect = wikiDocumentList.stream().filter(a -> ObjectUtils.isEmpty(a.getWikiCategory())).collect(Collectors.toList());
        //查询一级目录
        List<WikiCategory> topWikiCategoryList = findTopCategoryList(wikiCategoryMethodList);
        //查找并设置子分类列表
        if(topWikiCategoryList != null){
            for(WikiCategory topWikiCategory : topWikiCategoryList){
                setChildren(wikiCategoryList, topWikiCategory);
            }
        }

        if (!topWikiCategoryList.isEmpty()){
            for(WikiCategory topWikiCategory : topWikiCategoryList){
                objects.add(topWikiCategory);
            }

        }
        if(!collect.isEmpty()){
            for(WikiDocument wikiDocument :collect){
                objects.add(wikiDocument);
            }
        }
        return objects;
    }



    /**
     * 查找第一级分类目录
     * @param wikiCategoryList
     * @return
     */
    List<WikiCategory> findTopCategoryList(List<WikiCategory> wikiCategoryList){
        return wikiCategoryList.stream()
                .filter(category -> (category.getParentWikiCategory() == null || ObjectUtils.isEmpty(category.getParentWikiCategory().getId())))
                .collect(Collectors.toList());
    }

    /**
     * 查找并设置下级分类列表
     * @param matchWikiCategoryList
     * @param parentWikiCategory
     */
    void setChildren(List<WikiCategory> matchWikiCategoryList, WikiCategory parentWikiCategory){
        List<WikiCategory> childWikiCategoryList = matchWikiCategoryList.stream()
                .filter(category -> (category.getParentWikiCategory() != null && category.getParentWikiCategory().getId() != null && category.getParentWikiCategory().getId().equals(parentWikiCategory.getId())))
                .collect(Collectors.toList());

        if(childWikiCategoryList != null && childWikiCategoryList.size() > 0){
            ArrayList<Object> objects = new ArrayList<>();
//            objects.add(childCategoryList);
            if(!parentWikiCategory.getChildren().isEmpty() && parentWikiCategory.getChildren().size() > 0){
                ArrayList<Object> children = parentWikiCategory.getChildren();
                objects.addAll(children);
                objects.addAll(childWikiCategoryList);
            }else {
                objects.addAll(childWikiCategoryList);
            }
            parentWikiCategory.setChildren(objects);

            for(WikiCategory wikiCategory : childWikiCategoryList){
                setChildren(matchWikiCategoryList, wikiCategory);
            }
        }
    }

  /**
     * 查询 知识库相关的内容
     * @param wikiCategoryList
     * @return
     */
    List<WikiCategory> findCategoryMethodList(List<WikiCategory> wikiCategoryList){

        List<WikiCategory> wikiCategories = wikiCategoryList.stream().map(category -> {
            DocumentQuery documentQuery = new DocumentQuery();
            documentQuery.setCategoryId(category.getId());
            List<WikiDocument> repositoryDetailsList = documentService.findDocumentList(documentQuery);

            ArrayList<Object> objects = new ArrayList<>();
            objects.addAll(repositoryDetailsList);
//            category.setDocuments(repositoryDetailsList);
            category.setChildren(objects);
            return category;
        }).collect(Collectors.toList());

        return wikiCategories;
    }


}