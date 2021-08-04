package com.doublekit.wiki.repository.service;

import com.doublekit.common.Pagination;

import com.doublekit.wiki.repository.model.RepositoryPage;
import com.doublekit.wiki.repository.model.RepositoryPageQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryPageService
*/
public interface RepositoryPageService {

    /**
    * 创建
    * @param repositoryPage
    * @return
    */
    String createRepositoryPage(@NotNull @Valid RepositoryPage repositoryPage);

    /**
    * 更新
    * @param repositoryPage
    */
    void updateRepositoryPage(@NotNull @Valid RepositoryPage repositoryPage);

    /**
    * 删除
    * @param id
    */
    void deleteRepositoryPage(@NotNull String id);

    RepositoryPage findOne(@NotNull String id);

    List<RepositoryPage> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RepositoryPage findRepositoryPage(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RepositoryPage> findAllRepositoryPage();

    /**
    * 查询列表
    * @param repositoryPageQuery
    * @return
    */
    List<RepositoryPage> findRepositoryPageList(RepositoryPageQuery repositoryPageQuery);

    /**
    * 按分页查询
    * @param repositoryPageQuery
    * @return
    */
    Pagination<RepositoryPage> findRepositoryPagePage(RepositoryPageQuery repositoryPageQuery);

}