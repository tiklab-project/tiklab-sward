package net.tiklab.kanass.document.model;

import net.tiklab.core.BaseModel;
import net.tiklab.core.page.Page;
import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class CommentQuery  extends BaseModel {
        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        @ApiProperty(name ="documentId",desc = "文档id")
        private String documentId;

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

        public String getDocumentId() {
            return documentId;
        }

        public CommentQuery setDocumentId(String documentId) {
            this.documentId = documentId;
            return this;
        }

}