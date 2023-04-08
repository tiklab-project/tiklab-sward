package io.tiklab.kanass.document.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="kanass_share_relation")
public class ShareRelationEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;


    //验证码
    @Column(name = "type",length = 32,notNull = true)
    private String type;

    @Column(name = "document_id",length = 64)
    private String documentId;

    @Column(name = "category_id",length = 64)
    private String categoryId;

    @Column(name = "share_id",length = 64,notNull = true)
    private String shareId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
