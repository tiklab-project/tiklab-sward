package com.doublekit.wiki.repository.model;

import com.doublekit.dal.jpa.criteria.annotation.*;
import com.doublekit.dal.jpa.criteria.model.OrderParam;
import com.doublekit.dal.jpa.criteria.model.Orders;
import com.doublekit.dal.jpa.criteria.model.PageParam;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;

import java.util.List;

@ApiModel
@CriteriaQuery
public class DocumentQuery {
        @ApiProperty(name ="orderParams",desc = "排序参数")
        @OrderField
        private List<OrderParam> orderParams = Orders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        @PageField
        private PageParam pageParam = new PageParam();

        @ApiProperty(name ="categoryId",desc = "目录id")
        @QueryField(type = QueryTypeEnum.equal)
        private String categoryId;

        @ApiProperty(name ="repositoryId",desc = "空间id")
        @QueryField(type = QueryTypeEnum.equal)
        private String repositoryId;

        public List<OrderParam> getOrderParams() {
            return orderParams;
        }

        public void setOrderParams(List<OrderParam> orderParams) {
            this.orderParams = orderParams;
        }

        public PageParam getPageParam() {
            return pageParam;
        }

        public void setPageParam(PageParam pageParam) {
            this.pageParam = pageParam;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getRepositoryId() {
            return repositoryId;
        }

        public DocumentQuery setRepositoryId(String repositoryId) {
            this.repositoryId = repositoryId;
            return this;
        }
}