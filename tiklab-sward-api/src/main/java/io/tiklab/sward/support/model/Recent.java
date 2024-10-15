package io.tiklab.sward.support.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.sward.node.model.Node;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.sward.repository.model.WikiRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@ApiModel
@Join
@Mapper
public class Recent extends BaseModel {

    @ApiProperty(name="id",desc="id",required = true)
    private String id;

    @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private String name;

    @NotNull
    @ApiProperty(name="model",desc="model",required = true)
    private String model;

    @NotNull
    @ApiProperty(name="modelId",desc="modelId",required = true)
    private String modelId;


    @ApiProperty(name="master",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "masterId")
    })
    @JoinQuery(key = "id")
    private User master;


    @ApiProperty(name="wikiRepository",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "wikiRepository.id",target = "repositoryId")
    })
    @JoinQuery(key = "id")
    private WikiRepository wikiRepository;

    @ApiProperty(name="node",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "node.id",target = "modelId")
    })
    @JoinQuery(key = "id")
    private Node node;

    @ApiProperty(name="recentTime",desc="recentTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp recentTime;

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
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public WikiRepository getWikiRepository() {
        return wikiRepository;
    }

    public void setWikiRepository(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }

    public Timestamp getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(Timestamp recentTime) {
        this.recentTime = recentTime;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
