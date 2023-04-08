package io.tiklab.kanass.document.model;

import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.kanass.category.model.Category;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel
@Mapper(targetAlias = "ShareEntity")
public class Share extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="documentIds",desc="id")
    private String[] documentIds;

    @ApiProperty(name="categoryIds",desc="id")
    private String[] categoryIds;

    @ApiProperty(name="authCode",desc="验证码")
    private java.lang.String authCode;

    @ApiProperty(name="limits",desc="可见范围")
    private java.lang.String limits;

    @ApiProperty(name="createTime",desc="创建时间")
    private java.util.Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String[] getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(String[] documentIds) {
        this.documentIds = documentIds;
    }

    public String[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String[] categoryIds) {
        this.categoryIds = categoryIds;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }


}
