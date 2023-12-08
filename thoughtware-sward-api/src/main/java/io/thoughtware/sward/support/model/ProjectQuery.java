package io.thoughtware.sward.support.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 项目查询模型
 */
@ApiModel
public class ProjectQuery implements Serializable {

    /**
     * @pi.name: projectName
     * @pi.value: projectName
     */
    @ApiProperty(name ="projectName",desc = "项目名称，模糊匹配")
    private String projectName;

    /**
     * @pi.name: projectId
     * @pi.value: projectId
     */
    @ApiProperty(name ="projectId",desc = "项目id，模糊匹配")
    private String projectId;

    /**
     * @pi.name: projectIds
     * @pi.value: projectIds
     */
    @ApiProperty(name ="projectIds",desc = "项目id范围搜索 in，模糊匹配")
    private String[] projectIds;

    /**
     * @pi.name: projectSetId
     * @pi.value: projectSetId
     */
    @ApiProperty(name ="projectSetId",desc = "项目集id, 精确匹配")
    private String projectSetId;

    /**
     * @pi.name: projectLimits
     * @pi.value: projectLimits
     */
    @ApiProperty(name ="projectLimits",desc = "项目范围,精确匹配")
    private String projectLimits;

    /**
     * @pi.name: projectState
     * @pi.value: projectState
     */
    @ApiProperty(name ="projectState",desc = "项目集状态")
    private String projectState;

    /**
     * @pi.name: master
     * @pi.value: master
     */
    @ApiProperty(name ="master",desc = "负责人ID")
    private String master;

    /**
     * @pi.name: creator
     * @pi.value: creator
     */
    @ApiProperty(name = "creator",desc = "项目创建者")
    private String creator;

    /**
     * @pi.name: projectTypeId
     * @pi.value: projectTypeId
     */
    @ApiProperty(name ="projectTypeId",desc = "类型ID")
    private String projectTypeId;

    /**
     * @pi.name: recentMasterId
     * @pi.value: recentMasterId
     */
    @ApiProperty(name ="recentMasterId",desc = "点击人")
    private String recentMasterId;

    /**
     * @pi.model: Order
     */
    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("projectName").get();

    /**
     * @pi.model: Page
     */
    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public ProjectQuery() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Order> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(List<Order> orderParams) {
        this.orderParams = orderParams;
    }

    public Page getPageParam() {
        return pageParam;
    }

    public void setPageParam(Page pageParam) {
        this.pageParam = pageParam;
    }

    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(String projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public String getProjectLimits() {
        return projectLimits;
    }

    public void setProjectLimits(String projectLimits) {
        this.projectLimits = projectLimits;
    }

    public String[] getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String[] projectIds) {
        this.projectIds = projectIds;
    }

    public String getRecentMasterId() {
        return recentMasterId;
    }

    public void setRecentMasterId(String recentMasterId) {
        this.recentMasterId = recentMasterId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
