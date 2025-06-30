package io.tiklab.sward.document.approve.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.sward.document.approve.model.ApproveUser;
import io.tiklab.sward.document.approve.model.ApproveUserQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import java.util.List;

@JoinProvider(model = ApproveUser.class)
public interface ApproveUserService {

    /**
     * 创建用户审批
     * @param approveUser 用户
     * @return 用户Id
     */
    String createApproveUser(ApproveUser approveUser);


    /**
     * 更新用户审批
     * @param approveUser 用户
     */
    void updateApproveUser(ApproveUser approveUser);


    /**
     * 删除用户审批
     * @param id Id
     */
    void deleteApproveUser(String id);


    /**
     * 根据审批Id获取用户审批
     * @param id Id
     * @return 用户审批
     */
    @FindOne
    ApproveUser findApproveUser(String id);


    /**
     * 获取所有用户审批
     * @return 用户审批列表
     */
    @FindAll
    List<ApproveUser> findAllApproveUser();


    /**
     * 根据id列表获取用户审批
     * @param idList id列表
     * @return 用户审批列表
     */
    @FindList
    List<ApproveUser> findApproveUserList(List<String> idList);


    /**
     * 根据查询条件获取用户审批
     * @param query 查询条件
     * @return 用户审批列表
     */
    List<ApproveUser> findApproveUserList(ApproveUserQuery query);


    /**
     * 分页查询用户审批
     * @param query 查询条件
     * @return 分页查询结果
     */
    Pagination<ApproveUser> findApproveUserPage(ApproveUserQuery query);










}
