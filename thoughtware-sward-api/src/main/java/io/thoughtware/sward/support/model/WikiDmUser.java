package io.thoughtware.sward.support.model;

import io.thoughtware.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

public class WikiDmUser {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="projectId",desc="项目id")
    private String projectId;

    @NotNull
    @ApiProperty(name="name",desc="人员名称",eg="@text32",required = true)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
