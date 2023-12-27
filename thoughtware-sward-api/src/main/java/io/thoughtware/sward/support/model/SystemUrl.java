package io.thoughtware.sward.support.model;

import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

@ApiModel
@Join
@Mapper
public class SystemUrl {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="name",desc="标题")
    private String name;

    @ApiProperty(name="systemUrl",desc="标题")
    private String systemUrl;

    @ApiProperty(name="webUrl",desc="标题")
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
