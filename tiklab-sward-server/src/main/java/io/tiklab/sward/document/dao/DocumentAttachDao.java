package io.tiklab.sward.document.dao;

import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.sward.document.entity.DocumentAttachEntity;
import io.tiklab.sward.document.model.DocumentAttachQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DocumentAttachDao
 */
@Repository
public class DocumentAttachDao{

    private static Logger logger = LoggerFactory.getLogger(DocumentAttachDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param documentAttachEntity
     * @return
     */
    public String createDocumentAttach(DocumentAttachEntity documentAttachEntity) {
        return jpaTemplate.save(documentAttachEntity,String.class);
    }

    /**
     * 更新
     * @param documentAttachEntity
     */
    public void updateDocumentAttach(DocumentAttachEntity documentAttachEntity){
        jpaTemplate.update(documentAttachEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDocumentAttach(String id){
        jpaTemplate.delete(DocumentAttachEntity.class,id);
    }

    public void deleteDocumentAttachCondition(DocumentAttachQuery documentAttachQuery){
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(DocumentAttachEntity.class)
                .eq("documentId", documentAttachQuery.getDocumentId())
                .in("documentId", documentAttachQuery.getDocumentIds())
                .get();
        jpaTemplate.delete(deleteCondition);
    }

    public void deleteDocumentAttach(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DocumentAttachEntity findDocumentAttach(String id){
        return jpaTemplate.findOne(DocumentAttachEntity.class,id);
    }

    /**
    * findAllDocumentAttach
    * @return
    */
    public List<DocumentAttachEntity> findAllDocumentAttach() {
        return jpaTemplate.findAll(DocumentAttachEntity.class);
    }

    public List<DocumentAttachEntity> findDocumentAttachList(List<String> idList) {
        return jpaTemplate.findList(DocumentAttachEntity.class,idList);
    }

    public List<DocumentAttachEntity> findDocumentAttachList(DocumentAttachQuery documentAttachQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentAttachEntity.class)
                .orders(documentAttachQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, DocumentAttachEntity.class);
    }

    public Pagination<DocumentAttachEntity> findDocumentAttachPage(DocumentAttachQuery documentAttachQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentAttachEntity.class)
                .orders(documentAttachQuery.getOrderParams())
                .pagination(documentAttachQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, DocumentAttachEntity.class);
    }
}