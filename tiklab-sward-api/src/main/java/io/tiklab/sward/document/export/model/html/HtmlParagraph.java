package io.tiklab.sward.document.export.model.html;

import java.util.List;

public class HtmlParagraph {

    List<Object> list;

    public HtmlParagraph() {
    }

    public HtmlParagraph(List<Object> list) {
        this.list = list;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
