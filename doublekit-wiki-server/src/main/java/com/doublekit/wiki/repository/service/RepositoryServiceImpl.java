package com.doublekit.wiki.repository.service;

import com.doublekit.dal.jpa.builder.deletelist.condition.DeleteCondition;
import com.doublekit.dal.jpa.builder.deletelist.conditionbuilder.DeleteBuilders;
import com.doublekit.dss.client.DssClient;
import com.doublekit.privilege.prjprivilege.service.DmPrjRoleService;
import com.doublekit.user.auth.passport.context.TicketContext;
import com.doublekit.user.auth.passport.context.TicketHolder;
import com.doublekit.user.auth.passport.model.Ticket;
import com.doublekit.user.user.model.DmUser;
import com.doublekit.user.user.model.User;
import com.doublekit.user.user.service.DmUserService;
import com.doublekit.wiki.category.dao.CategoryDao;
import com.doublekit.wiki.repository.dao.DocumentDao;
import com.doublekit.wiki.repository.dao.RepositoryDao;
import com.doublekit.wiki.repository.entity.RepositoryPo;
import com.doublekit.wiki.repository.model.Repository;
import com.doublekit.wiki.repository.model.RepositoryQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* RepositoryServiceImpl
*/
@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    RepositoryDao repositoryDao;

    @Autowired
    JoinQuery joinQuery;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    DocumentDao documentDao;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmPrjRoleService dmPrjRoleService;

    @Autowired
    DssClient dssClient;

    @Override
    public String createRepository(@NotNull @Valid Repository repository) {
        RepositoryPo repositoryPo = BeanMapper.map(repository, RepositoryPo.class);

        String id = repositoryDao.createRepository(repositoryPo);
        //初始化项目成员
        DmUser dmUser = new DmUser();
        dmUser.setDomainId(id);
        dmUser.setUser(new User().setId(findCreatUser()));
        dmUserService.createDmUser(dmUser);

        //初始化项目权限
        dmPrjRoleService.initDmPrjRoles(id,findCreatUser());

        //构建索引
        Repository entity = findRepository(id);
        dssClient.save(entity);
        return id;
    }

    @Override
    public void updateRepository(@NotNull @Valid Repository repository) {
        RepositoryPo repositoryPo = BeanMapper.map(repository, RepositoryPo.class);

        repositoryDao.updateRepository(repositoryPo);
    }

    @Override
    public void deleteRepository(@NotNull String id) {

        //删除相关联的目录和内容
        DeleteCondition deleteCondition = DeleteBuilders.instance().eq("repositoryId", id).get();

        documentDao.deleteDocument(deleteCondition);
        categoryDao.deleteCategory(deleteCondition);

        repositoryDao.deleteRepository(id);
    }

    @Override
    public Repository findOne(String id) {
        RepositoryPo repositoryPo = repositoryDao.findRepository(id);

        Repository repository = BeanMapper.map(repositoryPo, Repository.class);
        return repository;
    }

    @Override
    public List<Repository> findList(List<String> idList) {
        List<RepositoryPo> repositoryPoList =  repositoryDao.findRepositoryList(idList);

        List<Repository> repositoryList =  BeanMapper.mapList(repositoryPoList,Repository.class);
        return repositoryList;
    }

    @Override
    public Repository findRepository(@NotNull String id) {
        Repository repository = findOne(id);

        joinQuery.queryOne(repository);
        return repository;
    }

    @Override
    public List<Repository> findAllRepository() {
        List<RepositoryPo> repositoryPoList =  repositoryDao.findAllRepository();

        List<Repository> repositoryList =  BeanMapper.mapList(repositoryPoList,Repository.class);

        joinQuery.queryList(repositoryList);
        return repositoryList;
    }

    @Override
    public List<Repository> findRepositoryList(RepositoryQuery repositoryQuery) {
        List<RepositoryPo> repositoryPoList = repositoryDao.findRepositoryList(repositoryQuery);

        List<Repository> repositoryList = BeanMapper.mapList(repositoryPoList,Repository.class);

        joinQuery.queryList(repositoryList);

        return repositoryList;
    }

    @Override
    public Pagination<Repository> findRepositoryPage(RepositoryQuery repositoryQuery) {
        Pagination<Repository> pg = new Pagination<>();

        Pagination<RepositoryPo>  pagination = repositoryDao.findRepositoryPage(repositoryQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Repository> repositoryList = BeanMapper.mapList(pagination.getDataList(),Repository.class);

        joinQuery.queryList(repositoryList);

        pg.setDataList(repositoryList);
        return pg;
    }

    /**
     * 查询用户（创建人）id
     * @param
     */
    public String findCreatUser(){
        String ticketId = TicketHolder.get();
        Ticket ticket = TicketContext.get(ticketId);
        return ticket.getUserId();
    }

}