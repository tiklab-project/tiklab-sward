package com.doublekit.wiki.repository.dao;

import com.doublekit.common.Pagination;
import com.doublekit.wiki.repository.entity.DocumentPo;
import com.doublekit.wiki.repository.model.DocumentQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
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
     * @param documentPo
     * @return
     */
    public String createDocument(DocumentPo documentPo) {
        return jpaTemplate.save(documentPo,String.class);
    }

    /**
     * 更新
     * @param documentPo
     */
    public void updateDocument(DocumentPo documentPo){
        jpaTemplate.update(documentPo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDocument(String id){
        jpaTemplate.delete(DocumentPo.class,id);
    }

    public void deleteDocument(DeleteCondition deleteCondition){
        jpaTemplate.delete(DocumentPo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DocumentPo findDocument(String id){
        return jpaTemplate.findOne(DocumentPo.class,id);
    }

    /**
    * findAllDocument
    * @return
    */
    public List<DocumentPo> findAllDocument() {
        return jpaTemplate.findAll(DocumentPo.class);
    }

    public List<DocumentPo> findDocumentList(List<String> idList) {
        return jpaTemplate.findList(DocumentPo.class,idList);
    }

    public List<DocumentPo> findDocumentList(DocumentQuery documentQuery) {
        return jpaTemplate.findList(DocumentPo.class,documentQuery);
    }

    public Pagination<DocumentPo> findDocumentPage(DocumentQuery documentQuery) {
        return jpaTemplate.findPage(DocumentPo.class,documentQuery);
    }
}