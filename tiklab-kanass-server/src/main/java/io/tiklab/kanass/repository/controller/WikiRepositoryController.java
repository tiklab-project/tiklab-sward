package io.tiklab.kanass.repository.controller;

import io.tiklab.kanass.repository.model.WikiRepository;
import io.tiklab.kanass.repository.model.WikiRepositoryQuery;
import io.tiklab.kanass.repository.service.WikiRepositoryService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
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
 * RepositoryController
 */
@RestController
@RequestMapping("/repository")
@Api(name = "RepositoryController",desc = "")
public class WikiRepositoryController {
    //
    private static Logger logger = LoggerFactory.getLogger(WikiRepositoryController.class);

    @Autowired
    private WikiRepositoryService wikiRepositoryService;

    @RequestMapping(path="/createRepository",method = RequestMethod.POST)
    @ApiMethod(name = "createRepository",desc = "createRepository")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<String> createRepository(@RequestBody @NotNull @Valid WikiRepository wikiRepository){
        String id = wikiRepositoryService.createRepository(wikiRepository);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRepository",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepository",desc = "updateRepository")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<Void> updateRepository(@RequestBody @NotNull @Valid WikiRepository wikiRepository){
        wikiRepositoryService.updateRepository(wikiRepository);


        return Result.ok();
    }

    @RequestMapping(path="/deleteRepository",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepository",desc = "deleteRepository")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRepository(@NotNull String id){
        wikiRepositoryService.deleteRepository(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findRepository",desc = "findRepository")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiRepository> findRepository(@NotNull String id){
        WikiRepository wikiRepository = wikiRepositoryService.findRepository(id);

        return Result.ok(wikiRepository);
    }

    @RequestMapping(path="/findAllRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepository",desc = "findAllRepository")
    public Result<List<WikiRepository>> findAllRepository(){
        List<WikiRepository> wikiRepositoryList = wikiRepositoryService.findAllRepository();

        return Result.ok(wikiRepositoryList);
    }

    @RequestMapping(path = "/findRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryList",desc = "findRepositoryList")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<List<WikiRepository>> findRepositoryList(@RequestBody @Valid @NotNull WikiRepositoryQuery wikiRepositoryQuery){
        List<WikiRepository> wikiRepositoryList = wikiRepositoryService.findRepositoryList(wikiRepositoryQuery);

        return Result.ok(wikiRepositoryList);
    }

    @RequestMapping(path = "/findRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryPage",desc = "findRepositoryPage")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<Pagination<WikiRepository>> findRepositoryPage(@RequestBody @Valid @NotNull WikiRepositoryQuery wikiRepositoryQuery){
        Pagination<WikiRepository> pagination = wikiRepositoryService.findRepositoryPage(wikiRepositoryQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findRecentRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentRepositoryList",desc = "findRepositoryPage")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<List<WikiRepository>> findRecentRepositoryList(@RequestBody @Valid @NotNull WikiRepositoryQuery documentRecentQuery){
        List<WikiRepository> pagination = wikiRepositoryService.findRecentRepositoryList(documentRecentQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findFocusRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findFocusRepositoryList",desc = "findRepositoryPage")
    @ApiParam(name = "repositoryQuery",desc = "repositoryQuery",required = true)
    public Result<List<WikiRepository>> findFocusRepositoryList(@RequestBody @Valid @NotNull WikiRepositoryQuery wikiRepositoryQuery){
        List<WikiRepository> pagination = wikiRepositoryService.findFocusRepositoryList(wikiRepositoryQuery);

        return Result.ok(pagination);
    }


}
