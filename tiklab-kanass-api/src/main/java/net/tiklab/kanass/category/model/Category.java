package net.tiklab.kanass.category.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.user.user.model.User;
import net.tiklab.kanass.document.model.Document;
import net.tiklab.kanass.repository.model.Repository;

import java.util.ArrayList;
import java.util.List;

@ApiModel
@Mapper(targetAlias = "CategoryEntity")
@Join
public class Category extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    //@NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private java.lang.String name;

   // @NotNull
    @ApiProperty(name="repository",desc="空间",eg="@selectOne")
    @Mappings({
            @Mapping(source = "repository.id",target = "repositoryId")
    })
    @JoinQuery(key = "id")
    private Repository repository;

    @ApiProperty(name="master",desc="负责人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    @ApiProperty(name="parentCategory",desc="上级分类",eg="@selectOne")
    @Mappings({
            @Mapping(source = "parentCategory.id",target = "parentCategoryId")
    })
    @JoinQuery(key = "id")
    private Category parentCategory;

    @ApiProperty(name="sort",desc="sort")
    private java.lang.Integer sort;

    @ApiProperty(name="children",desc="下级目录列表")
    private List<Category> children = new ArrayList<>();

   @ApiProperty(name="categoryMethod",desc="")
    private List<Document> documents=new ArrayList<>();

   @ApiProperty(name="formatType",desc="格式类型  category 目录  document 文档")
    private java.lang.String formatType="category";

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

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
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
}
