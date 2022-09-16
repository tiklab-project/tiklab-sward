package net.tiklab.kanass.document.entity;

import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="kanass_comment")
public class CommentEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    //文档id
    @Column(name = "document_id",length = 32,notNull = true)
    private String documentId;

    //上一级评论id
    @Column(name = "parent_comment_id")
    private String parentCommentId;

    //第一层评论id
    @Column(name = "first_one_comment_id")
    private String firstOneCommentId;

    //内容
    @Column(name = "details")
    private String details;

    //用户
    @Column(name = "user")
    private String user;

    //对谁的评论
    @Column(name = "aim_at_user")
    private String aimAtUser;

    //创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    //更新时间
    @Column(name = "update_time")
    private Timestamp updateTime;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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

    public String getFirstOneCommentId() {
        return firstOneCommentId;
    }

    public void setFirstOneCommentId(String firstOneCommentId) {
        this.firstOneCommentId = firstOneCommentId;
    }

    public String getAimAtUser() {
        return aimAtUser;
    }

    public void setAimAtUser(String aimAtUser) {
        this.aimAtUser = aimAtUser;
    }

}
