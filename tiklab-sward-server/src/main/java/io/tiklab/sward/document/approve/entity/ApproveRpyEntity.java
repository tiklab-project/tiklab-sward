package io.tiklab.sward.document.approve.entity;

import io.tiklab.dal.jpa.annotation.*;

@Entity
@Table(name="wiki_approve_rpy")
public class ApproveRpyEntity {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "rpy_id")
    private String rpyId;

    @Column(name = "rule_id")
    private String ruleId;

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

    public String getRpyId() {
        return rpyId;
    }

    public void setRpyId(String rpyId) {
        this.rpyId = rpyId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
}
