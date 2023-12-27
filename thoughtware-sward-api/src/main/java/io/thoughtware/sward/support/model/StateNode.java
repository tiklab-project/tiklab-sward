package io.thoughtware.sward.support.model;

import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * 状态节点模型
 */
@Join
@Mapper(targetAlias = "StateNodeEntity")
public class StateNode extends BaseModel implements java.io.Serializable{

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @NotNull
    @ApiProperty(name="name",desc="名称",required = true)
    private String name;

    @NotNull
    @ApiProperty(name="status",desc="状态",required = true)
    private String status = "PROGRESS";

    private Integer workNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkNum() {
        return workNum;
    }

    public void setWorkNum(Integer workNum) {
        this.workNum = workNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
