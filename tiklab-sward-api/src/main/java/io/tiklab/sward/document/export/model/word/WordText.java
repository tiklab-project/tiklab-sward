package io.tiklab.sward.document.export.model.word;

public class WordText {

    private String text;

    private Boolean blog = false;

    private String size = "10px";

    public WordText() {
    }

    public WordText(String text) {
        this.text = text;
    }

    public WordText(String text, String size) {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return text;
    }
}
