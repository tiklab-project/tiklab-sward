package io.tiklab.sward.document.export.model.word;

import java.util.List;

public class WordCodeBlock {

    private List<Object> list;

    private String text;


    public WordCodeBlock(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public WordCodeBlock() {
    }

    public WordCodeBlock(List<Object> list) {
        this.list = list;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
