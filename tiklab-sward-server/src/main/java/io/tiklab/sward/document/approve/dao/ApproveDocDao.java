package io.tiklab.sward.document.approve.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.document.approve.entity.ApproveDocEntity;
import io.tiklab.sward.document.approve.model.ApproveDocQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    


    /**
     * 根据查询条件获取审批详情
     * @param query 查询条件
     * @return 审批详情列表
     */
    public List<ApproveDocEntity> findApproveDocEntityList(ApproveDocQuery query){

        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveDocEntity.class)
                .eq("status", query.getStatus())
                .eq("approveId", query.getApproveId())
                .eq("docId", query.getDocId())
                .orders(query.getOrderParams())
                .get();
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
        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveDocEntity.class)
                .eq("status", query.getStatus())
                .eq("approveId", query.getApproveId())
                .eq("docId", query.getDocId())
                .orders(query.getOrderParams())
                .pagination(query.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApproveDocEntity.class);
    }










}
