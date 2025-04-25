package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardTableCol {

    private List<Object> children = new ArrayList<>();

    private Object data;

    private String type ;

    private String key;

    private String width;

    public SwardTableCol() {
    }

    public SwardTableCol(List<Object> children, String type, String key, String width) {
        this.children = children;
        this.type = type;
        this.data = new Object();
        this.key = key;
        this.width = width;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
