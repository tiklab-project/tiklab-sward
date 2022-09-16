package net.tiklab.kanass.repository.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.util.Date;

@Entity @Table(name="kanass_repository")
public class RepositoryEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
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

    //添加时间
    @Column(name = "create_time")
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
