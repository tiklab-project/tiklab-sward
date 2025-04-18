package io.tiklab.sward.support.service;

import io.tiklab.sward.document.model.DocumentQuery;
import io.tiklab.sward.repository.service.WikiRepositoryService;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.dss.client.DssClient;
import io.tiklab.dss.common.document.model.CountResponse;
import io.tiklab.dss.common.document.model.PageCondition;
import io.tiklab.dss.common.document.model.PageResponse;
import io.tiklab.dss.common.document.model.TopResponse;
import io.tiklab.sward.document.model.WikiDocument;
import io.tiklab.sward.document.service.DocumentService;
import io.tiklab.sward.repository.model.WikiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 全局搜索服务
*/
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    DssClient dssClient;

    @Autowired
    WikiRepositoryService wikiRepositoryService;

    @Autowired
    DocumentService documentService;

    @Override
    public void rebuild() {
        //删除索引
        deleteIndex();

        //构建索引
        buildIndex();
    }

    /**
     * 删除索引
     */
    void deleteIndex(){
        dssClient.deleteAll(WikiRepository.class);
        dssClient.deleteAll(WikiDocument.class);
    }

    /**
     * 构建索引
     */
    void buildIndex(){
        //构建项目索引
        List<WikiRepository> repositoryList = wikiRepositoryService.findAllRepository();
        if(repositoryList != null && !repositoryList.isEmpty()){
            dssClient.saveBatch(repositoryList);
        }

        //构建事项索引
        List<WikiDocument> wikiDocumentList = documentService.findAllDocument();
        if(wikiDocumentList != null || !wikiDocumentList.isEmpty()){
            dssClient.saveBatch(wikiDocumentList);
        }
    }

    @Override
    public <T> void save(T entity) {
        dssClient.save(entity);
    }

    @Override
    public <T> Map<String, Object> get(Class<T> entityClass, String id) {
        return dssClient.findOne(entityClass, id);
    }


    public <T> TopResponse<T> searchForTop(Class<T> entityClass, String keyword) {
        return dssClient.searchForTop(entityClass,keyword);
    }

    /**
     * 根据关键字查找文档与知识库
     * @param keyword
     * @return
     */
    @Override
    public HashMap<String, List<Object>> searchWikiDocumentForTop(String keyword) {
        HashMap<String, List<Object>> ObjectHashMap = new HashMap<>();
        List<Object> documentList = new ArrayList<>();
        TopResponse topResponse = searchForTop(WikiDocument.class,keyword);
        List dataList = topResponse.getDataList();
        if(!dataList.isEmpty()){
            for (Object documentObject : topResponse.getDataList()) {
                try {
                    Map<String, String> documentObject1 = (Map<String, String>) documentObject;
                    String id = documentObject1.get("id");
                    WikiDocument wikiDocument = documentService.findDocument(id);
                    if(wikiDocument != null){
                        documentList.add(wikiDocument);
                    }
                }catch (Exception e) {
                    throw new ApplicationException(e);
                }
            }
        }

        topResponse = searchForTop(WikiRepository.class,keyword);
        dataList = topResponse.getDataList();
        List<Object> wikiRepositoryList = new ArrayList<>();
        if(!dataList.isEmpty()){
            for (Object wikiObject : topResponse.getDataList()) {
                try {
                    Map<String, String> wikiObject1 = (Map<String, String>) wikiObject;
                    String id = wikiObject1.get("id");
                    WikiRepository wikiRepository = wikiRepositoryService.findRepository(id);
                    if(wikiRepository != null){
                        wikiRepositoryList.add(wikiRepository);
                    }
                }catch (Exception e) {
                    throw new ApplicationException(e);
                }
            }
        }
        ObjectHashMap.put("document", documentList);
        ObjectHashMap.put("wiki", wikiRepositoryList);
        return  ObjectHashMap;
    }

    @Override
    public List<WikiDocument> searchRepositoryDocument(DocumentQuery documentQuery) {
        List<WikiDocument> documentList = new ArrayList<WikiDocument>();
        String name = documentQuery.getName();
        String repositoryId = documentQuery.getRepositoryId();
        TopResponse topResponse = searchForTop(WikiDocument.class,name);
        List dataList = topResponse.getDataList();
        if(!dataList.isEmpty()){
            for (Object documentObject : topResponse.getDataList()) {
                try {
                    Map<String, String> documentObject1 = (Map<String, String>) documentObject;
                    String id = documentObject1.get("id");
                    WikiDocument wikiDocument = documentService.findDocument(id);
                    if(wikiDocument != null && wikiDocument.getNode().getWikiRepository().getId().equals(repositoryId)){
                        documentList.add(wikiDocument);
                    }
                }catch (Exception e) {
                    throw new ApplicationException(e);
                }
            }
        }

        return  documentList;
    }

    @Override
    public void initIndex() {
        dssClient.deleteAll(WikiRepository.class);
        dssClient.deleteAll(WikiDocument.class);

        List<WikiRepository> allRepository = wikiRepositoryService.findAllRepository();
        for (WikiRepository wikiRepository : allRepository) {
            dssClient.save(wikiRepository);
        }

        List<WikiDocument> allDocument = documentService.findAllDocument();
        for (WikiDocument wikiDocument : allDocument) {
            dssClient.save(wikiDocument);
        }

    }


    public <T> CountResponse<T> searchForCount(Class<T> entityClass, String keyword) {
        return dssClient.searchForCount(entityClass, keyword);
    }


    public <T> PageResponse<T> searchForPage(Class<T> entityClass, String keyword, PageCondition pageCondition) {
        return dssClient.searchForPage(entityClass, keyword, pageCondition);
    }
}