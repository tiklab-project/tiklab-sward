package com.doublekit.wiki.repository.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.dfs.model.DfsFile;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class DocumentAttach extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="document",desc="文档",required = true)
    @Mappings({
            @Mapping(source = "document.id",target = "documentId")
    })
    @JoinField(id = "fileName")
    private Document document;

    @NotNull
    @ApiProperty(name="attachment",desc="附件",required = true)
    @Mappings({
            @Mapping(source = "attachment.fileName",target = "attachment")
    })
    @JoinField(id = "fileName")
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
