package io.tiklab.kanass.support.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.support.model.*;
import io.tiklab.kanass.support.service.WikiProjectService;
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
import java.util.List;

@RestController
@RequestMapping("/wikiProject")
@Api(name = "WikiProjectController",desc = "")
public class WikiProjectController {
    private static Logger logger = LoggerFactory.getLogger(WikiProjectController.class);

    @Autowired
    private WikiProjectService wikiProjectService;

    @RequestMapping(path="/findAllProject",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProject",desc = "findAllProject")
    public Result<List<WikiProject>> findAllProject(){
        List<WikiProject> wikiProjectList =  wikiProjectService.findAllProject();

        return Result.ok(wikiProjectList);
    }

    @RequestMapping(path = "/findWorkItemPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemPage",desc = "findWorkItemList")
    @ApiParam(name = "wikiWorkItemQuery",desc = "wikiWorkItemQuery",required = true)
    public Result<Pagination<WikiWorkItem>> findWorkItemPage(@RequestBody @Valid @NotNull WikiWorkItemQuery wikiWorkItemQuery){
        Pagination<WikiWorkItem> workItemList = wikiProjectService.findWorkItemPage(wikiWorkItemQuery);

        return Result.ok(workItemList);
    }

    @RequestMapping(path = "/findWorkTypeList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkTypeList",desc = "findWorkTypeList")
    @ApiParam(name = "wikiWorkItemQuery",desc = "wikiWorkItemQuery",required = true)
    public Result<List<WikiWorkType>> findWorkTypeList(@RequestBody @Valid @NotNull WikiWorkItemQuery wikiWorkItemQuery){
        List<WikiWorkType> workTypeList = wikiProjectService.findWorkTypeList(wikiWorkItemQuery);

        return Result.ok(workTypeList);
    }

    @RequestMapping(path = "/findDmUserList",method = RequestMethod.POST)
    @ApiMethod(name = "findDmUserList",desc = "findWorkTypeList")
    @ApiParam(name = "findDmUserList",desc = "wikiWorkItemQuery",required = true)
    public Result<List<WikiWorkType>> findDmUserList(@RequestBody @Valid @NotNull WikiWorkItemQuery wikiWorkItemQuery){
        List<WikiDmUser> workTypeList = wikiProjectService.findDmUserList(wikiWorkItemQuery);

        return Result.ok(workTypeList);
    }

    @RequestMapping(path = "/findWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItem",desc = "findWorkTypeList")
    @ApiParam(name = "workItemId",desc = "workItemId",required = true)
    public Result<WikiWorkType> findWorkItem(@Valid @NotNull String workItemId){
        WikiWorkItem workItem = wikiProjectService.findWorkItem(workItemId);

        return Result.ok(workItem);
    }
}