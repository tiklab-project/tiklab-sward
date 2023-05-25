package io.tiklab.kanass.support.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.dss.annotation.IndexField;
import io.tiklab.dss.annotation.IndexId;
import io.tiklab.dss.annotation.IndexQueryField;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class WikiWorkItem {
    @ApiProperty(name="id",desc="唯一标识")
    @IndexField
    @IndexId
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="title",desc="标题",required = true)
    @IndexField
    @IndexQueryField
    private java.lang.String title;


    @ApiProperty(name="parentWorkItem",desc="上级事项")
    private String parentWorkItemName;

    @ApiProperty(name="parentName",desc="上级事项id")
    private String parentName;

    @ApiProperty(name="projectName",desc="所属项目",required = true)
    private String projectName;

    @ApiProperty(name="workTypeName",desc="所属的项目事项类型")
    private String workTypeName;

    @ApiProperty(name="workPriorityName",desc="优先级")
    private String workPriorityName;


    @ApiProperty(name="workStatusName",desc="事项所属项目的状态")
    private String workStatusName;


    @ApiProperty(name="sprintName",desc="所属迭代")
    private String sprintName;

    @ApiProperty(name="assignerName",desc="经办人")
    private String assignerName;

    @ApiProperty(name="planBeginTime",desc="计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date planBeginTime;

    @ApiProperty(name="planEndTime",desc="计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date planEndTime;


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

    public String getParentWorkItemName() {
        return parentWorkItemName;
    }

    public void setParentWorkItemName(String parentWorkItemName) {
        this.parentWorkItemName = parentWorkItemName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    public String getWorkPriorityName() {
        return workPriorityName;
    }

    public void setWorkPriorityName(String workPriorityName) {
        this.workPriorityName = workPriorityName;
    }

    public String getWorkStatusName() {
        return workStatusName;
    }

    public void setWorkStatusName(String workStatusName) {
        this.workStatusName = workStatusName;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public String getAssignerName() {
        return assignerName;
    }

    public void setAssignerName(String assignerName) {
        this.assignerName = assignerName;
    }

    public Date getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(Date planBeginTime) {
        this.planBeginTime = planBeginTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }
}
