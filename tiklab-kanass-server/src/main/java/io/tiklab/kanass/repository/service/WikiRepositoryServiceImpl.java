package io.tiklab.kanass.repository.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.document.model.DocumentQuery;
import io.tiklab.kanass.document.model.DocumentRecent;
import io.tiklab.kanass.document.model.DocumentRecentQuery;
import io.tiklab.kanass.document.service.DocumentRecentService;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dss.client.DssClient;
import io.tiklab.join.JoinTemplate;
import io.tiklab.kanass.category.model.WikiCategory;
import io.tiklab.kanass.category.model.WikiCategoryQuery;
import io.tiklab.kanass.repository.support.MessageTemplateRepository;
import io.tiklab.kanass.repository.support.OpLogTemplateRepository;
import io.tiklab.message.message.model.SendMessageNotice;
import io.tiklab.message.message.service.SendMessageNoticeService;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.security.logging.model.Logging;
import io.tiklab.security.logging.model.LoggingType;
import io.tiklab.security.logging.service.LoggingByTemplService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import io.tiklab.kanass.category.service.WikiCategoryService;
import io.tiklab.kanass.document.service.DocumentService;
import io.tiklab.kanass.repository.dao.WikiRepositoryDao;
import io.tiklab.kanass.repository.entity.WikiRepositoryEntity;
import io.tiklab.kanass.repository.model.WikiRepository;
import io.tiklab.kanass.repository.model.WikiRepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
* RepositoryServiceImpl
*/
@Service
@Exporter
public class WikiRepositoryServiceImpl implements WikiRepositoryService {

    @Autowired
    WikiRepositoryDao wikiRepositoryDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    WikiCategoryService wikiCategoryService;

    @Autowired
    DocumentService documentService;

    @Autowired
    DocumentRecentService documentRecentService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    DssClient dssClient;

    @Autowired
    UserService userService;

    @Autowired
    SendMessageNoticeService sendMessageNoticeService;

    @Autowired
    LoggingByTemplService loggingByTemplService;

    @Value("${base.url:null}")
    String baseUrl;

