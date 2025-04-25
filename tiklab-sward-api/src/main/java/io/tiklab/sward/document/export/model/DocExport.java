package io.tiklab.sward.document.export.model;

public class DocExport {

    private String fileName;

    private String path;

    private byte[] bytes;

    public DocExport() {
    }

    public DocExport(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }

    public DocExport(String fileName, String path, byte[] bytes) {
        this.fileName = fileName;
        this.path = path;
        this.bytes = bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
