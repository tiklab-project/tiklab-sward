package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardDivider {

    private String type = "divider";

    private List<Object> children;

    public SwardDivider() {
        List<Object> objects = new ArrayList<>();
        objects.add(new SwardText(""));
        children = objects;
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
