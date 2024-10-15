package io.tiklab.sward.document.service;

import io.tiklab.sward.document.dao.CommentDao;
import io.tiklab.sward.document.dao.LikeDao;
import io.tiklab.sward.document.entity.CommentEntity;
import io.tiklab.sward.document.entity.LikeEntity;
import io.tiklab.sward.document.model.Comment;
import io.tiklab.sward.document.model.CommentQuery;
import io.tiklab.sward.document.model.Like;
import io.tiklab.sward.document.model.LikeQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
* CommentServiceImpl
*/
@Service
@Exporter
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    LikeDao likeDao;

    @Override
    public String createComment(@NotNull @Valid Comment comment) {
        CommentEntity commentEntity = BeanMapper.map(comment, CommentEntity.class);

        //添加评论人
//        commentEntity.setUser(findCreatUser());
//        commentEntity.setCreateTime(new Date());
        if (!ObjectUtils.isEmpty(commentEntity.getParentCommentId())){
            CommentEntity parentComment = commentDao.findComment(commentEntity.getParentCommentId());
            //被回复的人
            commentEntity.setAimAtUser(parentComment.getUserId());
        }
        return commentDao.createComment(commentEntity);
    }

    @Override
    public void updateComment(@NotNull @Valid Comment comment) {

        CommentEntity commentEntity = BeanMapper.map(comment, CommentEntity.class);
//        commentEntity.setUpdateTime(new Date());
        commentDao.updateComment(commentEntity);
    }

    @Override
    public void deleteComment(@NotNull String id) {
        commentDao.deleteComment(id);
    }

    @Override
    public void deleteCommentCondition(@NotNull @Valid CommentQuery commentQuery) {
        commentDao.deleteCommentCondition(commentQuery);
    }

    @Override
    public Comment findOne(String id) {
        CommentEntity commentEntity = commentDao.findComment(id);

        Comment comment = BeanMapper.map(commentEntity, Comment.class);
        return comment;
    }

    @Override
    public List<Comment> findList(List<String> idList) {
        List<CommentEntity> commentEntityList =  commentDao.findCommentList(idList);

        List<Comment> commentList =  BeanMapper.mapList(commentEntityList,Comment.class);
        return commentList;
    }

    @Override
    public Comment findComment(@NotNull String id) {
        Comment comment = findOne(id);

        joinTemplate.joinQuery(comment);
        return comment;
    }

    @Override
    public List<Comment> findAllComment() {
        List<CommentEntity> commentEntityList =  commentDao.findAllComment();

        List<Comment> commentList =  BeanMapper.mapList(commentEntityList,Comment.class);

        joinTemplate.joinQuery(commentList);
        return commentList;
    }

    @Override
    public List<Comment> findCommentList(CommentQuery commentQuery) {
        List<CommentEntity> commentEntityList = commentDao.findCommentList(commentQuery);

        List<Comment> commentList = BeanMapper.mapList(commentEntityList,Comment.class);

        joinTemplate.joinQuery(commentList);

        return commentList;
    }


    public Pagination<Comment> findCommentTreePage1(CommentQuery commentQuery) {
        commentQuery.setFirstCommentNull(true);
        Pagination<CommentEntity> pagination = commentDao.findCommentPage(commentQuery);

        List<Comment> commentList = BeanMapper.mapList(pagination.getDataList(),Comment.class);
        joinTemplate.joinQuery(commentList);

        for (Comment comment : commentList) {
            String id = comment.getId();
            commentQuery.setFirstCommentNull(false);
            commentQuery.setFirstOneCommentId(id);
            List<Comment> commentList1 = findCommentList(commentQuery);
            comment.setCommentList(commentList1);
        }

        return PaginationBuilder.build(pagination,commentList);
    }

    @Override
    public Pagination<Comment> findCommentTreePage(CommentQuery commentQuery) {
        commentQuery.setFirstCommentNull(true);
        Pagination<CommentEntity> pagination = commentDao.findCommentPage(commentQuery);

        List<Comment> commentList = BeanMapper.mapList(pagination.getDataList(),Comment.class);
        joinTemplate.joinQuery(commentList);

        if(commentList.size() > 0){
            String commentIds = "(" + commentList.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";
            List<CommentEntity> commentChildrenList = commentDao.findCommentChildren(commentIds);
            joinTemplate.joinQuery(commentChildrenList);
            for (Comment comment : commentList) {
                String id = comment.getId();
//            commentQuery.setFirstCommentNull(false);
//            commentQuery.setFirstOneCommentId(id);
                List<CommentEntity> listEntity = commentChildrenList.stream().filter(chidrenComment -> chidrenComment.getParentCommentId().equals(id)).collect(Collectors.toList());

                List<Comment> comments = BeanMapper.mapList(listEntity, Comment.class);
                joinTemplate.joinQuery(comments);
                comment.setCommentList(comments);
            }
        }


        return PaginationBuilder.build(pagination,commentList);
    }



    @Override
    public Pagination<Comment> findCommentPage(CommentQuery commentQuery) {
        Pagination<CommentEntity> pagination = commentDao.findCommentPage(commentQuery);

        List<Comment> commentList = BeanMapper.mapList(pagination.getDataList(),Comment.class);
        joinTemplate.joinQuery(commentList);


        List<Comment> fistOneComment = findComment(commentList);

        return PaginationBuilder.build(pagination,fistOneComment);
    }


    /*
     * 查询用户（创建人）id
     * @param*/

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
            List<LikeEntity> likeList = likeDao.findLikeList(likeQuery);
            if (!likeList.isEmpty()){
                if ("view".equals(type)){
                    comment.setIsLike("false");
                }else {
                    //根据用户id判断该用户是否点赞了
//                    List<LikeEntity> collect1 = likeList.stream().filter(a -> findCreatUser().equals(a.getLikeUser())).collect(Collectors.toList());
//                    if (CollectionUtils.isNotEmpty(collect1)){
//                        comment.setIsLike("true");
//                    }else {
//                        comment.setIsLike("false");
//                    }
                }
                List<Like> likes = BeanMapper.mapList(likeList, Like.class);
                joinTemplate.joinQuery(likes);
                List<User> userList = likes.stream().map(Like::getLikeUser).collect(Collectors.toList());
                if (!userList.isEmpty()){
                    //取点赞人名字
                    List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
                    //点赞数
                    comment.setLikenumInt(likeList.size());
                    //点赞人
                    comment.setLikeUserList(collect);
                }
            }else {
                comment.setIsLike("false");
            }
        }
    }

}