package com.doublekit.wiki.repository.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.dss.annotation.Index;
import com.doublekit.dss.annotation.IndexField;
import com.doublekit.dss.annotation.IndexId;
import com.doublekit.dss.annotation.IndexQueryField;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinField;
import com.doublekit.user.user.model.User;
import com.doublekit.wiki.category.model.Category;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper
@Join
@Index
public class Repository extends BaseModel{

    @ApiProperty(name="id",desc="id")
    @IndexField
    @IndexId
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    @IndexField
    @IndexQueryField
    private java.lang.String name;

    //@NotNull
    @ApiProperty(name="typeId",desc="typeId",required = true)
    private java.lang.String typeId="1";

    @NotNull
    @ApiProperty(name="master",desc="负责人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinField(id = "id")
    private User master;

    @ApiProperty(name="desc",desc="desc")
    private java.lang.String desc;

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
    public java.lang.String getTypeId() {
        return typeId;
    }

    public void setTypeId(java.lang.String typeId) {
        this.typeId = typeId;
    }

    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }
}
