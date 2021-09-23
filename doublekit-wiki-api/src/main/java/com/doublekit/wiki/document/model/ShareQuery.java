package com.doublekit.wiki.document.model;

import com.doublekit.dal.jpa.criteria.annotation.*;
import com.doublekit.dal.jpa.criteria.model.OrderParam;
import com.doublekit.dal.jpa.criteria.model.Orders;
import com.doublekit.dal.jpa.criteria.model.PageParam;
import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;

import java.util.List;

@ApiModel
@CriteriaQuery
public class ShareQuery {
        @ApiProperty(name ="orderParams",desc = "排序参数")
        @OrderField
        private List<OrderParam> orderParams = Orders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        @PageField
        private PageParam pageParam = new PageParam();

        @ApiProperty(name ="shareLink",desc = "分享链接")
        @QueryField(type = QueryTypeEnum.equal)
        private String shareLink;

        @ApiProperty(name="whetherAuthCode",desc="是否创建验证码  true  false")
        private java.lang.Boolean whetherAuthCode;

        @ApiProperty(name ="authCode",desc = "验证码")
        @QueryField(type = QueryTypeEnum.equal)
        private String authCode ;

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

        public String getShareLink() {
            return shareLink;
        }

        public ShareQuery setShareLink(String shareLink) {
            this.shareLink = shareLink;
            return this;
        }

        public Boolean getWhetherAuthCode() {
            return whetherAuthCode;
        }

        public void setWhetherAuthCode(Boolean whetherAuthCode) {
            this.whetherAuthCode = whetherAuthCode;
        }

        public String getAuthCode() {
            return authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }
}