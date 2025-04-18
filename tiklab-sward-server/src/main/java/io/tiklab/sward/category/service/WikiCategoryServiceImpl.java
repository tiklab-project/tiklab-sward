package io.tiklab.sward.category.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.sward.category.model.WikiCategory;
import io.tiklab.sward.category.entity.WikiCategoryEntity;
import io.tiklab.sward.category.model.WikiCategoryQuery;
import io.tiklab.sward.document.model.DocumentQuery;
import io.tiklab.sward.node.model.Node;
import io.tiklab.sward.node.model.NodeQuery;
import io.tiklab.sward.node.service.NodeService;
import io.tiklab.sward.support.model.RecentQuery;
import io.tiklab.sward.support.service.RecentService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.sward.category.dao.WikiCategoryDao;
import io.tiklab.sward.document.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
*  目录接口
*/
@Service
@Exporter
public class WikiCategoryServiceImpl implements WikiCategoryService {
    private static Logger logger = LoggerFactory.getLogger(DocumentService.class);
    // 新建锁
    private final Lock lock = new ReentrantLock();

    @Autowired
    WikiCategoryDao wikiCategoryDao;

    @Autowired
    RecentService recentService;


    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    NodeService nodeService;

    @Autowired
    DocumentService documentService;

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
        // 删除所有下级文档, 以及文档关联的数据
        List<Node> allChildrenNodeList = nodeService.findAllChildrenNodeList(id);
        List<Node> categoryList = allChildrenNodeList.stream().filter(item -> item.getType().equals("category")).collect(Collectors.toList());
        List<String> categoryIdList = categoryList.stream().map(item -> item.getId()).collect(Collectors.toList());
        categoryIdList.add(id);
        if(categoryIdList.size() > 0){
            String[] categoryIds = categoryIdList.toArray(new String[categoryIdList.size()]);
            WikiCategoryQuery wikiCategoryQuery = new WikiCategoryQuery();
            wikiCategoryQuery.setIds(categoryIds);
            // 删除包括当前目录在内的所有目录
            batchDeleteCategory(wikiCategoryQuery);
        }


        // 删除所有文档
        allChildrenNodeList.removeAll(categoryIdList);
        List<String> documentIdList = allChildrenNodeList.stream().map(item -> item.getId()).collect(Collectors.toList());
        if(documentIdList.size() > 0){
            String[] documentIds = documentIdList.toArray(new String[documentIdList.size()]);
            DocumentQuery documentQuery = new DocumentQuery();
            documentQuery.setIds(documentIds);
            documentService.batchDeleteDocument(documentQuery);
        }

    }
    @Override
    public void batchDeleteCategory(WikiCategoryQuery wikiCategoryQuery){
        String[] ids = wikiCategoryQuery.getIds();
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WikiCategoryEntity.class)
                .in("id", wikiCategoryQuery.getIds())
                .eq("id", wikiCategoryQuery.getId())
                .get();
        wikiCategoryDao.deleteCategoryByIds(deleteCondition);

        //删除下面相关联的目录和文档
        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setIds(ids);
        nodeService.deleteNodeCondition(nodeQuery);

        // 删除最近点击的
        RecentQuery recentQuery = new RecentQuery();
        recentQuery.setModelIds(ids);
        recentService.deleteRecnetByCondition(recentQuery);
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