package io.tiklab.sward.document.approve.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.sward.document.document.model.WikiDocument;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;

import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Join
public class Approve {

    private String id;

    private String name;

    // 文档类型   项目 project  文档 document
    private String type;

    // 审核状态  待审核 pending , 审核中 review , 通过 complete
    private String status;

    // 仓库Id
    @ApiProperty(name="wikiRepository",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "wikiRepository.id",target = "rpyId")
    })
    @JoinField(key = "id")
    private WikiRepository wikiRepository;


    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    @JoinField(key = "id")
    private User user;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createTime;

    // 审核人列表
    private String userIds;

    // 用户列表
    private List<User> userList;

    // 文档id列表
    private String wikiDocumentIds;

    // 文档信息
    private List<ApproveDoc> approveDocList;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WikiRepository getWikiRepository() {
        return wikiRepository;
    }

    public void setWikiRepository(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getWikiDocumentIds() {
        return wikiDocumentIds;
    }

    public void setWikiDocumentIds(String wikiDocumentIds) {
        this.wikiDocumentIds = wikiDocumentIds;
    }

    public List<ApproveDoc> getApproveDocList() {
        return approveDocList;
    }

    public void setApproveDocList(List<ApproveDoc> approveDocList) {
        this.approveDocList = approveDocList;
    }
}
