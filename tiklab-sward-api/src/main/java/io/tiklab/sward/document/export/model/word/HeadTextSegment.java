package io.tiklab.sward.document.export.model.word;

public class HeadTextSegment {

    private String text;

    private String color;      // 前景色（如 red, 00FF00）

    private String background; // 背景高亮色（如 yellow）

    private String fontSize;  // 字体大小

    private Boolean bold = false;  // 加粗

    private Boolean italic = false; // 斜体

    private Boolean underline = false; //  下划线

    private Boolean strike = false; // 删除线

    private Boolean codeLine = false; // 行内代码

    public Boolean getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(Boolean codeLine) {
        this.codeLine = codeLine;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public Boolean getItalic() {
        return italic;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
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

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
    
    
}
