package io.tiklab.sward.confluence.model;

import java.util.ArrayList;
import java.util.List;

public class ConfluenceAttachment {

    // id
    private String id;

    // 名称
    private String name;

    // 创建时间
    private String createTime;

    // 状态
    private String contentStatus;

    // 页面Id
    private String pageId;

    // 版本
    private String version;

    // 空间Id
    private String spaceId;

    // 位置(未转义的位置)
    private String path;

    // 位置(已经转义的位置)
    private String address;

    private List<String> contentPropertiesList = new ArrayList<>();

    public List<String> getContentPropertiesList() {
        return contentPropertiesList;
    }

    public void setContentPropertiesList(List<String> contentPropertieList) {
        this.contentPropertiesList = contentPropertieList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ConfluenceAttachment() {
    }

    public ConfluenceAttachment(String id, String name, String createTime, String contentStatus, String pageId) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.contentStatus = contentStatus;
        this.pageId = pageId;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
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

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
