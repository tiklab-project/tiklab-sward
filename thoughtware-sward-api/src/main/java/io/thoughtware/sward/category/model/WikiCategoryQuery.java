package io.thoughtware.sward.category.model;

import io.tiklab.core.BaseModel;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class WikiCategoryQuery extends BaseModel {

        @ApiProperty(name ="repositoryId",desc = "空间id")
        private String repositoryId;

        @ApiProperty(name ="parentWikiCategory",desc = "父级目录id")
        private String parentWikiCategory;

        @ApiProperty(name ="parentWikiCategorys",desc = "父级目录ids")
        private String[] parentWikiCategorys;

        @ApiProperty(name ="dimension",desc = "层级")
        private Integer dimension;
        @ApiProperty(name ="dimensions",desc = "层级")
        private Integer[] dimensions;

        @ApiProperty(name ="parentWikiCategoryIsNull",desc = "是否为空")
        private Boolean parentWikiCategoryIsNull;

        @ApiProperty(name ="treePath",desc = "路径")
        private String treePath;

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

        public String getRepositoryId() {
            return repositoryId;
        }

        public void setRepositoryId(String repositoryId) {
            this.repositoryId = repositoryId;
        }

    public String getParentWikiCategory() {
        return parentWikiCategory;
    }

    public void setParentWikiCategory(String parentWikiCategory) {
        this.parentWikiCategory = parentWikiCategory;
    }


    public Boolean getParentWikiCategoryIsNull() {
        return parentWikiCategoryIsNull;
    }

    public void setParentWikiCategoryIsNull(Boolean parentWikiCategoryIsNull) {
        this.parentWikiCategoryIsNull = parentWikiCategoryIsNull;
    }

    public Integer[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(Integer[] dimensions) {
        this.dimensions = dimensions;
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public String[] getParentWikiCategorys() {
        return parentWikiCategorys;
    }

    public void setParentWikiCategorys(String[] parentWikiCategorys) {
        this.parentWikiCategorys = parentWikiCategorys;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }
}