package io.tiklab.sward.support.support.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.sward.support.support.model.*;
import io.tiklab.sward.support.support.service.WikiProjectService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
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
@RequestMapping("/wiki/kanass/project")
// @Api(name = "WikiProjectController",desc = "")
public class WikiProjectController {

    private static Logger logger = LoggerFactory.getLogger(WikiProjectController.class);

    @Autowired
    WikiProjectService wikiProjectService;

    @RequestMapping(path="/findKanassProjectPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllProject",desc = "findAllProject")
    public Result<List<kanassProject>> findKanassProjectPage(@RequestBody @Valid @NotNull kanassProjectQuery kanassProjectQuery){
        Pagination<kanassProject> projectPage =  wikiProjectService.findKanassProjectPage(kanassProjectQuery);

        return Result.ok(projectPage);
    }

    @RequestMapping(path="/findKanassProjectList",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllProject",desc = "findAllProject")
    public Result<List<kanassProject>> findKanassProjectList(@RequestBody @Valid @NotNull kanassProjectQuery kanassProjectQuery){
        List<kanassProject> projectList =  wikiProjectService.findKanassProjectList(kanassProjectQuery);

        return Result.ok(projectList);
    }


    @RequestMapping(path = "/findKanassWorkItemPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemPage",desc = "findWorkItemList")
    //@ApiParam(name = "wikiWorkItemQuery",desc = "wikiWorkItemQuery",required = true)
    public Result<Pagination<KanassWorkItem>> findWorkItemPage(@RequestBody @Valid @NotNull KanassWorkItemQuery kanassWorkItemQuery){
        Pagination<KanassWorkItem> workItemList = wikiProjectService.findKanassWorkItemPage(kanassWorkItemQuery);

        return Result.ok(workItemList);
    }

    @RequestMapping(path = "/findKanassWorkTypeList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkTypeList",desc = "findWorkTypeList")
    //@ApiParam(name = "wikiWorkItemQuery",desc = "wikiWorkItemQuery",required = true)
    public Result<List<KanassItemType>> findWorkTypeList(@RequestBody @Valid @NotNull KanassWorkTypeDmQuery workTypeDmQuery){
        List<KanassItemType> workTypeList = wikiProjectService.findKanassWorkTypeList(workTypeDmQuery);

        return Result.ok(workTypeList);
    }

    @RequestMapping(path = "/findDmUserPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findDmUserList",desc = "findWorkTypeList")
    //@ApiParam(name = "findDmUserList",desc = "wikiWorkItemQuery",required = true)
    public Result<List<KanassItemType>> findDmUserPage(@RequestBody @Valid @NotNull DmUserQuery dmUserQuery){
        Pagination<DmUser> workTypeList = wikiProjectService.findDmUserPage(dmUserQuery);

        return Result.ok(workTypeList);
    }

    @RequestMapping(path = "/findDmUserList",method = RequestMethod.POST)
    //@ApiMethod(name = "findDmUserList",desc = "findWorkTypeList")
    //@ApiParam(name = "findDmUserList",desc = "wikiWorkItemQuery",required = true)
    public Result<List<KanassItemType>> findDmUserList(@RequestBody @Valid @NotNull DmUserQuery dmUserQuery){
        List<DmUser> workTypeList = wikiProjectService.findDmUserList(dmUserQuery);

        return Result.ok(workTypeList);
    }

    @RequestMapping(path = "/findKanassWorkItem",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItem",desc = "findWorkTypeList")
    //@ApiParam(name = "workItemId",desc = "workItemId",required = true)
    public Result<KanassWorkItem> findWorkItem(@Valid @NotNull String workItemId){
        KanassWorkItem kanassWorkItem = wikiProjectService.findKanassWorkItem(workItemId);

        return Result.ok(kanassWorkItem);
    }
}
