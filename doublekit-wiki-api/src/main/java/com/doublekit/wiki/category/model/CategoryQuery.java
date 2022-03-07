package com.doublekit.wiki.category.model;

import com.doublekit.common.BaseModel;
import com.doublekit.common.order.Order;
import com.doublekit.common.order.OrderBuilders;
import com.doublekit.common.page.Page;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class CategoryQuery extends BaseModel {
        @ApiProperty(name ="repositoryId",desc = "空间id")
        private String repositoryId;

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

        public String getRepositoryId() {
            return repositoryId;
        }

        public void setRepositoryId(String repositoryId) {
            this.repositoryId = repositoryId;
        }
}