package io.tiklab.kanass.document.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.kanass.repository.model.Repository;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper(targetAlias = "DocumentRecentEntity")
public class DocumentRecent extends BaseModel {

    @ApiProperty(name="id",desc="id",required = true)
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="model",desc="model",required = true)
    private java.lang.String model;

    @NotNull
    @ApiProperty(name="modelId",desc="modelId",required = true)
    private java.lang.String modelId;

    @ApiProperty(name="master",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "masterId")
    })
    @JoinQuery(key = "id")
    private User master;


    @ApiProperty(name="repository",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "repository.id",target = "repositoryId")
    })
    @JoinQuery(key = "id")
    private Repository repository;


    @ApiProperty(name="recentTime",desc="recentTime")
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    @JSONField(format = "yyyy-MM-dd hh:MM:ss")
    private java.util.Date recentTime;

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
    public java.lang.String getModel() {
        return model;
    }

    public void setModel(java.lang.String model) {
        this.model = model;
    }
    public java.lang.String getModelId() {
        return modelId;
    }

    public void setModelId(java.lang.String modelId) {
        this.modelId = modelId;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public java.util.Date getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(java.util.Date recentTime) {
        this.recentTime = recentTime;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }
}