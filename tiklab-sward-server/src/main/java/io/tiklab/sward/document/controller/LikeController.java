package io.tiklab.sward.document.controller;

import io.tiklab.sward.document.model.Like;
import io.tiklab.sward.document.model.LikeQuery;
import io.tiklab.sward.document.service.LikeService;
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
import java.util.List;

/**
 * LikeController
 */
@RestController
@RequestMapping("/like")
@Api(name = "LikeController",desc = "点赞管理")
public class LikeController {

    private static Logger logger = LoggerFactory.getLogger(LikeController.class);

    @Autowired
    private LikeService likeService;

    @RequestMapping(path="/createLike",method = RequestMethod.POST)
    //@ApiMethod(name = "createLike",desc = "添加点赞")
    //@ApiParam(name = "like",desc = "like",required = true)
    public Result<String> createLike(@RequestBody @NotNull @Valid Like like){
        String id = likeService.createLike(like);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateLike",method = RequestMethod.POST)
    //@ApiMethod(name = "updateLike",desc = "updateLike")
    //@ApiParam(name = "like",desc = "like",required = true)
    public Result<Void> updateLike(@RequestBody @NotNull @Valid Like like){
        likeService.updateLike(like);

        return Result.ok();
    }

    @RequestMapping(path="/deleteLike",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteLike",desc = "取消点赞")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteLike(@NotNull String id){
        likeService.deleteLike(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteLikeCondition",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteLikeCondition",desc = "取消点赞")
    //@ApiParam(name = "deleteCondition",desc = "deleteCondition",required = true)
    public Result<Void> deleteLikeCondition(@RequestBody @NotNull @Valid LikeQuery likeQuery){
        likeService.deleteLikeCondition(likeQuery);

        return Result.ok();
    }

    @RequestMapping(path="/findLike",method = RequestMethod.POST)
    //@ApiMethod(name = "findLike",desc = "findLike")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<Like> findLike(@NotNull String id){
        Like like = likeService.findLike(id);

        return Result.ok(like);
    }

    @RequestMapping(path="/findAllLike",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllLike",desc = "findAllLike")
    public Result<List<Like>> findAllLike(){
        List<Like> likeList = likeService.findAllLike();

        return Result.ok(likeList);
    }

    @RequestMapping(path = "/findLikeList",method = RequestMethod.POST)
    //@ApiMethod(name = "findLikeList",desc = "findLikeList")
    //@ApiParam(name = "likeQuery",desc = "likeQuery",required = true)
    public Result<List<Like>> findLikeList(@RequestBody @Valid @NotNull LikeQuery likeQuery){
        List<Like> likeList = likeService.findLikeList(likeQuery);

        return Result.ok(likeList);
    }

    @RequestMapping(path = "/findLikePage",method = RequestMethod.POST)
    //@ApiMethod(name = "findLikePage",desc = "findLikePage")
    //@ApiParam(name = "likeQuery",desc = "likeQuery",required = true)
    public Result<Pagination<Like>> findLikePage(@RequestBody @Valid @NotNull LikeQuery likeQuery){
        Pagination<Like> pagination = likeService.findLikePage(likeQuery);

        return Result.ok(pagination);
    }

}
