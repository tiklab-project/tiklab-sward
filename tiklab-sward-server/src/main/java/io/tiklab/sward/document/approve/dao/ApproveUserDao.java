package io.tiklab.sward.document.approve.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.document.approve.entity.ApproveUserEntity;
import io.tiklab.sward.document.approve.model.ApproveUserQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ApproveUserDao {

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建审批详情
     * @param approveUserEntity 详情
     * @return 详情Id
     */
    public String createApproveUser(ApproveUserEntity approveUserEntity){
      return jpaTemplate.save(approveUserEntity,String.class);
    }


    /**
     * 更新审批详情
     * @param approveUserEntity 详情
     */
    public void updateApproveUser(ApproveUserEntity approveUserEntity){
        jpaTemplate.update(approveUserEntity);
    }


    /**
     * 删除审批详情
     * @param approveId 详情Id
     */
    public void deleteApproveUser(String approveId){
        jpaTemplate.delete(ApproveUserEntity.class, approveId);
    }


    /**
     * 根据审批Id获取审批详情
     * @param approveId 审批Id
     * @return 审批详情
     */
    @FindOne
    public ApproveUserEntity findApproveUser(String approveId){
       return jpaTemplate.findOne(ApproveUserEntity.class, approveId);
    }


    /**
     * 获取所有审批详情
     * @return 审批详情列表
     */
    @FindAll
    public List<ApproveUserEntity> findAllApproveUser(){
        List<ApproveUserEntity> entityList = jpaTemplate.findAll(ApproveUserEntity.class);
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
    public List<ApproveUserEntity> findApproveUserList(List<String> idList){
        List<ApproveUserEntity> entityList = jpaTemplate.findList(ApproveUserEntity.class, idList);
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
    public List<ApproveUserEntity> findApproveUserList(ApproveUserQuery query){
        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveUserEntity.class)
                .eq("approveId", query.getApproveId())
                .eq("status", query.getStatus())
                .eq("userId", query.getUserId())
                .eq("docId", query.getDocId())
                .orders(query.getOrderParams())
                .get();
        List<ApproveUserEntity> entityList = jpaTemplate.findList(queryCondition, ApproveUserEntity.class);
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
    public Pagination<ApproveUserEntity> findApproveUserPage(ApproveUserQuery query){
        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveUserEntity.class)
                .eq("approveId", query.getApproveId())
                .eq("status", query.getStatus())
                .eq("userId", query.getUserId())
                .eq("docId", query.getDocId())
                .orders(query.getOrderParams())
                .pagination(query.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApproveUserEntity.class);
    }

}
