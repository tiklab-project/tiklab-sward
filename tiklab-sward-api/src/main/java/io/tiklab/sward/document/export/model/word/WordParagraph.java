package io.tiklab.sward.document.export.model.word;

import java.util.List;

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
