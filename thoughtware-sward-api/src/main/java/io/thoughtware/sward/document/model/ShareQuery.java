package io.thoughtware.sward.document.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.core.page.Page;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class ShareQuery  extends BaseModel {
        @ApiProperty(name ="id",desc = "id")
        private String id;

        @ApiProperty(name ="type",desc = "验证码")
        private String type ;
        @ApiProperty(name ="authCode",desc = "验证码")
        private String authCode ;

        @ApiProperty(name ="limits",desc = "验证码")
        private String limits ;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        public String getAuthCode() {
            return authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLimits() {
            return limits;
        }

        public void setLimits(String limits) {
            this.limits = limits;
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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}