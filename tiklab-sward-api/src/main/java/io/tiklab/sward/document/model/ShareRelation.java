package io.tiklab.sward.document.model;

import io.tiklab.sward.node.model.Node;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper
public class ShareRelation extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="type",desc="类型")
    private String type = "document";

    @NotNull
    @ApiProperty(name="shareId",desc="类型")
    private String shareId;

    @ApiProperty(name="nodeIds",desc="id")
    private String[] nodeIds;



    @ApiProperty(name="node",desc="文档",required = true)
    @Mappings({
            @Mapping(source = "node.id",target = "nodeId")
    })
    @JoinQuery(key = "id")
    private Node node;

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

    public String[] getNodeIds() {
        return nodeIds;
    }

    public void setNodeIds(String[] nodeIds) {
        this.nodeIds = nodeIds;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
