package io.tiklab.sward.support.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.sql.Timestamp;

@Entity
@Table(name="wiki_recent")
public class RecentEntity {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12,notNull = true)
    private String id;

    //名字
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    //模型
    @Column(name = "model",length = 64,notNull = true)
    private String model;

    //关联id
    @Column(name = "model_id",length = 64,notNull = true)
    private String modelId;


    //操作人id
    @Column(name = "master_id",length = 64,notNull = true)
    private String masterId;

    //知识库id
    @Column(name = "repository_id",length = 32,notNull = true)
    private String repositoryId;

    //动态
    @Column(name = "recent_time")
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

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Timestamp getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(Timestamp recentTime) {
        this.recentTime = recentTime;
    }
}
