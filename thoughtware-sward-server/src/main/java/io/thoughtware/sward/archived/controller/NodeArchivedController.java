package io.thoughtware.sward.archived.controller;

import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.sward.archived.service.NodeArchivedService;
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
 * NodeArchivedController
 */
@RestController
@RequestMapping("/nodeArchived")
@Api(name = "NodeArchivedController",desc = "文档管理")
public class NodeArchivedController {

    private static Logger logger = LoggerFactory.getLogger(NodeArchivedController.class);

    @Autowired
    private NodeArchivedService nodeArchivedService;

    @RequestMapping(path="/archivedNode",method = RequestMethod.POST)
    @ApiMethod(name = "archivedNode",desc = "创建评论")
    @ApiParam(name = "node",desc = "node",required = true)
    public Result<String> archivedNode(@RequestBody @NotNull @Valid Node node){
        nodeArchivedService.archivedNode(node);

        return Result.ok();
    }

    @RequestMapping(path="/recoverArchivedNode",method = RequestMethod.POST)
    @ApiMethod(name = "recoverArchivedNode",desc = "创建评论")
    @ApiParam(name = "node",desc = "node",required = true)
    public Result<String> recoverArchivedNode(@RequestBody @NotNull @Valid Node node){
        nodeArchivedService.recoverArchivedNode(node);

        return Result.ok();
    }

    @RequestMapping(path="/findArchivedNode",method = RequestMethod.POST)
    @ApiMethod(name = "findArchivedNode",desc = "创建评论")
    @ApiParam(name = "node",desc = "node",required = true)
    public Result<String> archivedNode(@RequestBody @NotNull @Valid NodeQuery nodeQuery){
        List<Node> archivedNode = nodeArchivedService.findArchivedNode(nodeQuery);

        return Result.ok(archivedNode);
    }




}
