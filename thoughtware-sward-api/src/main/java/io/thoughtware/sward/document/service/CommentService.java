package io.thoughtware.sward.document.service;

import io.thoughtware.sward.document.model.Comment;
import io.thoughtware.sward.document.model.CommentQuery;
import io.thoughtware.core.page.Pagination;

import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* CommentService
*/
@JoinProvider(model = Comment.class)
public interface CommentService {

    /**
    * 创建
    * @param comment
    * @return
    */
    String createComment(@NotNull @Valid Comment comment);

    /**
    * 更新
    * @param comment
    */
    void updateComment(@NotNull @Valid Comment comment);

    /**
    * 删除
    * @param id
    */
    void deleteComment(@NotNull String id);
    void deleteCommentCondition(CommentQuery commentQuery);

    @FindOne
    Comment findOne(@NotNull String id);
    @FindList
    List<Comment> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    Comment findComment(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<Comment> findAllComment();

    /**
    * 查询列表
    * @param commentQuery
    * @return
    */
    List<Comment> findCommentList(CommentQuery commentQuery);

    Pagination<Comment> findCommentTreePage(CommentQuery commentQuery);
    /**
    * 按分页查询
    * @param commentQuery
    * @return
    */
    Pagination<Comment> findCommentPage(CommentQuery commentQuery);

}