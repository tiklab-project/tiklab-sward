package io.thoughtware.sward.repository.model;

import io.thoughtware.dss.annotation.Document;
import io.thoughtware.dss.annotation.DocumentField;
import io.thoughtware.dss.annotation.DocumentId;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.user.user.model.User;

import java.sql.Timestamp;

@ApiModel
@Join
@Mapper
@Document
public class WikiRepository extends BaseModel{

    @ApiProperty(name="id",desc="id")
    @DocumentField
    @DocumentId
    private java.lang.String id;


    @ApiProperty(name="name",desc="name")
    @DocumentField(queryField = true)
    private java.lang.String name;

    //@NotNull
    @ApiProperty(name="typeId",desc="typeId")
    private String typeId="1";

    @ApiProperty(name="master",desc="负责人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    //权限范围 0 全部可见 1成员可见
    @ApiProperty(name="limits",desc="limits")
    private String limits;

    @ApiProperty(name="iconUrl",desc="iconUrl")
    private String iconUrl;

    @ApiProperty(name="categoryNum",desc="iconUrl")
    private Integer categoryNum;

    @ApiProperty(name="documentNum",desc="iconUrl")
    private Integer documentNum;



    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp createTime;

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

    public Integer getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(Integer categoryNum) {
        this.categoryNum = categoryNum;
    }

    public Integer getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(Integer documentNum) {
        this.documentNum = documentNum;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
