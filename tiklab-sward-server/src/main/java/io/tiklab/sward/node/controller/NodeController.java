package io.tiklab.sward.node.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.sward.node.model.Node;
import io.tiklab.sward.node.model.NodeQuery;
import io.tiklab.sward.node.service.NodeService;
import io.tiklab.sward.support.model.RecentQuery;
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
 * NodeController
 */
@RestController
@RequestMapping("/node")
@Api(name = "NodeController",desc = "知识库目录分类管理")
public class NodeController {

    private static Logger logger = LoggerFactory.getLogger(NodeController.class);

    @Autowired
    private NodeService nodeService;

    @RequestMapping(path="/createNode",method = RequestMethod.POST)
    //@ApiMethod(name = "createNode",desc = "创建目录")
    //@ApiParam(name = "node",desc = "node",required = true)
    public Result<String> createNode(@RequestBody @NotNull @Valid Node node){
        String id = nodeService.createNode(node);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateNode",method = RequestMethod.POST)
    //@ApiMethod(name = "updateNode",desc = "更新目录")
    //@ApiParam(name = "node",desc = "node",required = true)
    public Result<Void> updateNode(@RequestBody @NotNull @Valid Node node){
        nodeService.updateNode(node);

        return Result.ok();
    }

    @RequestMapping(path="/deleteNode",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteNode",desc = "删除目录")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteNode(@NotNull String id){
        nodeService.deleteNode(id);

        return Result.ok();
    }

    @RequestMapping(path="/findNode",method = RequestMethod.POST)
    //@ApiMethod(name = "findNode",desc = "根据id 查询目录")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<Node> findNode(@NotNull String id){
        Node node = nodeService.findNode(id);

        return Result.ok(node);
    }


    @RequestMapping(path="/findAllNode",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllNode",desc = "查询所有目录")
    public Result<List<Node>> findAllNode(){
        List<Node> nodeList = nodeService.findAllNode();

        return Result.ok(nodeList);
    }

    @RequestMapping(path = "/findNodeList",method = RequestMethod.POST)
    //@ApiMethod(name = "findNodeList",desc = "条件查询目录")
    //@ApiParam(name = "nodeQuery",desc = "nodeQuery",required = true)
    public Result<List<Node>> findNodeList(@RequestBody @Valid @NotNull NodeQuery nodeQuery){
        List<Node> nodeList = nodeService.findNodeList(nodeQuery);

        return Result.ok(nodeList);
    }



    @RequestMapping(path = "/findNodePage",method = RequestMethod.POST)
    //@ApiMethod(name = "findNodePage",desc = "条件分页查询目录")
    //@ApiParam(name = "nodeQuery",desc = "nodeQuery",required = true)
    public Result<Pagination<Node>> findNodePage(@RequestBody @Valid @NotNull NodeQuery nodeQuery){
        Pagination<Node> pagination = nodeService.findNodePage(nodeQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findNodePageTree",method = RequestMethod.POST)
    //@ApiMethod(name = "findNodePageTree",desc = "查询树状结构")
    //@ApiParam(name = "nodeQuery",desc = "nodeQuery",required = true)
    public Result<List<Node>> findNodePageTree(@RequestBody @Valid @NotNull NodeQuery nodeQuery){
        List<Node> nodePageTree = nodeService.findNodePageTree(nodeQuery);

        return Result.ok(nodePageTree);
    }

    @RequestMapping(path = "/findChildrenNodeList",method = RequestMethod.POST)
    //@ApiMethod(name = "findChildrenNodeList",desc = "查询树状结构")
    //@ApiParam(name = "repositoryIds",desc = "repositoryIds",required = true)
    public Result<List<Node>> findChildrenNum(@NotNull String repositoryIds){
        List<Node> childrenNodeList = nodeService.findChildrenNodeList(repositoryIds);

        return Result.ok(childrenNodeList);
    }

    @RequestMapping(path = "/findNodeRecentList",method = RequestMethod.POST)
    //@ApiMethod(name = "findNodeRecentList",desc = "查询最近查看的文档")
    //@ApiParam(name = "recentQuery",desc = "recentQuery",required = true)
    public Result<List<Node>> findNodeRecentList(@RequestBody @Valid @NotNull RecentQuery recentQuery){
        List<Node> nodeList = nodeService.findNodeRecentList(recentQuery);

        return Result.ok(nodeList);
    }

    @RequestMapping(path = "/findAllHigherNode",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllHigherNode",desc = "根据id查找所有的上级node")
    //@ApiParam(name = "id",desc = "nodeQuery",required = true)
    public Result<List<Node>> findAllHigherNode(@NotNull String id){
        List<Node> nodeList = nodeService.findAllHigherNode(id);

        return Result.ok(nodeList);
    }

}
