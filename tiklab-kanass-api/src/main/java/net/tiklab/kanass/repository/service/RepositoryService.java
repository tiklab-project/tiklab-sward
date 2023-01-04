package net.tiklab.kanass.repository.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.kanass.document.model.DocumentRecentQuery;
import net.tiklab.kanass.repository.model.Repository;
import net.tiklab.kanass.repository.model.RepositoryQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryService
*/
@JoinProvider(model = Repository.class)
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

    @FindOne
    Repository findOne(@NotNull String id);

    @FindList
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
    @FindAll
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

    List<Repository> findRecentRepositoryList(DocumentRecentQuery documentRecentQuery);

    List<Repository> findFocusRepositoryList(RepositoryQuery repositoryQuery);

}