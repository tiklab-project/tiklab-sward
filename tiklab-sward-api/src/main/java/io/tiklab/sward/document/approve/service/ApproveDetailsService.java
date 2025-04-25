package io.tiklab.sward.document.approve.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.sward.document.approve.model.ApproveDetails;
import io.tiklab.sward.document.approve.model.ApproveDetailsQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import java.util.List;

@JoinProvider(model = ApproveDetails.class)
public interface ApproveDetailsService {

    /**
     * 创建审批详情
     * @param approveDetails 详情
     * @return 详情Id
     */
    String createApproveDetails(ApproveDetails approveDetails);


    /**
     * 更新审批详情
     * @param approveDetails 详情
     */
    void updateApproveDetails(ApproveDetails approveDetails);


    /**
     * 删除审批详情
     * @param approveId 详情Id
     */
    void deleteApproveDetails(String approveId);


    /**
     * 根据审批Id获取审批详情
     * @param approveId 审批Id
     * @return 审批详情
     */
    @FindOne
    ApproveDetails findApproveDetails(String approveId);


    /**
     * 获取所有审批详情
     * @return 审批详情列表
     */
    @FindAll
    List<ApproveDetails> findAllApproveDetails();


    /**
     * 根据id列表获取审批详情
     * @param idList id列表
     * @return 审批详情列表
     */
    @FindList
    List<ApproveDetails> findApproveDetailsList(List<String> idList);


    /**
     * 根据查询条件获取审批详情
     * @param query 查询条件
     * @return 审批详情列表
     */
    List<ApproveDetails> findApproveDetailsList(ApproveDetailsQuery query);


    /**
     * 分页查询审批详情
     * @param query 查询条件
     * @return 分页查询结果
     */
    Pagination<ApproveDetails> findApproveDetailsPage(ApproveDetailsQuery query);










}
