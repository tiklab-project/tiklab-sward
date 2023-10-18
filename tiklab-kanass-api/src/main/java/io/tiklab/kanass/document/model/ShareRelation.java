package io.tiklab.kanass.document.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.kanass.category.model.WikiCategory;
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

    @ApiProperty(name="documentIds",desc="id")
    private String[] documentIds;

    @ApiProperty(name="categoryIds",desc="id")
    private String[] categoryIds;


    @ApiProperty(name="document",desc="文档",required = true)
    @Mappings({
            @Mapping(source = "wikiDocument.id",target = "documentId")
    })
    @JoinQuery(key = "id")
    private WikiDocument wikiDocument;


    @ApiProperty(name="wikiCategory",desc="文档",required = true)
    @Mappings({
            @Mapping(source = "wikiCategory.id",target = "categoryId")
    })
    @JoinQuery(key = "id")
    private WikiCategory wikiCategory;

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

    public String[] getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(String[] documentIds) {
        this.documentIds = documentIds;
    }

    public String[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String[] categoryIds) {
        this.categoryIds = categoryIds;
    }



    public WikiCategory getWikiCategory() {
        return wikiCategory;
    }

    public void setWikiCategory(WikiCategory wikiCategory) {
        this.wikiCategory = wikiCategory;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public WikiDocument getWikiDocument() {
        return wikiDocument;
    }

    public void setWikiDocument(WikiDocument wikiDocument) {
        this.wikiDocument = wikiDocument;
    }
}
