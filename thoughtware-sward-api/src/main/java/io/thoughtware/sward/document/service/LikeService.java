package io.thoughtware.sward.document.service;

import io.thoughtware.sward.document.model.Like;
import io.thoughtware.sward.document.model.LikeQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* LikeService
*/
public interface LikeService {

    /**
    * 创建
    * @param like
    * @return
    */
    String createLike(@NotNull @Valid Like like);

    /**
    * 更新
    * @param like
    */
    void updateLike(@NotNull @Valid Like like);

    /**
    * 删除
    * @param id
    */
    void deleteLike(@NotNull String id);
    void deleteLikeCondition(@NotNull LikeQuery likeQuery);
    Like findOne(@NotNull String id);

    List<Like> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    Like findLike(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<Like> findAllLike();

    /**
    * 查询列表
    * @param likeQuery
    * @return
    */
    List<Like> findLikeList(LikeQuery likeQuery);

    /**
    * 按分页查询
    * @param likeQuery
    * @return
    */
    Pagination<Like> findLikePage(LikeQuery likeQuery);

}