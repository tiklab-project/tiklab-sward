package io.tiklab.sward.support.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.user.user.model.User;

import java.sql.Date;


/**
 * 项目模型
 * @pi.model: Project
 */
@ApiModel
@Mapper
@Join
public class Project extends BaseModel{

    /**
     * @pi.name: id
     * @pi.value: projectId
     */
    @ApiProperty(name="id",desc="id")
    private String id;

    /**
     * @pi.name: projectKey
     * @pi.value: 项目key
     */
    @ApiProperty(name = "projectKey",desc = "项目key")
    private String projectKey;

    /**
     * @pi.name: creator
     * @pi.value: 项目创建者
     */
    @ApiProperty(name = "creator",desc = "项目创建者")
    private String creator;

    /**
     * @pi.name: projectLimits
     * @pi.value: 0 1
     */
    @ApiProperty(name = "projectLimits",desc = "项目可见范围")
    private String projectLimits;

    /**
     * @pi.name: projectName
     * @pi.value: 项目名称
     */
    @ApiProperty(name="projectName",desc = "项目名称",eg="@text32")
    public String projectName;

    /**
     * @pi.model: User
     */
    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    /**
     * @pi.name: desc
     * @pi.value: 项目描述
     */
    @ApiProperty(name="desc",desc = "项目描述",eg="@text32")
    private String desc;

    /**
     * @pi.name: projectSetId
     * @pi.value: projectSetId
     */
    @ApiProperty(name = "projectSetId",desc = "项目集")
    private String projectSetId;

    /**
     * @pi.name: startTime
     * @pi.value: 2023-01-01 12:00:00
     */
    @ApiProperty(name="startTime",desc="开始时间")
    private Date startTime;

    /**
     * @pi.name: endTime
     * @pi.value: 2023-01-01 12:00:00
     */
    @ApiProperty(name="endTime",desc="结束时间")
    private Date endTime;

    /**
     * @pi.name: projectState
     * @pi.value: 1: 未完成
     */
    @ApiProperty(name="projectState",desc="项目状态")
    private String projectState;

    @ApiProperty(name="percent",desc="完成率")
    private int percent;

    @ApiProperty(name="workItemNumber",desc="事项数量")
    private int workItemNumber;

    @ApiProperty(name="endWorkItemNumber",desc="完成数量")
    private int endWorkItemNumber;

    @ApiProperty(name="processWorkItemNumber",desc="进行中数量")
    private int processWorkItemNumber;

    @ApiProperty(name="member",desc="成员")
    private int member;


    @ApiProperty(name="iconUrl",desc="项目类型icon")
    private String iconUrl;

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


    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getWorkItemNumber() {
        return workItemNumber;
    }

    public void setWorkItemNumber(int workItemNumber) {
        this.workItemNumber = workItemNumber;
    }

    public int getEndWorkItemNumber() {
        return endWorkItemNumber;
    }

    public void setEndWorkItemNumber(int endWorkItemNumber) {
        this.endWorkItemNumber = endWorkItemNumber;
    }

    public int getProcessWorkItemNumber() {
        return processWorkItemNumber;
    }

    public void setProcessWorkItemNumber(int processWorkItemNumber) {
        this.processWorkItemNumber = processWorkItemNumber;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
