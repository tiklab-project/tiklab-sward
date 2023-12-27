package io.thoughtware.sward.document.model;

import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.dss.annotation.Document;
import io.thoughtware.dss.annotation.DocumentField;
import io.thoughtware.dss.annotation.DocumentId;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.user.user.model.User;

import java.sql.Timestamp;
import java.util.List;

@ApiModel
@Mapper
@Join
@Document
public class WikiDocument extends BaseModel{

    @ApiProperty(name="oldParentId",desc="更新之前的父级")
    private String oldParentId;

    @ApiProperty(name="oldSort",desc="更新之前的位置")
    private Integer oldSort;

    @ApiProperty(name="id",desc="id")
    @DocumentField
    @DocumentId
    private String id;

   // @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    @DocumentField(queryField = true)
    private String name;

    @ApiProperty(name="typeId",desc="typeId",required = true)
    private String typeId;

    @ApiProperty(name ="treePath",desc = "路径")
    private String treePath;

    @ApiProperty(name="updateTime",desc="updateTime",required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp updateTime;



    @ApiProperty(name="master",desc="负责人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    //@NotNull
    @ApiProperty(name="wikiRepository",desc="空间",eg="@selectOne")
    @Mappings({
            @Mapping(source = "wikiRepository.id",target = "repositoryId")
    })
    @JoinQuery(key = "id")
    private WikiRepository wikiRepository;

    @ApiProperty(name="wikiCategory",desc="目录",eg="@selectOne")
    @Mappings({
            @Mapping(source = "wikiCategory.id",target = "categoryId")
    })
    @JoinQuery(key = "id")
    private WikiCategory wikiCategory;


    @ApiProperty(name="details",desc="details")
    private java.lang.String details;

    @ApiProperty(name="detailText",desc="detailText")
    @DocumentField(queryField = true)
    private java.lang.String detailText;

    //评论条数
    @ApiProperty(name="commentNumber",desc="评论条数")
    private java.lang.Integer commentNumber;

    @ApiProperty(name="formatType",desc="格式类型  category 目录  document 文档")
    private java.lang.String formatType="document";

    @ApiProperty(name="likenumInt",desc="点赞数")
    private java.lang.Integer likenumInt;

    @ApiProperty(name="likeUserList",desc="点赞人")
    private List<String> likeUserList;

    @ApiProperty(name="isLike",desc="查询人是否点赞")
    private boolean isLike;

    @ApiProperty(name="isFocus",desc="查询人是否收藏")
    private boolean isFocus;

    @ApiProperty(name="isRele",desc="是否关联事项")
    private java.lang.Boolean isRele;

    @ApiProperty(name="sort",desc="是否关联事项")
    private Integer sort;

    @ApiProperty(name="dimension",desc="层级")
    private Integer dimension;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getTypeId() {
        return typeId;
    }

    public void setTypeId(java.lang.String typeId) {
        this.typeId = typeId;
    }

    public WikiRepository getWikiRepository() {
        return wikiRepository;
    }

    public void setWikiRepository(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }

    public WikiCategory getWikiCategory() {
        return wikiCategory;
    }

    public void setWikiCategory(WikiCategory wikiCategory) {
        this.wikiCategory = wikiCategory;
    }

    public java.lang.String getDetails() {
        return details;
    }

    public void setDetails(java.lang.String details) {
        this.details = details;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getLikenumInt() {
        return likenumInt;
    }

    public void setLikenumInt(Integer likenumInt) {
        this.likenumInt = likenumInt;
    }

    public List<String> getLikeUserList() {
        return likeUserList;
    }

    public void setLikeUserList(List<String> likeUserList) {
        this.likeUserList = likeUserList;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public Boolean getRele() {
        return isRele;
    }

    public void setRele(Boolean rele) {
        isRele = rele;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getOldParentId() {
        return oldParentId;
    }

    public void setOldParentId(String oldParentId) {
        this.oldParentId = oldParentId;
    }

    public Integer getOldSort() {
        return oldSort;
    }

    public void setOldSort(Integer oldSort) {
        this.oldSort = oldSort;
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }
}
