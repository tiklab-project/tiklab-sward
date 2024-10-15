package io.thoughtware.sward.repository.model;

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

@ApiModel
@Join
@Mapper
@Document
public class WikiRepository extends BaseModel{

    @ApiProperty(name="id",desc="id")
    @DocumentField
    @DocumentId
    private String id;


    @ApiProperty(name="name",desc="name")
    @DocumentField(queryField = true)
    private String name;

    //@NotNull
    @ApiProperty(name="typeId",desc="typeId")
    private String typeId="1";

    @ApiProperty(name="master",desc="负责人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    //权限范围 0 全部可见 1成员可见
    @ApiProperty(name="limits",desc="limits")
    private String limits;

    @ApiProperty(name="iconUrl",desc="iconUrl")
    private String iconUrl;

    @ApiProperty(name="categoryNum",desc="iconUrl")
    private Integer categoryNum;

    @ApiProperty(name="documentNum",desc="iconUrl")
    private Integer documentNum;

    @ApiProperty(name = "status")
    private String status = "nomal";

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="desc",desc="desc")
    private String desc;

    @ApiProperty(name = "archivedTime")
    private String archivedTime;

    @ApiProperty(name="archivedUser",desc="负责人",eg="@selectOne")
    @Mappings({
            @Mapping(source = "archivedUser.id",target = "archivedUserId")
    })
    @JoinQuery(key = "id")
    private User archivedUser;

    @ApiProperty(name = "archivedDesc")
    private String archivedDesc;

    @ApiProperty(name = "recycle")
    private String recycle = "0";

    @Mappings({
            @Mapping(source = "recycleUser.id",target = "recycleUserId")
    })
    @JoinQuery(key = "id")
    private User recycleUser;

    @ApiProperty(name = "recycleDesc")
    private String recycleTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public Integer getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(Integer categoryNum) {
        this.categoryNum = categoryNum;
    }

    public Integer getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(Integer documentNum) {
        this.documentNum = documentNum;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArchivedTime() {
        return archivedTime;
    }

    public void setArchivedTime(String archivedTime) {
        this.archivedTime = archivedTime;
    }

    public User getArchivedUser() {
        return archivedUser;
    }

    public void setArchivedUser(User archivedUser) {
        this.archivedUser = archivedUser;
    }

    public String getArchivedDesc() {
        return archivedDesc;
    }

    public void setArchivedDesc(String archivedDesc) {
        this.archivedDesc = archivedDesc;
    }

    public String getRecycle() {
        return recycle;
    }

    public void setRecycle(String recycle) {
        this.recycle = recycle;
    }

    public User getRecycleUser() {
        return recycleUser;
    }

    public void setRecycleUser(User recycleUser) {
        this.recycleUser = recycleUser;
    }

    public String getRecycleTime() {
        return recycleTime;
    }

    public void setRecycleTime(String recycleTime) {
        this.recycleTime = recycleTime;
    }
}
