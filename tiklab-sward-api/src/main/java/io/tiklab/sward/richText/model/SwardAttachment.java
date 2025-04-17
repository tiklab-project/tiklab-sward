package io.tiklab.sward.richText.model;

import java.util.ArrayList;
import java.util.List;

public class SwardAttachment {

    private List<Object> children = new ArrayList<>();

    private String fileName ;

    private String fileUrl;

    private String type = "attachment";

    public SwardAttachment() {
    }

    public SwardAttachment(List<Object> children, String fileName, String fileUrl) {
        this.children = children;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
