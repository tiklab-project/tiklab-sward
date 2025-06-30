package io.tiklab.sward.document.approve.service;
import io.tiklab.core.page.Pagination;
import io.tiklab.sward.document.approve.model.ApproveRpy;
import io.tiklab.sward.document.approve.model.ApproveRpyQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import java.util.List;

@JoinProvider(model = ApproveRpy.class)
public interface ApproveRpyService {

    /**
     * 创建仓库审批
     * @param approveRpy
     * @return Id
     */
    String createApproveRpy(ApproveRpy approveRpy);


    /**
     * 更新仓库审批
     * @param approveRpy
     */
    void updateApproveRpy(ApproveRpy approveRpy);

    /**
     * 删除仓库审批
     * @param approveRpyId Id
     */
    void deleteApproveRpy(String approveRpyId);
    
    
    /**
     * 根据仓库审批Id获取仓库审批
     * @param approveRpyId 仓库审批Id
     * @return 仓库审批
     */
    @FindOne
    ApproveRpy findApproveRpy(String approveRpyId);
    
    
    /**
     * 获取所有仓库审批
     * @return 仓库审批列表
     */
    @FindAll
    List<ApproveRpy> findAllApproveRpy();



    /**
     * 根据id列表获取仓库审批
     * @param idList id列表
     * @return 仓库审批列表
     */
    @FindList
    List<ApproveRpy> findApproveRpyList(List<String> idList);
    
    
    /**
     * 根据查询条件获取仓库审批
     * @param query 查询条件
     * @return 仓库审批列表
     */
    List<ApproveRpy> findApproveRpyList(ApproveRpyQuery query);


    /**
     * 分页查询仓库审批
     * @param query 查询条件
     * @return 分页查询结果
     */
    Pagination<ApproveRpy> findApproveRpyPage(ApproveRpyQuery query);










}
