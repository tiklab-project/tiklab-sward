package io.tiklab.sward.document.approve.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.sql.Timestamp;

@Entity
@Table(name="wiki_approve")
public class ApproveEntity {

    @Id
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "rpy_id")
    private String rpyId;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "user_ids")
    private String userIds;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "wiki_document_ids")
    private String wikiDocumentIds;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRpyId() {
        return rpyId;
    }

    public void setRpyId(String rpyId) {
        this.rpyId = rpyId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWikiDocumentIds() {
        return wikiDocumentIds;
    }

    public void setWikiDocumentIds(String wikiDocumentIds) {
        this.wikiDocumentIds = wikiDocumentIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
