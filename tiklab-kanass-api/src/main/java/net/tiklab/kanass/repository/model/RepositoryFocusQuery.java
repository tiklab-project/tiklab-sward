package net.tiklab.kanass.repository.model;


import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.core.page.Page;
import net.tiklab.dal.jpa.annotation.criteria.CriteriaQuery;
import net.tiklab.dal.jpa.annotation.criteria.OrderField;
import net.tiklab.dal.jpa.annotation.criteria.PageField;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
@CriteriaQuery(entityAlias = "RepositoryFocusEntity")
public class RepositoryFocusQuery {
        @ApiProperty(name ="masterId",desc = "收藏者id")
        private String masterId;

        @ApiProperty(name ="repositoryId",desc = "知识库id")
        private String repositoryId;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        @OrderField
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        @PageField
        private Page pageParam = new Page();

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