package io.tiklab.sward.document.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.sward.document.model.ShareRelation;
import io.tiklab.sward.document.model.ShareRelationQuery;
import io.tiklab.sward.document.service.ShareRelationService;
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
 * ShareRelationController
 */
@RestController
@RequestMapping("/shareRelation")
@Api(name = "ShareRelationController",desc = "文档分享管理")
public class ShareRelationController {

    private static Logger logger = LoggerFactory.getLogger(ShareRelationController.class);

    @Autowired
    private ShareRelationService shareRelationService;

    @RequestMapping(path="/createShareRelation",method = RequestMethod.POST)
    @ApiMethod(name = "createShareRelation",desc = "createShareRelation")
    @ApiParam(name = "shareRelation",desc = "shareRelation",required = true)
    public Result<String> createShareRelation(@RequestBody @NotNull @Valid ShareRelation shareRelation){
        String id = shareRelationService.createShareRelation(shareRelation);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateShareRelation",method = RequestMethod.POST)
    @ApiMethod(name = "updateShareRelation",desc = "updateShareRelation")
    @ApiParam(name = "shareRelation",desc = "shareRelation",required = true)
    public Result<Void> updateShareRelation(@RequestBody @NotNull @Valid ShareRelation shareRelation){
        shareRelationService.updateShareRelation(shareRelation);

        return Result.ok();
    }

    @RequestMapping(path="/deleteShareRelation",method = RequestMethod.POST)
    @ApiMethod(name = "deleteShareRelation",desc = "deleteShareRelation")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteShareRelation(@NotNull String id){
        shareRelationService.deleteShareRelation(id);

        return Result.ok();
    }

    @RequestMapping(path="/findShareRelation",method = RequestMethod.POST)
    @ApiMethod(name = "findShareRelation",desc = "findShareRelation")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ShareRelation> findShareRelation(@NotNull String id){
        ShareRelation shareRelation = shareRelationService.findShareRelation(id);

        return Result.ok(shareRelation);
    }

    @RequestMapping(path="/findAllShareRelation",method = RequestMethod.POST)
    @ApiMethod(name = "findAllShareRelation",desc = "findAllShareRelation")
    public Result<List<ShareRelation>> findAllShareRelation(){
        List<ShareRelation> shareRelationList = shareRelationService.findAllShareRelation();

        return Result.ok(shareRelationList);
    }

    @RequestMapping(path = "/findShareRelationList",method = RequestMethod.POST)
    @ApiMethod(name = "findShareRelationList",desc = "findShareRelationList")
    @ApiParam(name = "shareRelationQuery",desc = "shareRelationQuery",required = true)
    public Result<List<ShareRelation>> findShareRelationList(@RequestBody @Valid @NotNull ShareRelationQuery shareRelationQuery){
        List<ShareRelation> shareRelationList = shareRelationService.findShareRelationList(shareRelationQuery);

        return Result.ok(shareRelationList);
    }

    @RequestMapping(path = "/findShareRelationPage",method = RequestMethod.POST)
    @ApiMethod(name = "findShareRelationPage",desc = "findShareRelationPage")
    @ApiParam(name = "shareRelationQuery",desc = "shareRelationQuery",required = true)
    public Result<Pagination<ShareRelation>> findShareRelationPage(@RequestBody @Valid @NotNull ShareRelationQuery shareRelationQuery){
        Pagination<ShareRelation> pagination = shareRelationService.findShareRelationPage(shareRelationQuery);

        return Result.ok(pagination);
    }

}
