package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardBulletedList {

    private String type = "bulleted-list";

    private List<Object> children= new ArrayList<>();

    public SwardBulletedList() {
    }

    public SwardBulletedList(String type, List<Object> children) {
        this.type = type;
        this.children = children;
    }

    public SwardBulletedList(List<Object> children) {
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
