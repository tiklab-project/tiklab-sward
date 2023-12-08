package io.thoughtware.sward.repository.dao;

import io.thoughtware.sward.repository.entity.WikiRepositoryEntity;
import io.thoughtware.sward.repository.entity.WikiRepositoryFocusEntity;
import io.thoughtware.sward.repository.model.WikiRepositoryQuery;
import io.thoughtware.sward.support.entity.RecentEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.OrQueryCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.OrQueryBuilders;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * RepositoryDao
 */
@Repository
public class WikiRepositoryDao {

    private static Logger logger = LoggerFactory.getLogger(WikiRepositoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param wikiRepositoryEntity
     * @return
     */
    public String createRepository(WikiRepositoryEntity wikiRepositoryEntity) {
        return jpaTemplate.save(wikiRepositoryEntity,String.class);
    }

    /**
     * 更新
     * @param wikiRepositoryEntity
     */
    public void updateRepository(WikiRepositoryEntity wikiRepositoryEntity){
        jpaTemplate.update(wikiRepositoryEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRepository(String id){
        jpaTemplate.delete(WikiRepositoryEntity.class,id);
    }

    public void deleteRepositoryAndRelation(String repositoryId){
        // 关注的知识库
        String sql = "DELETE FROM kanass_repository_focus where repository_id = '" + repositoryId + "'";
        Integer update =  jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除被关注的知识库成功");
        }else {
            logger.info("删除被关注的知识库是吧");
        }
        // 删除最近查看的知识库的文档，目录，知识库
        sql = "DELETE FROM kanass_recent where repository_id = '" + repositoryId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除最近查看的知识库成功");
        }else {
            logger.info("删除最近查看的知识库失败");
        }
        deleteDocument(repositoryId);
        deleteCategory(repositoryId);
    }

    public void deleteDocument(String repositoryId){
        String sql = "SELECT id FROM kanass_document where repository_id = '" + repositoryId + "'";
        List<String> documentIdList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);

        if(documentIdList.size() > 0){
            String documentIds = documentIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(", "));
            // 删除文档附件
            sql = "DELETE FROM kanass_document_attach where document_id in (" + documentIds + ")";
            int update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除文档关联的附件成功");
            }else {
                logger.info("删除文档关联的附件失败");
            }

