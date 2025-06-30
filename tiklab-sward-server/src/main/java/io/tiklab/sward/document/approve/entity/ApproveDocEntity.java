package io.tiklab.sward.document.approve.entity;

import io.tiklab.dal.jpa.annotation.*;

@Entity
@Table(name="wiki_approve_doc")
public class ApproveDocEntity {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "approve_id")
    private String approveId;

    @Column(name = "status")
    private String status;

    @Column(name = "document_id")
    private String documentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApproveId() {
        return approveId;
    }

    public void setApproveId(String approveId) {
        this.approveId = approveId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
