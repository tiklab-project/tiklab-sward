package com.tiklab.kanass.document.model;

import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.dfs.common.model.DfsFile;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetAlias = "DocumentAttachEntity")
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
    private Document document;

    @NotNull
    @ApiProperty(name="attachment",desc="附件",required = true)
    @Mappings({
            @Mapping(source = "attachment.fileName",target = "attachment")
    })
    @JoinQuery(key = "fileName")
    private DfsFile attachment;

    @ApiProperty(name="sort",desc="sort")
    private java.lang.Integer sort;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public DfsFile getAttachment() {
        return attachment;
    }

    public void setAttachment(DfsFile attachment) {
        this.attachment = attachment;
    }

    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }
}
