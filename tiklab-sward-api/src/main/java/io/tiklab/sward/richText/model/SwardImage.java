package io.tiklab.sward.richText.model;

import java.util.List;

public class SwardImage {

    private List<Object> children;

    private String type = "image";

    private String url;

    private String width;

    private String height;

    public SwardImage() {
    }

    public SwardImage(String url) {
        this.url = url;
    }

    public SwardImage(String url, List<Object> children) {
        this.url = url;
        this.children = children;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
