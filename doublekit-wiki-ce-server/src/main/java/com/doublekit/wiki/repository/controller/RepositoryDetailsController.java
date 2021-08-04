package com.doublekit.wiki.repository.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.wiki.repository.model.RepositoryDetails;
import com.doublekit.wiki.repository.model.RepositoryDetailsQuery;
import com.doublekit.wiki.repository.service.RepositoryDetailsService;
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
 * RepositoryDetailsController
 */
@RestController
@RequestMapping("/repositoryDetails")
@Api(name = "RepositoryDetailsController",desc = "RepositoryDetailsController")
public class RepositoryDetailsController {

    private static Logger logger = LoggerFactory.getLogger(RepositoryDetailsController.class);

    @Autowired
    private RepositoryDetailsService repositoryDetailsService;

    @RequestMapping(path="/createRepositoryDetails",method = RequestMethod.POST)
    @ApiMethod(name = "createRepositoryDetails",desc = "createRepositoryDetails")
    @ApiParam(name = "repositoryDetails",desc = "repositoryDetails",required = true)
    public Result<String> createRepositoryDetails(@RequestBody @NotNull @Valid RepositoryDetails repositoryDetails){
        String id = repositoryDetailsService.createRepositoryDetails(repositoryDetails);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRepositoryDetails",method = RequestMethod.POST)
    @ApiMethod(name = "updateRepositoryDetails",desc = "updateRepositoryDetails")
    @ApiParam(name = "repositoryDetails",desc = "repositoryDetails",required = true)
    public Result<Void> updateRepositoryDetails(@RequestBody @NotNull @Valid RepositoryDetails repositoryDetails){
        repositoryDetailsService.updateRepositoryDetails(repositoryDetails);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRepositoryDetails",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRepositoryDetails",desc = "deleteRepositoryDetails")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRepositoryDetails(@NotNull String id){
        repositoryDetailsService.deleteRepositoryDetails(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRepositoryDetails",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryDetails",desc = "findRepositoryDetails")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RepositoryDetails> findRepositoryDetails(@NotNull String id){
        RepositoryDetails repositoryDetails = repositoryDetailsService.findRepositoryDetails(id);

        return Result.ok(repositoryDetails);
    }

    @RequestMapping(path="/findAllRepositoryDetails",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepositoryDetails",desc = "findAllRepositoryDetails")
    public Result<List<RepositoryDetails>> findAllRepositoryDetails(){
        List<RepositoryDetails> repositoryDetailsList = repositoryDetailsService.findAllRepositoryDetails();

        return Result.ok(repositoryDetailsList);
    }

    @RequestMapping(path = "/findRepositoryDetailsList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryDetailsList",desc = "findRepositoryDetailsList")
    @ApiParam(name = "repositoryDetailsQuery",desc = "repositoryDetailsQuery",required = true)
    public Result<List<RepositoryDetails>> findRepositoryDetailsList(@RequestBody @Valid @NotNull RepositoryDetailsQuery repositoryDetailsQuery){
        List<RepositoryDetails> repositoryDetailsList = repositoryDetailsService.findRepositoryDetailsList(repositoryDetailsQuery);

        return Result.ok(repositoryDetailsList);
    }

    @RequestMapping(path = "/findRepositoryDetailsPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRepositoryDetailsPage",desc = "findRepositoryDetailsPage")
    @ApiParam(name = "repositoryDetailsQuery",desc = "repositoryDetailsQuery",required = true)
    public Result<Pagination<RepositoryDetails>> findRepositoryDetailsPage(@RequestBody @Valid @NotNull RepositoryDetailsQuery repositoryDetailsQuery){
        Pagination<RepositoryDetails> pagination = repositoryDetailsService.findRepositoryDetailsPage(repositoryDetailsQuery);

        return Result.ok(pagination);
    }

}
