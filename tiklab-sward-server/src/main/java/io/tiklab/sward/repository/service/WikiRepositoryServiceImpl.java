package io.tiklab.sward.repository.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.security.logging.logging.model.Logging;
import io.tiklab.security.logging.logging.model.LoggingType;
import io.tiklab.security.logging.logging.service.LoggingByTempService;
import io.tiklab.sward.category.service.WikiCategoryService;
import io.tiklab.sward.document.service.DocumentService;
import io.tiklab.sward.node.model.Node;
import io.tiklab.sward.node.service.NodeService;
import io.tiklab.sward.repository.dao.WikiRepositoryDao;
import io.tiklab.sward.repository.entity.WikiRepositoryEntity;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.sward.repository.model.WikiRepositoryFocusQuery;
import io.tiklab.sward.repository.model.WikiRepositoryQuery;
import io.tiklab.sward.repository.support.MessageTemplateRepository;
import io.tiklab.sward.repository.support.OpLogTemplateRepository;
import io.tiklab.sward.support.model.RecentQuery;
import io.tiklab.sward.support.service.RecentService;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dss.client.DssClient;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.message.message.model.SendMessageNotice;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
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
* 知识库接口
*/
@Service
@Exporter
public class WikiRepositoryServiceImpl implements WikiRepositoryService {
    private static Logger logger = LoggerFactory.getLogger(WikiRepositoryServiceImpl.class);
    @Autowired
    WikiRepositoryDao wikiRepositoryDao;

    @Autowired
    NodeService nodeService;

    @Autowired
    JoinTemplate joinTemplate;



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
    WikiRepositoryFocusService wikiRepositoryFocusService;

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

