package io.tiklab.sward.document.approve.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.document.approve.entity.ApproveDetailsEntity;
import io.tiklab.sward.document.approve.model.ApproveDetailsQuery;
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
public class ApproveDetailsDao {

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建审批详情
     * @param approveDetailsEntity 详情
     * @return 详情Id
     */
    public String createApproveDetailsEntity(ApproveDetailsEntity approveDetailsEntity){
      return jpaTemplate.save(approveDetailsEntity,String.class);
    }


    /**
     * 更新审批详情
     * @param approveDetailsEntity 详情
     */
    public void updateApproveDetailsEntity(ApproveDetailsEntity approveDetailsEntity){
        jpaTemplate.update(approveDetailsEntity);
    }


    /**
     * 删除审批详情
     * @param approveId 详情Id
     */
    public void deleteApproveDetailsEntity(String approveId){
        jpaTemplate.delete(ApproveDetailsEntity.class, approveId);
    }


    /**
     * 根据审批Id获取审批详情
     * @param approveId 审批Id
     * @return 审批详情
     */
    @FindOne
    public ApproveDetailsEntity findApproveDetailsEntity(String approveId){
       return jpaTemplate.findOne(ApproveDetailsEntity.class, approveId);
    }


    /**
     * 获取所有审批详情
     * @return 审批详情列表
     */
    @FindAll
    public List<ApproveDetailsEntity> findAllApproveDetailsEntity(){
        List<ApproveDetailsEntity> entityList = jpaTemplate.findAll(ApproveDetailsEntity.class);
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
    public List<ApproveDetailsEntity> findApproveDetailsEntityList(List<String> idList){
        List<ApproveDetailsEntity> entityList = jpaTemplate.findList(ApproveDetailsEntity.class, idList);
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
    public List<ApproveDetailsEntity> findApproveDetailsEntityList(ApproveDetailsQuery query){
        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveDetailsEntity.class)
                .eq("approveId", query.getApproveId())
                .eq("status", query.getStatus())
                .eq("userId", query.getUserId())
                .orders(query.getOrderParams())
                .get();
        List<ApproveDetailsEntity> entityList = jpaTemplate.findList(queryCondition, ApproveDetailsEntity.class);
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
    public Pagination<ApproveDetailsEntity> findApproveDetailsEntityPage(ApproveDetailsQuery query){
        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveDetailsEntity.class)
                .eq("approveId", query.getApproveId())
                .eq("status", query.getStatus())
                .eq("userId", query.getUserId())
                .orders(query.getOrderParams())
                .pagination(query.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApproveDetailsEntity.class);
    }










}
