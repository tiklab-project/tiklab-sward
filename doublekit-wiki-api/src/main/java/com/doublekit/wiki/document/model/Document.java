package com.doublekit.wiki.document.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinField;
import com.doublekit.wiki.category.model.Category;
import com.doublekit.wiki.repository.model.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Join
public class Document extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

   // @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="typeId",desc="typeId",required = true)
    private java.lang.String typeId="123";

    //@NotNull
    @ApiProperty(name="repository",desc="空间",eg="@selectOne")
    @Mappings({
            @Mapping(source = "repository.id",target = "repositoryId")
    })
    @JoinField(id = "id")
    private Repository repository;

    @ApiProperty(name="category",desc="目录",eg="@selectOne")
    @Mappings({
            @Mapping(source = "category.id",target = "categoryId")
    })
    @JoinField(id = "id")
    private Category category;

    @ApiProperty(name="details",desc="details")
    private java.lang.String details;

    //评论条数
    @ApiProperty(name="commentNumber",desc="评论条数")
    private java.lang.Integer commentNumber;

    @ApiProperty(name="formatType",desc="格式类型  category 目录  document 文档")
    private java.lang.String formatType="document";

    @ApiProperty(name="likenumInt",desc="点赞数")
    private java.lang.Integer likenumInt;

    @ApiProperty(name="likeUserList",desc="点赞人")
    private List<String> likeUserList;

    @ApiProperty(name="isLike",desc="查询人是否点赞")
    private  java.lang.String isLike;

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

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public java.lang.String getDetails() {
        return details;
    }

    public void setDetails(java.lang.String details) {
        this.details = details;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getLikenumInt() {
        return likenumInt;
    }

    public void setLikenumInt(Integer likenumInt) {
        this.likenumInt = likenumInt;
    }

    public List<String> getLikeUserList() {
        return likeUserList;
    }

    public void setLikeUserList(List<String> likeUserList) {
        this.likeUserList = likeUserList;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }
}