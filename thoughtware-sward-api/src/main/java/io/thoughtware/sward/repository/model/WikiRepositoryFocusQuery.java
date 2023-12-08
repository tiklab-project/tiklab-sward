package io.thoughtware.sward.repository.model;


import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class WikiRepositoryFocusQuery {
        @ApiProperty(name ="masterId",desc = "收藏者id")
        private String masterId;

        @ApiProperty(name ="repositoryId",desc = "知识库id")
        private String repositoryId;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().desc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
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