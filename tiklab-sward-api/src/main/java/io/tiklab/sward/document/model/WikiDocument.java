package io.tiklab.sward.document.model;

import io.tiklab.sward.node.model.Node;
import io.tiklab.dss.annotation.Document;
import io.tiklab.dss.annotation.DocumentField;
import io.tiklab.dss.annotation.DocumentId;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;

import java.util.List;

@ApiModel
@Mapper
@Join
@Document
public class WikiDocument extends BaseModel{

    @ApiProperty(name="id",desc="id")
    @DocumentField
    @DocumentId
    private String id;

    private Node node;

    @ApiProperty(name="details",desc="details")
    private String details;

    @ApiProperty(name="detailText",desc="detailText")
    @DocumentField(queryField = true)
    private String detailText;

    //评论条数
    @ApiProperty(name="commentNumber",desc="评论条数")
    private Integer commentNumber;

    @ApiProperty(name="likenumInt",desc="点赞数")
    private Integer likenumInt;

    @ApiProperty(name="likeUserList",desc="点赞人")
    private List<String> likeUserList;

    @ApiProperty(name="isLike",desc="查询人是否点赞")
    private boolean isLike;

    @ApiProperty(name="isFocus",desc="查询人是否收藏")
    private boolean isFocus;

    @ApiProperty(name="isRele",desc="是否关联事项")
    private Boolean isRele;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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


    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }


    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
