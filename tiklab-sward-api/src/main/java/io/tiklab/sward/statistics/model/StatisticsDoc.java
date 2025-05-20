package io.tiklab.sward.statistics.model;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

@Mapper
@Join
public class StatisticsDoc {

    private String id;

    // 类型 create:创建  read:阅读   edit:编辑  export:导出
    private String type;

    // 文档id
    private String documentId;

    // 仓库ID
    private String rpyId;

    // 用户ID
    private String userId;

    // 创建时间
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public StatisticsDoc() {
    }

    public StatisticsDoc(String type,  String rpyId, String documentId,String userId) {
        this.type = type;
        this.documentId = documentId;
        this.rpyId = rpyId;
        this.userId = userId;
    }

    public static StatisticsDoc create(String rpyId, String documentId){
        return new StatisticsDoc("create", rpyId, documentId, LoginContext.getLoginId());
    }

    public static StatisticsDoc read(String rpyId, String documentId){
        return new StatisticsDoc("read", rpyId, documentId, LoginContext.getLoginId());
    }

    public static StatisticsDoc edit(String rpyId, String documentId){
        return new StatisticsDoc("edit", rpyId, documentId, LoginContext.getLoginId());
    }

    public static StatisticsDoc export(String rpyId, String documentId){
        return new StatisticsDoc("export", rpyId, documentId, LoginContext.getLoginId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getRpyId() {
        return rpyId;
    }

    public void setRpyId(String rpyId) {
        this.rpyId = rpyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
