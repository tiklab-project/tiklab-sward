package io.thoughtware.sward.support.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * 事项类型模型
 */

@ApiModel
@Join
@Mapper
public class WorkType extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="projectId",desc="项目id")
    private String projectId;

    @NotNull
    @ApiProperty(name="name",desc="事项类型名称",eg="@text32",required = true)
    private String name;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    private String desc;

    @ApiProperty(name="useNumber",desc="使用数量",eg="@text32")
    private Integer useNumber;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="scope",desc="类型， 0: 系统 1: 自定义")
    private Integer scope;


    @ApiProperty(name="iconUrl",desc="图标地址",eg="@text32")
    private String iconUrl;

    @ApiProperty(name="grouper",desc="分组",eg="@text32")
    private String grouper;

    @ApiProperty(name="code",desc="事项类型编码",eg="demand, task, defect")
    private String code;


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


    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrouper() {
        return grouper;
    }

    public void setGrouper(String grouper) {
        this.grouper = grouper;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Integer useNumber) {
        this.useNumber = useNumber;
    }
}
