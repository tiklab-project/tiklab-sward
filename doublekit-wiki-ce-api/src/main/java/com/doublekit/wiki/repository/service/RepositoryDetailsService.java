package com.doublekit.wiki.repository.service;

import com.doublekit.common.Pagination;

import com.doublekit.wiki.repository.model.RepositoryDetails;
import com.doublekit.wiki.repository.model.RepositoryDetailsQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryDetailsService
*/
public interface RepositoryDetailsService {

    /**
    * 创建
    * @param repositoryDetails
    * @return
    */
    String createRepositoryDetails(@NotNull @Valid RepositoryDetails repositoryDetails);

    /**
    * 更新
    * @param repositoryDetails
    */
    void updateRepositoryDetails(@NotNull @Valid RepositoryDetails repositoryDetails);

    /**
    * 删除
    * @param id
    */
    void deleteRepositoryDetails(@NotNull String id);

    RepositoryDetails findOne(@NotNull String id);

    List<RepositoryDetails> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RepositoryDetails findRepositoryDetails(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RepositoryDetails> findAllRepositoryDetails();

    /**
    * 查询列表
    * @param repositoryDetailsQuery
    * @return
    */
    List<RepositoryDetails> findRepositoryDetailsList(RepositoryDetailsQuery repositoryDetailsQuery);

    /**
    * 按分页查询
    * @param repositoryDetailsQuery
    * @return
    */
    Pagination<RepositoryDetails> findRepositoryDetailsPage(RepositoryDetailsQuery repositoryDetailsQuery);

}