            // 删除评论
            sql = "DELETE FROM kanass_comment where document_id in (" + documentIds + ")";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除文档评论成功");
            }else {
                logger.info("删除文档评论失败");
            }

            // 删除文档点赞
            sql = "DELETE FROM kanass_like where to_whom_id in (" + documentIds + ")";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除事项的点赞成功");
            }else {
                logger.info("删除事项的点赞失败");
            }

            // 删除分享的文档数据
            sql = "SELECT share_id FROM kanass_share_relation where document_id in (" + documentIds + ")";
            List<String> shareIdList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
            String shareIds = shareIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(", "));
            if(shareIdList.size() > 0){
                sql = "DELETE FROM kanass_share where id in (" + shareIds + ")";
                update = jpaTemplate.getJdbcTemplate().update(sql);

                sql = "DELETE FROM kanass_share_relation where document_id in (" + documentIds + ")";
                update = jpaTemplate.getJdbcTemplate().update(sql);
                if(update >= 0){
                    logger.info("删除文档分享数据成功");
                }else {
                    logger.info("删除文档分享数据失败");
                }
            }


            // 删除文档
            sql = "DELETE FROM kanass_document where repository_id  = '" + repositoryId + "'";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除文档成功");
            }else {
                logger.info("删除文档失败");
            }

        }else {
            return;
        }
    }

    public void deleteCategory(String repositoryId){
        String sql = "SELECT id FROM kanass_category where repository_id = '" + repositoryId + "'";
        List<String> categoryIdList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);

        if(categoryIdList.size() > 0){
            String categoryIds = categoryIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(", "));


            // 删除分享的文档数据
            sql = "SELECT share_id FROM kanass_share_relation where category_id in (" + categoryIds + ")";
            List<String> shareIdList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
            String shareIds = shareIdList.stream().map(id -> "'" + id + "'").collect(Collectors.joining(", "));

            sql = "DELETE FROM kanass_share where id in (" + shareIds + ")";
            int update = jpaTemplate.getJdbcTemplate().update(sql);

            sql = "DELETE FROM kanass_share_relation where category_id in (" + categoryIds + ")";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除目录分享数据成功");
            }else {
                logger.info("删除目录分享数据失败");
            }

            sql = "DELETE FROM kanass_category where repository_id  = '" + repositoryId + "'";
            update = jpaTemplate.getJdbcTemplate().update(sql);
            if(update >= 0){
                logger.info("删除目录成功");
            }else {
                logger.info("删除目录失败");
            }
        }else {
            return;
        }
    }
    public void deleteRepository(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public WikiRepositoryEntity findRepository(String id){
        return jpaTemplate.findOne(WikiRepositoryEntity.class,id);
    }

    /**
    * findAllRepository
    * @return
    */
    public List<WikiRepositoryEntity> findAllRepository() {
        return jpaTemplate.findAll(WikiRepositoryEntity.class);
    }

    public List<WikiRepositoryEntity> findRepositoryList(List<String> idList) {

        return jpaTemplate.findList(WikiRepositoryEntity.class,idList);
    }
    public List<WikiRepositoryEntity> findRepositoryList(String time) {
        String sql = "SELECT * from kanass_repository WHERE create_time < '" + time +"'";
        List<WikiRepositoryEntity> wikiRepositoryEntityList = jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(WikiRepositoryEntity.class));
        return wikiRepositoryEntityList;
    }
    public List<WikiRepositoryEntity> findRepositoryListByUser(WikiRepositoryQuery wikiRepositoryQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(WikiRepositoryEntity.class, "rs");
        // 查找是公共知识库或者当前用户有权限的知识库
        OrQueryCondition orQueryBuildCondition = OrQueryBuilders.instance()
                .eq("limits","0")
                .in("id", wikiRepositoryQuery.getRepositoryIds())
                .get();
        QueryCondition queryCondition = queryBuilders.or(orQueryBuildCondition)
                .eq("master", wikiRepositoryQuery.getMasterId())
                .like("name", wikiRepositoryQuery.getName())
                .orders(wikiRepositoryQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, WikiRepositoryEntity.class);
    }

    public Pagination<WikiRepositoryEntity> findRepositoryPage(WikiRepositoryQuery wikiRepositoryQuery) {

        QueryBuilders queryBuilders = QueryBuilders.createQuery(WikiRepositoryEntity.class, "rs");
        OrQueryCondition orQueryBuildCondition = OrQueryBuilders.instance()
                .eq("limits",0)
                .in("id", wikiRepositoryQuery.getRepositoryIds())
                .get();
        QueryCondition queryCondition = queryBuilders.or(orQueryBuildCondition)
                .orders(wikiRepositoryQuery.getOrderParams())
                .pagination(wikiRepositoryQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WikiRepositoryEntity.class);
    }

    public List<WikiRepositoryEntity> findRecentRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WikiRepositoryEntity.class, "re")
                .leftJoin(RecentEntity.class,"dr","dr.modelId=re.id")
                .eq("dr.masterId", wikiRepositoryQuery.getMasterId())
                .eq("dr.model","repository")
                .like("re.name", wikiRepositoryQuery.getName())
                .orders(wikiRepositoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WikiRepositoryEntity.class);
    }

    public List<WikiRepositoryEntity> findFocusRepositoryList(WikiRepositoryQuery wikiRepositoryQuery){
        QueryCondition queryBuilders =  QueryBuilders.createQuery(WikiRepositoryEntity.class, "re")
                .leftJoin(WikiRepositoryFocusEntity.class,"rf","rf.repositoryId=re.id")
                .like("re.name", wikiRepositoryQuery.getName())
                .eq("rf.masterId", wikiRepositoryQuery.getMasterId())
                .orders(wikiRepositoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryBuilders, WikiRepositoryEntity.class);
    }
}