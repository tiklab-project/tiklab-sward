package io.tiklab.sward.confluence.model;

import java.util.List;

public class ConfluenceSpace {

    private String id;

    private String name;

    private String createTime;

    private String userId;

    private List<ConfluencePage> pageList;

    public ConfluenceSpace() {
    }

    public ConfluenceSpace(String id, String name, String createTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
    }

    public ConfluenceSpace(String id, String name, String createTime, String userId) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.userId = userId;
    }

    public List<ConfluencePage> getPageList() {
        return pageList;
    }

    public void setPageList(List<ConfluencePage> pageList) {
        this.pageList = pageList;
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
