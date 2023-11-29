package io.tiklab.kanass.repository.controller;

import io.tiklab.kanass.repository.model.WikiRepository;
import io.tiklab.kanass.repository.model.WikiRepositoryQuery;
import io.tiklab.kanass.repository.service.WikiRepositoryService;
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
     * @pi.path:/repository/createRepository
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: model=WikiRepository
     */
    @RequestMapping(path="/createRepository",method = RequestMethod.POST)
    @ApiMethod(name = "createRepository",desc = "创建知识库")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<String> createRepository(@RequestBody @NotNull @Valid WikiRepository wikiRepository){
        String id = wikiRepositoryService.createRepository(wikiRepository);

        return Result.ok(id);
    }

    /**
     * @pi.name:更新项目
     * @pi.path:/repository/updateRepository
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: model=WikiRepository
     */
    @RequestMapping(path="/updateRepository",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepository",desc = "更新知识库")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<Void> updateRepository(@RequestBody @NotNull @Valid WikiRepository wikiRepository){
        wikiRepositoryService.updateRepository(wikiRepository);


        return Result.ok();
    }

    /**
     * @pi.name:删除知识库
     * @pi.path:/repository/deleteRepository
     * @pi.method:post
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
     * @pi.path:/repository/findRepository
     * @pi.method:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/findRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findRepository",desc = "findRepository")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiRepository> findRepository(@NotNull String id){
        WikiRepository wikiRepository = wikiRepositoryService.findRepository(id);

        return Result.ok(wikiRepository);
    }

    /**
     * @pi.name:根据id查找知识库
     * @pi.path:/repository/findList
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: List<String>
     */
    @RequestMapping(path="/findList",method = RequestMethod.POST)
    @ApiMethod(name = "findList",desc = "findRepository")
    @ApiParam(name = "idList",desc = "idList",required = true)
    public Result<WikiRepository> findRepository(@RequestBody @NotNull @Valid List<String> idList){
        List<WikiRepository> wikiRepositoryList = wikiRepositoryService.findList(idList);

        return Result.ok(wikiRepositoryList);
    }

    /**
     * @pi.name:查找所有知识库
     * @pi.path:/repository/findAllRepository
     * @pi.method:post
     */
    @RequestMapping(path="/findAllRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepository",desc = "findAllRepository")
    public Result<List<WikiRepository>> findAllRepository(){
        List<WikiRepository> wikiRepositoryList = wikiRepositoryService.findAllRepository();

        return Result.ok(wikiRepositoryList);
    }

    /**
     * @pi.name:根据当前用户可查看的所有知识库
     * @pi.path:/repository/findRepositoryListByUser
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: WikiRepositoryQuery
     */
    @RequestMapping(path = "/findRepositoryListByUser",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryListByUser",desc = "findRepositoryList")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<List<WikiRepository>> findRepositoryListByUser(@RequestBody @Valid @NotNull WikiRepositoryQuery wikiRepositoryQuery){
        List<WikiRepository> wikiRepositoryList = wikiRepositoryService.findRepositoryListByUser(wikiRepositoryQuery);

        return Result.ok(wikiRepositoryList);
    }

    /**
     * @pi.name:按分页查找知识库
     * @pi.path:/repository/findRepositoryPage
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: WikiRepositoryQuery
     */
    @RequestMapping(path = "/findRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryPage",desc = "findRepositoryPage")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<Pagination<WikiRepository>> findRepositoryPage(@RequestBody @Valid @NotNull WikiRepositoryQuery wikiRepositoryQuery){
        Pagination<WikiRepository> pagination = wikiRepositoryService.findRepositoryPage(wikiRepositoryQuery);

        return Result.ok(pagination);
    }

    /**
     * @pi.name:按最近点击的知识库
     * @pi.path:/repository/findRecentRepositoryList
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: WikiRepositoryQuery
     */
    @RequestMapping(path = "/findRecentRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentRepositoryList",desc = "findRepositoryPage")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<List<WikiRepository>> findRecentRepositoryList(@RequestBody @Valid @NotNull WikiRepositoryQuery documentRecentQuery){
        List<WikiRepository> pagination = wikiRepositoryService.findRecentRepositoryList(documentRecentQuery);

        return Result.ok(pagination);
    }

    /**
     * @pi.name:按我关注的知识库
     * @pi.path:/repository/findFocusRepositoryList
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: WikiRepositoryQuery
     */
    @RequestMapping(path = "/findFocusRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findFocusRepositoryList",desc = "findRepositoryPage")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<List<WikiRepository>> findFocusRepositoryList(@RequestBody @Valid @NotNull WikiRepositoryQuery wikiRepositoryQuery){
        List<WikiRepository> pagination = wikiRepositoryService.findFocusRepositoryList(wikiRepositoryQuery);

        return Result.ok(pagination);
    }


}
