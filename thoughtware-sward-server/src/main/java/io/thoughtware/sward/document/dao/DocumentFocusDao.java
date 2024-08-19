package io.thoughtware.sward.document.dao;

import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.sward.document.entity.DocumentFocusEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.sward.document.model.DocumentFocusQuery;
import io.thoughtware.sward.node.entity.NodeEntity;
import io.thoughtware.sward.repository.entity.WikiRepositoryFocusEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DocumentFocusDao
 */
@Repository
public class DocumentFocusDao {

    private static Logger logger = LoggerFactory.getLogger(DocumentFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param wikiDocumentFocusEntity
     * @return
     */
    public String createDocumentFocus(DocumentFocusEntity wikiDocumentFocusEntity) {
        return jpaTemplate.save(wikiDocumentFocusEntity,String.class);
    }

    /**
     * 更新
     * @param wikiDocumentFocusEntity
     */
    public void updateDocumentFocus(DocumentFocusEntity wikiDocumentFocusEntity){
        jpaTemplate.update(wikiDocumentFocusEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDocumentFocus(String id){
        jpaTemplate.delete(DocumentFocusEntity.class,id);
    }

    public void deleteDocumentFocusByCondition(DocumentFocusQuery documentFocusQuery){
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(DocumentFocusEntity.class)
                .eq("documentId", documentFocusQuery.getDocumentId())
                .eq("masterId", documentFocusQuery.getMasterId())
                .in("documentId", documentFocusQuery.getDocumentIds())
                .get();
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DocumentFocusEntity findDocumentFocus(String id){
        return jpaTemplate.findOne(DocumentFocusEntity.class,id);
    }

    /**
     * findAllDocumentFocus
     * @return
     */
    public List<DocumentFocusEntity> findAllDocumentFocus() {
        return jpaTemplate.findAll(DocumentFocusEntity.class);
    }

    public List<DocumentFocusEntity> findDocumentFocusList(List<String> idList) {
        return jpaTemplate.findList(DocumentFocusEntity.class,idList);
    }

    public List<DocumentFocusEntity> findDocumentFocusList(DocumentFocusQuery documentFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentFocusEntity.class)
                .eq("documentId", documentFocusQuery.getDocumentId())
                .eq("masterId", documentFocusQuery.getMasterId())
                .orders(documentFocusQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, DocumentFocusEntity.class);

    }

    public Pagination<DocumentFocusEntity> findDocumentFocusPage(DocumentFocusQuery documentFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentFocusEntity.class, "df")
                .leftJoin(NodeEntity.class,"no","no.id=df.documentId")
                .eq("df.documentId", documentFocusQuery.getDocumentId())
                .eq("df.masterId", documentFocusQuery.getMasterId())
                .eq("df.repositoryId", documentFocusQuery.getRepositoryId())
                .like("no.name", documentFocusQuery.getName())
                .orders(documentFocusQuery.getOrderParams())
                .pagination(documentFocusQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, DocumentFocusEntity.class);
    }
}