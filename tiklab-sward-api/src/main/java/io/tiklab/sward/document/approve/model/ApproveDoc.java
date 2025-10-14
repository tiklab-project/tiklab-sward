package io.tiklab.sward.document.approve.model;

import io.tiklab.sward.document.document.model.WikiDocument;
import io.tiklab.sward.node.node.model.Node;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

@Mapper
@Join
public class ApproveDoc {

    private String id;

    private String approveId;

    @Mappings({
            @Mapping(source = "wikiDocument.id",target = "documentId")
    })
    @JoinField(key = "id")
    private WikiDocument wikiDocument;

    private Node node;

    // wait：等待审批 pass：已通过 reject：被拒绝
    private String status;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

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

    public WikiDocument getWikiDocument() {
        return wikiDocument;
    }

    public void setWikiDocument(WikiDocument wikiDocument) {
        this.wikiDocument = wikiDocument;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
