package io.tiklab.sward.confluence.model;

public class ConfluenceBody {

    private String id;

    private String content;

    private String bodyType;

    private String pageId;


    public ConfluenceBody() {
    }

    public ConfluenceBody(String id, String bodyType, String pageId) {
        this.id = id;
        this.bodyType = bodyType;
        this.pageId = pageId;
    }

    public ConfluenceBody(String id, String content, String bodyType, String pageId) {
        this.id = id;
        this.content = content;
        this.bodyType = bodyType;
        this.pageId = pageId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
