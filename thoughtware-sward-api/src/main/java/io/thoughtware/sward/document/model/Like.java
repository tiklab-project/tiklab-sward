package io.thoughtware.sward.document.model;

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

@ApiModel
@Mapper
@Join
public class Like extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="toWhomId",desc="文档或者评论id",required = true)
    private java.lang.String toWhomId;


    @ApiProperty(name="likeUser",desc="点赞人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "likeUser.id",target = "likeUser")
    })
    @JoinQuery(key = "id")
    private User likeUser;

    @ApiProperty(name="likeType",desc="点赞类型  doc文档 com 评论")
    private java.lang.String likeType;

    @ApiProperty(name="createTime",desc="createTime")
    private java.util.Date createTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getToWhomId() {
        return toWhomId;
    }

    public void setToWhomId(java.lang.String toWhomId) {
        this.toWhomId = toWhomId;
    }

    public User getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(User likeUser) {
        this.likeUser = likeUser;
    }

    public java.lang.String getLikeType() {
        return likeType;
    }

    public void setLikeType(java.lang.String likeType) {
        this.likeType = likeType;
    }
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
}
