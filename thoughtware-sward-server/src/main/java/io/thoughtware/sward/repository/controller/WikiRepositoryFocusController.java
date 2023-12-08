package io.thoughtware.sward.repository.controller;

import io.thoughtware.sward.repository.model.WikiRepositoryFocus;
import io.thoughtware.sward.repository.model.WikiRepositoryFocusQuery;
import io.thoughtware.sward.repository.service.WikiRepositoryFocusService;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
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
import java.util.List;

/**
 * RepositoryFocusController
 */
@RestController
@RequestMapping("/repositoryFocus")
@Api(name = "RepositoryFocusController",desc = "RepositoryFocusController")
public class WikiRepositoryFocusController {

    private static Logger logger = LoggerFactory.getLogger(WikiRepositoryFocusController.class);

    @Autowired
    private WikiRepositoryFocusService wikiRepositoryFocusService;

    @RequestMapping(path="/createRepositoryFocus",method = RequestMethod.POST)
    @ApiMethod(name = "createRepositoryFocus",desc = "createRepositoryFocus")
    @ApiParam(name = "repositoryFocus",desc = "repositoryFocus",required = true)
    public Result<String> createRepositoryFocus(@RequestBody @NotNull @Valid WikiRepositoryFocus wikiRepositoryFocus){
        String id = wikiRepositoryFocusService.createRepositoryFocus(wikiRepositoryFocus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRepositoryFocus",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepositoryFocus",desc = "updateRepositoryFocus")
    @ApiParam(name = "repositoryFocus",desc = "repositoryFocus",required = true)
    public Result<Void> updateRepositoryFocus(@RequestBody @NotNull @Valid WikiRepositoryFocus wikiRepositoryFocus){
        wikiRepositoryFocusService.updateRepositoryFocus(wikiRepositoryFocus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRepositoryFocus",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepositoryFocus",desc = "deleteRepositoryFocus")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRepositoryFocus(@NotNull String id){
        wikiRepositoryFocusService.deleteRepositoryFocus(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRepositoryFocusByCondition",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepositoryFocusByCondition",desc = "deleteRepositoryFocusByCondition")
    @ApiParam(name = "repositoryFocusQuery",desc = "repositoryFocusQuery",required = true)
    public Result<Void> deleteRepositoryFocusByCondition(@RequestBody @Valid @NotNull WikiRepositoryFocusQuery wikiRepositoryFocusQuery){
        wikiRepositoryFocusService.deleteRepositoryFocusByCondition(wikiRepositoryFocusQuery);

        return Result.ok();
    }

    @RequestMapping(path="/findRepositoryFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryFocus",desc = "findRepositoryFocus")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiRepositoryFocus> findRepositoryFocus(@NotNull String id){
        WikiRepositoryFocus wikiRepositoryFocus = wikiRepositoryFocusService.findRepositoryFocus(id);

        return Result.ok(wikiRepositoryFocus);
    }

    @RequestMapping(path="/findAllRepositoryFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepositoryFocus",desc = "findAllRepositoryFocus")
    public Result<List<WikiRepositoryFocus>> findAllRepositoryFocus(){
        List<WikiRepositoryFocus> wikiRepositoryFocusList = wikiRepositoryFocusService.findAllRepositoryFocus();

        return Result.ok(wikiRepositoryFocusList);
    }

    @RequestMapping(path = "/findRepositoryFocusList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryFocusList",desc = "findRepositoryFocusList")
    @ApiParam(name = "repositoryFocusQuery",desc = "repositoryFocusQuery",required = true)
    public Result<List<WikiRepositoryFocus>> findRepositoryFocusList(@RequestBody @Valid @NotNull WikiRepositoryFocusQuery wikiRepositoryFocusQuery){
        List<WikiRepositoryFocus> wikiRepositoryFocusList = wikiRepositoryFocusService.findRepositoryFocusList(wikiRepositoryFocusQuery);

        return Result.ok(wikiRepositoryFocusList);
    }

    @RequestMapping(path = "/findRepositoryFocusPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryFocusPage",desc = "findRepositoryFocusPage")
    @ApiParam(name = "repositoryFocusQuery",desc = "repositoryFocusQuery",required = true)
    public Result<Pagination<WikiRepositoryFocus>> findRepositoryFocusPage(@RequestBody @Valid @NotNull WikiRepositoryFocusQuery wikiRepositoryFocusQuery){
        Pagination<WikiRepositoryFocus> pagination = wikiRepositoryFocusService.findRepositoryFocusPage(wikiRepositoryFocusQuery);

        return Result.ok(pagination);
    }

}
