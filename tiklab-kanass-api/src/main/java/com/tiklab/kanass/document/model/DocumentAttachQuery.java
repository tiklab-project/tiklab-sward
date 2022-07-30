package com.tiklab.kanass.document.model;

import com.tiklab.core.BaseModel;
import com.tiklab.core.order.Order;
import com.tiklab.core.order.OrderBuilders;
import com.tiklab.core.page.Page;
import com.tiklab.postin.annotation.ApiModel;
import com.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class DocumentAttachQuery extends BaseModel {
        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

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
}