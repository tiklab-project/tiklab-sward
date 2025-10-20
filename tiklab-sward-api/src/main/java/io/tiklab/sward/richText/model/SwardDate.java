package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardDate {

    private List<Object> children = new ArrayList<>();

    private String dateValue;

    private String type = "date";

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
