package io.tiklab.sward.richText.model;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SwardTable {

    private List<Object> children = new ArrayList<>();

    private Object data;

    private String type = "table";

    public SwardTable() {
    }

    public SwardTable(List<Object> children) {
        this.children = children;
        this.data = new Object();
    }

    public SwardTable(List<Object> children, String type) {
        this.children = children;
        this.data = new Object();
        this.type = type;
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
