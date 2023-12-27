package io.thoughtware.sward.document.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@ApiModel
@Mapper
@Join
public class Comment extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="document",desc="文档",eg="@selectOne")
    @Mappings({
            @Mapping(source = "wikiDocument.id",target = "documentId")
    })
    @JoinQuery(key = "id")
    private WikiDocument wikiDocument;

    @ApiProperty(name="parentCommentId",desc="父评论id")
    private java.lang.String parentCommentId;

    @ApiProperty(name="details",desc="内容")
    private java.lang.String details;

    @ApiProperty(name="user",desc="用户",eg="@selectOne")
    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    @JoinQuery(key = "id")
    private User user;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp updateTime;

    @ApiProperty(name="aimAtUser",desc="对谁的评论",eg="@selectOne")
    @Mappings({
            @Mapping(source = "aimAtUser.id",target = "aimAtUser")
    })
    @JoinQuery(key = "id")
    private User aimAtUser;

    //第一层评论id
    @ApiProperty(name="firstOneCommentId",desc="第一层评论id")
    private java.lang.String firstOneCommentId;

    @ApiProperty(name="likenumInt",desc="点赞数")
    private java.lang.Integer likenumInt;

    private List<Comment> commentList;

    private List<String> likeUserList;

    @ApiProperty(name="isLike",desc="查询人是否点赞")
    private  java.lang.String isLike;


    public java.lang.String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WikiDocument getWikiDocument() {
        return wikiDocument;
    }

    public void setWikiDocument(WikiDocument wikiDocument) {
        this.wikiDocument = wikiDocument;
    }

    public java.lang.String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(java.lang.String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
    public java.lang.String getDetails() {
        return details;
    }

    public void setDetails(java.lang.String details) {
        this.details = details;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public User getAimAtUser() {
        return aimAtUser;
    }

    public void setAimAtUser(User aimAtUser) {
        this.aimAtUser = aimAtUser;
    }

    public String getFirstOneCommentId() {
        return firstOneCommentId;
    }

    public void setFirstOneCommentId(String firstOneCommentId) {
        this.firstOneCommentId = firstOneCommentId;
    }

    public Integer getLikenumInt() {
        return likenumInt;
    }

    public void setLikenumInt(Integer likenumInt) {
        this.likenumInt = likenumInt;
    }

    public List<String> getLikeUserList() {
        return likeUserList;
    }

    public void setLikeUserList(List<String> likeUserList) {
        this.likeUserList = likeUserList;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }
}
