package io.tiklab.sward.document.document.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="wiki_comment_version")
public class DocumentVersionEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    //文档id
    @Column(name = "document_id",length = 32,notNull = true)
    private String documentId;

    @Column(name = "version_name")
    private String versionName;

    //用户
    @Column(name = "user_id")
    private String userId;

    // 内容文本
    @Column(name = "text_document_id")
    private String textDocumentId;

    @Column(name = "create_time")
    private String createTime;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTextDocumentId() {
        return textDocumentId;
    }

    public void setTextDocumentId(String textDocumentId) {
        this.textDocumentId = textDocumentId;
    }
}
