package io.tiklab.sward.support.support.entity;

import io.tiklab.dal.jpa.annotation.*;

@Entity
@Table(name="wiki_tool_only_office")
public class OnlyOfficeEntity {

    @Id
    @GeneratorValue(length = 8)
    @Column(name = "id",length = 8)
    private String id;

    @Column(name = "server_address",length = 32,notNull = true)
    private String serverAddress;

    @Column(name = "token",length = 64,notNull = true)
    private String token;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
