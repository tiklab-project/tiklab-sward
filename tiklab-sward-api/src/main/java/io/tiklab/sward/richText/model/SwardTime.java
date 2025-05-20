package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardTime {


    private String type = "date";

    private String dateValue = "2020-01-01";

    private List<Object> children;

    public SwardTime(String dateValue) {
        this.dateValue = dateValue;
        List<Object> list = new ArrayList<>();
        list.add(new SwardText(""));
        this.children = list;
    }

    public SwardTime() {
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }
}
