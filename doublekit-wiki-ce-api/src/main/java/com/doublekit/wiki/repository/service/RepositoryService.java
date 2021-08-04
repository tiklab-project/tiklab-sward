package com.doublekit.wiki.repository.service;

import com.doublekit.common.Pagination;

import com.doublekit.wiki.repository.model.Repository;
import com.doublekit.wiki.repository.model.RepositoryQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryService
*/
public interface RepositoryService {

    /**
    * 创建
    * @param repository
    * @return
    */
    String createRepository(@NotNull @Valid Repository repository);

    /**
    * 更新
    * @param repository
    */
    void updateRepository(@NotNull @Valid Repository repository);

    /**
    * 删除
    * @param id
    */
    void deleteRepository(@NotNull String id);

    Repository findOne(@NotNull String id);

    List<Repository> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    Repository findRepository(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<Repository> findAllRepository();

    /**
    * 查询列表
    * @param repositoryQuery
    * @return
    */
    List<Repository> findRepositoryList(RepositoryQuery repositoryQuery);

    /**
    * 按分页查询
    * @param repositoryQuery
    * @return
    */
    Pagination<Repository> findRepositoryPage(RepositoryQuery repositoryQuery);

}