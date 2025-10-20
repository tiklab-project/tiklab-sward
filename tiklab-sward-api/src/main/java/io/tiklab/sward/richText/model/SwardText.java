package io.tiklab.sward.richText.model;

public class SwardText {

    private String text;

    private Boolean bold = false;  // 加粗

    private Boolean italic = false; // 斜体

    private Boolean underline = false; //  下划线

    private Boolean strike = false; // 删除线

    private String backgroundColor = "#FFFFFF";  // 背景颜色

    private String color = "#000000";

    private String fontSize = "10px";

    private Boolean codeLine = false;

    public Boolean getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(Boolean codeLine) {
        this.codeLine = codeLine;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Boolean getUnderline() {
        return underline;
    }

    public void setUnderline(Boolean underline) {
        this.underline = underline;
    }

    public Boolean getStrike() {
        return strike;
    }

    public void setStrike(Boolean strike) {
        this.strike = strike;
    }

    public Boolean getItalic() {
        return italic;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public SwardText() {
    }

    public SwardText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
