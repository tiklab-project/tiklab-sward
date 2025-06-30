package io.tiklab.sward.document.document.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;

import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

import java.io.Serializable;

@Mapper
@Join
public class DocumentVersion implements Serializable {

    // id
    private String id;

    //文档id
    private String documentId;

    // 版本名称
    private String versionName;

    //操作人
    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    @JoinField(key = "id")
    private User user;

    //
    @Mappings({
            @Mapping(source = "wikiDocument.id",target = "textDocumentId")
    })
    @JoinField(key = "id")
    private WikiDocument wikiDocument;

    // 创建时间
    private String createTime;


    public WikiDocument getWikiDocument() {
        return wikiDocument;
    }

    public void setWikiDocument(WikiDocument wikiDocument) {
        this.wikiDocument = wikiDocument;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
