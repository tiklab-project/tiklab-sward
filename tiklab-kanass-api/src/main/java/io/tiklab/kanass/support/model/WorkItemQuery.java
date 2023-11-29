package io.tiklab.kanass.support.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 事项查找条件模型
 */
@ApiModel
@Join
public class WorkItemQuery implements Serializable {

    @ApiProperty(name ="projectId",desc = "所属项目ID，精确匹配")
    private String projectId;

    @ApiProperty(name ="workTypeIds",desc = "事项类型IDs，范围匹配")
    private List<String> workTypeIds;

    @ApiProperty(name ="assignerId",desc = "事项类型IDs，范围匹配")
    private String assignerId;

    @ApiProperty(name ="workTypeSysId",desc = "事项类型IDs，范围匹配")
    private String workTypeSysId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("buildTime").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<String> getWorkTypeIds() {
        return workTypeIds;
    }

    public void setWorkTypeIds(List<String> workTypeIds) {
        this.workTypeIds = workTypeIds;
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


    public String getWorkTypeSysId() {
        return workTypeSysId;
    }

    public void setWorkTypeSysId(String workTypeSysId) {
        this.workTypeSysId = workTypeSysId;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }
}