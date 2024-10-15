package io.tiklab.sward.document.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;

import java.util.Date;

@ApiModel
@Mapper
public class Share extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="nodeIds",desc="id")
    private String[] nodeIds;


    @ApiProperty(name="authCode",desc="验证码")
    private java.lang.String authCode;

    @ApiProperty(name="limits",desc="可见范围")
    private java.lang.String limits;

    @ApiProperty(name="createTime",desc="创建时间")
    private java.util.Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String[] getNodeIds() {
        return nodeIds;
    }

    public void setNodeIds(String[] nodeIds) {
        this.nodeIds = nodeIds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }


}
