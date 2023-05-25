package io.tiklab.kanass.support.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.dss.annotation.IndexField;
import io.tiklab.dss.annotation.IndexId;
import io.tiklab.dss.annotation.IndexQueryField;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class WikiWorkType {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="projectId",desc="项目id")
    private String projectId;

    @NotNull
    @ApiProperty(name="name",desc="事项类型名称",eg="@text32",required = true)
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
