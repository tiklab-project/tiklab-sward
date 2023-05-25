package io.tiklab.kanass.repository.service;

import io.tiklab.kanass.repository.model.WikiRepository;
import io.tiklab.kanass.repository.model.WikiRepositoryQuery;
import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryService
*/
@JoinProvider(model = WikiRepository.class)
public interface WikiRepositoryService {

    /**
    * 创建
    * @param wikiRepository
    * @return
    */
    String createRepository(@NotNull @Valid WikiRepository wikiRepository);

    /**
    * 更新
    * @param wikiRepository
    */
    void updateRepository(@NotNull @Valid WikiRepository wikiRepository);

    /**
    * 删除
    * @param id
    */
    void deleteRepository(@NotNull String id);

    @FindOne
    WikiRepository findOne(@NotNull String id);

    @FindList
    List<WikiRepository> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WikiRepository findRepository(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<WikiRepository> findAllRepository();

    /**
    * 查询列表
    * @param wikiRepositoryQuery
    * @return
    */
    List<WikiRepository> findRepositoryList(WikiRepositoryQuery wikiRepositoryQuery);

    /**
    * 按分页查询
    * @param wikiRepositoryQuery
    * @return
    */
    Pagination<WikiRepository> findRepositoryPage(WikiRepositoryQuery wikiRepositoryQuery);

    List<WikiRepository> findRecentRepositoryList(WikiRepositoryQuery wikiRepositoryQuery);

    List<WikiRepository> findFocusRepositoryList(WikiRepositoryQuery wikiRepositoryQuery);

}