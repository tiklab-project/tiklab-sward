package io.tiklab.sward.confluence.model;

public class ConfluenceVersion {

    // 导出版本
    private String versionNumber;

    // 导出类型
    private String exportType;

    // 导出服务类型
    private String serverType;

    // 是否包含附件
    private Boolean backupAttachments = false;

    // 构建号
    private String buildNumber;

    // 导出空间的key
    private String spaceKey;

    // 是否支持导入
    private Boolean isSupport = false;

    // 文件名称
    private String fileName;

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getSupport() {
        return isSupport;
    }

    public void setSupport(Boolean support) {
        isSupport = support;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getExportType() {
        return exportType;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public Boolean getBackupAttachments() {
        return backupAttachments;
    }

    public void setBackupAttachments(Boolean backupAttachments) {
        this.backupAttachments = backupAttachments;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getSpaceKey() {
        return spaceKey;
    }

    public void setSpaceKey(String spaceKey) {
        this.spaceKey = spaceKey;
    }
}
