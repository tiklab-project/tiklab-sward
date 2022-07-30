package com.tiklab.kanass.repository.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.tiklab.postin.annotation.ApiModel;
import com.tiklab.postin.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.dss.annotation.Index;
import com.tiklab.dss.annotation.IndexField;
import com.tiklab.dss.annotation.IndexId;
import com.tiklab.dss.annotation.IndexQueryField;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;
import com.tiklab.user.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel
@Join
@Mapper(targetAlias = "RepositoryEntity")
@Index
public class Repository extends BaseModel{

    @ApiProperty(name="id",desc="id")
    @IndexField
    @IndexId
    private java.lang.String id;


    @ApiProperty(name="name",desc="name")
    @IndexField
    @IndexQueryField
    private java.lang.String name;

    //@NotNull
    @ApiProperty(name="typeId",desc="typeId")
    private java.lang.String typeId="1";

    @ApiProperty(name="master",desc="负责人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    //权限范围 0 全部可见 1成员可见
    @ApiProperty(name="limits",desc="limits")
    private java.lang.String limits;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @ApiProperty(name="desc",desc="desc")
    private java.lang.String desc;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getTypeId() {
        return typeId;
    }

    public void setTypeId(java.lang.String typeId) {
        this.typeId = typeId;
    }

    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
