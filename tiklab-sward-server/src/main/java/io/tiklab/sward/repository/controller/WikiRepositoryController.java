package io.tiklab.sward.repository.controller;

import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.sward.repository.model.WikiRepositoryQuery;
import io.tiklab.sward.repository.service.WikiRepositoryService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;


/**
 * @pi.protocol: http
 * @pi.groupName: 知识库控制器
 */
@RestController
@RequestMapping("/repository")
@Api(name = "WikiRepositoryController",desc = "")
public class WikiRepositoryController {

    private static Logger logger = LoggerFactory.getLogger(WikiRepositoryController.class);

    @Autowired
    private WikiRepositoryService wikiRepositoryService;

    /**
     * @pi.name:创建知识库
     * @pi.url:/repository/createRepository
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WikiRepository
     */
    @RequestMapping(path="/createRepository",method = RequestMethod.POST)
    @ApiMethod(name = "createRepository",desc = "创建知识库")
    @ApiParam(name = "wikiRepository",desc = "wikiRepository",required = true)
    public Result<String> createRepository(@RequestBody @NotNull @Valid WikiRepository wikiRepository){
        String id = wikiRepositoryService.createRepository(wikiRepository);

        return Result.ok(id);
    }

    /**
     * @pi.name:更新项目
     * @pi.url:/repository/updateRepository
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WikiRepository
     */
    @RequestMapping(path="/updateRepository",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepository",desc = "更新知识库")
    @ApiParam(name = "wikiRepository",desc = "wikiRepository",required = true)
    public Result<Void> updateRepository(@RequestBody @NotNull @Valid WikiRepository wikiRepository){
        wikiRepositoryService.updateRepository(wikiRepository);


        return Result.ok();
    }

    /**
     * @pi.name:删除知识库
     * @pi.url:/repository/deleteRepository
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/deleteRepository",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepository",desc = "删除知识库")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRepository(@NotNull String id){
        wikiRepositoryService.deleteRepository(id);

        return Result.ok();
    }

    /**
     * @pi.name:根据查找知识库
     * @pi.url:/repository/findRepository
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/findRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findRepository",desc = "根据Id查询知识库")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiRepository> findRepository(@NotNull String id){
        WikiRepository wikiRepository = wikiRepositoryService.findRepository(id);

        return Result.ok(wikiRepository);
    }


    @RequestMapping(path="/findList",method = RequestMethod.POST)
    //@ApiMethod(name = "findList",desc = "findRepository")
    //@ApiParam(name = "idList",desc = "idList",required = true)
    public Result<WikiRepository> findRepository(@RequestBody @NotNull @Valid List<String> idList){
        List<WikiRepository> wikiRepositoryList = wikiRepositoryService.findList(idList);

        return Result.ok(wikiRepositoryList);
    }

    /**
     * @pi.name:查找所有知识库
     * @pi.url:/repository/findAllRepository
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepository",desc = "查找所有知识库")
    public Result<List<WikiRepository>> findAllRepository(){
        List<WikiRepository> wikiRepositoryList = wikiRepositoryService.findAllRepository();

        return Result.ok(wikiRepositoryList);
    }

    /**
     * @pi.name:根据当前用户可查看的所有知识库
     * @pi.url:/repository/findRepositoryListByUser
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model= moWikiRepositoryQuery
     */
    @RequestMapping(path = "/findRepositoryListByUser",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryListByUser",desc = "根据当前用户可查看的所有知识库")
    @ApiParam(name = "wikiRepositoryQuery",desc = "wikiRepositoryQuery",required = true)
    public Result<List<WikiRepository>> findRepositoryListByUser(@RequestBody @Valid @NotNull WikiRepositoryQuery wikiRepositoryQuery){
        List<WikiRepository> wikiRepositoryList = wikiRepositoryService.findRepositoryListByUser(wikiRepositoryQuery);

        return Result.ok(wikiRepositoryList);
    }

    /**
     * @pi.name:按分页查找知识库
     * @pi.url:/repository/findRepositoryPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model= WikiRepositoryQuery
     */
    @RequestMapping(path = "/findRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryPage",desc = "按分页查找知识库")
    @ApiParam(name = "wikiRepositoryQuery",desc = "wikiRepositoryQuery",required = true)
    public Result<Pagination<WikiRepository>> findRepositoryPage(@RequestBody @Valid @NotNull WikiRepositoryQuery wikiRepositoryQuery){
        Pagination<WikiRepository> pagination = wikiRepositoryService.findRepositoryPage(wikiRepositoryQuery);

        return Result.ok(pagination);
    }

    /**
     * @pi.name:按最近点击的知识库
     * @pi.url:/repository/findRecentRepositoryList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WikiRepositoryQuery
     */
    @RequestMapping(path = "/findRecentRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentRepositoryList",desc = "按最近点击的知识库")
    @ApiParam(name = "documentRecentQuery",desc = "documentRecentQuery",required = true)
    public Result<List<WikiRepository>> findRecentRepositoryList(@RequestBody @Valid @NotNull WikiRepositoryQuery documentRecentQuery){
        List<WikiRepository> pagination = wikiRepositoryService.findRecentRepositoryList(documentRecentQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findAllRecentRepositoryList",method = RequestMethod.POST)
    // @ApiMethod(name = "findAllRecentRepositoryList",desc = "findAllRecentRepositoryList")
    // @ApiParam(name = "documentRecentQuery",desc = "documentRecentQuery",required = true)
    public Result<List<WikiRepository>> findAllRecentRepositoryList(@RequestBody @Valid @NotNull WikiRepositoryQuery documentRecentQuery){
        List<WikiRepository> pagination = wikiRepositoryService.findAllRecentRepositoryList(documentRecentQuery);

        return Result.ok(pagination);
    }


    /**
     * @pi.name:按我关注的知识库
     * @pi.url:/repository/findFocusRepositoryList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WikiRepositoryQuery
     */
    @RequestMapping(path = "/findFocusRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findFocusRepositoryList",desc = "按我关注的知识库")
    @ApiParam(name = "wikiRepositoryQuery",desc = "wikiRepositoryQuery",required = true)
    public Result<List<WikiRepository>> findFocusRepositoryList(@RequestBody @Valid @NotNull WikiRepositoryQuery wikiRepositoryQuery){
        List<WikiRepository> pagination = wikiRepositoryService.findFocusRepositoryList(wikiRepositoryQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findRepositoryNum",method = RequestMethod.POST)
    //@ApiMethod(name = "findRepositoryNum",desc = "findRepositoryPage")
    //@ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<Map<String, Integer>> findRepositoryNum(@RequestBody @Valid @NotNull WikiRepositoryQuery wikiRepositoryQuery){
        Map<String, Integer> repositoryNum = wikiRepositoryService.findRepositoryNum(wikiRepositoryQuery);

        return Result.ok(repositoryNum);
    }

}
