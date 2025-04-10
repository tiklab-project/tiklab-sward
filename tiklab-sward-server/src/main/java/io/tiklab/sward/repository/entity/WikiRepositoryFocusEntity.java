package io.tiklab.sward.repository.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="wiki_repository_focus")
public class WikiRepositoryFocusEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "repository_id",length = 64)
    private String repositoryId;

    @Column(name = "master_id",length = 64)
    private String masterId;

    @Column(name = "sort")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }
}
