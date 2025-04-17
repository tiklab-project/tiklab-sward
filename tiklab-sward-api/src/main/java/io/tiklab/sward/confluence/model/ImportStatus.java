package io.tiklab.sward.confluence.model;

// 导入状态模型
public class ImportStatus {

    // 进度
    private int progress;

    // 信息
    private String message;

    // 状态
    private String status;

    public ImportStatus() {
    }

    public ImportStatus(int progress, String message, String status) {
        this.progress = progress;
        this.message = message;
        this.status = status;
    }

    public static ImportStatus ok(String message){
        return new ImportStatus(100, message, "success");
    }

    public static ImportStatus run(Integer progress, String message){
        return new ImportStatus(progress, message, "run");
    }

    public static ImportStatus error(Integer progress,String message){
        return new ImportStatus(progress, message, "error");
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

