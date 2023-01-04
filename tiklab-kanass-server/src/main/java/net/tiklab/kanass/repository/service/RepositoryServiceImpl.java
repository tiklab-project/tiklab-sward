package net.tiklab.kanass.repository.service;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.dss.client.DssClient;
import net.tiklab.join.JoinTemplate;
import net.tiklab.kanass.category.support.OpLogTemplateCategory;
import net.tiklab.kanass.document.model.Document;
import net.tiklab.kanass.repository.support.OpLogTemplateRepository;
import net.tiklab.logging.modal.Logging;
import net.tiklab.logging.modal.LoggingType;
import net.tiklab.logging.service.LoggingByTemplService;
import net.tiklab.privilege.role.service.DmRoleService;
import net.tiklab.rpc.annotation.Exporter;
import net.tiklab.user.user.model.DmUser;
import net.tiklab.user.user.model.DmUserQuery;
import net.tiklab.user.user.model.User;
import net.tiklab.user.user.service.DmUserService;
import net.tiklab.user.user.service.UserService;
import net.tiklab.utils.context.LoginContext;
import net.tiklab.kanass.category.service.CategoryService;
import net.tiklab.kanass.document.model.DocumentRecent;
import net.tiklab.kanass.document.model.DocumentRecentQuery;
import net.tiklab.kanass.document.service.DocumentRecentService;
import net.tiklab.kanass.document.service.DocumentService;
import net.tiklab.kanass.repository.dao.RepositoryDao;
import net.tiklab.kanass.repository.entity.RepositoryEntity;
import net.tiklab.kanass.repository.model.Repository;
import net.tiklab.kanass.repository.model.RepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* RepositoryServiceImpl
*/
@Service
@Exporter
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    RepositoryDao repositoryDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    CategoryService categoryService;

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
    LoggingByTemplService loggingByTemplService;

    @Value("${base.url:null")
    String baseUrl;

    void creatDynamic( Map<String, String> content){
        Logging log = new Logging();
        log.setBgroup("kanass");

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);
        content.put("master", user.getName());
        content.put("updateTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        log.setLoggingTemplateId(OpLogTemplateRepository.TEAMWIRE_LOGTEMPLATE_REPOSITORYADD);

        LoggingType opLogType = new LoggingType();
        opLogType.setId(OpLogTemplateRepository.TEAMWIRE_LOGTYPE_REPOSITORYADD);
        log.setActionType(opLogType);

        log.setModule("repository");
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
        content.put("createUserIcon",user.getName().substring( 0, 1));
        log.setContent(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        loggingByTemplService.createLog(log);
    }

    @Override
    public String createRepository(@NotNull @Valid Repository repository) {
        Date date = new java.sql.Date(new Date().getTime());
        repository.setCreateTime(date);
        RepositoryEntity repositoryEntity = BeanMapper.map(repository, RepositoryEntity.class);

        String id = repositoryDao.createRepository(repositoryEntity);


        String masterId = repository.getMaster().getId();
        //初始化项目成员
        DmUser dmUser = new DmUser();
        dmUser.setDomainId(id);
        dmUser.setTagValue(masterId);
       // dmUser.setUser(new User().setId(findCreatUser()));
        User user = new User();
        user.setId(masterId);

        dmUser.setUser(user);
        dmUserService.createDmUser(dmUser);

        //初始化项目权限
        dmRoleService.initDmRoles(id,masterId, "kanass");

        Repository repository1 = findRepository(id);
        Map<String, String> content = new HashMap<>();
        content.put("repositoryId", repository1.getId());
        content.put("repositoryName", repository1.getName());

        creatDynamic(content);

        //构建索引
//        Repository entity = findRepository(id);
//        dssClient.save(entity);
        return id;
    }

    @Override
    public void updateRepository(@NotNull @Valid Repository repository) {
        RepositoryEntity repositoryEntity = BeanMapper.map(repository, RepositoryEntity.class);
        Repository repository1 = findRepository(repositoryEntity.getId());
        Map<String, String> content = new HashMap<>();
        content.put("repositoryId", repository1.getId());
        content.put("repositoryName", repository1.getName());

        creatDynamic(content);
        repositoryDao.updateRepository(repositoryEntity);
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
        categoryService.deleteCategory(id);

        repositoryDao.deleteRepository(id);
    }

    @Override
    public Repository findOne(String id) {
        RepositoryEntity repositoryEntity = repositoryDao.findRepository(id);

        Repository repository = BeanMapper.map(repositoryEntity, Repository.class);
        return repository;
    }

    @Override
    public List<Repository> findList(List<String> idList) {
        List<RepositoryEntity> repositoryEntityList =  repositoryDao.findRepositoryList(idList);

        List<Repository> repositoryList =  BeanMapper.mapList(repositoryEntityList,Repository.class);
        return repositoryList;
    }

    @Override
    public Repository findRepository(@NotNull String id) {
        Repository repository = findOne(id);

        joinTemplate.joinQuery(repository);
        return repository;
    }

    @Override
    public List<Repository> findAllRepository() {
        List<RepositoryEntity> repositoryEntityList =  repositoryDao.findAllRepository();

        List<Repository> repositoryList =  BeanMapper.mapList(repositoryEntityList,Repository.class);

        joinTemplate.joinQuery(repositoryList);
        return repositoryList;
    }

    @Override
    public List<Repository> findRepositoryList(RepositoryQuery repositoryQuery) {

        String userId = LoginContext.getLoginId();
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setTagValue(userId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> collect = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());
        String[] arr = collect.toArray(new String[collect.size()]);
        repositoryQuery.setRepositoryIds(arr);

        List<RepositoryEntity> repositoryEntityList = repositoryDao.findRepositoryList(repositoryQuery);

        List<Repository> repositoryList = BeanMapper.mapList(repositoryEntityList,Repository.class);

        joinTemplate.joinQuery(repositoryList);

        return repositoryList;
    }

    @Override
    public Pagination<Repository> findRepositoryPage(RepositoryQuery repositoryQuery) {

        String userId = LoginContext.getLoginId();
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setTagValue(userId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> collect = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());
        String[] arr = collect.toArray(new String[collect.size()]);
        repositoryQuery.setRepositoryIds(arr);

        Pagination<RepositoryEntity>  pagination = repositoryDao.findRepositoryPage(repositoryQuery);

        List<Repository> repositoryList = BeanMapper.mapList(pagination.getDataList(),Repository.class);

        joinTemplate.joinQuery(repositoryList);

        return PaginationBuilder.build(pagination,repositoryList);
    }

    @Override
    public List<Repository> findRecentRepositoryList(DocumentRecentQuery documentRecentQuery) {
        String createUserId = LoginContext.getLoginId();
        documentRecentQuery.setMasterId(createUserId);
        List<RepositoryEntity> recentRepositoryList = repositoryDao.findRecentRepositoryList(documentRecentQuery);

        List<Repository> repositoryList = BeanMapper.mapList(recentRepositoryList,Repository.class);

        joinTemplate.joinQuery(repositoryList);

        return repositoryList;
    }

    @Override
    public List<Repository> findFocusRepositoryList(RepositoryQuery repositoryQuery) {
        String createUserId = LoginContext.getLoginId();
        repositoryQuery.setMasterId(createUserId);
        List<RepositoryEntity> recentRepositoryList = repositoryDao.findFocusRepositoryList(repositoryQuery);

        List<Repository> repositoryList = BeanMapper.mapList(recentRepositoryList,Repository.class);

        joinTemplate.joinQuery(repositoryList);

        return repositoryList;
    }


}