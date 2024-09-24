package io.thoughtware.sward.node.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.sward.document.model.DocumentQuery;
import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.node.model.NodeQuery;
import io.thoughtware.sward.support.model.RecentQuery;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* NodeService
*/
@JoinProvider(model = Node.class)
public interface NodeService {

    /**
    * 创建
    * @param node
    * @return
    */
    String createNode(@NotNull @Valid Node node);

    String createConfluNode(@NotNull @Valid Node node);

    /**
    * 更新
    * @param node
    */
    void updateNode(@NotNull @Valid Node node);

    List<Node> findAllChildrenNodeList(String id);

    /**
    * 删除
    * @param id
    */
    void deleteNode(@NotNull String id);

    void deleteNodeById(String id);

    void deleteNodeCondition(@NotNull @Valid NodeQuery nodeQuery );


    void deleteRepositoryNodeCondition(String repositoryId);

    @FindOne
    Node findOne(@NotNull String id);

    @FindList
    List<Node> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    Node findNode(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<Node> findAllNode();

    /**
    * 查询列表
    * @param nodeQuery
    * @return
    */
    List<Node> findNodeList(NodeQuery nodeQuery);
    List<Node> findNodeListByName(NodeQuery nodeQuery);
    /**
    * 按分页查询
    * @param nodeQuery
    * @return
    */
    Pagination<Node> findNodePage(NodeQuery nodeQuery);

    List<Node> findNodePageTree(NodeQuery nodeQuery);

    List<Node> findChildrenNodeList(String repositoryIds);

    List<Node> findNodeRecentList(RecentQuery recentQuery);

    List<Node> findAllHigherNode(String id);

}