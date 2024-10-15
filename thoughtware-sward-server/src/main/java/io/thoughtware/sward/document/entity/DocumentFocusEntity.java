package io.thoughtware.sward.document.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="wiki_document_focus")
public class DocumentFocusEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "document_id",length = 64)
    private String documentId;

    @Column(name = "master_id",length = 64)
    private String masterId;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "focus_time")
    private String focusTime;

    @Column(name = "repository_id")
    private String repositoryId;

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

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }


    public String getFocusTime() {
        return focusTime;
    }

    public void setFocusTime(String focusTime) {
        this.focusTime = focusTime;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }
}
