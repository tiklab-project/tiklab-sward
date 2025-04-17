package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardTableRow {

    private List<Object> children = new ArrayList<>();

    private Object data;

    private String type ;

    private String key;

    public SwardTableRow() {
    }

    public SwardTableRow(List<Object> children, String type, String key) {
        this.children = children;
        this.type = type;
        this.data = new Object();
        this.key = key;
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
