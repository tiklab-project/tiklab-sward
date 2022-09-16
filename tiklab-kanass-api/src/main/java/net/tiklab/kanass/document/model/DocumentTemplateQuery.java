package net.tiklab.kanass.document.model;

import net.tiklab.core.BaseModel;
import net.tiklab.core.page.Page;
import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class DocumentTemplateQuery extends BaseModel {
        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        @ApiProperty(name ="name",desc = "名字，模糊匹配")
        private String name;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}