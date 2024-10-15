package io.tiklab.sward.document.entity;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="wiki_like")
public class LikeEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    //文档或者评论的id
    @Column(name = "to_whom_id",length = 32,notNull = true)
    private String toWhomId;

    //点赞人
    @Column(name = "like_user")
    private String likeUser;

    //点赞类型
    @Column(name = "like_type")
    private String likeType;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToWhomId() {
        return toWhomId;
    }

    public void setToWhomId(String toWhomId) {
        this.toWhomId = toWhomId;
    }

    public String getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(String likeUser) {
        this.likeUser = likeUser;
    }

    public String getLikeType() {
        return likeType;
    }

    public void setLikeType(String likeType) {
        this.likeType = likeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
