package io.thoughtware.sward.document.dao;

import io.thoughtware.sward.document.entity.DocumentTemplateEntity;
import io.thoughtware.sward.document.model.DocumentTemplateQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DocumentTemplateDao
 */
@Repository
public class DocumentTemplateDao{

    private static Logger logger = LoggerFactory.getLogger(DocumentTemplateDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param documentTemplateEntity
     * @return
     */
    public String createDocumentTemplate(DocumentTemplateEntity documentTemplateEntity) {
        return jpaTemplate.save(documentTemplateEntity,String.class);
    }

    /**
     * 更新
     * @param documentTemplateEntity
     */
    public void updateDocumentTemplate(DocumentTemplateEntity documentTemplateEntity){
        jpaTemplate.update(documentTemplateEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDocumentTemplate(String id){
        jpaTemplate.delete(DocumentTemplateEntity.class,id);
    }

    public void deleteDocumentTemplate(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DocumentTemplateEntity findDocumentTemplate(String id){
        return jpaTemplate.findOne(DocumentTemplateEntity.class,id);
    }

    /**
    * findAllDocumentTemplate
    * @return
    */
    public List<DocumentTemplateEntity> findAllDocumentTemplate() {
        return jpaTemplate.findAll(DocumentTemplateEntity.class);
    }

    public List<DocumentTemplateEntity> findDocumentTemplateList(List<String> idList) {
        return jpaTemplate.findList(DocumentTemplateEntity.class,idList);
    }

    public List<DocumentTemplateEntity> findDocumentTemplateList(DocumentTemplateQuery documentTemplateQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentTemplateEntity.class)
                .like("name", documentTemplateQuery.getName())
                .orders(documentTemplateQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, DocumentTemplateEntity.class);
    }

    public Pagination<DocumentTemplateEntity> findDocumentTemplatePage(DocumentTemplateQuery documentTemplateQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentTemplateEntity.class)
                .like("name", documentTemplateQuery.getName())
                .orders(documentTemplateQuery.getOrderParams())
                .pagination(documentTemplateQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition, DocumentTemplateEntity.class);
    }
}