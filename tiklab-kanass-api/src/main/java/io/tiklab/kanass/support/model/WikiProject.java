package io.tiklab.kanass.support.model;

import io.tiklab.dss.annotation.IndexField;
import io.tiklab.dss.annotation.IndexId;
import io.tiklab.dss.annotation.IndexQueryField;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

public class WikiProject {
    @ApiProperty(name="id",desc = "项目ID")
    @NotNull
    @IndexField
    @IndexId
    private String id;

    @ApiProperty(name = "projectKey",desc = "项目key",required = true)
    private String projectKey;

    @ApiProperty(name = "creator",desc = "项目创建者")
    private String creator;

    @ApiProperty(name = "projectLimits",desc = "项目可见范围",required = true)
    private String projectLimits;

    @ApiProperty(name="projectName",desc = "项目名称",eg="@text32",required = true)
    @IndexField
    @IndexQueryField
    private String projectName;

    @ApiProperty(name="projectTypeName",desc = "项目类型",required = true)
    private String projectTypeName;

    @ApiProperty(name="masterName",desc="负责人",required = true)
    private String masterName;

    @ApiProperty(name="desc",desc = "项目描述",eg="@text32")
    private java.lang.String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getProjectLimits() {
        return projectLimits;
    }

    public void setProjectLimits(String projectLimits) {
        this.projectLimits = projectLimits;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
