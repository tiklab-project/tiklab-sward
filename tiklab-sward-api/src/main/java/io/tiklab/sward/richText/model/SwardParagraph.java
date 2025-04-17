package io.tiklab.sward.richText.model;


import java.util.ArrayList;
import java.util.List;

public class SwardParagraph {

    private List<Object> children = new ArrayList<>();

    private String type = "paragraph";


    public SwardParagraph() {
    }

    public SwardParagraph(Object children) {
        List<Object> childrenList = new ArrayList<>();
        childrenList.add(children);
        this.children = childrenList;
    }

    public SwardParagraph(List<Object> children ) {
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
}
