package io.tiklab.kanass.document.model;

import io.tiklab.core.BaseModel;
import io.tiklab.core.page.Page;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class ShareQuery  extends BaseModel {
        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        @ApiProperty(name ="shareLink",desc = "分享链接")
        private String shareLink;

        @ApiProperty(name="whetherAuthCode",desc="是否创建验证码  true  false")
        private java.lang.Boolean whetherAuthCode;

        @ApiProperty(name ="authCode",desc = "验证码")
        private String authCode ;

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