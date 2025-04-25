package io.tiklab.sward.document.document.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.sward.document.document.entity.DocumentVersionEntity;
import io.tiklab.sward.document.document.model.DocumentVersionQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
* DocumentService
*/
@Repository
public class DocumentVersionDao {

    @Autowired
    JpaTemplate jpaTemplate;

    /**
    * 创建
    * @param versionEntity
    * @return
    */
    public String createDocumentVersion(DocumentVersionEntity versionEntity){
        return jpaTemplate.save(versionEntity, String.class);
    }

    /**
    * 更新
    * @param versionEntity
    */
    public void updateDocumentVersion(DocumentVersionEntity versionEntity){
        jpaTemplate.update(versionEntity);
    }

    /**
    * 删除
    * @param id
    */
    public void deleteDocumentVersion(String id){
        jpaTemplate.delete(DocumentVersionEntity.class, id);
    }


    @FindOne
    public DocumentVersionEntity findDocumentVersion(String id){
       return jpaTemplate.findOne(DocumentVersionEntity.class, id);
    }



    @FindList
    public List<DocumentVersionEntity> findDocumentVersionList(List<String> idList){
        List<DocumentVersionEntity> versionEntityList = jpaTemplate.findList(DocumentVersionEntity.class, idList);
        if (Objects.isNull(versionEntityList) || versionEntityList.isEmpty()) {
            return Collections.emptyList();
        }
        return versionEntityList;
    }


    /**
    * 查找所有
    * @return
    */
    @FindAll
    public List<DocumentVersionEntity> findAllDocumentVersion(){
        List<DocumentVersionEntity> versionEntityList = jpaTemplate.findAll(DocumentVersionEntity.class);
        if (Objects.isNull(versionEntityList) || versionEntityList.isEmpty()) {
            return Collections.emptyList();
        }
        return versionEntityList;
    }

    /**
    * 查询列表
    * @param versionQuery
    * @return
    */
    public List<DocumentVersionEntity> findDocumentVersionList(DocumentVersionQuery versionQuery){
        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentVersionEntity.class)
                .like("versionName", versionQuery.getVersionName())
                .eq("documentId", versionQuery.getDocumentId())
                .orders(versionQuery.getOrderParams())
                .get();
        List<DocumentVersionEntity> versionEntityList = jpaTemplate.findList(queryCondition, DocumentVersionEntity.class);

        if (Objects.isNull(versionEntityList) || versionEntityList.isEmpty()) {
            return Collections.emptyList();
        }
        return versionEntityList;
    }

    /**
    * 按分页查询
    * @param versionQuery
    * @return
    */
    public Pagination<DocumentVersionEntity> findDocumentVersionPage(DocumentVersionQuery versionQuery){

        QueryCondition queryCondition = QueryBuilders.createQuery(DocumentVersionEntity.class)
                .like("versionName", versionQuery.getVersionName())
                .eq("documentId", versionQuery.getDocumentId())
                .orders(versionQuery.getOrderParams())
                .pagination(versionQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, DocumentVersionEntity.class);

    }

}












