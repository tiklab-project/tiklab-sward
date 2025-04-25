package io.tiklab.sward.document.approve.model;

import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.sward.document.document.model.WikiDocument;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.user.user.model.User;

import java.util.List;

@Mapper
@Join
public class ApproveDoc {

    private String id;

    private String title;

    // 文档类型   项目 project  文档 document
    private String type;

    // 审核状态   待审核 pending ,  审核中 review , 通过 approved , 未通过 rejected
    private String status;

    // 文档
    @Mappings({
            @Mapping(source = "wikiDocument.id",target = "documentId")
    })
    @JoinQuery(key = "id")
    private WikiDocument wikiDocument;

    // 仓库Id
    @ApiProperty(name="wikiRepository",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "wikiRepository.id",target = "rpyId")
    })
    @JoinQuery(key = "id")
    private WikiRepository wikiRepository;

    // 审核人
    private String userIds;

    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    @JoinQuery(key = "id")
    private User user;

    // 创建时间
    private String createTime;

    // 用户id列表
    private List<String> userIdList;

    // 用户列表
    private List<User> userList;

    // 审核规则
    private ApproveRule approveRule;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public WikiRepository getWikiRepository() {
        return wikiRepository;
    }

    public void setWikiRepository(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public WikiDocument getWikiDocument() {
        return wikiDocument;
    }

    public void setWikiDocument(WikiDocument wikiDocument) {
        this.wikiDocument = wikiDocument;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ApproveRule getApproveRule() {
        return approveRule;
    }

    public void setApproveRule(ApproveRule approveRule) {
        this.approveRule = approveRule;
    }
}
