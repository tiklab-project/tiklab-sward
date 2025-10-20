package io.tiklab.sward.document.export.model.word;

public class WordCheckListItem {

    private String text;

    private boolean checked;

    public WordCheckListItem() {
    }

    public WordCheckListItem(String text, boolean checked) {
        this.text = text;
        this.checked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
