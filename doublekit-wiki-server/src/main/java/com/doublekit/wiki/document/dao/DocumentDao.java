package com.doublekit.wiki.document.dao;

import com.doublekit.common.page.Pagination;
import com.doublekit.dal.jpa.criterial.condition.DeleteCondition;
import com.doublekit.dal.jpa.criterial.condition.QueryCondition;
import com.doublekit.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import com.doublekit.wiki.document.entity.DocumentEntity;
import com.doublekit.wiki.document.model.DocumentQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DocumentDao
 */
@Repository
public class DocumentDao{

    private static Logger logger = LoggerFactory.getLogger(DocumentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param documentEntity
     * @return
     */
    public String createDocument(DocumentEntity documentEntity) {
        return jpaTemplate.save(documentEntity,String.class);
    }

    /**
     * 更新
     * @param documentEntity
     */
    public void updateDocument(DocumentEntity documentEntity){
        jpaTemplate.update(documentEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDocument(String id){
        jpaTemplate.delete(DocumentEntity.class,id);
    }

    public void deleteDocument(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DocumentEntity findDocument(String id){
        return jpaTemplate.findOne(DocumentEntity.class,id);
    }

    /**
    * findAllDocument
    * @return
    */
    public List<DocumentEntity> findAllDocument() {
        return jpaTemplate.findAll(DocumentEntity.class);
    }

    public List<DocumentEntity> findDocumentList(List<String> idList) {
        return jpaTemplate.findList(DocumentEntity.class,idList);
    }

    public List<DocumentEntity> findDocumentList(DocumentQuery documentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentEntity.class)
                .eq("id", documentQuery.getId())
                .like("name", documentQuery.getName())
                .eq("repositoryId", documentQuery.getRepositoryId())
                .eq("categoryId", documentQuery.getCategoryId())
                .orders(documentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, DocumentEntity.class);
    }

    public Pagination<DocumentEntity> findDocumentPage(DocumentQuery documentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentEntity.class)
                .eq("id", documentQuery.getId())
                .like("name", documentQuery.getName())
                .eq("repositoryId", documentQuery.getRepositoryId())
                .eq("categoryId", documentQuery.getCategoryId())
                .orders(documentQuery.getOrderParams())
                .pagination(documentQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(documentQuery, DocumentEntity.class);
    }
}