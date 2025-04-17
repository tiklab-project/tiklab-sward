package io.tiklab.sward.richText.model;

import io.tiklab.core.utils.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

public class SwardHead {

    private List<Object> children = new ArrayList<>();

    private String head;

    private String id = UuidGenerator.getRandomIdByUUID(12);

    private String type = "head";


    public SwardHead() {
    }

    public SwardHead(String head,Object children) {
        List<Object> childrenList = new ArrayList<>();
        childrenList.add(children);
        this.children = childrenList;
        this.head = head;
    }

    public SwardHead(String head,List<Object> children ) {
        this.children = children;
        this.head = head;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
