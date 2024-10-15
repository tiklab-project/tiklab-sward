package io.thoughtware.sward.category.service;

import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.category.model.WikiCategoryQuery;
import io.thoughtware.core.page.Pagination;

import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

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
    /**
    * 删除
    * @param id
    */
    void deleteCategory(@NotNull String id);

    void batchDeleteCategory(WikiCategoryQuery wikiCategoryQuery);

//    void deleteCategoryByIds(Object[] ids);
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

    /**
    * 按分页查询
    * @param wikiCategoryQuery
    * @return
    */
    Pagination<WikiCategory> findCategoryPage(WikiCategoryQuery wikiCategoryQuery);

}