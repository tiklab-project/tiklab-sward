package io.thoughtware.sward.document.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class DocumentAttachQuery extends BaseModel {

    @ApiProperty(name ="documentId",desc = "知识库id")
    private String documentId;

    @ApiProperty(name ="documentIds",desc = "知识库id")
    private String[] documentIds;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

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
}