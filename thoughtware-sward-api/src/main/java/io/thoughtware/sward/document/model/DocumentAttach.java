package io.thoughtware.sward.document.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper
@Join
public class DocumentAttach extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="document",desc="文档",required = true)
    @Mappings({
            @Mapping(source = "document.id",target = "documentId")
    })
    @JoinQuery(key = "fileName")
    private WikiDocument wikiDocument;

    @NotNull
    @ApiProperty(name="attachment",desc="附件",required = true)
    @JoinQuery(key = "fileName")
    private String attachment;

    @ApiProperty(name="sort",desc="sort")
    private java.lang.Integer sort;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public WikiDocument getDocument() {
        return wikiDocument;
    }

    public void setDocument(WikiDocument wikiDocument) {
        this.wikiDocument = wikiDocument;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }
}
