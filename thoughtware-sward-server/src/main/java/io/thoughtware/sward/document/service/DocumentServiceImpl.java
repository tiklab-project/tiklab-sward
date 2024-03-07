package io.thoughtware.sward.document.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.security.logging.service.LoggingByTempService;
import io.thoughtware.security.logging.service.LoggingService;
import io.thoughtware.sward.category.entity.WikiCategoryEntity;
import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.document.model.*;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dss.client.DssClient;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.sward.category.service.WikiCategoryService;
import io.thoughtware.sward.document.entity.WikiDocumentEntity;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.sward.document.dao.DocumentDao;
import io.thoughtware.sward.support.model.Recent;
import io.thoughtware.sward.support.model.RecentQuery;
import io.thoughtware.sward.support.service.RecentService;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.security.logging.model.Logging;
import io.thoughtware.security.logging.model.LoggingType;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
* DocumentServiceImpl
*/
@Exporter
@Service
public class DocumentServiceImpl implements DocumentService {
    private static Logger logger = LoggerFactory.getLogger(DocumentService.class);
    // 新建锁
    private final Lock lock = new ReentrantLock();
    @Autowired
    JpaTemplate jpaTemplate;
    @Autowired
    DocumentDao documentDao;

    @Autowired
    DocumentFocusService documentFocusService;
    @Autowired
    DssClient dssClient;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    UserService userService;

    @Autowired
    RecentService recentService;

    @Autowired
    LoggingByTempService loggingByTemplService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @Autowired
    WikiCategoryService wikiCategoryService;

    @Autowired
    LoggingService loggingService;

    @Value("${base.url:null}")
    String baseUrl;

