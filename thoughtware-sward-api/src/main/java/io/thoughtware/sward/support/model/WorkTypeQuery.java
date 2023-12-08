package io.thoughtware.sward.support.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 系统事项类型模型
 */
@ApiModel
public class WorkTypeQuery {

    @ApiProperty(name ="id",desc = "id，精准匹配")
    private String id;

    @ApiProperty(name ="id",desc = "已经被选中的事项类型id，精准匹配")
    private String[] selectIds;
    @ApiProperty(name ="projectId",desc = "项目id，精准匹配")
    private String projectId;
    @ApiProperty(name ="name",desc = "状态名称，模糊匹配")
    private String name;

    @ApiProperty(name ="code",desc = "类型code，精准匹配")
    private String code;

    @ApiProperty(name ="grouper",desc = "grouper，精准匹配")
    private String grouper;

    @ApiProperty(name ="scope",desc = "grouper，精准匹配")
    private Integer scope;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrouper() {
        return grouper;
    }

    public void setGrouper(String grouper) {
        this.grouper = grouper;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getSelectIds() {
        return selectIds;
    }

    public void setSelectIds(String[] selectIds) {
        this.selectIds = selectIds;
    }
}