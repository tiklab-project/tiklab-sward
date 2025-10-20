package io.tiklab.sward.richText.model;

import java.util.List;

public class SwardCheckListItem {

    private boolean checked = false;

    private String type = "check-list-item";

    private List<Object> children;

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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
