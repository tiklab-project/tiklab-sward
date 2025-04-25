package io.tiklab.sward.document.export.model;

public class WordHead {

    private String text;

    private Integer size;

    private Integer  level;

    public WordHead() {
    }

    public WordHead(String text, Integer size, Integer level) {
        this.text = text;
        this.size = size;
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
