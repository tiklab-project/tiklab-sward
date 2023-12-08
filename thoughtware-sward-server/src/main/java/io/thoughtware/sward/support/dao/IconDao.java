package io.thoughtware.sward.support.dao;

import io.thoughtware.sward.support.entity.IconEntity;
import io.thoughtware.sward.support.model.IconQuery;
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
public class IconDao {

    private static Logger logger = LoggerFactory.getLogger(IconDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建图标
     * @param iconEntity
     * @return
     */
    public String createIcon(IconEntity iconEntity) {
        return jpaTemplate.save(iconEntity,String.class);
    }

    /**
     * 更新图标
     * @param iconEntity
     */
    public void updateIcon(IconEntity iconEntity){
        jpaTemplate.update(iconEntity);
    }

    /**
     * 根据id删除图标
     * @param id
     */
    public void deleteIcon(String id){
        jpaTemplate.delete(IconEntity.class,id);
    }

    public void deleteIcon(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id 查找图标
     * @param id
     * @return
     */
    public IconEntity findIcon(String id){
        return jpaTemplate.findOne(IconEntity.class,id);
    }

    /**
    * 查找所有图标
    * @return
    */
    public List<IconEntity> findAllIcon() {
        return jpaTemplate.findAll(IconEntity.class);
    }


    public List<IconEntity> findIconList(List<String> idList) {
        return jpaTemplate.findList(IconEntity.class,idList);
    }

    /**
     * 查询图标列表
     * @param iconQuery
     * @return
     */
    public List<IconEntity> findIconList(IconQuery iconQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IconEntity.class)
                .eq("iconType", iconQuery.getIconType())
                .orders(iconQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,IconEntity.class);
    }

    /**
     * 按分页查询图标
     * @param iconQuery
     * @return
     */
    public Pagination<IconEntity> findIconPage(IconQuery iconQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(IconEntity.class)
                .eq("iconType", iconQuery.getIconType())
                .orders(iconQuery.getOrderParams())
                .pagination(iconQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,IconEntity.class);
    }
}