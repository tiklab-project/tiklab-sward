package io.thoughtware.sward.support.entity;

import io.thoughtware.dal.jpa.annotation.*;

@Entity
@Table(name="wiki_system_url")
public class SystemUrlEntity {
    @Id
    @GeneratorValue(length = 8)
    @Column(name = "id",length = 8)
    private String id;

    // 图标名称
    @Column(name = "name",length = 32,notNull = true)
    private String name;

    // 图标地址
    @Column(name = "system_url",length = 64,notNull = true)
    private String systemUrl;

    @Column(name = "web_url",length = 64,notNull = true)
    private String webUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemUrl() {
        return systemUrl;
    }

    public void setSystemUrl(String systemUrl) {
        this.systemUrl = systemUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
