package io.tiklab.kanass.document.dao;

import io.tiklab.kanass.document.entity.DocumentRecentEntity;
import io.tiklab.kanass.document.entity.WikiDocumentEntity;
import io.tiklab.kanass.document.model.DocumentQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.kanass.document.model.DocumentRecentQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
     * @param wikiDocumentEntity
     * @return
     */
    public String createDocument(WikiDocumentEntity wikiDocumentEntity) {
        wikiDocumentEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return jpaTemplate.save(wikiDocumentEntity,String.class);
    }

    /**
     * 更新
     * @param wikiDocumentEntity
     */
    public void updateDocument(WikiDocumentEntity wikiDocumentEntity){
        wikiDocumentEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        jpaTemplate.update(wikiDocumentEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteDocument(String id){
        jpaTemplate.delete(WikiDocumentEntity.class,id);
    }

    public void deleteDocument(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WikiDocumentEntity findDocument(String id){
        return jpaTemplate.findOne(WikiDocumentEntity.class,id);
    }

    /**
    * findAllDocument
    * @return
    */
    public List<WikiDocumentEntity> findAllDocument() {
        return jpaTemplate.findAll(WikiDocumentEntity.class);
    }

    public List<WikiDocumentEntity> findDocumentList(List<String> idList) {
        return jpaTemplate.findList(WikiDocumentEntity.class,idList);
    }

    public List<WikiDocumentEntity> findDocumentList(DocumentQuery documentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiDocumentEntity.class)
                .eq("id", documentQuery.getId())
                .like("name", documentQuery.getName())
                .eq("repositoryId", documentQuery.getRepositoryId())
                .in("repositoryId", documentQuery.getRepositoryIds())
                .eq("categoryId", documentQuery.getCategoryId())
                .orders(documentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WikiDocumentEntity.class);
    }


    public Pagination<WikiDocumentEntity> findDocumentPage(DocumentQuery documentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiDocumentEntity.class)
                .eq("id", documentQuery.getId())
                .like("name", documentQuery.getName())
                .eq("repositoryId", documentQuery.getRepositoryId())
                .eq("categoryId", documentQuery.getCategoryId())
                .in("repositoryId", documentQuery.getRepositoryIds())
                .notIn("id", documentQuery.getIds())
                .orders(documentQuery.getOrderParams())
                .pagination(documentQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WikiDocumentEntity.class);
    }
    public List<WikiDocumentEntity> findDocuementByKeyWork(String keyWork) {
        String sql = "WITH RECURSIVE recursive_extract AS (\n" +
                "    SELECT \n" +
                "          kanass_document.id,\n" +
                "      kanass_document.name,\n" +
                "      kanass_document.details,\n" +
                "      kanass_document.type_id,\n" +
                "      kanass_document.repository_id,\n" +
                "      kanass_document.category_id,\n" +
                "      kanass_document.master,\n" +
                "      kanass_document.update_time,\n" +
                "          jsonb_array_elements(details) AS element\n" +
                "    FROM kanass_document\n" +
                "    UNION ALL\n" +
                "    SELECT \n" +
                "          recursive_extract.id,\n" +
                "      recursive_extract.name,\n" +
                "      recursive_extract.details,\n" +
                "      recursive_extract.type_id,\n" +
                "      recursive_extract.repository_id,\n" +
                "      recursive_extract.category_id,\n" +
                "      recursive_extract.master,\n" +
                "      recursive_extract.update_time,\n" +
                "          jsonb_array_elements(element->'children') AS element\n" +
                "    FROM recursive_extract\n" +
                "    WHERE jsonb_typeof(element) = 'object' AND element->'children' IS NOT NULL\n" +
                "  )\n" +
                "  \n" +
                "  SELECT *\n" +
                "  FROM \n" +
                "      recursive_extract,\n" +
                "      to_tsvector(recursive_extract.name || recursive_extract.element) document,\n" +
                "    to_tsquery( ? ) query\n" +
                "  WHERE element->>'text' IS NOT NULL AND query @@ document;";
        List<WikiDocumentEntity> wikiDocumentEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{keyWork}, new BeanPropertyRowMapper(WikiDocumentEntity.class));
        return wikiDocumentEntityList;
    }

    public List<WikiDocumentEntity> findRecentDocumentList(DocumentRecentQuery documentRecentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiDocumentEntity.class,"wd")
                .leftJoin(DocumentRecentEntity.class, "dr","dr.modelId=wd.id")
                .eq("dr.modelId", documentRecentQuery.getModelId())
                .eq("dr.masterId", documentRecentQuery.getMasterId())
                .eq("dr.repositoryId", documentRecentQuery.getRepositoryId())
                .eq("dr.model", documentRecentQuery.getModel())
                .orders(documentRecentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WikiDocumentEntity.class);
    }

}