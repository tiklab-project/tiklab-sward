package io.tiklab.sward.document.document.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;
import java.util.Date;

@ApiModel
@Mapper
@Join
public class Share extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="nodeIds",desc="id")
    private String[] nodeIds;

    @ApiProperty(name="authCode",desc="验证码")
    private String authCode;

    @ApiProperty(name="limits",desc="可见范围")
    private String limits;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="wikiRepository",desc="知识库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "wikiRepository.id",target = "rpyId")
    })
    @JoinField(key = "id")
    private WikiRepository wikiRepository;


    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    @JoinField(key = "id")
    private User user;

    private String name;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public WikiRepository getWikiRepository() {
        return wikiRepository;
    }

    public void setWikiRepository(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String[] getNodeIds() {
        return nodeIds;
    }

    public void setNodeIds(String[] nodeIds) {
        this.nodeIds = nodeIds;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }


}
