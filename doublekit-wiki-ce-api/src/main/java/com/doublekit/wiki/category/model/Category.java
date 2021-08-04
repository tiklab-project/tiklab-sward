package com.doublekit.wiki.category.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinField;
import com.doublekit.wiki.repository.model.RepositoryDetails;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ApiModel
@Mapper
@Join
public class Category extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="repositoryId",desc="repositoryId",required = true)
    private java.lang.String repositoryId;

    @ApiProperty(name="parentCategory",desc="上级分类",eg="@selectOne")
    @Mappings({
            @Mapping(source = "parentCategory.id",target = "parentCategoryId")
    })
    @JoinField(id = "id")
    private Category parentCategory;

    @ApiProperty(name="sort",desc="sort")
    private java.lang.Integer sort;

    @ApiProperty(name="children",desc="下级目录列表")
    private List<Category> children = new ArrayList<>();

    @ApiProperty(name="categoryMethod",desc="")
    private List<RepositoryDetails> repositoryDetails=new ArrayList<>();

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(java.lang.String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<RepositoryDetails> getRepositoryDetails() {
        return repositoryDetails;
    }

    public void setRepositoryDetails(List<RepositoryDetails> repositoryDetails) {
        this.repositoryDetails = repositoryDetails;
    }
}
