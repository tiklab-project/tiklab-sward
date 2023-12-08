package io.thoughtware.sward.support.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * 优先级模型
 */
@ApiModel
@Mapper
public class WorkPriority extends BaseModel {

    @ApiProperty(name="id",desc="优先级ID")
    private String id;

    @NotNull
    @ApiProperty(name="name",desc="优先级名称",eg="@text32")
    private String name;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    private String desc;

    @ApiProperty(name="sort",desc="排序",eg="@int16")
    private Integer sort;

    @ApiProperty(name="group",desc="分组,系统:system;自定义:custom")
    private String group;

    @ApiProperty(name="iconUrl",desc="优先级图标地址",required = true)
    private String iconUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
