package io.thoughtware.sward.category.controller;

import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.category.model.WikiCategoryQuery;
import io.thoughtware.sward.category.service.WikiCategoryService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * CategoryController
 */
@RestController
@RequestMapping("/category")
@Api(name = "CategoryController",desc = "知识库目录分类管理")
public class WikiCategoryController {

    private static Logger logger = LoggerFactory.getLogger(WikiCategoryController.class);

    @Autowired
    private WikiCategoryService wikiCategoryService;

    @RequestMapping(path="/createCategory",method = RequestMethod.POST)
    @ApiMethod(name = "createCategory",desc = "创建目录")
    @ApiParam(name = "category",desc = "category",required = true)
    public Result<String> createCategory(@RequestBody @NotNull @Valid WikiCategory wikiCategory){
        String id = wikiCategoryService.createCategory(wikiCategory);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateCategory",method = RequestMethod.POST)
    @ApiMethod(name = "updateCategory",desc = "更新目录")
    @ApiParam(name = "category",desc = "category",required = true)
    public Result<Void> updateCategory(@RequestBody @NotNull @Valid WikiCategory wikiCategory){
        wikiCategoryService.updateCategory(wikiCategory);

        return Result.ok();
    }

    @RequestMapping(path="/deleteCategory",method = RequestMethod.POST)
    @ApiMethod(name = "deleteCategory",desc = "删除目录")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteCategory(@NotNull String id){
        wikiCategoryService.deleteCategory(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteCategoryAndSort",method = RequestMethod.POST)
    @ApiMethod(name = "deleteCategoryAndSort",desc = "删除目录")
    @ApiParam(name = "wikiCategory",desc = "wikiCategory",required = true)
    public Result<Void> deleteCategory(@RequestBody @Valid @NotNull WikiCategory wikiCategory){
        wikiCategoryService.deleteCategoryAndSort(wikiCategory);

        return Result.ok();
    }

    @RequestMapping(path="/findCategoryChildren",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryChildren",desc = "删除目录")
    @ApiParam(name = "parentWikiCategoryId",desc = "parentWikiCategoryId",required = true)
    public HashMap<String, List<String>> findCategoryChildren(@NotNull String parentWikiCategoryId){
        HashMap<String, List<String>> categoryChildren = wikiCategoryService.findCategoryChildren(parentWikiCategoryId);

        return categoryChildren;
    }

    @RequestMapping(path="/findCategory",method = RequestMethod.POST)
    @ApiMethod(name = "findCategory",desc = "根据id 查询目录")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiCategory> findCategory(@NotNull String id){
        WikiCategory wikiCategory = wikiCategoryService.findCategory(id);

        return Result.ok(wikiCategory);
    }

    @RequestMapping(path="/findCategoryDocument",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryDocument",desc = "根据id 查询下级目录文档")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<List> findCategoryDocument(@NotNull String id){
        List category = wikiCategoryService.findCategoryDocument(id);

        return Result.ok(category);
    }

    @RequestMapping(path="/findAllCategory",method = RequestMethod.POST)
    @ApiMethod(name = "findAllCategory",desc = "查询所有目录")
    public Result<List<WikiCategory>> findAllCategory(){
        List<WikiCategory> wikiCategoryList = wikiCategoryService.findAllCategory();

        return Result.ok(wikiCategoryList);
    }

    @RequestMapping(path = "/findCategoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryList",desc = "条件查询目录")
    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<List<WikiCategory>> findCategoryList(@RequestBody @Valid @NotNull WikiCategoryQuery wikiCategoryQuery){
        List<WikiCategory> wikiCategoryList = wikiCategoryService.findCategoryList(wikiCategoryQuery);

        return Result.ok(wikiCategoryList);
    }

    @RequestMapping(path = "/findCategoryListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryListTree",desc = "条件查询目录树")
    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<List> findCategoryListTree(@RequestBody @Valid @NotNull WikiCategoryQuery wikiCategoryQuery){
        List categoryListTree = wikiCategoryService.findCategoryListTree(wikiCategoryQuery);
        return Result.ok(categoryListTree);
    }

    @RequestMapping(path = "/findCategoryListTreeById",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryListTreeById",desc = "条件查询目录树")
    @ApiParam(name = "category",desc = "category",required = true)
    public Result<List<Object>> findCategoryListTreeById(@NotNull String id, String treePath){
        List categoryListTree = wikiCategoryService.findCategoryListTreeById(id, treePath);
        return Result.ok(categoryListTree);
    }

    @RequestMapping(path = "/findCategoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryPage",desc = "条件分页查询目录")
    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<Pagination<WikiCategory>> findCategoryPage(@RequestBody @Valid @NotNull WikiCategoryQuery wikiCategoryQuery){
        Pagination<WikiCategory> pagination = wikiCategoryService.findCategoryPage(wikiCategoryQuery);

        return Result.ok(pagination);
    }

}