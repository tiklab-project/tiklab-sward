package io.tiklab.sward.support.controller;

import io.tiklab.sward.support.model.SystemUrlQuery;
import io.tiklab.sward.support.service.SystemUrlService;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.sward.support.model.SystemUrl;
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
 * 最近访问的控制器
 */
@RestController
@RequestMapping("/systemUrl")
@Api(name = "SystemUrlController",desc = "SystemUrlController")
public class SystemUrlController {

    private static Logger logger = LoggerFactory.getLogger(SystemUrlController.class);

    @Autowired
    private SystemUrlService systemUrlService;

    @RequestMapping(path="/createSystemUrl",method = RequestMethod.POST)
    //@ApiMethod(name = "createSystemUrl",desc = "创建最近访问的")
    //@ApiParam(name = "systemUrl",desc = "最近访问的模型",required = true)
    public Result<String> createSystemUrl(@RequestBody @NotNull @Valid SystemUrl systemUrl){
        String id = systemUrlService.createSystemUrl(systemUrl);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateSystemUrl",method = RequestMethod.POST)
    //@ApiMethod(name = "updateSystemUrl",desc = "更新最近访问的")
    //@ApiParam(name = "systemUrl",desc = "最近访问的模型",required = true)
    public Result<Void> updateSystemUrl(@RequestBody @NotNull @Valid SystemUrl systemUrl){
        systemUrlService.updateSystemUrl(systemUrl);

        return Result.ok();
    }

    @RequestMapping(path="/deleteSystemUrl",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteSystemUrl",desc = "删除最近访问的")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteSystemUrl(@NotNull String id){
        systemUrlService.deleteSystemUrl(id);

        return Result.ok();
    }

    @RequestMapping(path="/findSystemUrl",method = RequestMethod.POST)
    //@ApiMethod(name = "findSystemUrl",desc = "根据id 查找最近访问的")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<SystemUrl> findSystemUrl(@NotNull String id){
        SystemUrl systemUrl = systemUrlService.findSystemUrl(id);

        return Result.ok(systemUrl);
    }

    @RequestMapping(path="/findAllSystemUrl",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllSystemUrl",desc = "查找所有最近访问的")
    public Result<List<SystemUrl>> findAllSystemUrl(){
        List<SystemUrl> systemUrlList = systemUrlService.findAllSystemUrl();

        return Result.ok(systemUrlList);
    }

    @RequestMapping(path = "/findSystemUrlList",method = RequestMethod.POST)
    //@ApiMethod(name = "findSystemUrlList",desc = "查询最近访问列表")
    //@ApiParam(name = "systemUrlQuery",desc = "查找最近访问的添加模型",required = true)
    public Result<List<SystemUrl>> findSystemUrlList(@RequestBody @Valid @NotNull SystemUrlQuery systemUrlQuery){
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);

        return Result.ok(systemUrlList);
    }

    @RequestMapping(path = "/findSystemUrlPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findSystemUrlPage",desc = "按分页查询最近访问的")
    //@ApiParam(name = "systemUrlQuery",desc = "查找最近访问的添加模型",required = true)
    public Result<Pagination<SystemUrl>> findSystemUrlPage(@RequestBody @Valid @NotNull SystemUrlQuery systemUrlQuery){
        Pagination<SystemUrl> pagination = systemUrlService.findSystemUrlPage(systemUrlQuery);

        return Result.ok(pagination);
    }
    
}
