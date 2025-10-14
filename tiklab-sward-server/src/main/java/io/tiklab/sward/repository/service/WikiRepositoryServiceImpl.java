package io.tiklab.sward.repository.service;

import io.tiklab.core.exception.SystemException;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dss.client.DssClient;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.message.message.model.MessageNoticePatch;
import io.tiklab.message.message.service.MessageDmNoticeService;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.sward.node.node.model.Node;
import io.tiklab.sward.node.node.service.NodeService;
import io.tiklab.sward.repository.dao.WikiRepositoryDao;
import io.tiklab.sward.repository.entity.WikiRepositoryEntity;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.sward.repository.model.WikiRepositoryFocusQuery;
import io.tiklab.sward.repository.model.WikiRepositoryQuery;
import io.tiklab.sward.support.message.model.SwardMessage;
import io.tiklab.sward.support.message.service.SwardSendMessageService;
import io.tiklab.sward.support.support.model.RecentQuery;
import io.tiklab.sward.support.support.service.RecentService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.tiklab.sward.support.constant.MessageConstant.*;

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
    UserProcessor userService;

    @Autowired
    DssClient dssClient;

    @Autowired
    WikiRepositoryFocusService wikiRepositoryFocusService;

    @Autowired
    MessageDmNoticeService messageDmNoticeService;

    @Autowired
    SwardSendMessageService swardSendMessageService;

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

        //构建索引
        WikiRepository entity = findRepository(id);
        dssClient.save(entity);

        MessageNoticePatch messageNoticePatch = new MessageNoticePatch();
        messageNoticePatch.setDomainId(id);
        messageNoticePatch.setUserList(List.of(masterId));
        messageDmNoticeService.initMessageDmNotice(messageNoticePatch);


        new Thread(() -> {
            try {
                SwardMessage swardMessage = new SwardMessage();
                swardMessage.setRpyId(id);
                swardMessage.setUserId(masterId);
                swardMessage.setNoticeId(SW_RPY_CREATE);
                swardMessage.setSendType(SW_RPY_CREATE);
                swardSendMessageService.sendMessage(swardMessage);
            }catch (Exception e){
                logger.error("发送消息失败", e);
                e.printStackTrace();
            }
        }).start();

        return id;
    }

    @Override
    public String createRepositoryByValidCreateUser(@NotNull @Valid WikiRepository wikiRepository) {
        wikiRepository.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String masterId = wikiRepository.getMaster().getId();
        User user = userService.findUser(masterId);
        if (Objects.isNull(user)){
            throw new SystemException("创建知识库的用户不存在！");
        }
        wikiRepository.setMaster(user);

        WikiRepositoryEntity wikiRepositoryEntity = BeanMapper.map(wikiRepository, WikiRepositoryEntity.class);
        String id = wikiRepositoryDao.createRepository(wikiRepositoryEntity);

        initRepositoryDmRole(masterId, id);

        //构建索引
        WikiRepository entity = findRepository(id);
        dssClient.save(entity);

        MessageNoticePatch messageNoticePatch = new MessageNoticePatch();
        messageNoticePatch.setDomainId(id);
        messageNoticePatch.setUserList(List.of(masterId));
        messageDmNoticeService.initMessageDmNotice(messageNoticePatch);


        new Thread(() -> {
            try {
                SwardMessage swardMessage = new SwardMessage();
                swardMessage.setRpyId(id);
                swardMessage.setUserId(masterId);
                swardMessage.setNoticeId(SW_RPY_CREATE);
                swardMessage.setSendType(SW_RPY_CREATE);
                swardSendMessageService.sendMessage(swardMessage);
            }catch (Exception e){
                logger.error("发送消息失败", e);
                e.printStackTrace();
            }
        }).start();

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

        //构建索引
        WikiRepository entity = findRepository(id);
        dssClient.save(entity);
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
        wikiRepositoryDao.updateRepository(wikiRepositoryEntity);

        WikiRepository wikiRepository1 = findRepository(wikiRepositoryEntity.getId());

        dssClient.update(wikiRepository1);
        String masterId = LoginContext.getLoginId();
        new Thread(() -> {
            try {
                SwardMessage swardMessage = new SwardMessage();
                swardMessage.setRpyId(wikiRepositoryEntity.getId());
                swardMessage.setUserId(masterId);
                swardMessage.setRpyName(wikiRepository1.getName());
                swardMessage.setNoticeId(SW_RPY_UPDATE);
                swardMessage.setSendType(SW_RPY_UPDATE);
                swardSendMessageService.sendMessage(swardMessage);
            }catch (Exception e){
                logger.error("发送消息失败", e);
                e.printStackTrace();
            }
        }).start();
    }


    @Override
    public void updateRepositoryNoMessage(@NotNull @Valid WikiRepository wikiRepository) {
        WikiRepositoryEntity wikiRepositoryEntity = BeanMapper.map(wikiRepository, WikiRepositoryEntity.class);
        wikiRepositoryDao.updateRepository(wikiRepositoryEntity);

        WikiRepository wikiRepository1 = findRepository(wikiRepositoryEntity.getId());

        dssClient.update(wikiRepository1);
    }

    /**
     * 删除知识库
     * @param id
     */
    @Override
    public void deleteRepository(@NotNull String id) {

        WikiRepository wikiRepository = findRepository(id);

        String masterId = LoginContext.getLoginId();
        SwardMessage swardMessage = new SwardMessage();
        swardMessage.setRpyId(id);
        swardMessage.setUserId(masterId);
        swardMessage.setRpyName(wikiRepository.getName());
        swardMessage.setNoticeId(SW_RPY_DELETE);
        swardMessage.setSendType(SW_RPY_DELETE);
        try {
            swardSendMessageService.sendMessage(swardMessage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送消息失败", e);
        }

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

        joinTemplate.joinQuery(wikiRepositoryList,new String[]{"archivedUser","master","recycleUser"});

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
            joinTemplate.joinQuery(wikiRepository,new String[]{"archivedUser","master","recycleUser"});
        }

        return wikiRepository;
    }

    @Override
    public List<WikiRepository> findAllRepository() {
        List<WikiRepositoryEntity> wikiRepositoryEntityList =  wikiRepositoryDao.findAllRepository();

        List<WikiRepository> wikiRepositoryList =  BeanMapper.mapList(wikiRepositoryEntityList, WikiRepository.class);

        joinTemplate.joinQuery(wikiRepositoryList,new String[]{"archivedUser","master","recycleUser"});
        return wikiRepositoryList;
    }

    @Override
    public List<WikiRepository> findAllRepositoryNoQuery() {
        List<WikiRepositoryEntity> wikiRepositoryEntityList =  wikiRepositoryDao.findAllRepository();

        List<WikiRepository> wikiRepositoryList =  BeanMapper.mapList(wikiRepositoryEntityList, WikiRepository.class);

        return wikiRepositoryList;
    }

    @Override
    public List<WikiRepository> findRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {
        List<WikiRepositoryEntity> wikiRepositoryEntityList =  wikiRepositoryDao.findRepositoryList(wikiRepositoryQuery);

        List<WikiRepository> wikiRepositoryList =  BeanMapper.mapList(wikiRepositoryEntityList, WikiRepository.class);

        joinTemplate.joinQuery(wikiRepositoryList,new String[]{"archivedUser","master","recycleUser"});
        return wikiRepositoryList;
    }


    @Override
    public List<WikiRepository> findRepositoryListByUser(WikiRepositoryQuery wikiRepositoryQuery) {

        String userId = wikiRepositoryQuery.getUserId();
        if(StringUtils.isEmpty(userId)){
            userId = LoginContext.getLoginId();
        }

        String[] rpyIdList = findRepositoryIdListByUser(userId);

        String[] repositoryIds = wikiRepositoryQuery.getRepositoryIds();
        wikiRepositoryQuery.setRepositoryIds(rpyIdList);
        if(!Objects.isNull(repositoryIds) && repositoryIds.length > 0){
            String join = String.join(",", repositoryIds);
            String[] array = Arrays.stream(rpyIdList)
                    .filter(join::contains)
                    .toArray(String[]::new);
            wikiRepositoryQuery.setRepositoryIds(array);
        }

        // 修改排序参数
        List<WikiRepositoryEntity> wikiRepositoryEntityList = wikiRepositoryDao.findRecentRepositoryList(wikiRepositoryQuery);

        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(wikiRepositoryEntityList, WikiRepository.class);

        wikiRepositoryList = new ArrayList<>(
                wikiRepositoryList.stream()
                        .collect(Collectors.toMap(
                                WikiRepository::getId, // 按 id 去重
                                u -> u,                      // 值为对象
                                (u1, u2) -> u1,               // 如果重复，保留第一个
                                LinkedHashMap::new            // 用 LinkedHashMap 保证顺序
                        ))
                        .values()
        );

        joinTemplate.joinQuery(wikiRepositoryList,new String[]{"archivedUser","master","recycleUser"});

        return wikiRepositoryList;
    }

    @Override
    public Pagination<WikiRepository> findRepositoryPage(WikiRepositoryQuery wikiRepositoryQuery) {

        String userId = wikiRepositoryQuery.getUserId();
        if(StringUtils.isEmpty(userId)){
            userId = LoginContext.getLoginId();
        }

        String[] rpyIdList = findRepositoryIdListByUser(userId);
        wikiRepositoryQuery.setRepositoryIds(rpyIdList);

        Pagination<WikiRepositoryEntity>  pagination = wikiRepositoryDao.findRepositoryPage(wikiRepositoryQuery);

        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(pagination.getDataList(), WikiRepository.class);

        joinTemplate.joinQuery(wikiRepositoryList,new String[]{"archivedUser","master","recycleUser"});

        Map<String, Integer> repositoryDocNumber = findRepositoryDocNumber();

        wikiRepositoryList.stream().forEach(wikiRepository -> {
            Integer integer = repositoryDocNumber.get(wikiRepository.getId());
            if(Objects.isNull(integer)){
                integer = 0;
            }
            wikiRepository.setDocumentNum(integer);
        });

        return PaginationBuilder.build(pagination, wikiRepositoryList);
    }


    private String[] findRepositoryIdListByUser(String userId) {
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setUserId(userId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        String[] arr = dmUserList.stream().map(DmUser::getDomainId).toArray(String[]::new);
        List<String> rpyPubilcIdList = wikiRepositoryDao.findRpyPubilcIdList();

        // 合并并去重
        List<String> mergedList = Stream.concat(
                        Arrays.stream(arr),
                        rpyPubilcIdList.stream()
                ).distinct() // 去重
                .collect(Collectors.toList());
        return mergedList.stream().toArray(String[]::new);
    }


    @Override
    public List<WikiRepository> findAllRecentRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {
        String createUserId = LoginContext.getLoginId();
        wikiRepositoryQuery.setMasterId(createUserId);
        List<WikiRepositoryEntity> recentRepositoryList = wikiRepositoryDao.findRecentRepositoryList(wikiRepositoryQuery);
        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(recentRepositoryList, WikiRepository.class);
        joinTemplate.joinQuery(wikiRepositoryList,new String[]{"archivedUser","master","recycleUser"});

        return  wikiRepositoryList;
    }

    @Override
    public List<WikiRepository> findRecentRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {

        wikiRepositoryQuery.setMasterId(LoginContext.getLoginId());

        List<WikiRepositoryEntity> recentRepositoryList = wikiRepositoryDao.findRecentRepositoryList(wikiRepositoryQuery);

        recentRepositoryList = new ArrayList<>(
                recentRepositoryList.stream()
                        .collect(Collectors.toMap(
                                WikiRepositoryEntity::getId, // 按 id 去重
                                u -> u,                      // 值为对象
                                (u1, u2) -> u1,               // 如果重复，保留第一个
                                LinkedHashMap::new            // 用 LinkedHashMap 保证顺序
                        ))
                        .values()
        );

        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(recentRepositoryList, WikiRepository.class);
        // joinTemplate.joinQuery(wikiRepositoryList,new String[]{"archivedUser","master","recycleUser"});

        String repositoryId = wikiRepositoryQuery.getRepositoryId();
        // 如果在切换知识库的时候查找最近项目，去掉当前项目
        if(repositoryId != null){
            wikiRepositoryList = wikiRepositoryList.stream().
                    filter(wikiRepository -> !wikiRepository.getId().equals(repositoryId))
                    .collect(Collectors.toList());
        }

        List<String> wikiRepositoryIds = wikiRepositoryList.stream()
                .map(WikiRepository::getId)
                .filter(id -> id != null && !id.isEmpty()) // 去除 null 和 空字符串
                .distinct() // 去重
                .collect(Collectors.toList());

        if (!StringUtils.isEmpty(repositoryId)){
            wikiRepositoryIds.add(repositoryId);
        }
        int size = wikiRepositoryIds.size();

        // 判断查看的知识库是否小于5个
        wikiRepositoryQuery.setMasterId(null);
        List<WikiRepository> list = new ArrayList<>();
        if(size <= 5){
            wikiRepositoryQuery.setRepositoryIds(wikiRepositoryIds.toArray(String[]::new));
            list = findRepositoryListByUser(wikiRepositoryQuery);
        }else {
            List<String> strings = wikiRepositoryIds.subList(0, 5);
            wikiRepositoryQuery.setRepositoryIds(strings.toArray(String[]::new));
            List<WikiRepository> listByUser = findRepositoryListByUser(wikiRepositoryQuery);
            list.addAll(listByUser);
        }

        String repositoryIds = "(" + list.stream().map(item -> "'" + item.getId() + "'")
                .collect(Collectors.joining(", ")) + ")";
        List<Node> childrenNodeList = nodeService.findChildrenNodeList(repositoryIds);
        List<WikiRepository> returnList = new ArrayList<>();
        for (WikiRepository wikiRepository : list) {
            String id = wikiRepository.getId();
            if (!StringUtils.isEmpty(repositoryId) &&repositoryId.equals(id)) {
                continue;
            }

            List<Node> document = childrenNodeList.stream()
                    .filter(node -> node.getWikiRepository().getId().equals(id) && node.getType().equals("document"))
                    .collect(Collectors.toList());

            List<Node> category = childrenNodeList.stream()
                    .filter(node -> node.getWikiRepository().getId().equals(id) && node.getType().equals("category"))
                    .collect(Collectors.toList());

            wikiRepository.setDocumentNum(document.size());
            wikiRepository.setCategoryNum(category.size());

            returnList.add(wikiRepository);
        }

        return returnList;
    }

    @Override
    public List<WikiRepository> findFocusRepositoryList(WikiRepositoryQuery wikiRepositoryQuery) {
        String createUserId = LoginContext.getLoginId();
        wikiRepositoryQuery.setMasterId(createUserId);
        List<WikiRepositoryEntity> recentRepositoryList = wikiRepositoryDao.findFocusRepositoryList(wikiRepositoryQuery);

        List<WikiRepository> wikiRepositoryList = BeanMapper.mapList(recentRepositoryList, WikiRepository.class);
        if(!wikiRepositoryList.isEmpty()){
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
        joinTemplate.joinQuery(wikiRepositoryList,new String[]{"archivedUser","master","recycleUser"});

        return wikiRepositoryList;
    }

    @Override
    public Map<String, Integer> findRepositoryNum(WikiRepositoryQuery wikiRepositoryQuery) {
        HashMap<String, Integer> nums = new HashMap<>();
        List<WikiRepositoryEntity> focusRepositoryList = wikiRepositoryDao.findFocusRepositoryList(wikiRepositoryQuery);
        nums.put("focus", focusRepositoryList.size());

        Pagination<WikiRepository> repositoryListByUser = findRepositoryPage(wikiRepositoryQuery);
        nums.put("create", repositoryListByUser.getTotalRecord());

        wikiRepositoryQuery.setMasterId(null);
        Pagination<WikiRepository> repositoryListByUser1 = findRepositoryPage(wikiRepositoryQuery);
        nums.put("all", repositoryListByUser1.getTotalRecord());
        return  nums;

    }

    @Override
    public Map<String, Integer> findRepositoryDocNumber(){
        return wikiRepositoryDao.findRepositoryDocNumber();
    }



}