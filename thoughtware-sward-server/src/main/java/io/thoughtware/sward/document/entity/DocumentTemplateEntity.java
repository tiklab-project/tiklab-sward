package io.thoughtware.sward.document.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity
@Table(name="kanass_document_template")
public class DocumentTemplateEntity implements Serializable {

    @Id
    @GeneratorValue(length = 8)
    @Column(name = "id",length = 8)
    private String id;
    //名称
    @Column(name = "name",length = 32,notNull = true)
    private String name;
    //描述
    @Column(name = "description",length = 64,notNull = true)
    private String description;
    //详情
    @Column(name = "details",length = 32)
    private String details;

    @Column(name = "icon_url",length = 32,notNull = true)
    private String iconUrl;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
