package io.thoughtware.sward.document.model;

import io.thoughtware.core.page.Page;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class DocumentQuery implements Serializable {

        @ApiProperty(name ="parentWikiCategoryIsNull",desc = "是否为空")
        private Boolean parentWikiCategoryIsNull;

        @ApiProperty(name ="categoryId",desc = "目录id")
        private String categoryId;

        @ApiProperty(name ="categoryIds",desc = "目录id")
        private String[] categoryIds;

        @ApiProperty(name ="repositoryId",desc = "空间id")
        private String repositoryId;

        @ApiProperty(name ="repositoryIds",desc = "空间id")
        private String[] repositoryIds;

        @ApiProperty(name ="ids",desc = "空间id")
        private String[] ids;

        @ApiProperty(name ="name",desc = "文档名字")
        private String name;

        @ApiProperty(name ="id",desc = "文档id")
        private String id;

        @ApiProperty(name ="dimension",desc = "层级")
        private Integer dimension;

        @ApiProperty(name ="dimensions",desc = "层级")
        private Integer[] dimensions;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    public String[] getRepositoryIds() {
        return repositoryIds;
    }

    public void setRepositoryIds(String[] repositoryIds) {
        this.repositoryIds = repositoryIds;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public Boolean getParentWikiCategoryIsNull() {
        return parentWikiCategoryIsNull;
    }

    public void setParentWikiCategoryIsNull(Boolean parentWikiCategoryIsNull) {
        this.parentWikiCategoryIsNull = parentWikiCategoryIsNull;
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public Integer[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(Integer[] dimensions) {
        this.dimensions = dimensions;
    }

    public String[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String[] categoryIds) {
        this.categoryIds = categoryIds;
    }
}