package net.tiklab.kanass.repository.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.kanass.repository.model.RepositoryFocus;
import net.tiklab.kanass.repository.model.RepositoryFocusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryFocusService
*/
public interface RepositoryFocusService {

    /**
    * 创建
    * @param repositoryFocus
    * @return
    */
    String createRepositoryFocus(@NotNull @Valid RepositoryFocus repositoryFocus);

    /**
    * 更新
    * @param repositoryFocus
    */
    void updateRepositoryFocus(@NotNull @Valid RepositoryFocus repositoryFocus);

    /**
    * 删除
    * @param id
    */
    void deleteRepositoryFocus(@NotNull String id);

    void deleteRepositoryFocusByCondition(@NotNull @Valid RepositoryFocusQuery repositoryFocusQuery);

    RepositoryFocus findOne(@NotNull String id);

    List<RepositoryFocus> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RepositoryFocus findRepositoryFocus(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RepositoryFocus> findAllRepositoryFocus();

    /**
    * 查询列表
    * @param repositoryFocusQuery
    * @return
    */
    List<RepositoryFocus> findRepositoryFocusList(RepositoryFocusQuery repositoryFocusQuery);

    /**
    * 按分页查询
    * @param repositoryFocusQuery
    * @return
    */
    Pagination<RepositoryFocus> findRepositoryFocusPage(RepositoryFocusQuery repositoryFocusQuery);

}