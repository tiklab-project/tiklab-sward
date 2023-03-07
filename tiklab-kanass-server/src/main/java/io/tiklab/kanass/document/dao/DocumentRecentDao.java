package io.tiklab.kanass.document.dao;

import io.tiklab.kanass.document.model.DocumentRecentQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.document.entity.DocumentRecentEntity;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * DocumentRecentDao
 */
@Repository
public class DocumentRecentDao{

    private static Logger logger = LoggerFactory.getLogger(DocumentRecentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param documentRecentEntity
     * @return
     */
    public String createDocumentRecent(DocumentRecentEntity documentRecentEntity) {
        DocumentRecentQuery documentRecentQuery = new DocumentRecentQuery();
        documentRecentQuery.setModelId(documentRecentEntity.getModelId());
        List<DocumentRecentEntity> documentRecentList = findDocumentRecentList(documentRecentQuery);
        if(documentRecentList.size() > 0){
            deleteDocumentRecent(documentRecentList.get(0).getId());
        }
        documentRecentEntity.setRecentTime(new Date());
        return jpaTemplate.save(documentRecentEntity,String.class);
    }

    /**
     * 更新
     * @param documentRecentEntity
     */
    public void updateDocumentRecent(DocumentRecentEntity documentRecentEntity){
        jpaTemplate.update(documentRecentEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDocumentRecent(String id){
        jpaTemplate.delete(DocumentRecentEntity.class,id);
    }

    public void deleteDocumentRecent(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DocumentRecentEntity findDocumentRecent(String id){
        return jpaTemplate.findOne(DocumentRecentEntity.class,id);
    }

    /**
    * findAllDocumentRecent
    * @return
    */
    public List<DocumentRecentEntity> findAllDocumentRecent() {
        return jpaTemplate.findAll(DocumentRecentEntity.class);
    }

    public List<DocumentRecentEntity> findDocumentRecentList(List<String> idList) {
        return jpaTemplate.findList(DocumentRecentEntity.class,idList);
    }

    public List<DocumentRecentEntity> findDocumentRecentList(DocumentRecentQuery documentRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentRecentEntity.class)
                .eq("modelId", documentRecentQuery.getModelId())
                .eq("masterId", documentRecentQuery.getMasterId())
                .eq("repositoryId", documentRecentQuery.getRepositoryId())
                .eq("model", documentRecentQuery.getModel())
                .in("model", documentRecentQuery.getModels())
                .orders(documentRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, DocumentRecentEntity.class);
//        return jpaTemplate.findList(documentRecentQuery,DocumentRecentEntity.class);
    }

    public Pagination<DocumentRecentEntity> findDocumentRecentPage(DocumentRecentQuery documentRecentQuery) {
        return jpaTemplate.findPage(documentRecentQuery,DocumentRecentEntity.class);
    }
}