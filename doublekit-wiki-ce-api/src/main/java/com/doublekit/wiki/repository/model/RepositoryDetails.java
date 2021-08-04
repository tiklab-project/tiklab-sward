package com.doublekit.wiki.repository.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.common.BaseModel;
import com.doublekit.dal.jpa.mapper.annotation.Column;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper
public class RepositoryDetails extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="repositoryId",desc="repositoryId",required = true)
    private java.lang.String repositoryId;

    @ApiProperty(name="categoryId",desc="目录id",required = true)
    private String categoryId;

    @ApiProperty(name="details",desc="内容")
    private java.lang.String details;

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
    public java.lang.String getDetails() {
        return details;
    }

    public void setDetails(java.lang.String details) {
        this.details = details;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
