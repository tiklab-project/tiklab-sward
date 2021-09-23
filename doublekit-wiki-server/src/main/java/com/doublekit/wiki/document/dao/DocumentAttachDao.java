package com.doublekit.wiki.document.dao;

import com.doublekit.common.Pagination;
import com.doublekit.wiki.document.entity.DocumentAttachPo;
import com.doublekit.wiki.document.model.DocumentAttachQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
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
     * @param documentAttachPo
     * @return
     */
    public String createDocumentAttach(DocumentAttachPo documentAttachPo) {
        return jpaTemplate.save(documentAttachPo,String.class);
    }

    /**
     * 更新
     * @param documentAttachPo
     */
    public void updateDocumentAttach(DocumentAttachPo documentAttachPo){
        jpaTemplate.update(documentAttachPo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDocumentAttach(String id){
        jpaTemplate.delete(DocumentAttachPo.class,id);
    }

    public void deleteDocumentAttach(DeleteCondition deleteCondition){
        jpaTemplate.delete(DocumentAttachPo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DocumentAttachPo findDocumentAttach(String id){
        return jpaTemplate.findOne(DocumentAttachPo.class,id);
    }

    /**
    * findAllDocumentAttach
    * @return
    */
    public List<DocumentAttachPo> findAllDocumentAttach() {
        return jpaTemplate.findAll(DocumentAttachPo.class);
    }

    public List<DocumentAttachPo> findDocumentAttachList(List<String> idList) {
        return jpaTemplate.findList(DocumentAttachPo.class,idList);
    }

    public List<DocumentAttachPo> findDocumentAttachList(DocumentAttachQuery documentAttachQuery) {
        return jpaTemplate.findList(DocumentAttachPo.class,documentAttachQuery);
    }

    public Pagination<DocumentAttachPo> findDocumentAttachPage(DocumentAttachQuery documentAttachQuery) {
        return jpaTemplate.findPage(DocumentAttachPo.class,documentAttachQuery);
    }
}