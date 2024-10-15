package io.thoughtware.sward.support.dao;

import io.thoughtware.sward.support.entity.SystemUrlEntity;
import io.thoughtware.sward.support.model.SystemUrlQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 图标数据访问
 */
@Repository
public class SystemUrlDao {

    private static Logger logger = LoggerFactory.getLogger(SystemUrlDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建图标
     * @param systemUrlEntity
     * @return
     */
    public String createSystemUrl(SystemUrlEntity systemUrlEntity) {
        return jpaTemplate.save(systemUrlEntity,String.class);
    }

    /**
     * 更新图标
     * @param systemUrlEntity
     */
    public void updateSystemUrl(SystemUrlEntity systemUrlEntity){
        jpaTemplate.update(systemUrlEntity);
    }

    /**
     * 根据id删除图标
     * @param id
     */
    public void deleteSystemUrl(String id){
        jpaTemplate.delete(SystemUrlEntity.class,id);
    }

    public void deleteSystemUrl(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id 查找图标
     * @param id
     * @return
     */
    public SystemUrlEntity findSystemUrl(String id){
        return jpaTemplate.findOne(SystemUrlEntity.class,id);
    }

    /**
    * 查找所有图标
    * @return
    */
    public List<SystemUrlEntity> findAllSystemUrl() {
        return jpaTemplate.findAll(SystemUrlEntity.class);
    }


    public List<SystemUrlEntity> findSystemUrlList(List<String> idList) {
        return jpaTemplate.findList(SystemUrlEntity.class,idList);
    }

    /**
     * 查询图标列表
     * @param systemUrlQuery
     * @return
     */
    public List<SystemUrlEntity> findSystemUrlList(SystemUrlQuery systemUrlQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SystemUrlEntity.class)
                .get();
        return jpaTemplate.findList(queryCondition,SystemUrlEntity.class);
    }

    /**
     * 按分页查询图标
     * @param systemUrlQuery
     * @return
     */
    public Pagination<SystemUrlEntity> findSystemUrlPage(SystemUrlQuery systemUrlQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SystemUrlEntity.class).get();
        return jpaTemplate.findPage(queryCondition,SystemUrlEntity.class);
    }

    public Integer findSystemUrlNumber() {
        String sql = "select count(1) as total from wiki_system_url";
        Integer total = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,Integer.class);
        return total;
    }
}