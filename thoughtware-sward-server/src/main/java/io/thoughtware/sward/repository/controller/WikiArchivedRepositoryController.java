package io.thoughtware.sward.repository.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.sward.repository.model.WikiRepositoryQuery;
import io.thoughtware.sward.repository.service.WikiArchivedRepositoryService;
import io.thoughtware.sward.repository.service.WikiRepositoryService;
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
 * @pi.protocol: http
 * @pi.groupName: 知识库控制器
 */
@RestController
@RequestMapping("/repositoryArchived")
@Api(name = "WikiArchivedRepositoryController",desc = "")
public class WikiArchivedRepositoryController {

    private static Logger logger = LoggerFactory.getLogger(WikiArchivedRepositoryController.class);

    @Autowired
    private WikiArchivedRepositoryService wikiArchivedRepositoryService;

    /**
     * @pi.name:创建知识库
     * @pi.path:/repository/createRepository
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WikiRepository
     */
    @RequestMapping(path="/archivedRepository",method = RequestMethod.POST)
    @ApiMethod(name = "archivedRepository",desc = "创建知识库")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<String> createRepository(@RequestBody @NotNull @Valid WikiRepository wikiRepository){
        wikiArchivedRepositoryService.archivedRepository(wikiRepository);

        return Result.ok();
    }

    /**
     * @pi.name:更新项目
     * @pi.path:/repository/updateRepository
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WikiRepository
     */
    @RequestMapping(path="/recoverArchivedRepository",method = RequestMethod.POST)
    @ApiMethod(name = "recoverArchivedRepository",desc = "更新知识库")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<Void> updateRepository(@RequestBody @NotNull @Valid WikiRepository wikiRepository){
        wikiArchivedRepositoryService.recoverArchivedRepository(wikiRepository);

        return Result.ok();
    }

    @RequestMapping(path="/findArchivedRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findArchivedRepository",desc = "更新知识库")
    @ApiParam(name = "repository",desc = "repository",required = true)
    public Result<Void> findArchivedRepository(@RequestBody @NotNull @Valid WikiRepositoryQuery wikiRepositoryQuery){
        List<WikiRepository> recycleRepositoryList = wikiArchivedRepositoryService.findArchivedRepository(wikiRepositoryQuery);

        return Result.ok(recycleRepositoryList);
    }

}
