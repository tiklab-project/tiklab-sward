package io.thoughtware.sward.category.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.sward.document.model.WikiDocument;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.join.annotation.JoinQuery;
import io.thoughtware.user.user.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ApiModel
@Mapper
@Join
public class WikiCategory extends BaseModel{
    @ApiProperty(name="oldParentId",desc="更新之前的父级")
    private String oldParentId;

    @ApiProperty(name="oldDimension",desc="更新之前的层级")
    private Integer oldDimension;
    @ApiProperty(name="oldSort",desc="更新之前的位置")
    private Integer oldSort;

    @ApiProperty(name ="treePath",desc = "路径")
    private String treePath;

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

    @ApiProperty(name="childrenNum",desc="下级目录列表")
    private Integer childrenNum;

    @ApiProperty(name="categoryMethod",desc="")
    private List<WikiDocument> wikiDocuments =new ArrayList<>();

    @ApiProperty(name="formatType",desc="格式类型  category 目录  document 文档")
    private java.lang.String formatType="category";

    @ApiProperty(name="updateTime",desc="updateTime",required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp updateTime;

    @ApiProperty(name="dimension",desc="层级")
    private Integer dimension;

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

    public Integer getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(Integer childrenNum) {
        this.childrenNum = childrenNum;
    }

    public Integer getOldSort() {
        return oldSort;
    }

    public void setOldSort(Integer oldSort) {
        this.oldSort = oldSort;
    }

    public String getOldParentId() {
        return oldParentId;
    }

    public void setOldParentId(String oldParentId) {
        this.oldParentId = oldParentId;
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public Integer getOldDimension() {
        return oldDimension;
    }

    public void setOldDimension(Integer oldDimension) {
        this.oldDimension = oldDimension;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }
}
