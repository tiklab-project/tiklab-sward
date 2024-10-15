package io.thoughtware.sward.support.service;


import io.thoughtware.sward.support.model.Recent;
import io.thoughtware.sward.support.model.RecentQuery;
import io.thoughtware.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RecentService
*/
public interface RecentService {

    /**
    * 创建
    * @param recent
    * @return
    */
    String createRecent(@NotNull @Valid Recent recent);

    /**
    * 更新
    * @param recent
    */
    void updateRecent(@NotNull @Valid Recent recent);

    /**
    * 删除
    * @param id
    */
    void deleteRecent(@NotNull String id);

    void deleteRecnetByCondition(@NotNull @Valid RecentQuery recentQuery);

    Recent findOne(@NotNull String id);

    List<Recent> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    Recent findRecent(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<Recent> findAllRecent();

    /**
    * 查询列表
    * @param recentQuery
    * @return
    */
    List<Recent> findRecentList(RecentQuery recentQuery);

    /**
     * 按分页查询
     *
     * @param recentQuery
     * @return
     */
    Pagination<Recent> findRecentPage(RecentQuery recentQuery);

}