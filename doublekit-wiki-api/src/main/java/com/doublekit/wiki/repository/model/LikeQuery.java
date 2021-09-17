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
public class LikeQuery {
        @ApiProperty(name ="orderParams",desc = "排序参数")
        @OrderField
        private List<OrderParam> orderParams = Orders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        @PageField
        private PageParam pageParam = new PageParam();

        @ApiProperty(name ="toWhomId",desc = "id")
        @QueryField(type = QueryTypeEnum.like)
        private String toWhomId;

        @ApiProperty(name ="likeType",desc = "点赞类型")
        @QueryField(type = QueryTypeEnum.like)
        private String likeType;

        @ApiProperty(name ="likeUser",desc = "点赞人")
        @QueryField(type = QueryTypeEnum.like)
        private String likeUser;

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

        public String getToWhomId() {
            return toWhomId;
        }

        public void setToWhomId(String toWhomId) {
            this.toWhomId = toWhomId;
        }

        public String getLikeType() {
                return likeType;
            }

            public void setLikeType(String likeType) {
                this.likeType = likeType;
            }

        public String getLikeUser() {
            return likeUser;
        }

        public void setLikeUser(String likeUser) {
            this.likeUser = likeUser;
        }
}