package io.tiklab.sward.document.export.model;

public class WordText {

    private String text;

    private Boolean blog = false;

    private Integer size = 10;

    public WordText() {
    }

    public WordText(String text, Integer size) {
        this.text = text;
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getBlog() {
        return blog;
    }

    public void setBlog(Boolean blog) {
        this.blog = blog;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return text;
    }
}
