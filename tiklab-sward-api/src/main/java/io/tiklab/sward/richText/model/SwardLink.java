package io.tiklab.sward.richText.model;

import io.tiklab.core.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class SwardLink  extends BaseModel {

    private String url;

    public List<Object> children;

    private String type = "link";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SwardLink() {
    }

    public SwardLink(String url, String text) {
        this.url = url;
        List<Object> list = new ArrayList<>();
        list.add(new SwardText(text));
        this.children = list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }
}
