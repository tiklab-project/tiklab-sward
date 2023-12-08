package io.thoughtware.sward.document.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="kanass_document")
public class WikiDocumentEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id", length = 12)
    private String id;

    //名称
    @Column(name = "name", length = 64, notNull = true)
    private String name;

    //知识库类别
    @Column(name = "type_id", length = 32, notNull = true)
    private String typeId;
    //知识库id
    @Column(name = "repository_id", length = 32, notNull = true)
    private String repositoryId;

    //目录id
    @Column(name = "category_id", length = 32)
    private String categoryId;

    //管理员
    @Column(name = "master", length = 32, notNull = true)
    private String master;

    //内容
    @Column(name = "details")
    private String details;

    @Column(name = "detail_text")
    private String detailText;

    @Column(name = "tree_path")
    private String treePath;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "dimension")
    private Integer dimension;

    private java.lang.String formatType = "document";


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

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }
}