    void creatDynamic( Map<String, String> content){
        Logging log = new Logging();
        log.setBgroup("sward");

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);
        content.put("createUser", user.getName());
        content.put("updateTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        LoggingType opLogType = new LoggingType();
        opLogType.setId("SWARD_LOGTYPE_DOCUMENTADD");
        log.setActionType(opLogType);

        log.setModule("document");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        content.put("createUserIcon",user.getName().substring( 0, 1));
        log.setData(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        log.setAction(content.get("documentName"));
        log.setLink("/repositorydetail/${repositoryId}/doc/${documentId}");
        loggingByTemplService.createLog(log);
    }


    @Override
    public String createDocument(@NotNull @Valid WikiDocument wikiDocument) {
        // 设置顺序
        String documentId = new String();
        lock.lock();
        try {
            String categoryId = wikiDocument.getWikiCategory().getId();
            String repositoryId = wikiDocument.getWikiRepository().getId();
            Integer brotherNum = documentDao.getBrotherNum(repositoryId, categoryId);
            wikiDocument.setSort(brotherNum);

            WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
            documentId = documentDao.createDocument(wikiDocumentEntity);
        } catch (Exception e) {
            throw new ApplicationException(2000, "文档添加失败" + e.getMessage());
        } finally {
            lock.unlock();
        }

        WikiDocument wikiDocument1 = findDocumentById(documentId);
        Map<String, String> content = new HashMap<>();
        content.put("documentId", wikiDocument1.getId());
        content.put("documentName", wikiDocument1.getName());
        content.put("repositoryId", wikiDocument1.getWikiRepository().getId());

        dssClient.save(wikiDocument1);
        creatDynamic(content);
        return documentId;
    }


    @Override
    public Integer getBrotherNum(String repositoryId, String categoryId) {
        Integer brotherNum = documentDao.getBrotherNum(repositoryId, categoryId);
        return brotherNum;
    }

    @Override
    public Integer getMaxSort(String repositoryId, String categoryId) {
        Integer maxSort = documentDao.getMaxSort(repositoryId, categoryId);
        return maxSort;
    }

    @Override
    public void updateDocument(@NotNull @Valid WikiDocument wikiDocument) {
        String moveToId = wikiDocument.getMoveToId();
        if(moveToId != null){
            updateDocumentSort(wikiDocument);
        }
        WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
        documentDao.updateDocument(wikiDocumentEntity);

        WikiDocument wikiDocument1 = findDocumentById(wikiDocumentEntity.getId());
//        Map<String, String> content = new HashMap<>();
//        content.put("documentId", wikiDocument1.getId());
//        content.put("documentName", wikiDocument1.getName());
//        content.put("repositoryId", wikiDocument1.getWikiRepository().getId());
//        String typeId = wikiDocument1.getTypeId();
//
//        if(typeId.equals("document")){
//            content.put("iconUrl", "/images/mindMap.png");
//        }else {
//            content.put("iconUrl", "/images/document.png");
//        }

        dssClient.update(wikiDocument1);
    }

    @Override
    public void updateDocumentSort(WikiDocument wikiDocument) {
        // 被移动的id
        String id = wikiDocument.getId();
        WikiDocument document = findDocument(id);
        Integer sort = document.getSort();
        WikiCategory wikiCategory = document.getWikiCategory();

        // 第一步,先把大于被移动着的顺序的文档，目录的sort减一
        // 判断被移动的文档有没有上级目录
        if(wikiCategory != null){
            String wikiCategoryId = wikiCategory.getId();
            reduceSortInCategory(wikiCategoryId, sort);
            wikiCategoryService.reduceSortInCategory(wikiCategoryId, sort);
        }else {
            // 如果没有上级目录
            WikiRepository wikiRepository = document.getWikiRepository();
            String repositoryId = wikiRepository.getId();
            reduceSortInRepository(repositoryId, sort);
            wikiCategoryService.reduceSortInRepository(repositoryId, sort);
        }


        // 第二步, 把移动到的位置的， 大于移动位置的文档，目录排序都加一
        // moveId 不为空的话，表示文档移动到moveToId的位置
        String moveType = wikiDocument.getMoveType();
        String moveToId = wikiDocument.getMoveToId();

        // 拖动类型1，文档被托放在id 为moveToId 的目录或者文档存在的位置
        if(moveType.equals("1")){
            String moveToType = wikiDocument.getMoveToType();
            // 如果被插入位置原来是文档
            if(moveToType.equals("document")){
                WikiDocument toDocument = findDocument(moveToId);
                Integer toSort = toDocument.getSort();
                WikiCategory toWikiCategory = toDocument.getWikiCategory();

                // 如果移动到的位置有上级
                if(!ObjectUtils.isEmpty(toWikiCategory)){
                    String toWikiCategoryId = toWikiCategory.getId();
                    addSortInCategory(toWikiCategoryId, toSort);
                    wikiCategoryService.addSortInCategory(toWikiCategoryId, toSort);
                    // 更新被拖动的文档的顺序，
                    wikiDocument.setSort(toSort);
                    wikiDocument.setDimension(toDocument.getDimension());
                    wikiDocument.setTreePath(toDocument.getTreePath());
                    wikiDocument.setWikiCategory(toWikiCategory);
                }
                // 如果移动到的位置没有上级, 就是在知识库下面
                if(ObjectUtils.isEmpty(toWikiCategory)){
                    WikiRepository wikiRepository = toDocument.getWikiRepository();
                    String repositoryId = wikiRepository.getId();
                    addSortInRepository(repositoryId, toSort);
                    wikiCategoryService.addSortInRepository(repositoryId, toSort);

                    // 更新被拖动的文档的顺序，
                    wikiDocument.setSort(toSort);
                    wikiDocument.setDimension(toDocument.getDimension());
                    wikiDocument.setTreePath(toDocument.getTreePath());
                    WikiCategory wikiCategory1 = new WikiCategory();
                    wikiCategory1.setId("nullstring");
                    wikiDocument.setWikiCategory(wikiCategory1);
                }
            }

            // 如果被插入位置原来是目录
            if(moveToType.equals("category")){
                WikiCategory toCategory = wikiCategoryService.findCategory(moveToId);
                Integer toSort = toCategory.getSort();
                WikiCategory toWikiParentCategory = toCategory.getParentWikiCategory();
                // 如果移动到的位置有上级
                if(!ObjectUtils.isEmpty(toWikiParentCategory)){
                    // 如果插入位置与之前的位置都在同一个目录下面
                    if(toWikiParentCategory.getId().equals(wikiCategory.getId())){
                        toSort = toSort - 1;
                    }
                    addSortInCategory(toWikiParentCategory.getId(), toSort);
                    wikiCategoryService.addSortInCategory(toWikiParentCategory.getId(), toSort);
                    // 更新被拖动的文档的顺序，
                    wikiDocument.setSort(toSort);
                    wikiDocument.setDimension(toCategory.getDimension());
                    wikiDocument.setTreePath(toCategory.getTreePath());
                    wikiDocument.setWikiCategory(toWikiParentCategory);
                }
                // 如果移动到的位置没有上级, 就是在知识库下面
                if(ObjectUtils.isEmpty(toWikiParentCategory)){
                    if(ObjectUtils.isEmpty(wikiCategory)){
                        toSort = toSort - 1;
                    }
                    WikiRepository wikiRepository = toCategory.getWikiRepository();
                    String repositoryId = wikiRepository.getId();
                    addSortInRepository(repositoryId, toSort);
                    wikiCategoryService.addSortInRepository(repositoryId, toSort);

                    wikiDocument.setSort(toSort);
                    wikiDocument.setDimension(toCategory.getDimension());
                    wikiDocument.setTreePath(toCategory.getTreePath() != null ? toCategory.getTreePath() : "nullstring");
                    WikiCategory wikiCategory1 = new WikiCategory();
                    wikiCategory1.setId("nullstring");
                    wikiDocument.setWikiCategory(wikiCategory1);
                }
            }
        }

        //  拖动类型2，文档被托放在id 为moveToId 的目录下的最后一个位置
        if(moveType.equals("2")){
            WikiCategory toCategory = wikiCategoryService.findCategory(moveToId);
            WikiRepository wikiRepository = toCategory.getWikiRepository();
            // 获取移动到的目录有多少个下级
            Integer maxSort = documentDao.getMaxSort(wikiRepository.getId(), moveToId);
            // 判断是同一个目录移动还是不同目录移动
            // wikiCategory移动之前文档所在的目录
            if(wikiCategory != null){
                if(wikiCategory.getId() != moveToId){
                    wikiDocument.setSort(maxSort + 1);
                }else {
                    wikiDocument.setSort(maxSort);
                }
            }else {
                wikiDocument.setSort(maxSort + 1);
            }

            wikiDocument.setDimension(toCategory.getDimension() + 1);
            if(toCategory.getTreePath() != null){
                wikiDocument.setTreePath(toCategory.getTreePath() + moveToId + ";");
            }else {
                wikiDocument.setTreePath(moveToId + ";");
            }
            wikiDocument.setWikiCategory(toCategory);
        }

        //  拖动类型3，文档被托放知识库最后
        if(moveType.equals("3")){
            // 获取移动到的目录有多少个下级
            WikiRepository wikiRepository = document.getWikiRepository();
            Integer maxSort = documentDao.getMaxSort(wikiRepository.getId(), null);
            if(wikiCategory != null){
                wikiDocument.setSort(maxSort + 1);
            }else {
                wikiDocument.setSort(maxSort);
            }
            // 找到当前目录或者知识库最大的顺序号
            wikiDocument.setSort(maxSort + 1);
            wikiDocument.setDimension(1);
            wikiDocument.setTreePath("nullstring");
            WikiCategory wikiCategory1 = new WikiCategory();
            wikiCategory1.setId("nullstring");
            wikiDocument.setWikiCategory(wikiCategory1);
        }
    }

    @Override
    public void updateDocumentInit(WikiDocument wikiDocument) {
        WikiDocumentEntity wikiDocumentEntity = BeanMapper.map(wikiDocument, WikiDocumentEntity.class);
        documentDao.updateDocument(wikiDocumentEntity);
    }


    @Override
    public void deleteDocument(@NotNull String id) {
        //删除事项的关联
    //    workItemDocumentController.delete(id);
    //    WikiDocumentEntity wikiDocumentEntity = documentDao.findDocument(id);
        RecentQuery recentQuery = new RecentQuery();
        recentQuery.setModelId(id);
        List<Recent> recentList = recentService.findRecentList(recentQuery);
        for (Recent recent : recentList) {
            recentService.deleteRecent(recent.getId());
        }

        dssClient.delete(WikiDocument.class, id);
        documentDao.deleteDocument(id);

    }
    @Override
    public void deleteDocumentAndSort(@NotNull @Valid WikiDocument wikiDocument) {
        //删除下面相关联的目录和文档
        String id = wikiDocument.getId();
        String repositoryId = wikiDocument.getWikiRepository().getId();
        WikiCategory wikiCategory = wikiDocument.getWikiCategory();
        Integer sort = wikiDocument.getSort();
        String parentWikiCategoryId = new String();
        if(wikiCategory != null){
            parentWikiCategoryId = wikiCategory.getId();
        }
        wikiCategoryService.updateSortAfterDelete(repositoryId, parentWikiCategoryId, sort);
        documentDao.deleteDocument(id);
    }

    @Override
    public WikiDocument findOne(String id) {

        WikiDocumentEntity wikiDocumentEntity = documentDao.findDocument(id);

        WikiDocument wikiDocument = BeanMapper.map(wikiDocumentEntity, WikiDocument.class);

        return wikiDocument;
    }

    @Override
    public List<WikiDocument> findList(List<String> idList) {
        List<WikiDocumentEntity> wikiDocumentEntityList =  documentDao.findDocumentList(idList);

        List<WikiDocument> wikiDocumentList =  BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);
        return wikiDocumentList;
    }

