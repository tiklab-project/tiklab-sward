package io.tiklab.sward.document.approve.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;

@Mapper
@Join
public class ApproveRpy {

    private String id;

    private String rpyId;

    @Mappings({
            @Mapping(source = "approveRule.id",target = "ruleId")
    })
    @JoinQuery(key = "id")
    private ApproveRule approveRule;

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

    public ApproveRule getApproveRule() {
        return approveRule;
    }

    public void setApproveRule(ApproveRule approveRule) {
        this.approveRule = approveRule;
    }
}