    void creatDynamic( Map<String, String> content){
        Logging log = new Logging();
        log.setBgroup("kanass");

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);
        content.put("master", user.getName());
        content.put("updateTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        log.setLoggingTemplateId(OpLogTemplateRepository.KANASS_LOGTEMPLATE_REPOSITORYADD);

        LoggingType opLogType = new LoggingType();
        opLogType.setId(OpLogTemplateRepository.KANASS_LOGTYPE_REPOSITORYADD);
        log.setActionType(opLogType);

        log.setModule("repository");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        content.put("createUserIcon",user.getName().substring( 0, 1));
        log.setContent(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        loggingByTemplService.createLog(log);


    }

    /**
     * 发送消息
     * @param content
     */
    void sendMessageForCreat(Map<String, String> content ){
        SendMessageNotice messageDispatchNotice = new SendMessageNotice();

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);

        content.put("master", user.getName());
        content.put("createUserIcon",user.getName().substring( 0, 1));
        content.put("sendTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        String msg = JSONObject.toJSONString(content);
        messageDispatchNotice.setId(MessageTemplateRepository.KANASS_MESSAGETEMPLATE_REPOSITORYADD);
        messageDispatchNotice.setSiteData(msg);
        messageDispatchNotice.setDingdingData(msg);
        messageDispatchNotice.setQywechatData(msg);
        messageDispatchNotice.setEmailData(msg);
        messageDispatchNotice.setBaseUrl(baseUrl);
        sendMessageNoticeService.createMessageItem(messageDispatchNotice);
    }
    @Override
    public String createRepository(@NotNull @Valid WikiRepository wikiRepository) {
        wikiRepository.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String masterId = LoginContext.getLoginId();;
        User user = new User();
        user.setId(masterId);
        wikiRepository.setMaster(user);

        WikiRepositoryEntity wikiRepositoryEntity = BeanMapper.map(wikiRepository, WikiRepositoryEntity.class);
        String id = wikiRepositoryDao.createRepository(wikiRepositoryEntity);


        initRepositoryDmRole(masterId, id);

        WikiRepository wikiRepository1 = findRepository(id);
        Map<String, String> content = new HashMap<>();
        content.put("repositoryId", wikiRepository1.getId());
        content.put("repositoryName", wikiRepository1.getName());
        content.put("repositoryIcon", wikiRepository1.getIconUrl());
        creatDynamic(content);
        sendMessageForCreat(content);
        //构建索引
        WikiRepository entity = findRepository(id);
        dssClient.save(entity);
        return id;
    }

    public void initRepositoryDmRole(String masterId, String repositoryId){
        List<PatchUser> patchUsers = new ArrayList<PatchUser>();
        if(!masterId.equals("111111")){
            // 初始化创建者
            PatchUser patchUser = new PatchUser();
            DmUser dmUser = new DmUser();
            dmUser.setDomainId(repositoryId);
            User user = new User();
            user.setId(masterId);
            dmUser.setUser(user);
            patchUser.setId(masterId);
            patchUser.setAdminRole(true);
            patchUsers.add(patchUser);

            // 初始化"111111"
            PatchUser patchUser1 = new PatchUser();
            DmUser dmUser1 = new DmUser();
            dmUser1.setDomainId(repositoryId);
            User user1 = new User();
            user1.setId("111111");
            dmUser1.setUser(user1);

            patchUser1.setId("111111");
            patchUser1.setAdminRole(true);
            patchUsers.add(patchUser1);

        }else {
            PatchUser patchUser = new PatchUser();
            DmUser dmUser = new DmUser();
            dmUser.setDomainId(repositoryId);
            User user = new User();
            user.setId(masterId);
            dmUser.setUser(user);
            patchUser.setId(masterId);
            patchUser.setAdminRole(true);
            patchUsers.add(patchUser);
        }
        dmRoleService.initPatchDmRole(repositoryId,patchUsers, "kanass");
    }

    @Override
    public void updateRepository(@NotNull @Valid WikiRepository wikiRepository) {
        WikiRepositoryEntity wikiRepositoryEntity = BeanMapper.map(wikiRepository, WikiRepositoryEntity.class);
//        creatDynamic(content);
        wikiRepositoryDao.updateRepository(wikiRepositoryEntity);
        WikiRepository wikiRepository1 = findRepository(wikiRepositoryEntity.getId());
        Map<String, String> content = new HashMap<>();
        content.put("repositoryId", wikiRepository1.getId());
        content.put("repositoryName", wikiRepository1.getName());
        dssClient.update(wikiRepository1);
    }

    @Override
    public void deleteRepository(@NotNull String id) {
        //删除相关联的目录和内容
//        DeleteCondition deleteCondition = DeleteBuilders.createDelete(DocumentEntity.class)
//                .eq("repositoryId", id)
//                .get();
        documentService.deleteDocument(id);
        DocumentRecentQuery documentRecentQuery = new DocumentRecentQuery();
        documentRecentQuery.setModelId(id);
        documentRecentQuery.setModel("kanass");

        List<DocumentRecent> documentRecentList = documentRecentService.findDocumentRecentList(documentRecentQuery);
            if(documentRecentList.size() >0){
            String id1 = documentRecentList.get(0).getId();
            documentRecentService.deleteDocumentRecent(id1);
        }

//        deleteCondition = DeleteBuilders.createDelete(CategoryEntity.class)
//                .eq("repositoryId", id)
//                .get();
        wikiCategoryService.deleteCategory(id);

        wikiRepositoryDao.deleteRepository(id);
    }

    @Override
    public WikiRepository findOne(String id) {
        WikiRepositoryEntity wikiRepositoryEntity = wikiRepositoryDao.findRepository(id);

        WikiRepository wikiRepository = BeanMapper.map(wikiRepositoryEntity, WikiRepository.class);
        return wikiRepository;
    }

    @Override
    public List<WikiRepository> findList(List<String> idList) {
        List<WikiRepositoryEntity> wikiRepositoryEntityList =  wikiRepositoryDao.findRepositoryList(idList);

        List<WikiRepository> wikiRepositoryList =  BeanMapper.mapList(wikiRepositoryEntityList, WikiRepository.class);

        joinTemplate.joinQuery(wikiRepositoryList);

        return wikiRepositoryList;
    }

    @Override
    public WikiRepository findRepository(@NotNull String id) {
        WikiRepository wikiRepository = findOne(id);
        DocumentQuery documentQuery = new DocumentQuery();
        documentQuery.setRepositoryId(id);
        Integer documentCount = documentService.findDocumentCount(documentQuery);
        wikiRepository.setDocumentNum(documentCount);

        WikiCategoryQuery wikiCategoryQuery = new WikiCategoryQuery();
        wikiCategoryQuery.setRepositoryId(id);
        List<WikiCategory> wikiCategoryList = wikiCategoryService.findCategoryList(wikiCategoryQuery);
        wikiRepository.setCategoryNum(wikiCategoryList.size());

        joinTemplate.joinQuery(wikiRepository);
        return wikiRepository;
    }

    @Override
    public List<WikiRepository> findAllRepository() {
        List<WikiRepositoryEntity> wikiRepositoryEntityList =  wikiRepositoryDao.findAllRepository();

        List<WikiRepository> wikiRepositoryList =  BeanMapper.mapList(wikiRepositoryEntityList, WikiRepository.class);

        joinTemplate.joinQuery(wikiRepositoryList);
        return wikiRepositoryList;
    }

    @Override
    public List<WikiRepository> findRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {

        String userId = LoginContext.getLoginId();
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setUserId(userId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> collect = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());
        String[] arr = collect.toArray(new String[collect.size()]);
        wikiRepositoryQuery.setRepositoryIds(arr);

        List<WikiRepositoryEntity> wikiRepositoryEntityList = wikiRepositoryDao.findRepositoryList(wikiRepositoryQuery);

        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(wikiRepositoryEntityList, WikiRepository.class);

        joinTemplate.joinQuery(wikiRepositoryList);

        return wikiRepositoryList;
    }

    @Override
    public Pagination<WikiRepository> findRepositoryPage(WikiRepositoryQuery wikiRepositoryQuery) {

        String userId = LoginContext.getLoginId();
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setUserId(userId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> collect = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());
        String[] arr = collect.toArray(new String[collect.size()]);
        wikiRepositoryQuery.setRepositoryIds(arr);

        Pagination<WikiRepositoryEntity>  pagination = wikiRepositoryDao.findRepositoryPage(wikiRepositoryQuery);

        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(pagination.getDataList(), WikiRepository.class);

        joinTemplate.joinQuery(wikiRepositoryList);

        return PaginationBuilder.build(pagination, wikiRepositoryList);
    }

    @Override
    public List<WikiRepository> findRecentRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {
        String createUserId = LoginContext.getLoginId();
        wikiRepositoryQuery.setMasterId(createUserId);
        List<WikiRepositoryEntity> recentRepositoryList = wikiRepositoryDao.findRecentRepositoryList(wikiRepositoryQuery);

        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(recentRepositoryList, WikiRepository.class);

        String repositoryIds = "(" + wikiRepositoryList.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";
        List<Map<String, Object>> categoryList = wikiCategoryService.findCategoryByRepositoryIds(repositoryIds);
        List<Map<String, Object>> documentList = documentService.findDocumentByRepositoryIds(repositoryIds);
        for (WikiRepository wikiRepository : wikiRepositoryList) {
            String id = wikiRepository.getId();

            List<Map<String, Object>> categorys = categoryList.stream().filter(category -> category.get("repository_id").equals(id)).collect(Collectors.toList());
            wikiRepository.setCategoryNum(categorys.size());

            List<Map<String, Object>> documents = documentList.stream().filter(document -> document.get("repository_id").equals(id)).collect(Collectors.toList());
            wikiRepository.setDocumentNum(documents.size());
        }

        joinTemplate.joinQuery(wikiRepositoryList);

        return wikiRepositoryList;
    }

    @Override
    public List<WikiRepository> findFocusRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {
        String createUserId = LoginContext.getLoginId();
        wikiRepositoryQuery.setMasterId(createUserId);
        List<WikiRepositoryEntity> recentRepositoryList = wikiRepositoryDao.findFocusRepositoryList(wikiRepositoryQuery);

        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(recentRepositoryList, WikiRepository.class);

        joinTemplate.joinQuery(wikiRepositoryList);

        return wikiRepositoryList;
    }


}