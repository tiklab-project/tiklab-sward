package io.thoughtware.sward.recycle.controller;

import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.sward.recycle.service.NodeRecycleService;
import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.node.model.NodeQuery;
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
 * NodeRecycleController
 */
@RestController
@RequestMapping("/nodeRecycle")
@Api(name = "NodeRecycleController",desc = "文档管理")
public class NodeRecycleController {

    private static Logger logger = LoggerFactory.getLogger(NodeRecycleController.class);

    @Autowired
    private NodeRecycleService nodeRecycleService;

    @RequestMapping(path="/recycleNode",method = RequestMethod.POST)
    @ApiMethod(name = "recycleNode",desc = "创建评论")
    @ApiParam(name = "node",desc = "node",required = true)
    public Result<String> recycleNode(@RequestBody @NotNull @Valid Node node){
        nodeRecycleService.recycleNode(node);
        return Result.ok();
    }

    @RequestMapping(path="/recoverRecycleNode",method = RequestMethod.POST)
    @ApiMethod(name = "recoverRecycleNode",desc = "创建评论")
    @ApiParam(name = "node",desc = "node",required = true)
    public Result<String> recoverRecycleNode(@RequestBody @NotNull @Valid Node node){
        nodeRecycleService.recoverRecycleNode(node);

        return Result.ok();
    }

    @RequestMapping(path="/findRecycleNode",method = RequestMethod.POST)
    @ApiMethod(name = "findRecycleNode",desc = "创建评论")
    @ApiParam(name = "node",desc = "node",required = true)
    public Result<String> recycleNode(@RequestBody @NotNull @Valid NodeQuery nodeQuery){
        List<Node> recycleNode = nodeRecycleService.findRecycleNode(nodeQuery);

        return Result.ok(recycleNode);
    }




}
