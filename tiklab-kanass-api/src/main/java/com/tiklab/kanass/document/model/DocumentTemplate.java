package com.tiklab.kanass.document.model;

import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.core.BaseModel;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetAlias = "DocumentTemplateEntity")
public class DocumentTemplate extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="名称",required = true)
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="description",desc="描述",required = true)
    private java.lang.String description;

    @ApiProperty(name="details",desc="详情")
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
    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }
    public java.lang.String getDetails() {
        return details;
    }

    public void setDetails(java.lang.String details) {
        this.details = details;
    }
}
