package io.thoughtware.sward.repository.dao;

import io.thoughtware.sward.repository.entity.WikiRepositoryFocusEntity;
import io.thoughtware.sward.repository.model.WikiRepositoryFocusQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RepositoryFocusDao
 */
@Repository
public class WikiRepositoryFocusDao {

    private static Logger logger = LoggerFactory.getLogger(WikiRepositoryFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param wikiRepositoryFocusEntity
     * @return
     */
    public String createRepositoryFocus(WikiRepositoryFocusEntity wikiRepositoryFocusEntity) {
        return jpaTemplate.save(wikiRepositoryFocusEntity,String.class);
    }

    /**
     * 更新
     * @param wikiRepositoryFocusEntity
     */
    public void updateRepositoryFocus(WikiRepositoryFocusEntity wikiRepositoryFocusEntity){
        jpaTemplate.update(wikiRepositoryFocusEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepositoryFocus(String id){
        jpaTemplate.delete(WikiRepositoryFocusEntity.class,id);
    }

    public void deleteRepositoryFocus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WikiRepositoryFocusEntity findRepositoryFocus(String id){
        return jpaTemplate.findOne(WikiRepositoryFocusEntity.class,id);
    }

    /**
    * findAllRepositoryFocus
    * @return
    */
    public List<WikiRepositoryFocusEntity> findAllRepositoryFocus() {
        return jpaTemplate.findAll(WikiRepositoryFocusEntity.class);
    }

    public List<WikiRepositoryFocusEntity> findRepositoryFocusList(List<String> idList) {
        return jpaTemplate.findList(WikiRepositoryFocusEntity.class,idList);
    }

    public List<WikiRepositoryFocusEntity> findRepositoryFocusList(WikiRepositoryFocusQuery wikiRepositoryFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiRepositoryFocusEntity.class)
                .eq("repositoryId", wikiRepositoryFocusQuery.getRepositoryId())
                .eq("masterId", wikiRepositoryFocusQuery.getMasterId())
                .orders(wikiRepositoryFocusQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, WikiRepositoryFocusEntity.class);
    }

    public Pagination<WikiRepositoryFocusEntity> findRepositoryFocusPage(WikiRepositoryFocusQuery wikiRepositoryFocusQuery) {
        return jpaTemplate.findPage(wikiRepositoryFocusQuery, WikiRepositoryFocusEntity.class);
    }
}