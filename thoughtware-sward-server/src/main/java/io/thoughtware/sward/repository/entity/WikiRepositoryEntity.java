package io.thoughtware.sward.repository.entity;


import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.GeneratorValue;
import io.thoughtware.dal.jpa.annotation.Id;
import io.thoughtware.dal.jpa.annotation.Table;import io.thoughtware.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="kanass_repository")
public class WikiRepositoryEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    //名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    //知识库类别
    @Column(name = "type_id",length = 32,notNull = true)
    private String typeId;

    //管理员
    @Column(name = "master",length = 32,notNull = true)
    private String master;

    //权限范围 0 全部可见 1成员可见
    @Column(name = "limits",length = 32,notNull = true)
    private String limits;

    @Column(name = "icon_url",length = 32,notNull = true)
    private String iconUrl;

    //添加时间
    @Column(name = "create_time")
    private Timestamp createTime;

    //描述
    @Column(name = "description",length = 64)
    private String desc;

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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
