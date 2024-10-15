package io.thoughtware.sward.repository.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.core.page.Page;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class WikiRepositoryQuery extends BaseModel {
    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("createTime").get();

    @ApiProperty(name ="repositoryIds",desc = "知识库ids")
    private String[] repositoryIds;

    @ApiProperty(name ="repositoryId",desc = "知识库id")
    private String repositoryId;

    @ApiProperty(name="masterId",desc="masterId")
    private String masterId;

    @ApiProperty(name="limits",desc="limits")
    private String limits;

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name ="name",desc = "名字，模糊匹配")
    private String name;

    @ApiProperty(name ="status",desc = "是否归档")
    private String status = "nomal";

    @ApiProperty(name ="recycle",desc = "是否逻辑删除")
    private String recycle = "0";

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

    public String[] getRepositoryIds() {
        return repositoryIds;
    }

    public void setRepositoryIds(String[] repositoryIds) {
        this.repositoryIds = repositoryIds;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecycle() {
        return recycle;
    }

    public void setRecycle(String recycle) {
        this.recycle = recycle;
    }
}