package io.tiklab.sward.document.model;


import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class DocumentFocusQuery {
        @ApiProperty(name ="masterId",desc = "收藏者id")
        private String masterId;

        @ApiProperty(name ="name",desc = "文档名称")
        private String name;

        @ApiProperty(name ="documentId",desc = "知识库id")
        private String documentId;

        @ApiProperty(name ="documentIds",desc = "文档ids")
        private String[] documentIds;

        @ApiProperty(name="repositoryId",desc="repositoryId")
        private String repositoryId;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().desc("focusTime").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        public String getMasterId() {
                return masterId;
        }

        public void setMasterId(String masterId) {
                this.masterId = masterId;
        }

        public String getDocumentId() {
                return documentId;
        }

        public void setDocumentId(String documentId) {
                this.documentId = documentId;
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

        public String[] getDocumentIds() {
                return documentIds;
        }

        public void setDocumentIds(String[] documentIds) {
                this.documentIds = documentIds;
        }

        public String getRepositoryId() {
                return repositoryId;
        }

        public void setRepositoryId(String repositoryId) {
                this.repositoryId = repositoryId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }
}