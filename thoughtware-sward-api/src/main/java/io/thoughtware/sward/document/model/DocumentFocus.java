package io.thoughtware.sward.document.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.user.user.model.User;

import java.sql.Timestamp;

@ApiModel
@Join
@Mapper
public class DocumentFocus extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="documentId",desc="documentId")
    private String documentId;



    @ApiProperty(name="node",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "node.id",target = "documentId")
    })
    @JoinQuery(key = "id")
    private Node node;

    @ApiProperty(name="master",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "masterId")
    })
    @JoinQuery(key = "id")
    private User master;



    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String focusTime;

    @ApiProperty(name="sort",desc="sort")
    private Integer sort;

    @ApiProperty(name="wikiRepository",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "wikiRepository.id",target = "repositoryId")
    })
    @JoinQuery(key = "id")
    private WikiRepository wikiRepository;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFocusTime() {
        return focusTime;
    }

    public void setFocusTime(String focusTime) {
        this.focusTime = focusTime;
    }

    public WikiRepository getWikiRepository() {
        return wikiRepository;
    }

    public void setWikiRepository(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
