package io.thoughtware.sward.category.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.security.logging.logging.service.LoggingByTempService;
import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.sward.category.entity.WikiCategoryEntity;
import io.thoughtware.sward.category.model.WikiCategoryQuery;
import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.node.service.NodeService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.sward.category.dao.WikiCategoryDao;
import io.thoughtware.sward.document.service.DocumentService;
import io.thoughtware.user.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* CategoryServiceImpl
*/
@Service
@Exporter
public class WikiCategoryServiceImpl implements WikiCategoryService {
    private static Logger logger = LoggerFactory.getLogger(DocumentService.class);
    // 新建锁
    private final Lock lock = new ReentrantLock();
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

    @Autowired
    NodeService nodeService;

    @Value("${base.url:null}")
    String baseUrl;

    @Override
    public String createCategory(@NotNull @Valid WikiCategory wikiCategory) {
        lock.lock();
        String categoryId = new String();
        try {
            Node node = wikiCategory.getNode();
            String nodeId = nodeService.createNode(node);

            wikiCategory.setId(nodeId);
            WikiCategoryEntity wikiCategoryEntity = BeanMapper.map(wikiCategory, WikiCategoryEntity.class);
            categoryId = wikiCategoryDao.createCategory(wikiCategoryEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return categoryId;
    }

    /**
     * 洗数据
     * @param wikiCategory
     */


    @Override
    public void updateCategory(@NotNull @Valid WikiCategory wikiCategory) {
        Node node = wikiCategory.getNode();
        nodeService.updateNode(node);
        if(wikiCategory.getDesc() != null){
            WikiCategoryEntity wikiCategoryEntity = BeanMapper.map(wikiCategory, WikiCategoryEntity.class);
            wikiCategoryDao.updateCategory(wikiCategoryEntity);
        }

    }




    @Override
    public void deleteCategory(@NotNull String id) {
        //删除下面相关联的目录和文档
        nodeService.deleteNode(id);
        wikiCategoryDao.deleteCategory(id);
    }

    @Override
    public void deleteCategoryByIds(Object[] ids) {
        wikiCategoryDao.deleteCategoryByIds(ids);
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
        if(wikiCategory != null){
            Node node = nodeService.findNode(id);
            wikiCategory.setNode(node);
        }

        joinTemplate.joinQuery(wikiCategory);
        return wikiCategory;
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


}