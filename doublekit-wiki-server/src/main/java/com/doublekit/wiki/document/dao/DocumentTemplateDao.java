package com.doublekit.wiki.document.dao;

import com.doublekit.common.Pagination;
import com.doublekit.wiki.document.entity.DocumentTemplatePo;
import com.doublekit.wiki.document.model.DocumentTemplateQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.*;
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
     * @param documentTemplatePo
     * @return
     */
    public String createDocumentTemplate(DocumentTemplatePo documentTemplatePo) {
        return jpaTemplate.save(documentTemplatePo,String.class);
    }

    /**
     * 更新
     * @param documentTemplatePo
     */
    public void updateDocumentTemplate(DocumentTemplatePo documentTemplatePo){
        jpaTemplate.update(documentTemplatePo);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDocumentTemplate(String id){
        jpaTemplate.delete(DocumentTemplatePo.class,id);
    }

    public void deleteDocumentTemplate(DeleteCondition deleteCondition){
        jpaTemplate.delete(DocumentTemplatePo.class,deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public DocumentTemplatePo findDocumentTemplate(String id){
        return jpaTemplate.findOne(DocumentTemplatePo.class,id);
    }

    /**
    * findAllDocumentTemplate
    * @return
    */
    public List<DocumentTemplatePo> findAllDocumentTemplate() {
        return jpaTemplate.findAll(DocumentTemplatePo.class);
    }

    public List<DocumentTemplatePo> findDocumentTemplateList(List<String> idList) {
        return jpaTemplate.findList(DocumentTemplatePo.class,idList);
    }

    public List<DocumentTemplatePo> findDocumentTemplateList(DocumentTemplateQuery documentTemplateQuery) {
        return jpaTemplate.findList(DocumentTemplatePo.class,documentTemplateQuery);
    }

    public Pagination<DocumentTemplatePo> findDocumentTemplatePage(DocumentTemplateQuery documentTemplateQuery) {
        return jpaTemplate.findPage(DocumentTemplatePo.class,documentTemplateQuery);
    }
}