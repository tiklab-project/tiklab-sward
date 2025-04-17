package io.tiklab.sward.richText.model;

public class SwardBulletedChildren {

    // 是否加加粗
    private Boolean bold  = false;

    private String  text;

    public SwardBulletedChildren() {
    }

    public SwardBulletedChildren(Boolean bold, String text) {
        this.bold = bold;
        this.text = text;
    }

    public SwardBulletedChildren(String text) {
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
