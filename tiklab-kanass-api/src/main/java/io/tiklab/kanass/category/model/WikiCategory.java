package io.tiklab.kanass.category.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.kanass.document.model.WikiDocument;
import io.tiklab.kanass.repository.model.WikiRepository;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ApiModel
@Mapper
@Join
public class WikiCategory extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    //@NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private java.lang.String name;

   // @NotNull
    @ApiProperty(name="wikiRepository",desc="空间",eg="@selectOne")
    @Mappings({
            @Mapping(source = "wikiRepository.id",target = "repositoryId")
    })
    @JoinQuery(key = "id")
    private WikiRepository wikiRepository;

    @ApiProperty(name="master",desc="负责人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    @ApiProperty(name="parentWikiCategory",desc="上级分类",eg="@selectOne")
    @Mappings({
            @Mapping(source = "parentWikiCategory.id",target = "parentCategoryId")
    })
    @JoinQuery(key = "id")
    private WikiCategory parentWikiCategory;

    @ApiProperty(name="sort",desc="sort")
    private java.lang.Integer sort;

    @ApiProperty(name="children",desc="下级目录列表")
    private ArrayList<Object> children = new ArrayList<>();

   @ApiProperty(name="categoryMethod",desc="")
    private List<WikiDocument> wikiDocuments =new ArrayList<>();

   @ApiProperty(name="formatType",desc="格式类型  category 目录  document 文档")
    private java.lang.String formatType="category";

    @ApiProperty(name="updateTime",desc="updateTime",required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp updateTime;

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

    public WikiRepository getWikiRepository() {
        return wikiRepository;
    }

    public void setWikiRepository(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }

    public WikiCategory getParentWikiCategory() {
        return parentWikiCategory;
    }

    public void setParentWikiCategory(WikiCategory parentWikiCategory) {
        this.parentWikiCategory = parentWikiCategory;
    }

    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    public ArrayList<Object> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Object> children) {
        this.children = children;
    }

    public List<WikiDocument> getDocuments() {
        return wikiDocuments;
    }

    public void setDocuments(List<WikiDocument> wikiDocuments) {
        this.wikiDocuments = wikiDocuments;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
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