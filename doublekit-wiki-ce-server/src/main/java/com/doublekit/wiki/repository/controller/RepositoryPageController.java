package com.doublekit.wiki.repository.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.wiki.repository.model.RepositoryPage;
import com.doublekit.wiki.repository.model.RepositoryPageQuery;
import com.doublekit.wiki.repository.service.RepositoryPageService;
import com.doublekit.common.Pagination;
import com.doublekit.common.Result;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * RepositoryPageController
 */
@RestController
@RequestMapping("/repositoryPage")
@Api(name = "RepositoryPageController",desc = "RepositoryPageController")
public class RepositoryPageController {

    private static Logger logger = LoggerFactory.getLogger(RepositoryPageController.class);

    @Autowired
    private RepositoryPageService repositoryPageService;

    @RequestMapping(path="/createRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "createRepositoryPage",desc = "createRepositoryPage")
    @ApiParam(name = "repositoryPage",desc = "repositoryPage",required = true)
    public Result<String> createRepositoryPage(@RequestBody @NotNull @Valid RepositoryPage repositoryPage){
        String id = repositoryPageService.createRepositoryPage(repositoryPage);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepositoryPage",desc = "updateRepositoryPage")
    @ApiParam(name = "repositoryPage",desc = "repositoryPage",required = true)
    public Result<Void> updateRepositoryPage(@RequestBody @NotNull @Valid RepositoryPage repositoryPage){
        repositoryPageService.updateRepositoryPage(repositoryPage);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepositoryPage",desc = "deleteRepositoryPage")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRepositoryPage(@NotNull String id){
        repositoryPageService.deleteRepositoryPage(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryPage",desc = "findRepositoryPage")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RepositoryPage> findRepositoryPage(@NotNull String id){
        RepositoryPage repositoryPage = repositoryPageService.findRepositoryPage(id);

        return Result.ok(repositoryPage);
    }

    @RequestMapping(path="/findAllRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepositoryPage",desc = "findAllRepositoryPage")
    public Result<List<RepositoryPage>> findAllRepositoryPage(){
        List<RepositoryPage> repositoryPageList = repositoryPageService.findAllRepositoryPage();

        return Result.ok(repositoryPageList);
    }

    @RequestMapping(path = "/findRepositoryPageList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryPageList",desc = "findRepositoryPageList")
    @ApiParam(name = "repositoryPageQuery",desc = "repositoryPageQuery",required = true)
    public Result<List<RepositoryPage>> findRepositoryPageList(@RequestBody @Valid @NotNull RepositoryPageQuery repositoryPageQuery){
        List<RepositoryPage> repositoryPageList = repositoryPageService.findRepositoryPageList(repositoryPageQuery);

        return Result.ok(repositoryPageList);
    }

    @RequestMapping(path = "/findRepositoryPagePage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryPagePage",desc = "findRepositoryPagePage")
    @ApiParam(name = "repositoryPageQuery",desc = "repositoryPageQuery",required = true)
    public Result<Pagination<RepositoryPage>> findRepositoryPagePage(@RequestBody @Valid @NotNull RepositoryPageQuery repositoryPageQuery){
        Pagination<RepositoryPage> pagination = repositoryPageService.findRepositoryPagePage(repositoryPageQuery);

        return Result.ok(pagination);
    }

}
