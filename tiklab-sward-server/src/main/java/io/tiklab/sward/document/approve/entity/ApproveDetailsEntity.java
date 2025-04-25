package io.tiklab.sward.document.approve.entity;

import io.tiklab.dal.jpa.annotation.*;

@Entity
@Table(name="wiki_approve_details")
public class ApproveDetailsEntity {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "approve_id")
    private String approveId;

    @Column(name = "status")
    private String status;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "describe")
    private String describe;

    @Column(name = "create_time")
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
