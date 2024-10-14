package io.tiklab.sward.node.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity @Table(name="wiki_node")
public class NodeEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    //名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    //管理员
    @Column(name = "master",length = 12,notNull = true)
    private String master;

    //知识库id
    @Column(name = "repository_id",length = 12,notNull = true)
    private String repositoryId;

    //上一级id
    @Column(name = "parent_id",length = 12)
    private String parentId;

    @Column(name = "status",length = 32)
    private String status;

    @Column(name = "update_time")
    private String updateTime;

    //事项创建时间
    @Column(name = "create_time")
    private String createTime;

    //描述
    @Column(name = "sort")
    private Integer sort;

    @Column(name = "dimension")
    private Integer dimension;

    @Column(name = "tree_path")
    private String treePath;

    // 类型， 目录 category, 文档 document
    @Column(name = "type")
    private String type;

    // 文档类型 富文本， markdown
    @Column(name = "document_type")
    private String documentType;

    @Column(name = "archived_time")
    private String archivedTime;

    @Column(name = "archived_user_id")
    private String archivedUserId;

    @Column(name = "archived_desc")
    private String archivedDesc;

    @Column(name = "recycle")
    private String recycle;

    @Column(name = "recycle_time")
    private String recycleTime;

    @Column(name = "recycle_user_id")
    private String recycleUserId;

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



    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArchivedTime() {
        return archivedTime;
    }

    public void setArchivedTime(String archivedTime) {
        this.archivedTime = archivedTime;
    }

    public String getArchivedUserId() {
        return archivedUserId;
    }

    public void setArchivedUserId(String archivedUserId) {
        this.archivedUserId = archivedUserId;
    }

    public String getArchivedDesc() {
        return archivedDesc;
    }

    public void setArchivedDesc(String archivedDesc) {
        this.archivedDesc = archivedDesc;
    }

    public String getRecycle() {
        return recycle;
    }

    public void setRecycle(String recycle) {
        this.recycle = recycle;
    }

    public String getRecycleTime() {
        return recycleTime;
    }

    public void setRecycleTime(String recycleTime) {
        this.recycleTime = recycleTime;
    }

    public String getRecycleUserId() {
        return recycleUserId;
    }

    public void setRecycleUserId(String recycleUserId) {
        this.recycleUserId = recycleUserId;
    }
}
