package io.tiklab.sward.confluence.model;

import java.util.ArrayList;
import java.util.List;

public class ConfluencePage {

    private String id;

    private String name;

    private String createTime;

    private String userId;

    private ConfluenceBody body;

    private String spaceId;

    private String contentStatus;

    private String version;

    private List<ConfluenceAttachment> attachmentIdList = new ArrayList<>();

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ConfluenceAttachment> getAttachmentIdList() {
        return attachmentIdList;
    }

    public void setAttachmentIdList(List<ConfluenceAttachment> attachmentIdList) {
        this.attachmentIdList = attachmentIdList;
    }

    public ConfluencePage() {
    }

    public ConfluencePage(String name, String createTime, String userId, String id, String spaceId) {
        this.name = name;
        this.createTime = createTime;
        this.userId = userId;
        this.id = id;
        this.spaceId = spaceId;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public ConfluenceBody getBody() {
        return body;
    }

    public void setBody(ConfluenceBody body) {
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
