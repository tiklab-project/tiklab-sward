package io.tiklab.sward.document.approve.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.document.approve.entity.ApproveRpyEntity;
import io.tiklab.sward.document.approve.model.ApproveRpyQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ApproveRpyDao {
    
    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建审批详情
     * @param approveRpyEntity 详情
     * @return 详情Id
     */
    public String createApproveRpyEntity(ApproveRpyEntity approveRpyEntity){
      return jpaTemplate.save(approveRpyEntity,String.class);
    }


    /**
     * 更新审批详情
     * @param approveRpyEntity 详情
     */
    public void updateApproveRpyEntity(ApproveRpyEntity approveRpyEntity){
        jpaTemplate.update(approveRpyEntity);
    }


    /**
     * 删除审批详情
     * @param approveId 详情Id
     */
    public void deleteApproveRpyEntity(String approveId){
        jpaTemplate.delete(ApproveRpyEntity.class, approveId);
    }


    /**
     * 根据审批Id获取审批详情
     * @param approveId 审批Id
     * @return 审批详情
     */
    @FindOne
    public ApproveRpyEntity findApproveRpyEntity(String approveId){
       return jpaTemplate.findOne(ApproveRpyEntity.class, approveId);
    }


    /**
     * 获取所有审批详情
     * @return 审批详情列表
     */
    @FindAll
    public List<ApproveRpyEntity> findAllApproveRpyEntity(){
        List<ApproveRpyEntity> entityList = jpaTemplate.findAll(ApproveRpyEntity.class);
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
    public List<ApproveRpyEntity> findApproveRpyEntityList(List<String> idList){
        List<ApproveRpyEntity> entityList = jpaTemplate.findList(ApproveRpyEntity.class, idList);
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
    public List<ApproveRpyEntity> findApproveRpyEntityList(ApproveRpyQuery query){

        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveRpyEntity.class)
                .eq("rpyId", query.getRpyId())
                .orders(query.getOrderParams())
                .get();
        List<ApproveRpyEntity> entityList = jpaTemplate.findList(queryCondition, ApproveRpyEntity.class);
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
    public Pagination<ApproveRpyEntity> findApproveRpyEntityPage(ApproveRpyQuery query){
        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveRpyEntity.class)
                .eq("rpyId", query.getRpyId())
                .orders(query.getOrderParams())
                .pagination(query.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApproveRpyEntity.class);
    }










}
