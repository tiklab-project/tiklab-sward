package com.doublekit.wiki.document.model;

import com.doublekit.core.BaseModel;
import com.doublekit.core.page.Page;
import com.doublekit.core.order.Order;
import com.doublekit.core.order.OrderBuilders;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class LikeQuery extends BaseModel {
        @ApiProperty(name ="toWhomId",desc = "id")
        private String toWhomId;

        @ApiProperty(name ="likeType",desc = "点赞类型")
        private String likeType;

        @ApiProperty(name ="likeUser",desc = "点赞人")
        private String likeUser;

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