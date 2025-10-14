package io.tiklab.sward.document.document.entity;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="wiki_share")
public class ShareEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 分享名称
    @Column(name = "name")
    private String name;

    //创建时间
    @Column(name = "create_time",notNull = true)
    private Timestamp createTime;

    @Column(name = "rpy_id",length = 12)
    private String rpyId;

    @Column(name = "user_id",length = 12)
    private String userId;

    // 1.外部分享 2.知识库内分享
    @Column(name = "type")
    private Integer type;

    // 认证类型 1. 无密码 2.密码访问
    @Column(name = "auth_type")
    private Integer authType;

    // 分享密码
    @Column(name = "password")
    private String password;

    // 成员列表
    @Column(name = "user_ids")
    private String userIds;

    // 分享列表
    @Column(name = "share_ids")
    private String shareIds;

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRpyId() {
        return rpyId;
    }

    public void setRpyId(String rpyId) {
        this.rpyId = rpyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShareIds() {
        return shareIds;
    }

    public void setShareIds(String shareIds) {
        this.shareIds = shareIds;
    }
}
