package io.tiklab.kanass.support.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.support.model.Icon;
import io.tiklab.kanass.support.model.IconQuery;
import io.tiklab.kanass.support.service.IconService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 图标控制器
 */
@RestController
@RequestMapping("/icon")
@Api(name = "IconController",desc = "IconController")
public class IconController {

    private static Logger logger = LoggerFactory.getLogger(IconController.class);

    @Autowired
    private IconService iconService;

    @RequestMapping(path="/createIcon",method = RequestMethod.POST)
    @ApiMethod(name = "createIcon",desc = "创建图标")
    @ApiParam(name = "icon",desc = "图标模型",required = true)
    public Result<String> createIcon(@RequestBody @NotNull @Valid Icon icon){
        String id = iconService.createIcon(icon);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateIcon",method = RequestMethod.POST)
    @ApiMethod(name = "updateIcon",desc = "更新图标")
    @ApiParam(name = "icon",desc = "图标模型",required = true)
    public Result<Void> updateIcon(@RequestBody @NotNull @Valid Icon icon){
        iconService.updateIcon(icon);

        return Result.ok();
    }

    @RequestMapping(path="/deleteIcon",method = RequestMethod.POST)
    @ApiMethod(name = "deleteIcon",desc = "根据id删除图标")
    @ApiParam(name = "id",desc = "图标id",required = true)
    public Result<Void> deleteIcon(@NotNull String id){
        iconService.deleteIcon(id);

        return Result.ok();
    }

    @RequestMapping(path="/findIcon",method = RequestMethod.POST)
    @ApiMethod(name = "findIcon",desc = "根据id 查找图标")
    @ApiParam(name = "id",desc = "图标id",required = true)
    public Result<Icon> findIcon(@NotNull String id){
        Icon icon = iconService.findIcon(id);

        return Result.ok(icon);
    }

    @RequestMapping(path="/findAllIcon",method = RequestMethod.POST)
    @ApiMethod(name = "findAllIcon",desc = "查找所有图标")
    public Result<List<Icon>> findAllIcon(){
        List<Icon> iconList = iconService.findAllIcon();

        return Result.ok(iconList);
    }

    @RequestMapping(path = "/findIconList",method = RequestMethod.POST)
    @ApiMethod(name = "findIconList",desc = "查询图标列表")
    @ApiParam(name = "iconQuery",desc = "图标搜索条件模型",required = true)
    public Result<List<Icon>> findIconList(@RequestBody @Valid @NotNull IconQuery iconQuery){
        List<Icon> iconList = iconService.findIconList(iconQuery);

        return Result.ok(iconList);
    }

    @RequestMapping(path = "/findIconPage",method = RequestMethod.POST)
    @ApiMethod(name = "findIconPage",desc = "按分页查询图标")
    @ApiParam(name = "iconQuery",desc = "图标搜索条件模型",required = true)
    public Result<Pagination<Icon>> findIconPage(@RequestBody @Valid @NotNull IconQuery iconQuery){
        Pagination<Icon> pagination = iconService.findIconPage(iconQuery);

        return Result.ok(pagination);
    }

}
