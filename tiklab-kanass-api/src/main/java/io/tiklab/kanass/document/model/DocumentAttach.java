package io.tiklab.kanass.document.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;

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
