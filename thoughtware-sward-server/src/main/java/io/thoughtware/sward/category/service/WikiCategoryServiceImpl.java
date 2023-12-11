package io.thoughtware.sward.category.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.security.logging.service.LoggingByTempService;
import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.category.support.OpLogTemplateCategory;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.sward.category.entity.WikiCategoryEntity;
import io.thoughtware.sward.category.model.WikiCategoryQuery;
import io.thoughtware.sward.document.entity.WikiDocumentEntity;
import io.thoughtware.sward.document.model.WikiDocument;
import io.thoughtware.sward.document.model.DocumentQuery;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.sward.category.dao.WikiCategoryDao;
import io.thoughtware.sward.document.service.DocumentService;
import io.thoughtware.security.logging.model.Logging;
import io.thoughtware.security.logging.model.LoggingType;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
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
public class WikiCategoryServiceImpl implements WikiCategoryService {
    @Autowired
    JpaTemplate jpaTemplate;
    @Autowired
    WikiCategoryDao wikiCategoryDao;

    @Autowired
    UserService userService;

    @Autowired
    LoggingByTempService loggingByTemplService;


    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DocumentService documentService;

    @Value("${base.url:null}")
    String baseUrl;

    void creatDynamic( Map<String, String> content){
        Logging log = new Logging();
        log.setBgroup("sward");

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

        WikiCategory parentWikiCategory = wikiCategory.getParentWikiCategory();
        String parentWikiCategoryId = new String();
        if(parentWikiCategory == null){
            parentWikiCategoryId = null;
        }else {
            parentWikiCategoryId = parentWikiCategory.getId();
        }

        String repositoryId = wikiCategory.getWikiRepository().getId();
        Integer brotherNum = documentService.getBrotherNum(repositoryId, parentWikiCategoryId);
        wikiCategory.setSort(brotherNum);

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

    /**
     * 洗数据
     * @param wikiCategory
     */
    @Override
    public void updateCategoryInit(@NotNull @Valid WikiCategory wikiCategory){
        WikiCategoryEntity wikiCategoryEntity = BeanMapper.map(wikiCategory, WikiCategoryEntity.class);
        wikiCategoryDao.updateCategory(wikiCategoryEntity);
    }

    @Override
    public void updateCategory(@NotNull @Valid WikiCategory wikiCategory) {
        wikiCategory.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        wikiCategory.setMaster(user);
        updateSort(wikiCategory, "category");
        WikiCategoryEntity wikiCategoryEntity = BeanMapper.map(wikiCategory, WikiCategoryEntity.class);
        if (wikiCategory.getParentWikiCategory() != null && wikiCategory.getParentWikiCategory().getId() == "nullString"){
            wikiCategoryEntity.setParentCategoryId(null);
            wikiCategoryDao.updateCategory(wikiCategoryEntity);
        } else {
            wikiCategoryDao.updateCategory(wikiCategoryEntity);
        }
        // 更新排序
        String id = wikiCategory.getId();

        WikiCategory wikiCategory1 = findCategory(id);
        Map<String, String> content = new HashMap<>();
        content.put("categoryId", wikiCategory1.getId());
        content.put("categoryName", wikiCategory1.getName());
        content.put("repositoryId", wikiCategory1.getWikiRepository().getId());
        creatDynamic(content);
    }

    public void updateSort(@NotNull @Valid WikiCategory wikiCategory, String type){
        Integer sort = wikiCategory.getSort();
        if(sort != null){
            String repositoryId = wikiCategory.getWikiRepository().getId();
            String parentWikiCategoryId = new String();
            WikiCategory parentWikiCategory = wikiCategory.getParentWikiCategory();
            if(parentWikiCategory != null){
                parentWikiCategoryId = wikiCategory.getParentWikiCategory().getId();
            }

            String oldParentId = wikiCategory.getOldParentId();
            Integer oldSort = wikiCategory.getOldSort();
            boolean haveParent =  parentWikiCategory != null && parentWikiCategoryId != null && !parentWikiCategoryId.equals("nullString");
            boolean haveOldParent = (oldParentId != null && !oldParentId.equals("nullString") );
            // 知识库到知识库
            if(!haveParent && !haveOldParent){
                wikiCategoryDao.updateOnRepository(repositoryId, oldSort, sort );
            }
            // 知识库到目录
            if(haveParent && !haveOldParent){
                wikiCategoryDao.updateRepositoryToCategory(repositoryId, parentWikiCategoryId, oldSort, sort );
            }

            // 目录到知识库
            if(!haveParent && haveOldParent){
                wikiCategoryDao.updateCategoryToRepository(repositoryId, parentWikiCategoryId, oldSort, sort );
            }

            // 目录到目录
            if(haveParent && haveOldParent && !parentWikiCategoryId.equals(oldParentId)){
                wikiCategoryDao.updateOnCategory(oldParentId, parentWikiCategoryId, oldSort, sort );

            }

            // 本目录到本目录
            if(haveParent && haveOldParent && parentWikiCategoryId.equals(oldParentId)){
                wikiCategoryDao.updateCurrentCategory(parentWikiCategoryId, oldSort, sort );
            }

            if(type.equals("category") && !(haveParent && haveOldParent && parentWikiCategoryId.equals(oldParentId)) ){
                Integer oldDimension = wikiCategory.getOldDimension();
                Integer dimension = wikiCategory.getDimension();
                Integer distance = dimension - oldDimension;
                if(distance != 0){
                    updateChildrenDimension(wikiCategory);
                }

            }


        }
    }

    public void updateChildrenDimension(@NotNull @Valid WikiCategory wikiCategory){
        HashMap<String, List<String>> categoryChildren = wikiCategoryDao.findCategoryChildren(wikiCategory.getId());
        Integer oldDimension = wikiCategory.getOldDimension();
        Integer dimension = wikiCategory.getDimension();
        Integer distance = dimension - oldDimension;
        List<String> document = categoryChildren.get("document");
        String documentIds = new String();
        if(document.size() > 0){
            documentIds = "(" + document.stream().map(item -> "'" + item + "'").
                    collect(Collectors.joining(", ")) + ")";
        }else {
            documentIds = null;
        }
        List<String> category = categoryChildren.get("category");
        String categoryIds = new String();
        if(category.size() >0 ){
            categoryIds = "(" + category.stream().map(item -> "'" + item + "'").
                    collect(Collectors.joining(", ")) + ")";
        }else {
            categoryIds = null;
        }
        wikiCategoryDao.updateDimension(documentIds, categoryIds, distance);

    }
    @Override
    public void deleteCategory(@NotNull String id) {
        //删除下面相关联的目录和文档
        wikiCategoryDao.deleteCategory(id);
    }

    public void deleteCategoryAndSort(@NotNull @Valid WikiCategory wikiCategory) {
        //删除下面相关联的目录和文档
        String id = wikiCategory.getId();
        String repositoryId = wikiCategory.getWikiRepository().getId();
        WikiCategory parentWikiCategory = wikiCategory.getParentWikiCategory();
        Integer sort = wikiCategory.getSort();
        String parentWikiCategoryId = new String();
        if(parentWikiCategory != null){
            parentWikiCategoryId = parentWikiCategory.getId();
        }
        wikiCategoryDao.updateSortAfterDelete(repositoryId, parentWikiCategoryId, sort);
        wikiCategoryDao.deleteCategory(id);
    }
    @Override
    public void updateSortAfterDelete(@NotNull String repositoryId, String parentWikiCategoryId, Integer sort){
        wikiCategoryDao.updateSortAfterDelete(repositoryId, parentWikiCategoryId, sort);
    }

    @Override
    public HashMap<String, List<String>> findCategoryChildren(@NotNull String parentWikiCategoryId) {
        //删除下面相关联的目录和文档
        HashMap<String, List<String>> categoryChildren = wikiCategoryDao.findCategoryChildren(parentWikiCategoryId);
        return categoryChildren;
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

    public List<Map<String, Object>> findCategoryByRepositoryIds(String repositoryIds) {
        String sql = "select repository_id from wiki_category t where t.repository_id in "+ repositoryIds;
        List<Map<String, Object>> categoryList = this.jpaTemplate.getJdbcTemplate().queryForList(sql);
        return categoryList;
    }

    @Override
    public Pagination<WikiCategory> findCategoryPage(WikiCategoryQuery wikiCategoryQuery) {

        Pagination<WikiCategoryEntity>  pagination = wikiCategoryDao.findCategoryPage(wikiCategoryQuery);

        List<WikiCategory> wikiCategoryList = BeanMapper.mapList(pagination.getDataList(), WikiCategory.class);

        joinTemplate.joinQuery(wikiCategoryList);

        return PaginationBuilder.build(pagination, wikiCategoryList);
    }

//    @Override
    public List<Object> findCategoryListTree1(WikiCategoryQuery wikiCategoryQuery) {
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

    @Override
    public List<Object> findCategoryListTree(WikiCategoryQuery wikiCategoryQuery) {
        String parentWikiCategory = wikiCategoryQuery.getParentWikiCategory();
        //查询第一次的所有目录
        List<WikiCategory> wikiCategoryList = this.findCategoryList(wikiCategoryQuery);

        DocumentQuery documentQuery = new DocumentQuery();
        // 查找某个目录下的子级查找的时候，要带上要查找第一级的目录
        if(parentWikiCategory != null && wikiCategoryList.size() > 0){
            List<String> wikiCategoryIdList = wikiCategoryList.stream().map(item -> item.getId() ).
                    collect(Collectors.toList());
            wikiCategoryIdList.add(wikiCategoryQuery.getParentWikiCategory());
            String[] wikiCategoryIds = new String[wikiCategoryIdList.size()];
            wikiCategoryIds = wikiCategoryIdList.toArray(wikiCategoryIds);
            //查找子级的子级
            wikiCategoryQuery.setParentWikiCategorys(wikiCategoryIds);
            wikiCategoryQuery.setParentWikiCategory(null);
            wikiCategoryList = this.findCategoryList(wikiCategoryQuery);
            documentQuery.setCategoryIds(wikiCategoryIds);
        }else {
            documentQuery.setCategoryId(parentWikiCategory);
        }
        //查询符合条件的所有文档
        documentQuery.setRepositoryId(wikiCategoryQuery.getRepositoryId());
        documentQuery.setParentWikiCategoryIsNull(wikiCategoryQuery.getParentWikiCategoryIsNull());
        documentQuery.setDimension(wikiCategoryQuery.getDimension());
        documentQuery.setDimensions(wikiCategoryQuery.getDimensions());
        List<WikiDocument> wikiDocumentList = documentService.findDocumentList(documentQuery);
        ArrayList<Object> objects = setTreeBySort(wikiCategoryList, wikiDocumentList, parentWikiCategory);
        return  objects;
    }


    /**
     * 点击文档或者目录从概况页跳转获取当前文档的上级树
     * @return
     */
    @Override
    public List<Object> findCategoryListTreeById(String id, String treePath){
        List<Object> objects = new ArrayList<>();
        if(treePath == null){
            return objects;
        }else {
            treePath = treePath + id + ";";
            String[] categoryIds = treePath.split(";");
            List<String> categoryIdList = Arrays.asList(categoryIds);

            // 获取第二级极以下目录
            WikiCategoryQuery wikiCategoryQuery = new WikiCategoryQuery();
            wikiCategoryQuery.setParentWikiCategorys(categoryIds);
            List<WikiCategory> categoryList = findCategoryList(wikiCategoryQuery);

            // 获取第一级的目录
            String firstIds = categoryIdList.get(0);
            WikiCategory category = findCategory(firstIds);
            categoryList.add(category);

            DocumentQuery documentQuery = new DocumentQuery();
            documentQuery.setCategoryIds(categoryIds);
            List<WikiDocument> documentList = documentService.findDocumentList(documentQuery);

            // 拼接数组
            objects = setTreeBySort(categoryList, documentList, null);
        }
        return objects;
    }
    public ArrayList<Object> setTreeBySort(List<WikiCategory> wikiCategoryList, List<WikiDocument> wikiDocumentList, String parentWikiCategory){
        // 按照排序组装树
        ArrayList<Object> objects = new ArrayList<>();
        List<WikiCategory> wikiCategories = new ArrayList<>();
        if(parentWikiCategory == null){
            wikiCategories = wikiCategoryList.stream().filter(wikiCategory -> ObjectUtils.isEmpty(wikiCategory.getParentWikiCategory())).collect(Collectors.toList());
        }else {
            wikiCategories = wikiCategoryList.stream().filter(wikiCategory -> !ObjectUtils.isEmpty(wikiCategory.getParentWikiCategory()) && wikiCategory.getParentWikiCategory().getId().equals(parentWikiCategory)).collect(Collectors.toList());
        }

        List<WikiDocument> wikiDocuments = new ArrayList<>();
        if(parentWikiCategory == null){
            wikiDocuments = wikiDocumentList.stream().filter(wikiCategory -> ObjectUtils.isEmpty(wikiCategory.getWikiCategory())).collect(Collectors.toList());
        }else {
            wikiDocuments = wikiDocumentList.stream().filter(wikiCategory -> !ObjectUtils.isEmpty(wikiCategory.getWikiCategory()) && wikiCategory.getWikiCategory().getId().equals(parentWikiCategory)).collect(Collectors.toList());
        }

        HashMap<Integer, Object> sortMap = new HashMap<>();
        if(wikiCategories.size() > 0 ){
            for (WikiCategory wikiCategory : wikiCategories) {
                Integer sort = wikiCategory.getSort();
                sortMap.put(sort, wikiCategory);
                String id = wikiCategory.getId();
                ArrayList<Object> children = setTreeBySort(wikiCategoryList, wikiDocumentList, id);
                wikiCategory.setChildren(children);
            }
        }


        if(wikiDocuments.size() > 0){
            for (WikiDocument wikiDocument : wikiDocuments) {
                Integer sort = wikiDocument.getSort();
                sortMap.put(sort, wikiDocument);
            }
        }

        Set<Integer> sorts = sortMap.keySet();
        System.out.println(sorts);
        Set<Integer> sortedSet = new TreeSet<>(sorts);
        System.out.println(sortedSet);
        for(Integer i:sortedSet){
            Object o = sortMap.get(i);
            objects.add(o);
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