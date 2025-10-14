package io.tiklab.sward.document.document.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.sward.document.document.model.Share;
import io.tiklab.sward.document.document.model.ShareQuery;
import io.tiklab.sward.node.node.model.Node;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ShareService
*/
@JoinProvider(model = Share.class)
public interface ShareService {

    /**
    * 创建
    * @param share 分享信息
    * @return 分享ID
    */
    String createShare(@NotNull @Valid Share share);

    /**
    * 更新
    * @param share 分享信息
    */
    void updateShare(@NotNull @Valid Share share);

    /**
    * 删除
    * @param id id
    */
    void deleteShare(@NotNull String id);


    @FindList
    List<Share> findList(List<String> idList);

    /**
    * 查找
    * @param id id
    * @return 分享信息
    */
    @FindOne
    Share findShare(@NotNull String id);

    /**
    * 查找所有
    * @return 分享信息
    */
    @FindAll
    List<Share> findAllShare();

    /**
    * 查询列表
    * @param shareQuery 查询条件
    * @return 分享信息
    */
    List<Share> findShareList(ShareQuery shareQuery);

    /**
    * 按分页查询
    * @param shareQuery 查询条件
    * @return 分享信息
    */
    Pagination<Share> findSharePage(ShareQuery shareQuery);

    /**
     * 创建分享并返回链接和验证码
     * @param share 分享信息
     * @return 分享信息
     */
    Share addShare(Share share);

    /**
     * 获取分享的文件
     * @param id ID
     * @return 分享的信息
     */
    List<Node> findShareCategory(String id);


}