package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardImage {

    private List<Object> children;

    private String type = "image";

    private String url;

    private Integer width;

    private Integer height;

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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
