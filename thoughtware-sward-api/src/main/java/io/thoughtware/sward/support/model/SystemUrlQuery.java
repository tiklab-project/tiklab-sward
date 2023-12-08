package io.thoughtware.sward.support.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

/**
 * 查找最近访问的添加模型
 */
@ApiModel
public class SystemUrlQuery {
    @ApiProperty(name = "id", desc = "id")
    private String id;

    @ApiProperty(name = "name", desc = "系统名称")
    private String name;

    @ApiProperty(name = "systemUrl", desc = "系统地址")
    private String systemUrl;

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
}