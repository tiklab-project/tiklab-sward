package io.tiklab.sward.document.document.model;

import io.tiklab.core.BaseModel;
import io.tiklab.core.page.Page;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class ShareQuery  extends BaseModel {

    private String id;

    @ApiProperty(name ="shareLink",desc = "shareLink")
    private String shareLink;

    @ApiProperty(name ="type",desc = "类型")
    private String type ;

    @ApiProperty(name ="name",desc = "名称")
    private String name;

    @ApiProperty(name ="authCode",desc = "验证码")
    private String authCode ;

    @ApiProperty(name ="limits",desc = "验证码")
    private String limits ;

    private String rpyId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getRpyId() {
        return rpyId;
    }

    public void setRpyId(String rpyId) {
        this.rpyId = rpyId;
    }

    public String getAuthCode() {
            return authCode;
        }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public List<Order> getOrderParams() {
            return orderParams;
        }

    public void setOrderParams(List<Order> orderParams) {
        this.orderParams = orderParams;
    }

    public Page getPageParam() {
        return pageParam;
    }

    public void setPageParam(Page pageParam) {
            this.pageParam = pageParam;
        }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}