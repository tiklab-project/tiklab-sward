package io.tiklab.sward.confluence.model;

import io.tiklab.sward.richText.model.SwardText;

import java.util.ArrayList;
import java.util.List;

public class ConfluenceData {

    private String label;

    private String content;

    private List<Object> contentList = new ArrayList<>();

    private List<ConfluenceData> child;

    public List<Object> getContentList() {
        return contentList;
    }

    public void setContentList(List<Object> contentList) {
        this.contentList = contentList;
    }

    // Getter 和 Setter

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ConfluenceData> getChild() {
        return child;
    }

    public void setChild(List<ConfluenceData> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return toString(0);
    }

    private String toString(int indent) {
        StringBuilder sb = new StringBuilder();
        String prefix = "  ".repeat(indent);
        sb.append(prefix).append(label)
                .append(content != null && !content.isEmpty() ? " : " + content : "")
                .append("\n");
        if (child != null) {
            for (ConfluenceData c : child) {
                sb.append(c.toString(indent + 1));
            }
        }
        return sb.toString();
    }
}
