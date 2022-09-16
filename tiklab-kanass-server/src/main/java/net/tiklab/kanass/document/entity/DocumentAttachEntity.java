package net.tiklab.kanass.document.entity;

import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity
@Table(name="kanass_document_attach")
public class DocumentAttachEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "document_id",length = 32,notNull = true)
    private String documentId;

    @Column(name = "attachment",length = 256,notNull = true)
    private String attachment;

    @Column(name = "sort",length = 4)
    private Integer sort;

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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
