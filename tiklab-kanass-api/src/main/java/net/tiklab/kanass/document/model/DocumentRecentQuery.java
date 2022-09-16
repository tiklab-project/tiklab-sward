package net.tiklab.kanass.document.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.core.page.Page;

import java.util.List;

@ApiModel
public class DocumentRecentQuery {

        @ApiProperty(name ="modelId",desc = "目录id")
        private String modelId;

        @ApiProperty(name ="model",desc = "目录id")
        private String model;

        @ApiProperty(name ="model",desc = "目录id")
        private String[] models;

        @ApiProperty(name ="masterId",desc = "目录id")
        private String masterId;

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

        public String getModelId() {
            return modelId;
        }

        public void setModelId(String modelId) {
            this.modelId = modelId;
        }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String[] getModels() {
        return models;
    }

    public void setModels(String[] models) {
        this.models = models;
    }
}