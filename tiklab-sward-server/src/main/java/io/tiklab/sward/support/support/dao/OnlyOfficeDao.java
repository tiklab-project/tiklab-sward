package io.tiklab.sward.support.support.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.support.support.entity.OnlyOfficeEntity;
import io.tiklab.sward.support.support.model.OnlyOfficeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OnlyOfficeDao {

    @Autowired
    JpaTemplate jpaTemplate;

    public String createOnlyOffice(OnlyOfficeEntity onlyOffice) {
        return jpaTemplate.save(onlyOffice, String.class);
    }

    public void updateOnlyOffice(OnlyOfficeEntity onlyOffice) {
        jpaTemplate.update(onlyOffice);
    }

    public void deleteOnlyOffice(String id) {
        jpaTemplate.delete(OnlyOfficeEntity.class, id);
    }

    public OnlyOfficeEntity findOnlyOffice(String id) {
        return jpaTemplate.findOne(OnlyOfficeEntity.class, id);
    }

    public List<OnlyOfficeEntity> findOnlyOfficeList(List<String> idList) {
        return jpaTemplate.findList(OnlyOfficeEntity.class, idList);
    }

    public List<OnlyOfficeEntity> findAllOnlyOffice() {
        return jpaTemplate.findAll(OnlyOfficeEntity.class);
    }

    public List<OnlyOfficeEntity> findOnlyOfficeList(OnlyOfficeQuery query) {
        QueryCondition queryCondition = QueryBuilders.createQuery(OnlyOfficeEntity.class)
                .orders(query.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, OnlyOfficeEntity.class);
    }


    public Pagination<OnlyOfficeEntity> findOnlyOfficePage(OnlyOfficeQuery query) {
        QueryCondition queryCondition = QueryBuilders.createQuery(OnlyOfficeEntity.class)
                .orders(query.getOrderParams())
                .pagination(query.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, OnlyOfficeEntity.class);
    }

}













