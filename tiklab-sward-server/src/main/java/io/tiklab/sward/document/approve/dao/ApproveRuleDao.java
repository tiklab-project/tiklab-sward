package io.tiklab.sward.document.approve.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.document.approve.entity.ApproveRuleEntity;
import io.tiklab.sward.document.approve.model.ApproveRuleQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class ApproveRuleDao {
    
    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建审批详情
     * @param approveRuleEntity 详情
     * @return 详情Id
     */
    public String createApproveRuleEntity(ApproveRuleEntity approveRuleEntity){
      return jpaTemplate.save(approveRuleEntity,String.class);
    }


    /**
     * 更新审批详情
     * @param approveRuleEntity 详情
     */
    public void updateApproveRuleEntity(ApproveRuleEntity approveRuleEntity){
        jpaTemplate.update(approveRuleEntity);
    }


    /**
     * 删除审批详情
     * @param approveId 详情Id
     */
    public void deleteApproveRuleEntity(String approveId){
        jpaTemplate.delete(ApproveRuleEntity.class, approveId);
    }


    /**
     * 根据审批Id获取审批详情
     * @param approveId 审批Id
     * @return 审批详情
     */
    @FindOne
    public ApproveRuleEntity findApproveRuleEntity(String approveId){
       return jpaTemplate.findOne(ApproveRuleEntity.class, approveId);
    }


    /**
     * 获取所有审批详情
     * @return 审批详情列表
     */
    @FindAll
    public List<ApproveRuleEntity> findAllApproveRuleEntity(){
        List<ApproveRuleEntity> entityList = jpaTemplate.findAll(ApproveRuleEntity.class);
        if (Objects.isNull(entityList)){
            return Collections.emptyList();
        }
        return entityList;
    }


    /**
     * 根据id列表获取审批详情
     * @param idList id列表
     * @return 审批详情列表
     */
    @FindList
    public List<ApproveRuleEntity> findApproveRuleEntityList(List<String> idList){
        List<ApproveRuleEntity> entityList = jpaTemplate.findList(ApproveRuleEntity.class, idList);
        if (Objects.isNull(entityList)){
            return Collections.emptyList();
        }
        return entityList;
    }


    /**
     * 根据查询条件获取审批详情
     * @param query 查询条件
     * @return 审批详情列表
     */
    public List<ApproveRuleEntity> findApproveRuleEntityList(ApproveRuleQuery query){
        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveRuleEntity.class)
                .orders(query.getOrderParams())
                .get();
        List<ApproveRuleEntity> entityList = jpaTemplate.findList(queryCondition, ApproveRuleEntity.class);
        if (Objects.isNull(entityList)){
            return Collections.emptyList();
        }
        return entityList;
    }


    /**
     * 分页查询审批详情
     * @param query 查询条件
     * @return 分页查询结果
     */
    public Pagination<ApproveRuleEntity> findApproveRuleEntityPage(ApproveRuleQuery query){
        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveRuleEntity.class)
                .orders(query.getOrderParams())
                .pagination(query.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApproveRuleEntity.class);
    }










}
