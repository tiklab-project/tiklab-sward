package net.tiklab.kanass.document.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.kanass.document.model.Comment;
import net.tiklab.kanass.document.model.CommentQuery;

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
     * @param type  转发后查看评论
    * @return
    */
    List<Comment> findCommentList(CommentQuery commentQuery);

    /**
    * 按分页查询
    * @param commentQuery
    * @return
    */
    Pagination<Comment> findCommentPage(CommentQuery commentQuery);

}