package io.thoughtware.sward.node.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.core.BaseModel;
import io.thoughtware.dss.annotation.Document;
import io.thoughtware.dss.annotation.DocumentField;
import io.thoughtware.dss.annotation.DocumentId;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.document.model.WikiDocument;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.user.user.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ApiModel
@Mapper
@Join
@Document
public class Node extends BaseModel{
    // 移动到位置的id
    private String moveToId;

    // 根据前端传入的数据，与moveToId 2选1
    private String moveType;

    // 移动到位置的类型，文档，知识库
    private String moveToType;

    @ApiProperty(name="id",desc="id")
    @DocumentField
    @DocumentId
    private String id;

    //@NotNull
    @ApiProperty(name="name",desc="name",required = true)
    @DocumentField(queryField = true)
    private String name;


    @ApiProperty(name="wikiRepository",desc="知识库",eg="@selectOne")
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

    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private Node parent;


    @ApiProperty(name="children",desc="下级目录列表")
    private List<Node> children = new ArrayList<>();


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;

    @ApiProperty(name="dimension",desc="层级")
    private Integer dimension;

    @ApiProperty(name="sort",desc="sort")
    private Integer sort;

    @ApiProperty(name ="treePath",desc = "路径")
    private String treePath;

    @ApiProperty(name ="type",desc = "类型， 目录或者文档")
    private String type;

    @ApiProperty(name ="documentType",desc = "文档类型")
    private String documentType;

    public String getMoveToId() {
        return moveToId;
    }

    public void setMoveToId(String moveToId) {
        this.moveToId = moveToId;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public String getMoveToType() {
        return moveToType;
    }

    public void setMoveToType(String moveToType) {
        this.moveToType = moveToType;
    }


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

    public WikiRepository getWikiRepository() {
        return wikiRepository;
    }

    public void setWikiRepository(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
}
