package io.tiklab.kanass.repository.dao;

import io.tiklab.kanass.document.entity.DocumentRecentEntity;
import io.tiklab.kanass.document.model.DocumentRecentQuery;
import io.tiklab.kanass.repository.entity.RepositoryEntity;
import io.tiklab.kanass.repository.entity.RepositoryFocusEntity;
import io.tiklab.kanass.repository.model.RepositoryQuery;
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
public class RepositoryDao{

    private static Logger logger = LoggerFactory.getLogger(RepositoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param repositoryEntity
     * @return
     */
    public String createRepository(RepositoryEntity repositoryEntity) {
        return jpaTemplate.save(repositoryEntity,String.class);
    }

    /**
     * 更新
     * @param repositoryEntity
     */
    public void updateRepository(RepositoryEntity repositoryEntity){
        jpaTemplate.update(repositoryEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepository(String id){
        jpaTemplate.delete(RepositoryEntity.class,id);
    }

    public void deleteRepository(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RepositoryEntity findRepository(String id){
        return jpaTemplate.findOne(RepositoryEntity.class,id);
    }

    /**
    * findAllRepository
    * @return
    */
    public List<RepositoryEntity> findAllRepository() {
        return jpaTemplate.findAll(RepositoryEntity.class);
    }

    public List<RepositoryEntity> findRepositoryList(List<String> idList) {

        return jpaTemplate.findList(RepositoryEntity.class,idList);
    }

    public List<RepositoryEntity> findRepositoryList(RepositoryQuery repositoryQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(RepositoryEntity.class, "rs");
        OrQueryCondition orQueryBuildCondition = OrQueryBuilders.instance()
                .eq("limits",0)
                .in("id",repositoryQuery.getRepositoryIds())
                .get();
        QueryCondition queryCondition = queryBuilders.or(orQueryBuildCondition)
                .eq("master", repositoryQuery.getMasterId())
                .orders(repositoryQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, RepositoryEntity.class);
    }

    public Pagination<RepositoryEntity> findRepositoryPage(RepositoryQuery repositoryQuery) {

        QueryBuilders queryBuilders = QueryBuilders.createQuery(RepositoryEntity.class, "rs");
        OrQueryCondition orQueryBuildCondition = OrQueryBuilders.instance()
                .eq("limits",0)
                .in("id",repositoryQuery.getRepositoryIds())
                .get();
        QueryCondition queryCondition = queryBuilders.or(orQueryBuildCondition)
                .orders(repositoryQuery.getOrderParams())
                .pagination(repositoryQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, RepositoryEntity.class);
    }

    public List<RepositoryEntity> findRecentRepositoryList(DocumentRecentQuery documentRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RepositoryEntity.class, "re")
                .leftJoin(DocumentRecentEntity.class,"dr","dr.modelId=re.id")
                .eq("dr.masterId", documentRecentQuery.getMasterId())
                .eq("dr.model","repository")
                .orders(documentRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RepositoryEntity.class);
    }

    public List<RepositoryEntity> findFocusRepositoryList(RepositoryQuery repositoryQuery){
        QueryCondition queryBuilders =  QueryBuilders.createQuery(RepositoryEntity.class, "re")
                .leftJoin(RepositoryFocusEntity.class,"rf","rf.repositoryId=re.id")
                .like("rf.name", repositoryQuery.getName())
                .eq("rf.masterId", repositoryQuery.getMasterId())
                .orders(repositoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryBuilders, RepositoryEntity.class);
    }
}