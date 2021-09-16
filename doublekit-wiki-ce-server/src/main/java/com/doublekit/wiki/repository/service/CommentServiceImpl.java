package com.doublekit.wiki.repository.service;

import com.doublekit.user.auth.passport.context.TicketContext;
import com.doublekit.user.auth.passport.context.TicketHolder;
import com.doublekit.user.auth.passport.model.Ticket;
import com.doublekit.user.user.model.User;
import com.doublekit.wiki.repository.dao.CommentDao;
import com.doublekit.wiki.repository.dao.LikeDao;
import com.doublekit.wiki.repository.entity.CommentPo;
import com.doublekit.wiki.repository.entity.LikePo;
import com.doublekit.wiki.repository.model.Comment;
import com.doublekit.wiki.repository.model.CommentQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
import com.doublekit.wiki.repository.model.Like;
import com.doublekit.wiki.repository.model.LikeQuery;
import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

/**
* CommentServiceImpl
*/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Autowired
    JoinQuery joinQuery;

    @Autowired
    LikeDao likeDao;

    @Override
    public String createComment(@NotNull @Valid Comment comment) {
        CommentPo commentPo = BeanMapper.map(comment, CommentPo.class);
        //添加评论人
        commentPo.setUser(findCreatUser());
        commentPo.setCreateTime(new Date());
        if (!ObjectUtils.isEmpty(commentPo.getParentCommentId())){
            CommentPo parentComment = commentDao.findComment(commentPo.getParentCommentId());
            //被回复的人
            commentPo.setAimAtUser(parentComment.getUser());
        }
        return commentDao.createComment(commentPo);
    }

    @Override
    public void updateComment(@NotNull @Valid Comment comment) {
        CommentPo commentPo = BeanMapper.map(comment, CommentPo.class);
        commentPo.setUpdateTime(new Date());
        commentDao.updateComment(commentPo);
    }

    @Override
    public void deleteComment(@NotNull String id) {
        commentDao.deleteComment(id);
    }

    @Override
    public Comment findOne(String id) {
        CommentPo commentPo = commentDao.findComment(id);

        Comment comment = BeanMapper.map(commentPo, Comment.class);
        return comment;
    }

    @Override
    public List<Comment> findList(List<String> idList) {
        List<CommentPo> commentPoList =  commentDao.findCommentList(idList);

        List<Comment> commentList =  BeanMapper.mapList(commentPoList,Comment.class);
        return commentList;
    }

    @Override
    public Comment findComment(@NotNull String id) {
        Comment comment = findOne(id);

        joinQuery.queryOne(comment);
        return comment;
    }

    @Override
    public List<Comment> findAllComment() {
        List<CommentPo> commentPoList =  commentDao.findAllComment();

        List<Comment> commentList =  BeanMapper.mapList(commentPoList,Comment.class);

        joinQuery.queryList(commentList);
        return commentList;
    }

    @Override
    public List<Comment> findCommentList(CommentQuery commentQuery,String type) {
        List<CommentPo> commentPoList = commentDao.findCommentList(commentQuery);


        List<Comment> commentList = BeanMapper.mapList(commentPoList,Comment.class);

        joinQuery.queryList(commentList);

        findLike(commentList,type);
        List<Comment> fistOneComment = findComment(commentList);

        return fistOneComment;
    }

    @Override
    public Pagination<Comment> findCommentPage(CommentQuery commentQuery) {
        Pagination<Comment> pg = new Pagination<>();

        Pagination<CommentPo>  pagination = commentDao.findCommentPage(commentQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Comment> commentList = BeanMapper.mapList(pagination.getDataList(),Comment.class);

        joinQuery.queryList(commentList);

        pg.setDataList(commentList);
        return pg;
    }


    /**
     * 查询用户（创建人）id
     * @param
     */
    public String findCreatUser(){
        String ticketId = TicketHolder.get();
        Ticket ticket = TicketContext.get(ticketId);
        return ticket.getUserId();
    }

    /**
     * 第一级以及下面的评论
     * @param
     */
    public List<Comment> findComment(List<Comment> commentList){
        List<Comment> collect = commentList.stream().filter(
                a -> ObjectUtils.isEmpty(a.getParentCommentId())).collect(Collectors.toList());
        for (Comment comment:collect){
            //第一层评论下面的所有评论
            List<Comment> collect1 = commentList.stream().filter(b -> comment.getId().equals(b.getFirstOneCommentId())).collect(Collectors.toList());
            comment.setCommentList(collect1);
            }
        return collect;
        }


    /**
     *查询点赞
     * @param
     */
    public void findLike(List<Comment> commentList,String type){
        for (Comment comment:commentList){
            LikeQuery likeQuery = new LikeQuery();
            likeQuery.setToWhomId(comment.getId());
            likeQuery.setLikeType("com");
            //查询点赞数
            List<LikePo> likeList = likeDao.findLikeList(likeQuery);
            if (CollectionUtils.isNotEmpty(likeList)){
                if ("view".equals(type)){
                    comment.setIsLike("false");
                }else {
                    //根据用户id判断该用户是否点赞了
                    List<LikePo> collect1 = likeList.stream().filter(a -> findCreatUser().equals(a.getLikeUser())).collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(collect1)){
                        comment.setIsLike("true");
                    }else {
                        comment.setIsLike("false");
                    }
                }
                List<Like> likes = BeanMapper.mapList(likeList, Like.class);
                joinQuery.queryList(likes);
                List<User> userList = likes.stream().map(Like::getLikeUser).collect(Collectors.toList());
                //取点赞人名字
                List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
                //点赞数
                comment.setLikenumInt(likeList.size());
                //点赞人
                comment.setLikeUserList(collect);
            }else {
                comment.setIsLike("false");
            }
        }
    }

}