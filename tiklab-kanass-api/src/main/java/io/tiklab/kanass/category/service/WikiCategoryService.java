package io.tiklab.kanass.category.service;

import io.tiklab.kanass.category.model.WikiCategoryQuery;
import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.kanass.category.model.WikiCategory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    /**
    * 删除
    * @param id
    */
    void deleteCategory(@NotNull String id);
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
    List findCategoryListTree(WikiCategoryQuery wikiCategoryQuery);
}