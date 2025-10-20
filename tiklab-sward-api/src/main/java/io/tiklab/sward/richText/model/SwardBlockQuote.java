package io.tiklab.sward.richText.model;

import java.util.List;

public class SwardBlockQuote {

    private String type = "block-quote";

    private List<Object> children;


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
