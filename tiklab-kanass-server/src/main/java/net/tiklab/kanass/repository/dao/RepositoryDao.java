package net.tiklab.kanass.repository.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.OrQueryCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.OrQueryBuilders;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.kanass.repository.entity.RepositoryEntity;
import net.tiklab.kanass.repository.model.RepositoryQuery;
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
        QueryCondition queryCondition = QueryBuilders.createQuery(RepositoryEntity.class)
                .like("name", repositoryQuery.getName())
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
//        String sql = "select DISTINCT w.id,w.name,w.type_id,w.master,w.description,w.limits,w.create_time from kanass_repository w left join orc_dm_user d on w.id = d.domain_id ";
//        sql = sql.concat("where w.limits = '1' and d.user_id = ? or w.limits = '0'");
//        Pagination<RepositoryEntity> repositoryEntityList =
//                this.jpaTemplate.getJdbcTemplate().findPage(sql,new Object[]{userId},repositoryQuery.getPageParam(),new BeanPropertyRowMapper(RepositoryEntity.class));
//                (sql, new String[]{userId}, new BeanPropertyRowMapper(RepositoryEntity.class));;
        return jpaTemplate.findPage(queryCondition, RepositoryEntity.class);
    }
}