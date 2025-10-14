package io.tiklab.sward.support.message.model;

public class SwardMessage {

    // 通知方案ID
    private String noticeId;

    // 发送消息类型
    private String sendType;

    // 仓库ID
    private String rpyId;

    // 仓库名称
    private String rpyName;

    // 节点ID
    private String nodeId;

    // 节点名称
    private String nodeName;

    // 审批ID
    private String reviewId;

    // 审批名称
    private String reviewName;

    // 用户ID
    private String userId;

    // 状态
    private String status;

    public String getReviewName() {
        return reviewName;
    }

    public SwardMessage setReviewName(String reviewName) {
        this.reviewName = reviewName;
        return this;
    }

    public String getRpyName() {
        return rpyName;
    }

    public SwardMessage setRpyName(String rpyName) {
        this.rpyName = rpyName;
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }

    public SwardMessage setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public SwardMessage() {
    }

    public String getSendType() {
        return sendType;
    }

    public SwardMessage setSendType(String sendType) {
        this.sendType = sendType;
        return this;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public SwardMessage setNoticeId(String noticeId) {
        this.noticeId = noticeId;
        return this;
    }

    public String getRpyId() {
        return rpyId;
    }

    public SwardMessage setRpyId(String rpyId) {
        this.rpyId = rpyId;
        return this;
    }

    public String getNodeId() {
        return nodeId;
    }

    public SwardMessage setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public String getReviewId() {
        return reviewId;
    }

    public SwardMessage setReviewId(String reviewId) {
        this.reviewId = reviewId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public SwardMessage setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public SwardMessage setStatus(String status) {
        this.status = status;
        return this;
    }
}
