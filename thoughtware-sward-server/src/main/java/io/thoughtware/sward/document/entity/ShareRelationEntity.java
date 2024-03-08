package io.thoughtware.sward.document.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="wiki_share_relation")
public class ShareRelationEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;


    //验证码
    @Column(name = "type",length = 32,notNull = true)
    private String type;


    @Column(name = "node_id",length = 64)
    private String nodeId;

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

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
