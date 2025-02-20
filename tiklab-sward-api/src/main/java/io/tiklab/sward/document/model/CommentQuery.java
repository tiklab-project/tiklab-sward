package io.tiklab.sward.document.model;

import io.tiklab.core.BaseModel;
import io.tiklab.core.page.Page;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class CommentQuery  extends BaseModel {
        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().desc("createTime").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        @ApiProperty(name ="documentId",desc = "文档id")
        private String documentId;

        @ApiProperty(name ="documentIds",desc = "文档ids")
        private String[] documentIds;

        @ApiProperty(name ="isFirstCommentNull",desc = "是否为空")
        private Boolean isFirstCommentNull;

        @ApiProperty(name ="parentCommentId",desc = "父级评论")
        private String parentCommentId;

        @ApiProperty(name ="firstOneCommentId",desc = "父级评论")
        private String firstOneCommentId;

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

    public Boolean getFirstCommentNull() {
        return isFirstCommentNull;
    }

    public void setFirstCommentNull(Boolean firstCommentNull) {
        isFirstCommentNull = firstCommentNull;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getFirstOneCommentId() {
        return firstOneCommentId;
    }

    public void setFirstOneCommentId(String firstOneCommentId) {
        this.firstOneCommentId = firstOneCommentId;
    }

    public String[] getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(String[] documentIds) {
        this.documentIds = documentIds;
    }
}