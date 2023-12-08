package io.thoughtware.sward.support.entity;

import io.tiklab.dal.jpa.annotation.*;

/**
 * 图标实例
 */
@Entity
@Table(name="kanass_icon")
public class IconEntity {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 图标名称
    @Column(name = "icon_name",length = 64,notNull = true)
    private String iconName;

    // 图标地址
    @Column(name = "icon_url",length = 64,notNull = true)
    private String iconUrl;

    //图标类型
    @Column(name = "icon_type",length = 32)
    private String iconType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }
}
