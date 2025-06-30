package io.tiklab.sward.document.approve.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.document.approve.entity.ApproveEntity;
import io.tiklab.sward.document.approve.model.ApproveQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class ApproveDao {
    
    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建审批
     * @param approveEntity 详情
     * @return 详情Id
     */
    public String createApproveEntity(ApproveEntity approveEntity){
        String id = approveEntity.getId();
        if (StringUtils.isEmpty(id)){
            id = UuidGenerator.getRandomIdByUUID(12);
        }
        approveEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        approveEntity.setId(id);
        return jpaTemplate.save(approveEntity,String.class);
    }


    /**
     * 更新审批
     * @param approveEntity 详情
     */
    public void updateApproveEntity(ApproveEntity approveEntity){
        jpaTemplate.update(approveEntity);
    }


    /**
     * 删除审批
     * @param approveId 详情Id
     */
    public void deleteApproveEntity(String approveId){
        jpaTemplate.delete(ApproveEntity.class, approveId);
    }


    /**
     * 根据审批Id获取审批
     * @param approveId 审批Id
     * @return 审批
     */
    @FindOne
    public ApproveEntity findApproveEntity(String approveId){
       return jpaTemplate.findOne(ApproveEntity.class, approveId);
    }


    /**
     * 获取所有审批
     * @return 审批列表
     */
    @FindAll
    public List<ApproveEntity> findAllApproveEntity(){
        List<ApproveEntity> entityList = jpaTemplate.findAll(ApproveEntity.class);
        if (Objects.isNull(entityList)){
            return new ArrayList<>();
        }
        return entityList;
    }


    /**
     * 根据id列表获取审批
     * @param idList id列表
     * @return 审批列表
     */
    @FindList
    public List<ApproveEntity> findApproveEntityList(List<String> idList){
        List<ApproveEntity> entityList = jpaTemplate.findList(ApproveEntity.class, idList);
        if (Objects.isNull(entityList)){
            return new ArrayList<>();
        }
        return entityList;
    }


    /**
     * 根据查询条件获取审批
     * @param query 查询条件
     * @return 审批列表
     */
    public List<ApproveEntity> findApproveEntityList(ApproveQuery query){
        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveEntity.class)
                .eq("rpyId", query.getRpyId())
                .eq("userId", query.getUserId())
                .eq("type", query.getType())
                .eq("status", query.getStatus())
                .orders(query.getOrderParams())
                .get();
        List<ApproveEntity> entityList = jpaTemplate.findList(queryCondition, ApproveEntity.class);
        if (Objects.isNull(entityList)){
            return new ArrayList<>();
        }
        return entityList;
    }


    /**
     * 分页查询审批
     * @param query 查询条件
     * @return 分页查询结果
     */
    public Pagination<ApproveEntity> findApproveEntityPage(ApproveQuery query){
        QueryCondition queryCondition = QueryBuilders.createQuery(ApproveEntity.class)
                .eq("rpyId", query.getRpyId())
                .eq("userId", query.getUserId())
                .eq("type", query.getType())
                .eq("status", query.getStatus())
                .orders(query.getOrderParams())
                .pagination(query.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApproveEntity.class);
    }










}
