package io.tiklab.kanass.repository.dao;

import io.tiklab.kanass.document.entity.DocumentRecentEntity;
import io.tiklab.kanass.repository.entity.WikiRepositoryEntity;
import io.tiklab.kanass.repository.entity.WikiRepositoryFocusEntity;
import io.tiklab.kanass.repository.model.WikiRepositoryQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.OrQueryCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.OrQueryBuilders;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RepositoryDao
 */
@Repository
public class WikiRepositoryDao {

    private static Logger logger = LoggerFactory.getLogger(WikiRepositoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param wikiRepositoryEntity
     * @return
     */
    public String createRepository(WikiRepositoryEntity wikiRepositoryEntity) {
        return jpaTemplate.save(wikiRepositoryEntity,String.class);
    }

    /**
     * 更新
     * @param wikiRepositoryEntity
     */
    public void updateRepository(WikiRepositoryEntity wikiRepositoryEntity){
        jpaTemplate.update(wikiRepositoryEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepository(String id){
        jpaTemplate.delete(WikiRepositoryEntity.class,id);
    }

    public void deleteRepository(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WikiRepositoryEntity findRepository(String id){
        return jpaTemplate.findOne(WikiRepositoryEntity.class,id);
    }

    /**
    * findAllRepository
    * @return
    */
    public List<WikiRepositoryEntity> findAllRepository() {
        return jpaTemplate.findAll(WikiRepositoryEntity.class);
    }

    public List<WikiRepositoryEntity> findRepositoryList(List<String> idList) {

        return jpaTemplate.findList(WikiRepositoryEntity.class,idList);
    }

    public List<WikiRepositoryEntity> findRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(WikiRepositoryEntity.class, "rs");
        OrQueryCondition orQueryBuildCondition = OrQueryBuilders.instance()
                .eq("limits","0")
                .in("id", wikiRepositoryQuery.getRepositoryIds())
                .get();
        QueryCondition queryCondition = queryBuilders.or(orQueryBuildCondition)
                .eq("master", wikiRepositoryQuery.getMasterId())
                .like("name", wikiRepositoryQuery.getName())
                .orders(wikiRepositoryQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, WikiRepositoryEntity.class);
    }

    public Pagination<WikiRepositoryEntity> findRepositoryPage(WikiRepositoryQuery wikiRepositoryQuery) {

        QueryBuilders queryBuilders = QueryBuilders.createQuery(WikiRepositoryEntity.class, "rs");
        OrQueryCondition orQueryBuildCondition = OrQueryBuilders.instance()
                .eq("limits",0)
                .in("id", wikiRepositoryQuery.getRepositoryIds())
                .get();
        QueryCondition queryCondition = queryBuilders.or(orQueryBuildCondition)
                .orders(wikiRepositoryQuery.getOrderParams())
                .pagination(wikiRepositoryQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WikiRepositoryEntity.class);
    }

    public List<WikiRepositoryEntity> findRecentRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiRepositoryEntity.class, "re")
                .leftJoin(DocumentRecentEntity.class,"dr","dr.modelId=re.id")
                .eq("dr.masterId", wikiRepositoryQuery.getMasterId())
                .eq("dr.model","repository")
                .like("re.name", wikiRepositoryQuery.getName())
                .orders(wikiRepositoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WikiRepositoryEntity.class);
    }

    public List<WikiRepositoryEntity> findFocusRepositoryList(WikiRepositoryQuery wikiRepositoryQuery){
        QueryCondition queryBuilders =  QueryBuilders.createQuery(WikiRepositoryEntity.class, "re")
                .leftJoin(WikiRepositoryFocusEntity.class,"rf","rf.repositoryId=re.id")
                .like("re.name", wikiRepositoryQuery.getName())
                .eq("rf.masterId", wikiRepositoryQuery.getMasterId())
                .orders(wikiRepositoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryBuilders, WikiRepositoryEntity.class);
    }
}