package io.tiklab.sward.repository.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.sward.repository.model.WikiRepositoryFocus;
import io.tiklab.sward.repository.model.WikiRepositoryFocusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RepositoryFocusService
*/
public interface WikiRepositoryFocusService {

    /**
    * 创建
    * @param wikiRepositoryFocus
    * @return
    */
    String createRepositoryFocus(@NotNull @Valid WikiRepositoryFocus wikiRepositoryFocus);

    /**
    * 更新
    * @param wikiRepositoryFocus
    */
    void updateRepositoryFocus(@NotNull @Valid WikiRepositoryFocus wikiRepositoryFocus);

    /**
    * 删除
    * @param id
    */
    void deleteRepositoryFocus(@NotNull String id);

    void deleteRepositoryFocusByCondition(@NotNull @Valid WikiRepositoryFocusQuery wikiRepositoryFocusQuery);

    WikiRepositoryFocus findOne(@NotNull String id);

    List<WikiRepositoryFocus> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WikiRepositoryFocus findRepositoryFocus(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<WikiRepositoryFocus> findAllRepositoryFocus();

    /**
    * 查询列表
    * @param wikiRepositoryFocusQuery
    * @return
    */
    List<WikiRepositoryFocus> findRepositoryFocusList(WikiRepositoryFocusQuery wikiRepositoryFocusQuery);

    /**
    * 按分页查询
    * @param wikiRepositoryFocusQuery
    * @return
    */
    Pagination<WikiRepositoryFocus> findRepositoryFocusPage(WikiRepositoryFocusQuery wikiRepositoryFocusQuery);

}