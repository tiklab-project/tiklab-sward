package net.tiklab.kanass.document.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetAlias = "LikeEntity")
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
