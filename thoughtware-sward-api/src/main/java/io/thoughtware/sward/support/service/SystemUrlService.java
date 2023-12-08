package io.thoughtware.sward.support.service;

import io.tiklab.core.page.Pagination;
import io.thoughtware.sward.support.model.SystemUrl;
import io.thoughtware.sward.support.model.SystemUrlQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 最近访问的服务
*/
public interface SystemUrlService {

    /**
     * 创建最近访问的
     * @param systemUrl
     * @return
     */
    String createSystemUrl(@NotNull @Valid SystemUrl systemUrl);

    /**
     * 更新最近访问的
     * @param systemUrl
     */
    void updateSystemUrl(@NotNull @Valid SystemUrl systemUrl);

    /**
     * 删除最近访问的
     * @param id
     */
    void deleteSystemUrl(@NotNull String id);

    /**
     * 根据id 查找最近访问的
     * @param id
     * @return
     */
    SystemUrl findOne(@NotNull String id);

    /**
     * 根据多个id, 查找最近访问的
     * @param idList
     * @return
     */
    List<SystemUrl> findList(List<String> idList);

    /**
     * 根据id, 查找最近访问的
     * @param id
     * @return
     */
    SystemUrl findSystemUrl(@NotNull String id);

    /**
     * 查找所有最近访问的
     * @return
     */
    List<SystemUrl> findAllSystemUrl();

    /**
     * 查询最近访问列表
     * @param systemUrlQuery
     * @return
     */
    List<SystemUrl> findSystemUrlList(SystemUrlQuery systemUrlQuery);

    /**
     * 按分页查询最近访问的
     * @param systemUrlQuery
     * @return
     */
    Pagination<SystemUrl> findSystemUrlPage(SystemUrlQuery systemUrlQuery);

}