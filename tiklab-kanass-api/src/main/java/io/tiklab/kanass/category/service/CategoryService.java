package io.tiklab.kanass.category.service;

import io.tiklab.kanass.category.model.CategoryQuery;
import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.kanass.category.model.Category;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* CategoryService
*/
@JoinProvider(model = Category.class)
public interface CategoryService {

    /**
    * 创建
    * @param category
    * @return
    */
    String createCategory(@NotNull @Valid Category category);

    /**
    * 更新
    * @param category
    */
    void updateCategory(@NotNull @Valid Category category);

    /**
    * 删除
    * @param id
    */
    void deleteCategory(@NotNull String id);
    @FindOne
    Category findOne(@NotNull String id);

    @FindList
    List<Category> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    Category findCategory(@NotNull String id);

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
    List<Category> findAllCategory();

    /**
    * 查询列表
    * @param categoryQuery
    * @return
    */
    List<Category> findCategoryList(CategoryQuery categoryQuery);

    /**
    * 按分页查询
    * @param categoryQuery
    * @return
    */
    Pagination<Category> findCategoryPage(CategoryQuery categoryQuery);
    /**
     *查询目录树
     * @param categoryQuery
     * @return
     */
    List findCategoryListTree(CategoryQuery categoryQuery);
}