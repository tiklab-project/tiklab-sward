package com.doublekit.wiki.integration.workitem.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.common.Pagination;
import com.doublekit.common.Result;
import com.doublekit.project.workitem.model.WorkBoard;
import com.doublekit.project.workitem.model.WorkItem;
import com.doublekit.project.workitem.model.WorkItemQuery;
import com.doublekit.project.workitem.service.WorkItemService;
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
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/workItem")
@Api(name = "WorkItemController",desc = "事项管理")
public class WorkItemController {

    private static Logger logger = LoggerFactory.getLogger(WorkItemController.class);

    @Autowired
    private WorkItemService workItemService;

    @RequestMapping(path="/createWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkItem",desc = "创建事项")
    @ApiParam(name = "workItem",desc = "事项DTO",required = true)
    public Result<String> createWorkItem(@RequestBody @NotNull @Valid WorkItem workItem){
        String id = workItemService.createWorkItem(workItem);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkItem",desc = "更新事项")
    @ApiParam(name = "workItem",desc = "事项DTO",required = true)
    public Result<Void> updateWorkItem(@RequestBody @NotNull WorkItem workItem){
        workItemService.updateWorkItem(workItem);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkItem",desc = "根据事项ID删除事项")
    @ApiParam(name = "id",desc = "事项ID",required = true)
    public Result<Void> deleteWorkItem(@NotNull String id){
        workItemService.deleteWorkItem(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItem",desc = "根据事项ID查找事项")
    @ApiParam(name = "id",desc = "事项ID",required = true)
    public Result<WorkItem> findWorkItem(@NotNull String id){
        WorkItem workItem = workItemService.findWorkItem(id);

        return Result.ok(workItem);
    }

    @RequestMapping(path="/findAllWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkItem",desc = "查找所有事项")
    public Result<List<WorkItem>> findAllWorkItem(){
        List<WorkItem> workItemList = workItemService.findAllWorkItem();

        return Result.ok(workItemList);
    }


    @RequestMapping(path = "/findWorkItemList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemList",desc = "根据查询对象查找事项列表")
    @ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<List<WorkItem>> findWorkItemList(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);

        return Result.ok(workItemList);
    }


    @RequestMapping(path = "/findWorkItemListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemListTree",desc = "根据查询对象查找事项列表树")
    @ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<List<WorkItem>> findWorkItemListTree(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        List<WorkItem> workItemList = workItemService.findWorkItemListTree(workItemQuery);

        return Result.ok(workItemList);
    }


    @RequestMapping(path = "/findWorkItemPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemPage",desc = "根据查询对象按分页查找事项列表")
    @ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findWorkItemPage(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> pagination = workItemService.findWorkItemPage(workItemQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path = "/findWorkItemPageTree",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemPageTree",desc = "根据查询对象按分页查找事项列表树")
    @ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findWorkItemPageTree(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> pagination = workItemService.findWorkItemPageTree(workItemQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path = "/findWorkBoardList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkBoardList",desc = "根据查询对象查找事项看板列表")
    @ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<List<WorkBoard>> findWorkBoardList(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        List<WorkBoard> workBoardList = workItemService.findWorkBoardList(workItemQuery);

        return Result.ok(workBoardList);
    }


    @RequestMapping(path = "/findWorkBoard",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkBoard",desc = "根据查询对象查找事项看板")
    @ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<WorkBoard> findWorkBoard(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        WorkBoard workBoard = workItemService.findWorkBoard(workItemQuery);

        return Result.ok(workBoard);
    }


}
