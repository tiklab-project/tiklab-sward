package io.tiklab.sward.document.document.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper
public class DocumentTemplate extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    // 类型
    private String type;

    @ApiProperty(name="name",desc="名称",required = true)
    private java.lang.String name;

    @ApiProperty(name="details",desc="详情")
    private java.lang.String details;

    @ApiProperty(name="detailText",desc="详情文字")
    private String detailText;

    @ApiProperty(name = "iconUrl",desc="缩略图",required = true)
    private String iconUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public java.lang.String getDetails() {
        return details;
    }

    public void setDetails(java.lang.String details) {
        this.details = details;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }
}
