package io.thoughtware.sward.document.entity;

import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.GeneratorValue;
import io.thoughtware.dal.jpa.annotation.Id;
import io.thoughtware.dal.jpa.annotation.Table;import io.thoughtware.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="kanass_share")
public class ShareEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    //验证码
    @Column(name = "auth_code",length = 32)
    private String authCode;

    @Column(name = "limits",length = 64,notNull = true)
    private String limits;

    //创建时间
    @Column(name = "create_time",notNull = true)
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }
}
