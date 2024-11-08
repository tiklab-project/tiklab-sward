package io.tiklab.sward.support.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;

import java.util.List;

@ApiModel
public class RecentQuery {

        @ApiProperty(name ="modelId",desc = "目录id")
        private String modelId;

        @ApiProperty(name ="model",desc = "目录id")
        private String model;

        @ApiProperty(name ="model",desc = "目录id")
        private String[] modelIds;

        @ApiProperty(name ="masterId",desc = "目录id")
        private String masterId;

        @ApiProperty(name ="repositoryId",desc = "知识库id")
        private String repositoryId;

        @ApiProperty(name = "recycle")
        private String recycle;

        @ApiProperty(name = "recycle")
        private String status;

        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().desc("recentTime").get();

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

    public String[] getModelIds() {
        return modelIds;
    }

    public void setModelIds(String[] modelIds) {
        this.modelIds = modelIds;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getRecycle() {
        return recycle;
    }

    public void setRecycle(String recycle) {
        this.recycle = recycle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}