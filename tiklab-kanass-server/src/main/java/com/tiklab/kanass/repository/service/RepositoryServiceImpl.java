package com.tiklab.kanass.repository.service;

import com.tiklab.beans.BeanMapper;
import com.tiklab.core.page.Pagination;
import com.tiklab.core.page.PaginationBuilder;
import com.tiklab.dss.client.DssClient;
import com.tiklab.join.JoinTemplate;
import com.tiklab.privilege.role.service.DmRoleService;
import com.tiklab.rpc.annotation.Exporter;
import com.tiklab.user.user.model.DmUser;
import com.tiklab.user.user.model.DmUserQuery;
import com.tiklab.user.user.model.User;
import com.tiklab.user.user.service.DmUserService;
import com.tiklab.utils.context.LoginContext;
import com.tiklab.kanass.category.service.CategoryService;
import com.tiklab.kanass.document.model.DocumentRecent;
import com.tiklab.kanass.document.model.DocumentRecentQuery;
import com.tiklab.kanass.document.service.DocumentRecentService;
import com.tiklab.kanass.document.service.DocumentService;
import com.tiklab.kanass.repository.dao.RepositoryDao;
import com.tiklab.kanass.repository.entity.RepositoryEntity;
import com.tiklab.kanass.repository.model.Repository;
import com.tiklab.kanass.repository.model.RepositoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
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
       // dmUser.setUser(new User().setId(findCreatUser()));
        dmUser.setUser(new User().setId(masterId));
        dmUserService.createDmUser(dmUser);

        //初始化项目权限
//        dmRoleService.initDmRoles(id,findCreatUser());

        //构建索引
//        Repository entity = findRepository(id);
//        dssClient.save(entity);
        return id;
    }

    @Override
    public void updateRepository(@NotNull @Valid Repository repository) {
        RepositoryEntity repositoryEntity = BeanMapper.map(repository, RepositoryEntity.class);

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
        List<RepositoryEntity> repositoryEntityList = repositoryDao.findRepositoryList(repositoryQuery);

        List<Repository> repositoryList = BeanMapper.mapList(repositoryEntityList,Repository.class);

        joinTemplate.joinQuery(repositoryList);

        return repositoryList;
    }

    @Override
    public Pagination<Repository> findRepositoryPage(RepositoryQuery repositoryQuery) {

        String userId = LoginContext.getLoginId();
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setUserId(userId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> collect = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());
        String[] arr = collect.toArray(new String[collect.size()]);
        repositoryQuery.setRepositoryIds(arr);

        Pagination<RepositoryEntity>  pagination = repositoryDao.findRepositoryPage(repositoryQuery);

        List<Repository> repositoryList = BeanMapper.mapList(pagination.getDataList(),Repository.class);

        joinTemplate.joinQuery(repositoryList);

        return PaginationBuilder.build(pagination,repositoryList);
    }

    /**
     * 查询用户（创建人）id
     * @param
     */
//    public String findCreatUser(){
//        String ticketId = TicketHolder.get();
//        Ticket ticket = TicketContext.get(ticketId);
//        return ticket.getUserId();
//    }

}