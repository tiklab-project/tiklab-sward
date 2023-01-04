package net.tiklab.kanass.repository.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.kanass.repository.entity.RepositoryFocusEntity;
import net.tiklab.kanass.repository.model.RepositoryFocusQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RepositoryFocusDao
 */
@Repository
public class RepositoryFocusDao{

    private static Logger logger = LoggerFactory.getLogger(RepositoryFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param repositoryFocusEntity
     * @return
     */
    public String createRepositoryFocus(RepositoryFocusEntity repositoryFocusEntity) {
        return jpaTemplate.save(repositoryFocusEntity,String.class);
    }

    /**
     * 更新
     * @param repositoryFocusEntity
     */
    public void updateRepositoryFocus(RepositoryFocusEntity repositoryFocusEntity){
        jpaTemplate.update(repositoryFocusEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepositoryFocus(String id){
        jpaTemplate.delete(RepositoryFocusEntity.class,id);
    }

    public void deleteRepositoryFocus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RepositoryFocusEntity findRepositoryFocus(String id){
        return jpaTemplate.findOne(RepositoryFocusEntity.class,id);
    }

    /**
    * findAllRepositoryFocus
    * @return
    */
    public List<RepositoryFocusEntity> findAllRepositoryFocus() {
        return jpaTemplate.findAll(RepositoryFocusEntity.class);
    }

    public List<RepositoryFocusEntity> findRepositoryFocusList(List<String> idList) {
        return jpaTemplate.findList(RepositoryFocusEntity.class,idList);
    }

    public List<RepositoryFocusEntity> findRepositoryFocusList(RepositoryFocusQuery repositoryFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RepositoryFocusEntity.class)
                .eq("repositoryId", repositoryFocusQuery.getRepositoryId())
                .eq("masterId", repositoryFocusQuery.getMasterId())
                .orders(repositoryFocusQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, RepositoryFocusEntity.class);
    }

    public Pagination<RepositoryFocusEntity> findRepositoryFocusPage(RepositoryFocusQuery repositoryFocusQuery) {
        return jpaTemplate.findPage(repositoryFocusQuery,RepositoryFocusEntity.class);
    }
}