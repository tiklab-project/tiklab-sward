package io.tiklab.sward.document.export.model;

import java.util.List;
import java.util.Objects;

public class WordParagraph {

    List<Object> list;

    public WordParagraph() {
    }

    public WordParagraph(List<Object> list) {
        this.list = list;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
