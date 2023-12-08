package io.thoughtware.sward.category.service;

import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.category.model.WikiCategoryQuery;
import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* CategoryService
*/
@JoinProvider(model = WikiCategory.class)
public interface WikiCategoryService {

    /**
    * 创建
    * @param wikiCategory
    * @return
    */
    String createCategory(@NotNull @Valid WikiCategory wikiCategory);

    /**
    * 更新
    * @param wikiCategory
    */
    void updateCategory(@NotNull @Valid WikiCategory wikiCategory);
    void updateCategoryInit(@NotNull @Valid WikiCategory wikiCategory);
    void updateSort(@NotNull @Valid WikiCategory wikiCategory, String type);
    /**
    * 删除
    * @param id
    */
    void deleteCategory(@NotNull String id);
    void deleteCategoryAndSort(@NotNull @Valid WikiCategory wikiCategory);
    void updateSortAfterDelete(@NotNull @Valid String repositoryId, @Valid String parentWikiCategoryId, @Valid Integer sort);
    HashMap<String, List<String>> findCategoryChildren(@NotNull String parentWikiCategoryId);
    @FindOne
    WikiCategory findOne(@NotNull String id);

    @FindList
    List<WikiCategory> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WikiCategory findCategory(@NotNull String id);

    /**
     * 查找
     * @param id
     * @return
     */
    List<Object> findCategoryDocument(@NotNull String id);
    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<WikiCategory> findAllCategory();

    /**
    * 查询列表
    * @param wikiCategoryQuery
    * @return
    */
    List<WikiCategory> findCategoryList(WikiCategoryQuery wikiCategoryQuery);

    List<Map<String, Object>> findCategoryByRepositoryIds(String repositoryIds);

    /**
    * 按分页查询
    * @param wikiCategoryQuery
    * @return
    */
    Pagination<WikiCategory> findCategoryPage(WikiCategoryQuery wikiCategoryQuery);
    /**
     *查询目录树
     * @param wikiCategoryQuery
     * @return
     */
    List<Object> findCategoryListTree(WikiCategoryQuery wikiCategoryQuery);

    List<Object> findCategoryListTreeById(String id, String treePath);

}