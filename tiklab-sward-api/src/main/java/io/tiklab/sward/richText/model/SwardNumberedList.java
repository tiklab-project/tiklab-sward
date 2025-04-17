package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardNumberedList {

    private String type = "numbered-list" ;

    private List<Object>  children = new ArrayList<>();

    public SwardNumberedList() {
    }

    public SwardNumberedList(String type, List<Object> children) {
        this.type = type;
        this.children = children;
    }

    public SwardNumberedList(List<Object> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }
}