    @Override
    public WikiDocument findDocument(@NotNull String id) {
        WikiDocument wikiDocument = findOne(id);

        joinTemplate.joinQuery(wikiDocument);

        if(!ObjectUtils.isEmpty(wikiDocument)){
            CommentQuery commentQuery = new CommentQuery();
            commentQuery.setDocumentId(id);
            List<Comment> commentList = commentService.findCommentList(commentQuery);

            if (!commentList.isEmpty()){
                //添加评论数
                wikiDocument.setCommentNumber(commentList.size());
            }else {
                wikiDocument.setCommentNumber(0);
            }
            findLike(wikiDocument);
            findFocus(wikiDocument);

        }
        return wikiDocument;
    }

    @Override
    public WikiDocument findDocumentById(@NotNull String id) {
        WikiDocumentEntity wikiDocumentEntity = documentDao.findDocument(id);
        WikiDocument wikiDocument = BeanMapper.map(wikiDocumentEntity, WikiDocument.class);

        CommentQuery commentQuery = new CommentQuery();
        commentQuery.setDocumentId(id);
        List<Comment> commentList = commentService.findCommentList(commentQuery);

        if (!commentList.isEmpty()){
            //添加评论数
            wikiDocument.setCommentNumber(commentList.size());
        }else {
            wikiDocument.setCommentNumber(0);
        }
        joinTemplate.joinQuery(wikiDocument);
        return wikiDocument;
    }

