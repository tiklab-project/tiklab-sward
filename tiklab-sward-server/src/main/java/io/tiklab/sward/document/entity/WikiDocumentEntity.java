package io.tiklab.sward.document.entity;


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

}


