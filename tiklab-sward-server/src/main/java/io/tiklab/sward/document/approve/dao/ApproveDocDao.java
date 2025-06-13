package io.tiklab.sward.document.approve.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.document.approve.entity.ApproveDocEntity;
import io.tiklab.sward.document.approve.model.ApproveDoc;
import io.tiklab.sward.document.approve.model.ApproveDocQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class ApproveDocDao {
    
    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建审批详情
     * @param approveDocEntity 详情
     * @return 详情Id
     */
    public String createApproveDocEntity(ApproveDocEntity approveDocEntity){
      return jpaTemplate.save(approveDocEntity,String.class);
    }


    /**
     * 更新审批详情
     * @param approveDocEntity 详情
     */
    public void updateApproveDocEntity(ApproveDocEntity approveDocEntity){
        jpaTemplate.update(approveDocEntity);
    }


    /**
     * 删除审批详情
     * @param approveId 详情Id
     */
    public void deleteApproveDocEntity(String approveId){
        jpaTemplate.delete(ApproveDocEntity.class, approveId);
    }


    /**
     * 根据审批Id获取审批详情
     * @param approveId 审批Id
     * @return 审批详情
     */
    @FindOne
    public ApproveDocEntity findApproveDocEntity(String approveId){
       return jpaTemplate.findOne(ApproveDocEntity.class, approveId);
    }


    /**
     * 获取所有审批详情
     * @return 审批详情列表
     */
    @FindAll
    public List<ApproveDocEntity> findAllApproveDocEntity(){
        List<ApproveDocEntity> entityList = jpaTemplate.findAll(ApproveDocEntity.class);
        if (Objects.isNull(entityList)){
            return new ArrayList<>();
        }
        return entityList;
    }


    /**
     * 根据id列表获取审批详情
     * @param idList id列表
     * @return 审批详情列表
     */
    @FindList
    public List<ApproveDocEntity> findApproveDocEntityList(List<String> idList){
        List<ApproveDocEntity> entityList = jpaTemplate.findList(ApproveDocEntity.class, idList);
        if (Objects.isNull(entityList)){
            return new ArrayList<>();
        }
        return entityList;
    }


    public boolean validateApproveDoc(String rpyId){
        ApproveDocQuery query = new ApproveDocQuery();
        query.setRpyId(rpyId);
        List<ApproveDocEntity> approveDocList = findApproveDocEntityList(query);
        if (approveDocList.isEmpty()){
            return true;
        }

        for (ApproveDocEntity approveDoc : approveDocList) {
            String status = approveDoc.getStatus();
            if (status.equals("pending") || status.equals("review")){
                return false;
            }
        }
        return true;
    }


    /**
     * 根据查询条件获取审批详情
     * @param query 查询条件
     * @return 审批详情列表
     */
    public List<ApproveDocEntity> findApproveDocEntityList(ApproveDocQuery query){

        QueryBuilders queryBuilders = QueryBuilders.createQuery(ApproveDocEntity.class)
                .eq("rpyId", query.getRpyId())
                .eq("status", query.getStatus())
                .eq("type", query.getType())
                .eq("documentId", query.getDocumentId())
                .eq("userId", query.getUserId())
                .like("userIds", query.getApproveUserId());
        if (!query.getStatusList().isEmpty()){
            queryBuilders = queryBuilders.in("status", query.getStatusList().toArray());
        }
        QueryCondition queryCondition = queryBuilders.orders(query.getOrderParams()).get();
        List<ApproveDocEntity> entityList = jpaTemplate.findList(queryCondition, ApproveDocEntity.class);
        if (Objects.isNull(entityList)){
            return new ArrayList<>();
        }
        return entityList;
    }


    /**
     * 分页查询审批详情
     * @param query 查询条件
     * @return 分页查询结果
     */
    public Pagination<ApproveDocEntity> findApproveDocEntityPage(ApproveDocQuery query){
        QueryBuilders queryBuilders = QueryBuilders.createQuery(ApproveDocEntity.class)
                .eq("rpyId", query.getRpyId())
                .eq("status", query.getStatus())
                .eq("type", query.getType())
                .eq("documentId", query.getDocumentId())
                .eq("userId", query.getUserId())
                .like("userIds", query.getApproveUserId());

        if (!query.getStatusList().isEmpty()){
            queryBuilders = queryBuilders.in("status", query.getStatusList().toArray());
        }

        if (!query.getDocumentList().isEmpty()){
            queryBuilders = queryBuilders.in("documentId", query.getDocumentList().toArray());
        }

        QueryCondition queryCondition = queryBuilders.orders(query.getOrderParams())
                .pagination(query.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApproveDocEntity.class);
    }










}
