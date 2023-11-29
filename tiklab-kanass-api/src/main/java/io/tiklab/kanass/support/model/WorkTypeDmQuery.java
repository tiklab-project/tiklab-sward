package io.tiklab.kanass.support.model;


import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 项目下事项类型搜索条件模型
 */
@ApiModel
public class WorkTypeDmQuery implements Serializable{

        @ApiProperty(name ="projectId",desc = "项目id，精准匹配")
        private String projectId;

        @ApiProperty(name ="workTypeId",desc = "事项类型id，精准匹配")
        private String workTypeId;

        @ApiProperty(name ="code",desc = "事项类型id，精准匹配")
        private String code;



        @ApiProperty(name ="codes",desc = "事项类型id，精准匹配")
        private String[] codes;

        @ApiProperty(name ="grouper",desc = "类型分组，系统system，自定义custom")
        private String grouper;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("code").get();

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

    public String getGrouper() {
        return grouper;
    }

    public void setGrouper(String grouper) {
        this.grouper = grouper;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }
}