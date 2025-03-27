package io.tiklab.sward.support.controller;


import io.tiklab.sward.document.model.DocumentQuery;
import io.tiklab.sward.support.service.SearchService;
import io.tiklab.core.Result;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.sward.document.model.WikiDocument;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局搜索控制器
 */
@RestController
@RequestMapping("/search")
@Api(name = "SearchController",desc = "搜索管理")
public class SearchController {

    private static Logger logger = LoggerFactory.getLogger(SearchController.class);

    //索引->实体类映射表
    private static Map<String,Class<?>> indexMapping = new HashMap<>();

    @Autowired
    SearchService searchService;


    @RequestMapping(path="/rebuild",method = RequestMethod.POST)
    public Result<Void> rebuild(){
        searchService.rebuild();
        return Result.ok();
    }


    @RequestMapping(path="/get",method = RequestMethod.POST)
    //@ApiMethod(name = "get",desc = "根据ID查找")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<Map<String,Object>> get(@NotNull String id){
        Map<String,Object> map = searchService.get(WikiDocument.class,id);

        return Result.ok(map);
    }


    @RequestMapping(path="/searchForTop",method = RequestMethod.POST)
    //@ApiMethod(name = "searchForTop",desc = "根据关键字搜索")
    //@ApiParam(name = "keyword",desc = "关键字",required = true)
    public Result<HashMap<String, List<Object>>> searchForTop(@NotNull String keyword){
        HashMap<String, List<Object>> stringListHashMap = searchService.searchWikiDocumentForTop(keyword);

        return Result.ok(stringListHashMap);
    }

    @RequestMapping(path="/searchRepositoryDocument",method = RequestMethod.POST)
    public Result<List<WikiDocument>> searchForTop(@RequestBody @NotNull @Valid DocumentQuery documentQuery){
        List<WikiDocument> wikiDocuments = searchService.searchRepositoryDocument(documentQuery);

        return Result.ok(wikiDocuments);
    }


//    @RequestMapping(path="/searchForCount",method = RequestMethod.POST)
//    //@ApiMethod(name = "searchForCount",desc = "统计搜索结果")
//    //@ApiParam(name = "keyword",desc = "关键字",required = true)
//    public Result<AllCountResponse> searchForCount(@NotNull String keyword){
//        AllCountResponse allCountResponse = new AllCountResponse();
//
//        //搜索项目
//        CountResponse countResponse = searchService.searchForCount(WikiRepository.class,keyword);
//        allCountResponse.getResponseList().add(countResponse);
//
//        //搜索事项
//        countResponse = searchService.searchForCount(WikiDocument.class,keyword);
//        allCountResponse.getResponseList().add(countResponse);
//
//        return Result.ok(allCountResponse);
//    }


//    @RequestMapping(path="/searchForPage",method = RequestMethod.POST)
//    //@ApiMethod(name = "searchForPage",desc = "按索引名称、关键字分页搜索")
//    //@ApiParam(name = "pageRequest",desc = "分页搜索条件",required = true)
//    public Result<PageResponse> searchForPage(@RequestBody @Valid @NotNull PageRequest pageRequest){
//        String index = pageRequest.getIndex();
//        Class entityClass = getEntityClass(index);
//        if(entityClass == null){
//            throw new ApplicationException("not found index:" + index);
//        }
//        String keyword = pageRequest.getKeyword();
//        PageCondition pageCondition = pageRequest.getPageCondition();
//        if(pageCondition == null){
//            pageCondition = PageCondition.instance();
//        }
//
//        //分页搜索
//        PageResponse pageResponse = searchService.searchForPage(entityClass,keyword,pageCondition);
//        return Result.ok(pageResponse);
//    }

    @RequestMapping(path="/initIndex",method = RequestMethod.POST)
    //@ApiMethod(name = "initIndex",desc = "初始化索引")
    public Result<Void> initIndex(){
        searchService.initIndex();

        return Result.ok();
    }

    /**
     * 根据索引名称查找对应的实体类
     * @param index
     * @return
     */
    Class getEntityClass(String index){
        Class entityClass = indexMapping.get(index);
        if(entityClass == null){
            if("repository".equalsIgnoreCase(index)){
                indexMapping.put(index, WikiRepository.class);
            }else if("document".equalsIgnoreCase(index)){
                indexMapping.put(index, WikiDocument.class);
            }else{
                throw new ApplicationException("unsupport index type:" + index);
            }
            entityClass = indexMapping.get(index);
        }
        return entityClass;
    }

}
