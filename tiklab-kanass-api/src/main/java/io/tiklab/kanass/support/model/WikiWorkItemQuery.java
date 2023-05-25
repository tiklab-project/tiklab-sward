package io.tiklab.kanass.support.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.page.Page;
import io.tiklab.dss.annotation.IndexField;
import io.tiklab.dss.annotation.IndexId;
import io.tiklab.dss.annotation.IndexQueryField;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class WikiWorkItemQuery {
    @ApiProperty(name="id",desc="唯一标识")
    @IndexField
    @IndexId
    private String id;

    @ApiProperty(name="title",desc="标题",required = true)
    @IndexField
    @IndexQueryField
    private String title;

    @ApiProperty(name="projectId",desc="所属项目",required = true)
    private String projectId;

    @ApiProperty(name="workTypeId",desc="所属的项目事项类型")
    private String workTypeId;

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public Page getPageParam() {
        return pageParam;
    }

    public void setPageParam(Page pageParam) {
        this.pageParam = pageParam;
    }
}
