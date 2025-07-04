package io.tiklab.sward.document.approve.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.sward.document.approve.model.Approve;
import io.tiklab.sward.document.approve.model.ApproveQuery;
import io.tiklab.sward.document.approve.model.ApproveUser;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import java.util.List;

@JoinProvider(model = Approve.class)
public interface ApproveService {

    /**
     * 创建仓库审批
     * @param approve 
     * @return Id
     */
    String createApprove(Approve approve);


    /**
     * 更新仓库审批
     * @param approve 
     */
    void updateApprove(Approve approve);

    /**
     * 更新仓库审批
     * @param approveUser
     */
    void updateApproveStatus(ApproveUser approveUser);
    
    
    /**
     * 删除仓库审批
     * @param approveId Id
     */
    void deleteApprove(String approveId);


    /**
     * 评审
     * @param approveUser 评审信息
     */
    void approve(ApproveUser approveUser);
    
    
    /**
     * 根据仓库审批Id获取仓库审批
     * @param approveId 仓库审批Id
     * @return 仓库审批
     */
    @FindOne
    Approve findApprove(String approveId);
    
    
    /**
     * 获取所有仓库审批
     * @return 仓库审批列表
     */
    @FindAll
    List<Approve> findAllApprove();


    /**
     * 根据id列表获取仓库审批
     * @param idList id列表
     * @return 仓库审批列表
     */
    @FindList
    List<Approve> findApproveList(List<String> idList);
    
    
    /**
     * 根据查询条件获取仓库审批
     * @param query 查询条件
     * @return 仓库审批列表
     */
    List<Approve> findApproveList(ApproveQuery query);


    /**
     * 分页查询仓库审批
     * @param query 查询条件
     * @return 分页查询结果
     */
    Pagination<Approve> findApprovePage(ApproveQuery query);










}
