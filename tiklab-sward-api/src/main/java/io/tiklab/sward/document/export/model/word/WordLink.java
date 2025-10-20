package io.tiklab.sward.document.export.model.word;

public class WordLink {

    private String url;

    private String text;

    public WordLink() {
    }

    public WordLink(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