        //构建索引
        WikiRepository entity = findRepository(id);
//        dssClient.save(entity);
        return id;
    }

    /**
     * confluence导入
     * @param wikiRepository
     * @return
     */
    @Override
    public String createConfluRepository(@NotNull @Valid WikiRepository wikiRepository) {
        WikiRepositoryEntity wikiRepositoryEntity = BeanMapper.map(wikiRepository, WikiRepositoryEntity.class);
        String id = wikiRepositoryDao.createRepository(wikiRepositoryEntity);

        User master = wikiRepository.getMaster();
        String masterId = master.getId();
        initRepositoryDmRole(masterId, id);

        WikiRepository wikiRepository1 = findRepository(id);
        Map<String, String> content = new HashMap<>();
        content.put("repositoryId", wikiRepository1.getId());
        content.put("repositoryName", wikiRepository1.getName());
        content.put("repositoryIcon", wikiRepository1.getIconUrl());

        //构建索引
        WikiRepository entity = findRepository(id);
//        dssClient.save(entity);
        return id;
    }

    /**
     * 初始化知识库角色
     * @param masterId
     * @param repositoryId
     */
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
            patchUser.setUserId(masterId);
            patchUser.setRoleType(2);
            patchUsers.add(patchUser);

            // 初始化"111111"
            PatchUser patchUser1 = new PatchUser();
            DmUser dmUser1 = new DmUser();
            dmUser1.setDomainId(repositoryId);
            User user1 = new User();
            user1.setId("111111");
            dmUser1.setUser(user1);

            patchUser1.setUserId("111111");
            patchUser1.setRoleType(2);
            patchUsers.add(patchUser1);

        }else {
            PatchUser patchUser = new PatchUser();
            DmUser dmUser = new DmUser();
            dmUser.setDomainId(repositoryId);
            User user = new User();
            user.setId(masterId);
            dmUser.setUser(user);
            patchUser.setUserId(masterId);
            patchUser.setRoleType(2);
            patchUsers.add(patchUser);
        }
        dmRoleService.initPatchDmRole(repositoryId, patchUsers);
    }

    /**
     * 更新知识库
     * @param wikiRepository
     */
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

    /**
     * 删除知识库
     * @param id
     */
    @Override
    public void deleteRepository(@NotNull String id) {

        // 删除所有节点、和节点关联的所有数据
        nodeService.deleteRepositoryNodeCondition(id);
        // 项目成员
        dmUserService.deleteDmUserByDomainId(id);
        // 项目角色、权限
        dmRoleService.deleteDmRoleByDomainId(id);
        // 删除知识库
        wikiRepositoryDao.deleteRepository(id);

        // 删除最近查看的知识库
        RecentQuery recentQuery = new RecentQuery();
        recentQuery.setModelId(id);
        recentService.deleteRecnetByCondition(recentQuery);

        WikiRepositoryFocusQuery wikiRepositoryFocusQuery = new WikiRepositoryFocusQuery();
        wikiRepositoryFocusQuery.setRepositoryId(id);
        wikiRepositoryFocusService.deleteRepositoryFocusByCondition(wikiRepositoryFocusQuery);
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

    /**
     * 查找知识库和下级的目录和文档
     * @param id
     * @return
     */
    @Override
    public WikiRepository findRepository(@NotNull String id) {
        WikiRepository wikiRepository = findOne(id);
        if(wikiRepository != null){
            String repositoryIds = "('" + id + "')";
            List<Node> childrenNodeList = nodeService.findChildrenNodeList(repositoryIds);
            List<Node> document = childrenNodeList.stream().filter(node -> node.getType().equals("document")).collect(Collectors.toList());

            int size = document.size();
            wikiRepository.setDocumentNum(size);
            wikiRepository.setCategoryNum(childrenNodeList.size() - size);
            joinTemplate.joinQuery(wikiRepository);
        }

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
        List<WikiRepositoryEntity> wikiRepositoryEntityList =  wikiRepositoryDao.findRepositoryList(wikiRepositoryQuery);

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
    public List<WikiRepository> findAllRecentRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {
        String createUserId = LoginContext.getLoginId();
        wikiRepositoryQuery.setMasterId(createUserId);
        List<WikiRepositoryEntity> recentRepositoryList = wikiRepositoryDao.findRecentRepositoryList(wikiRepositoryQuery);
        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(recentRepositoryList, WikiRepository.class);
        joinTemplate.joinQuery(wikiRepositoryList);

        return  wikiRepositoryList;
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
            List<Node> childrenNodeList = nodeService.findChildrenNodeList(repositoryIds);
            for (WikiRepository wikiRepository : wikiRepositoryList) {
                String id = wikiRepository.getId();
                List<Node> document = childrenNodeList.stream().filter
                        (node -> node.getWikiRepository().getId().equals(id) && node.getType().equals("document")).collect(Collectors.toList());

                List<Node> category = childrenNodeList.stream().filter
                        (node -> node.getWikiRepository().getId().equals(id) && node.getType().equals("category")).collect(Collectors.toList());

                wikiRepository.setDocumentNum(document.size());
                wikiRepository.setCategoryNum(category.size());
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
        if(wikiRepositoryList.size() > 0){
            String repositoryIds = "(" + wikiRepositoryList.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";
            List<Node> childrenNodeList = nodeService.findChildrenNodeList(repositoryIds);
            for (WikiRepository wikiRepository : wikiRepositoryList) {
                String id = wikiRepository.getId();
                List<Node> document = childrenNodeList.stream().filter
                        (node -> node.getWikiRepository().getId().equals(id) && node.getType().equals("document")).collect(Collectors.toList());

                List<Node> category = childrenNodeList.stream().filter
                        (node -> node.getWikiRepository().getId().equals(id) && node.getType().equals("category")).collect(Collectors.toList());

                wikiRepository.setDocumentNum(document.size());
                wikiRepository.setCategoryNum(category.size());
            }

        }
        joinTemplate.joinQuery(wikiRepositoryList);

        return wikiRepositoryList;
    }

    @Override
    public Map<String, Integer> findRepositoryNum(WikiRepositoryQuery wikiRepositoryQuery) {
        HashMap<String, Integer> nums = new HashMap<>();
        List<WikiRepositoryEntity> focusRepositoryList = wikiRepositoryDao.findFocusRepositoryList(wikiRepositoryQuery);
        nums.put("focus", focusRepositoryList.size());
        List<WikiRepository> repositoryListByUser = findRepositoryListByUser(wikiRepositoryQuery);
        nums.put("create", repositoryListByUser.size());
        wikiRepositoryQuery.setMasterId(null);
        List<WikiRepository> repositoryListByUser1 = findRepositoryListByUser(wikiRepositoryQuery);
        nums.put("all", repositoryListByUser1.size());
        return  nums;

    }


}