package io.tiklab.sward.document.export.model;

import java.util.List;

public class WordCodeBlock {

    private List<Object> list;

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
