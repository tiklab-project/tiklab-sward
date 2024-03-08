package io.thoughtware.sward.category.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.sward.document.model.WikiDocument;
import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.user.user.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ApiModel
@Mapper
@Join
public class WikiCategory extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    private Node node;

    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
