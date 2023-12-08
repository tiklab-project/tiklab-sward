package io.thoughtware.sward.repository.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.join.annotation.FindAll;
import io.thoughtware.join.annotation.FindList;
import io.thoughtware.join.annotation.FindOne;
import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.sward.repository.model.WikiRepositoryQuery;

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
    List<WikiRepository> findRepositoryList(String time);
    /**
    * 查询列表
    * @param wikiRepositoryQuery
    * @return
    */
    List<WikiRepository> findRepositoryListByUser(WikiRepositoryQuery wikiRepositoryQuery);

    /**
    * 按分页查询
    * @param wikiRepositoryQuery
    * @return
    */
    Pagination<WikiRepository> findRepositoryPage(WikiRepositoryQuery wikiRepositoryQuery);

    List<WikiRepository> findRecentRepositoryList(WikiRepositoryQuery wikiRepositoryQuery);

    List<WikiRepository> findFocusRepositoryList(WikiRepositoryQuery wikiRepositoryQuery);

}