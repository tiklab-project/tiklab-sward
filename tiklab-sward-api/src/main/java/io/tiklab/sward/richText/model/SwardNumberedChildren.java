package io.tiklab.sward.richText.model;

public class SwardNumberedChildren {

    // 是否加加粗
    private Boolean bold  = false;

    private String  text;

    public SwardNumberedChildren() {
    }

    public SwardNumberedChildren(Boolean bold, String text) {
        this.bold = bold;
        this.text = text;
    }

    public SwardNumberedChildren(String text) {
        this.text = text;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
