package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardTableContent {


    private String type = "table-content";


    private List<Object> children = new ArrayList<>();

    public SwardTableContent() {
    }

    public SwardTableContent(Object children) {
        List<Object> list = new ArrayList<>();
        list.add(children);
        this.children = list;
    }

    public SwardTableContent(List<Object> children) {
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
