package net.tiklab.kanass.document.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.dfs.common.model.DfsFile;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;

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
