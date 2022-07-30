package com.tiklab.kanass.document.model;

import com.tiklab.postin.annotation.ApiModel;
import com.tiklab.postin.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;
import com.tiklab.user.user.model.User;
import com.tiklab.kanass.category.model.Category;
import com.tiklab.kanass.repository.model.Repository;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

@ApiModel
@Mapper(targetAlias = "DocumentEntity")
@Join
public class Document extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

   // @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private java.lang.String name;

    @ApiProperty(name="typeId",desc="typeId",required = true)
    private java.lang.String typeId;

    @ApiProperty(name="updateTime",desc="updateTime",required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp updateTime;


    @ApiProperty(name="master",desc="负责人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    //@NotNull
    @ApiProperty(name="repository",desc="空间",eg="@selectOne")
    @Mappings({
            @Mapping(source = "repository.id",target = "repositoryId")
    })
    @JoinQuery(key = "id")
    private Repository repository;

    @ApiProperty(name="category",desc="目录",eg="@selectOne")
    @Mappings({
            @Mapping(source = "category.id",target = "categoryId")
    })
    @JoinQuery(key = "id")
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

    @ApiProperty(name="isRele",desc="是否关联事项")
    private  java.lang.Boolean isRele;

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

    public Boolean getRele() {
        return isRele;
    }

    public void setRele(Boolean rele) {
        isRele = rele;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
