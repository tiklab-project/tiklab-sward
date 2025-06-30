package io.tiklab.sward.document.approve.service;
import io.tiklab.core.page.Pagination;
import io.tiklab.sward.document.approve.model.ApproveDoc;
import io.tiklab.sward.document.approve.model.ApproveDocQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import java.util.List;

@JoinProvider(model = ApproveDoc.class)
public interface ApproveDocService {

    /**
     * 创建文档审批
     * @param approveDoc
     * @return Id
     */
    String createApproveDoc(ApproveDoc approveDoc);


    /**
     * 更新文档审批
     * @param approveDoc
     */
    void updateApproveDoc(ApproveDoc approveDoc);

    /**
     * 删除文档审批
     * @param approveDocId Id
     */
    void deleteApproveDoc(String approveDocId);
    
    
    /**
     * 根据文档审批Id获取文档审批
     * @param approveDocId 文档审批Id
     * @return 文档审批
     */
    @FindOne
    ApproveDoc findApproveDoc(String approveDocId);
    
    
    /**
     * 获取所有文档审批
     * @return 文档审批列表
     */
    @FindAll
    List<ApproveDoc> findAllApproveDoc();



    /**
     * 根据id列表获取文档审批
     * @param idList id列表
     * @return 文档审批列表
     */
    @FindList
    List<ApproveDoc> findApproveDocList(List<String> idList);
    
    
    /**
     * 根据查询条件获取文档审批
     * @param query 查询条件
     * @return 文档审批列表
     */
    List<ApproveDoc> findApproveDocList(ApproveDocQuery query);


    /**
     * 分页查询文档审批
     * @param query 查询条件
     * @return 分页查询结果
     */
    Pagination<ApproveDoc> findApproveDocPage(ApproveDocQuery query);










}
