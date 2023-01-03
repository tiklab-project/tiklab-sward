package net.tiklab.kanass.repository.controller;

import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.kanass.repository.model.RepositoryFocus;
import net.tiklab.kanass.repository.model.RepositoryFocusQuery;
import net.tiklab.kanass.repository.service.RepositoryFocusService;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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
public class RepositoryFocusController {

    private static Logger logger = LoggerFactory.getLogger(RepositoryFocusController.class);

    @Autowired
    private RepositoryFocusService repositoryFocusService;

    @RequestMapping(path="/createRepositoryFocus",method = RequestMethod.POST)
    @ApiMethod(name = "createRepositoryFocus",desc = "createRepositoryFocus")
    @ApiParam(name = "repositoryFocus",desc = "repositoryFocus",required = true)
    public Result<String> createRepositoryFocus(@RequestBody @NotNull @Valid RepositoryFocus repositoryFocus){
        String id = repositoryFocusService.createRepositoryFocus(repositoryFocus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRepositoryFocus",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepositoryFocus",desc = "updateRepositoryFocus")
    @ApiParam(name = "repositoryFocus",desc = "repositoryFocus",required = true)
    public Result<Void> updateRepositoryFocus(@RequestBody @NotNull @Valid RepositoryFocus repositoryFocus){
        repositoryFocusService.updateRepositoryFocus(repositoryFocus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRepositoryFocus",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepositoryFocus",desc = "deleteRepositoryFocus")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRepositoryFocus(@NotNull String id){
        repositoryFocusService.deleteRepositoryFocus(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRepositoryFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryFocus",desc = "findRepositoryFocus")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RepositoryFocus> findRepositoryFocus(@NotNull String id){
        RepositoryFocus repositoryFocus = repositoryFocusService.findRepositoryFocus(id);

        return Result.ok(repositoryFocus);
    }

    @RequestMapping(path="/findAllRepositoryFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepositoryFocus",desc = "findAllRepositoryFocus")
    public Result<List<RepositoryFocus>> findAllRepositoryFocus(){
        List<RepositoryFocus> repositoryFocusList = repositoryFocusService.findAllRepositoryFocus();

        return Result.ok(repositoryFocusList);
    }

    @RequestMapping(path = "/findRepositoryFocusList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryFocusList",desc = "findRepositoryFocusList")
    @ApiParam(name = "repositoryFocusQuery",desc = "repositoryFocusQuery",required = true)
    public Result<List<RepositoryFocus>> findRepositoryFocusList(@RequestBody @Valid @NotNull RepositoryFocusQuery repositoryFocusQuery){
        List<RepositoryFocus> repositoryFocusList = repositoryFocusService.findRepositoryFocusList(repositoryFocusQuery);

        return Result.ok(repositoryFocusList);
    }

    @RequestMapping(path = "/findRepositoryFocusPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryFocusPage",desc = "findRepositoryFocusPage")
    @ApiParam(name = "repositoryFocusQuery",desc = "repositoryFocusQuery",required = true)
    public Result<Pagination<RepositoryFocus>> findRepositoryFocusPage(@RequestBody @Valid @NotNull RepositoryFocusQuery repositoryFocusQuery){
        Pagination<RepositoryFocus> pagination = repositoryFocusService.findRepositoryFocusPage(repositoryFocusQuery);

        return Result.ok(pagination);
    }

}
