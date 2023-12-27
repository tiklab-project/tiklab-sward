package io.thoughtware.sward.repository.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.security.logging.service.LoggingByTempService;
import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.category.model.WikiCategoryQuery;
import io.thoughtware.sward.category.service.WikiCategoryService;
import io.thoughtware.sward.document.model.DocumentQuery;
import io.thoughtware.sward.document.service.DocumentService;
import io.thoughtware.sward.repository.dao.WikiRepositoryDao;
import io.thoughtware.sward.repository.entity.WikiRepositoryEntity;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.sward.repository.model.WikiRepositoryQuery;
import io.thoughtware.sward.repository.support.MessageTemplateRepository;
import io.thoughtware.sward.repository.support.OpLogTemplateRepository;
import io.thoughtware.sward.support.service.RecentService;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.dss.client.DssClient;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.message.message.model.SendMessageNotice;
import io.thoughtware.message.message.service.SendMessageNoticeService;
import io.thoughtware.privilege.dmRole.service.DmRoleService;
import io.thoughtware.privilege.role.model.PatchUser;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.security.logging.model.Logging;
import io.thoughtware.security.logging.model.LoggingType;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.dmUser.service.DmUserService;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(WikiRepositoryServiceImpl.class);
    @Autowired
    WikiRepositoryDao wikiRepositoryDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    WikiCategoryService wikiCategoryService;

    @Autowired
    DocumentService documentService;

    @Autowired
    RecentService recentService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    DssClient dssClient;

    @Autowired
    UserService userService;



    @Autowired
    LoggingByTempService loggingByTemplService;

    @Value("${base.url:null}")
    String baseUrl;

    void creatDynamic( Map<String, String> content){
        Logging log = new Logging();
        log.setBgroup("sward");

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);
        content.put("master", user.getName());
        content.put("updateTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        LoggingType opLogType = new LoggingType();
        opLogType.setId(OpLogTemplateRepository.KANASS_LOGTYPE_REPOSITORYADD);
        log.setActionType(opLogType);

        log.setModule("repository");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        content.put("createUserIcon",user.getName().substring( 0, 1));
        log.setData(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        log.setAction(content.get("documentName"));
        log.setLink("/repositorydetail/${repositoryId}/doc/${documentId}");
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
//        sendMessageNoticeService.createMessageItem(messageDispatchNotice);
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
//        creatDynamic(content);
//        sendMessageForCreat(content);
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
        dmRoleService.initPatchDmRole(repositoryId,patchUsers, "sward");
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
        wikiRepositoryDao.deleteRepositoryAndRelation(id);
        // 项目成员
        dmUserService.deleteDmUserByDomainId(id);
        // 项目角色、权限
        dmRoleService.deleteDmRoleByDomainId(id);
        // 删除知识库
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
    public List<WikiRepository> findRepositoryList(String time) {
        List<WikiRepositoryEntity> wikiRepositoryEntityList =  wikiRepositoryDao.findRepositoryList(time);

        List<WikiRepository> wikiRepositoryList =  BeanMapper.mapList(wikiRepositoryEntityList, WikiRepository.class);

        joinTemplate.joinQuery(wikiRepositoryList);
        return wikiRepositoryList;
    }


    @Override
    public List<WikiRepository> findRepositoryListByUser(WikiRepositoryQuery wikiRepositoryQuery) {

        String userId = LoginContext.getLoginId();
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setUserId(userId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> collect = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());
        String[] arr = collect.toArray(new String[collect.size()]);
        wikiRepositoryQuery.setRepositoryIds(arr);


        // 修改排序参数

        List<WikiRepositoryEntity> wikiRepositoryEntityList = wikiRepositoryDao.findRepositoryListByUser(wikiRepositoryQuery);

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

        long aTime1 = System.currentTimeMillis();
        List<WikiRepositoryEntity> recentRepositoryList = wikiRepositoryDao.findRecentRepositoryList(wikiRepositoryQuery);
        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(recentRepositoryList, WikiRepository.class);
        joinTemplate.joinQuery(wikiRepositoryList);
        long bTime1 = System.currentTimeMillis();

        logger.info("joinQuery cost time:{}",bTime1-aTime1);
        String repositoryId = wikiRepositoryQuery.getRepositoryId();
        // 如果在切换知识库的时候查找最近项目，去掉当前项目
        if(repositoryId != null){

            wikiRepositoryList = wikiRepositoryList.stream().
                    filter(wikiRepository -> !wikiRepository.getId().equals(repositoryId)).collect(Collectors.toList());
        }
        List<String> wikiRepositoryIds = wikiRepositoryList.stream().map(wikiRepository -> wikiRepository.getId()).collect(Collectors.toList());
        wikiRepositoryIds.add(repositoryId);
        int size = recentRepositoryList.size();

        // 判断查看的知识库是否小于5个
        if(size < 5){
            int lackSize = 5 - size;

            List<Order> order = OrderBuilders.instance().desc("createTime").get();
            wikiRepositoryQuery.setOrderParams(order);
            wikiRepositoryQuery.setMasterId(null);
            List<WikiRepository> repositoryListByUser = findRepositoryListByUser(wikiRepositoryQuery);
            long cTime1 = System.currentTimeMillis();
            logger.info("joinQuery cost time:{}",cTime1-bTime1);
            // 去掉与点击过的重复
            repositoryListByUser = repositoryListByUser.stream().filter(item -> !wikiRepositoryIds.contains(item.getId())).collect(Collectors.toList());

            if(repositoryListByUser.size() > lackSize){
                List<WikiRepository> repositoryList = repositoryListByUser.subList(0, lackSize);
                wikiRepositoryList.addAll(repositoryList);
            }else {
                wikiRepositoryList.addAll(repositoryListByUser);
            }
        }
        if(wikiRepositoryList.size() > 0){
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

        }


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