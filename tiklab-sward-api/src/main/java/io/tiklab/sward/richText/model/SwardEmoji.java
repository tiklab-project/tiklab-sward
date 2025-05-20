package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardEmoji {


    private String type = "emoji";

    private String src;

    private List<Object> children;

    public SwardEmoji() {
    }

    public SwardEmoji(String type, String src) {
        this.type = type;
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }
}
