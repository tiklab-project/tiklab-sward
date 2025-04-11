package io.tiklab.sward.support.service;

import io.tiklab.core.page.Page;
import io.tiklab.core.page.Pagination;
import io.tiklab.sward.document.model.DocumentQuery;
import io.tiklab.sward.node.model.Node;
import io.tiklab.sward.node.model.NodeQuery;
import io.tiklab.sward.node.service.NodeService;
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

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    NodeService nodeService;

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


    public <T> TopResponse<T> searchForTop(Class<T> entityClass, Map<String,String> keyword) {
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
        List<WikiDocument> documentList = new ArrayList<>();
        Map map = new HashMap();
        map.put("detailText", keyword);
        TopResponse topResponse = searchForTop(WikiDocument.class,map);
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

        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setName(keyword);
        Page page = new Page();
        page.setPageSize(20);
        nodeQuery.setPageParam(page);
        Pagination<Node> nodePage = nodeService.findNodePage(nodeQuery);
        List<Node> dataList1 = nodePage.getDataList();
        if (dataList1 != null && !dataList1.isEmpty()) {
            for (Node node : dataList1) {
                WikiDocument wikiDocument = documentService.findDocument(node.getId());
                if(wikiDocument != null){
                    documentList.add(wikiDocument);
                }
            }
        }
        List<Object> list = documentList.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(WikiDocument::getId))),
                        ArrayList::new
                ));
        if (list.size() > 10){
            list = list.subList(0, 10);
        }

        Map rpyMap = new HashMap();
        rpyMap.put("name", keyword);
        topResponse = searchForTop(WikiRepository.class,rpyMap);
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
        ObjectHashMap.put("document", list);
        ObjectHashMap.put("wiki", wikiRepositoryList);
        return  ObjectHashMap;
    }


    @Override
    public List<WikiDocument> searchRepositoryDocument(DocumentQuery documentQuery) {
        List<WikiDocument> documentList = new ArrayList<>();
        String name = documentQuery.getName();
        String repositoryId = documentQuery.getRepositoryId();
        Map map = new HashMap();
        map.put("detailText", name);
        map.put("repositoryId", repositoryId);
        TopResponse topResponse = searchForTop(WikiDocument.class,map);
        List dataList = topResponse.getDataList();
        if(!dataList.isEmpty()){
            for (Object documentObject : topResponse.getDataList()) {
                try {
                    Map<String, String> documentObject1 = (Map<String, String>) documentObject;
                    String id = documentObject1.get("id");
                    WikiDocument wikiDocument = documentService.findDocument(id);
                    if(!Objects.isNull(wikiDocument)){
                        documentList.add(wikiDocument);
                    }
                }catch (Exception e) {
                    throw new ApplicationException(e);
                }
            }
        }

        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setName(name);
        nodeQuery.setRepositoryId(repositoryId);
        Page page = new Page();
        page.setPageSize(20);
        nodeQuery.setPageParam(page);
        Pagination<Node> nodePage = nodeService.findNodePage(nodeQuery);
        List<Node> dataList1 = nodePage.getDataList();
        if (dataList1 != null && !dataList1.isEmpty()) {
            for (Node node : dataList1) {
                WikiDocument wikiDocument = documentService.findDocument(node.getId());
                if(wikiDocument != null){
                    documentList.add(wikiDocument);
                }
            }
        }

        List<WikiDocument> list = documentList.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(WikiDocument::getId))),
                        ArrayList::new
                ));
        if (list.size() > 10){
            list = list.subList(0, 10);
        }

        return  list;
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


    // public <T> CountResponse<T> searchForCount(Class<T> entityClass, String keyword) {
    //     return dssClient.searchForCount(entityClass, keyword);
    // }
    //
    //
    // public <T> PageResponse<T> searchForPage(Class<T> entityClass, String keyword, PageCondition pageCondition) {
    //     return dssClient.searchForPage(entityClass, keyword, pageCondition);
    // }
}