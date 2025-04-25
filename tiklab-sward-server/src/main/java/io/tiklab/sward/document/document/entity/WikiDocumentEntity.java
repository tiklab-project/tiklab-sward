package io.tiklab.sward.document.document.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="wiki_document")
public class WikiDocumentEntity implements Serializable {

    @Id
    @Column(name = "id", length = 12)
    private String id;

    //内容
    @Column(name = "details")
    private String details;

    @Column(name = "detail_text")
    private String detailText;

    @Column(name = "repository_id")
    private String repositoryId;

    @Column(name = "version_id")
    private String versionId;

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}