    @Override
    public List<Map<String, Object>> findDocumentByRepositoryIds(String repositoryIds) {
        String sql = "select repository_id from wiki_document t where t.repository_id in "+ repositoryIds;
        List<Map<String, Object>> documentList = this.jpaTemplate.getJdbcTemplate().queryForList(sql);
        return documentList;
    }

    @Override
    public List<WikiDocument> findAllDocument() {
        List<WikiDocumentEntity> wikiDocumentEntityList =  documentDao.findAllDocument();

        List<WikiDocument> wikiDocumentList =  BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);
        return wikiDocumentList;
    }

    @Override
    public List<WikiDocument> findDocumentList(DocumentQuery documentQuery) {
        List<WikiDocumentEntity> wikiDocumentEntityList = documentDao.findDocumentList(documentQuery);

        List<WikiDocument> wikiDocumentList = BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);

        return wikiDocumentList;
    }

    @Override
    public Integer findDocumentCount(DocumentQuery documentQuery) {
        List<WikiDocumentEntity> documentList = documentDao.findDocumentList(documentQuery);
        int size = documentList.size();
        return size;
    }

    @Override
    public Pagination<WikiDocument> findDocumentPage(DocumentQuery documentQuery) {

        Pagination<WikiDocumentEntity>  pagination = documentDao.findDocumentPage(documentQuery);

        List<WikiDocument> wikiDocumentList = BeanMapper.mapList(pagination.getDataList(), WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);

        return PaginationBuilder.build(pagination, wikiDocumentList);
    }

    @Override
    public List<WikiDocument> findDocuementByKeyWork(String keyWork) {
        List<WikiDocumentEntity> wikiDocumentEntityList = documentDao.findDocuementByKeyWork(keyWork);

        List<WikiDocument> wikiDocumentList = BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);

        return wikiDocumentList;
    }


    /**
     *查询点赞
     * @param
     */
    public void findLike( WikiDocument wikiDocument){
        LikeQuery likeQuery = new LikeQuery();
        likeQuery.setToWhomId(wikiDocument.getId());
        likeQuery.setLikeType("doc");
        List<Like> likeList = likeService.findLikeList(likeQuery);

        if (!likeList.isEmpty()){
            String createUserId = LoginContext.getLoginId();
            List<Like> collect1 = likeList.stream().filter(a -> createUserId.equals(a.getLikeUser().getId())).collect(Collectors.toList());
            if (!collect1.isEmpty()){
                wikiDocument.setLike(true);
            }else {
                wikiDocument.setLike(false);
            }
            List<User> userList = likeList.stream().map(Like::getLikeUser).collect(Collectors.toList());
            if(!userList.isEmpty()){
                //取点赞人名字
                List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
                wikiDocument.setLikeUserList(collect);
            }
            wikiDocument.setLikenumInt(likeList.size());

        }else {
            wikiDocument.setLike(false);
            wikiDocument.setLikenumInt(0);
        }
    }

    public void findFocus( WikiDocument wikiDocument){
        DocumentFocusQuery documentFocusQuery = new DocumentFocusQuery();
        documentFocusQuery.setDocumentId(wikiDocument.getId());
        String createUserId = LoginContext.getLoginId();
        documentFocusQuery.setMasterId(createUserId);
        List<DocumentFocus> documentFocusList = documentFocusService.findDocumentFocusList(documentFocusQuery);

        if (!documentFocusList.isEmpty()){
            wikiDocument.setFocus(true);
        }else {
            wikiDocument.setFocus(false);
        }
    }

    @Override
    public List<WikiDocument> findRecentDocumentList(RecentQuery recentQuery) {
        List<WikiDocumentEntity> wikiDocumentEntityList = documentDao.findRecentDocumentList(recentQuery);

        List<WikiDocument> wikiDocumentList = BeanMapper.mapList(wikiDocumentEntityList, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);

        return wikiDocumentList;
    }

    @Override
    public void reduceSortInCategory(String wikiCategoryId, Integer sort) {
        documentDao.reduceSortInCategory(wikiCategoryId, sort);
    }

    @Override
    public void reduceSortInRepository(String repositoryId, Integer sort) {
        documentDao.reduceSortInRepository(repositoryId, sort);
    }


    @Override
    public void addSortInCategory(String wikiCategoryId, Integer sort) {
        documentDao.addSortInCategory(wikiCategoryId, sort);
    }

    @Override
    public void addSortInRepository(String repositoryId, Integer sort) {
        documentDao.addSortInRepository(repositoryId, sort);
    }

    /**
     * 查找所有目录所有下级的文件
     * @param categoryId
     * @return
     */
    public List<WikiDocument> findAllChildrenDocumentList(String categoryId) {
        List<WikiDocumentEntity> documentListEntity = documentDao.findAllChildrenDocumentList(categoryId);

        List<WikiDocument> wikiDocumentList = BeanMapper.mapList(documentListEntity, WikiDocument.class);

        joinTemplate.joinQuery(wikiDocumentList);

        return wikiDocumentList;
    }

}