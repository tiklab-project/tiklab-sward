package io.tiklab.sward.document.approve.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.sward.document.approve.model.ApproveRule;
import io.tiklab.sward.document.approve.model.ApproveRuleQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import java.util.List;

@JoinProvider(model = ApproveRule.class)
public interface ApproveRuleService {

    /**
     * 创建仓库审批规则
     * @param approveRule 
     * @return Id
     */
    String createApproveRule(ApproveRule approveRule);


    /**
     * 更新仓库审批规则
     * @param approveRule 
     */
    void updateApproveRule(ApproveRule approveRule);
    
    
    /**
     * 删除仓库审批规则
     * @param approveId Id
     */
    void deleteApproveRule(String approveId);
    
    
    /**
     * 根据仓库审批规则Id获取仓库审批规则
     * @param approveId 仓库审批规则Id
     * @return 仓库审批规则
     */
    @FindOne
    ApproveRule findApproveRule(String approveId);
    
    
    /**
     * 获取所有仓库审批规则
     * @return 仓库审批规则列表
     */
    @FindAll
    List<ApproveRule> findAllApproveRule();


    /**
     * 根据id列表获取仓库审批规则
     * @param idList id列表
     * @return 仓库审批规则列表
     */
    @FindList
    List<ApproveRule> findApproveRuleList(List<String> idList);
    
    
    /**
     * 根据查询条件获取仓库审批规则
     * @param query 查询条件
     * @return 仓库审批规则列表
     */
    List<ApproveRule> findApproveRuleList(ApproveRuleQuery query);


    /**
     * 分页查询仓库审批规则
     * @param query 查询条件
     * @return 分页查询结果
     */
    Pagination<ApproveRule> findApproveRulePage(ApproveRuleQuery query);










}
