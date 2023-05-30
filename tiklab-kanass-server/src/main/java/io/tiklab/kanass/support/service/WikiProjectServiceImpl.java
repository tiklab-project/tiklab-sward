package io.tiklab.kanass.support.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.document.service.DocumentService;
import io.tiklab.kanass.support.model.*;
import io.tiklab.kanass.support.support.SystemId;
import io.tiklab.kanass.support.util.RpcClientTeamWireUtil;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;
import io.tiklab.teamwire.workitem.model.WorkTypeDm;
import io.tiklab.teamwire.workitem.model.WorkTypeDmQuery;
import io.tiklab.teamwire.workitem.service.WorkItemService;
import io.tiklab.teamwire.workitem.service.WorkTypeDmService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WikiProjectServiceImpl implements WikiProjectService {
    @Autowired
    SystemUrlService systemUrlService;

    ProjectService projectServiceRpc(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("teamwire");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(ProjectService.class, new FixedLookup(url));
    }

    WorkItemService workItemServiceRpc(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("teamwire");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(WorkItemService.class, new FixedLookup(url));
    }

    WorkTypeDmService workTypeDmServiceRpc(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("teamwire");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(WorkTypeDmService.class, new FixedLookup(url));
    }

    DmUserService dmUserServiceRpc(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("teamwire");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(DmUserService.class, new FixedLookup(url));
    }

    @Override
    public List<WikiProject> findAllProject() {
        List<Project> allProject = projectServiceRpc().findAllProject();
        List<WikiProject> wikiProjectList = new ArrayList<>();
        for (Project project : allProject) {
            WikiProject wikiProject = new WikiProject();
            wikiProject.setId(project.getId());
            wikiProject.setProjectName(project.getProjectName());
            wikiProject.setProjectTypeName(project.getProjectType().getName());
            wikiProject.setMasterName(project.getMaster().getName());
            wikiProjectList.add(wikiProject);
        }

        return wikiProjectList;
    }

    @Override
    public Pagination<WikiWorkItem> findWorkItemPage(WikiWorkItemQuery wikiWorkItemQuery) {
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setProjectId(wikiWorkItemQuery.getProjectId());
        workItemQuery.setWorkTypeId(wikiWorkItemQuery.getWorkTypeId());
        workItemQuery.setPageParam(wikiWorkItemQuery.getPageParam());
        Pagination<WorkItem> workItemPage = workItemServiceRpc().findWorkItemPage(workItemQuery);

        Pagination<WikiWorkItem> wikiWorkItemPagination = new Pagination<>();

        wikiWorkItemPagination.setBeginIndex(workItemPage.getBeginIndex());
        wikiWorkItemPagination.setCurrentPage(workItemPage.getCurrentPage());
        wikiWorkItemPagination.setEndIndex(workItemPage.getEndIndex());
        wikiWorkItemPagination.setPageSize(workItemPage.getPageSize());
        wikiWorkItemPagination.setTotalPage(workItemPage.getTotalPage());
        wikiWorkItemPagination.setTotalRecord(workItemPage.getTotalRecord());

        List<WikiWorkItem> wikiWorkItemList = new ArrayList<>();
        for (WorkItem workItem : workItemPage.getDataList()) {
            WikiWorkItem wikiWorkItem = new WikiWorkItem();
            wikiWorkItem.setId(workItem.getId());
            wikiWorkItem.setTitle(workItem.getTitle());
            wikiWorkItem.setProjectName(workItem.getProject().getProjectName());
            wikiWorkItem.setWorkTypeName(workItem.getWorkTypeSys().getName());
            wikiWorkItem.setWorkStatusName(workItem.getWorkStatusNode().getName());
            wikiWorkItem.setProjectId(workItem.getProject().getId());
            wikiWorkItemList.add(wikiWorkItem);
        }
        wikiWorkItemPagination.setDataList(wikiWorkItemList);
        return wikiWorkItemPagination;
    }

    @Override
    public List<WikiWorkType> findWorkTypeList(WikiWorkItemQuery wikiWorkItemQuery) {
        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setProjectId(wikiWorkItemQuery.getProjectId());
        List<WorkTypeDm> workTypeDmList = workTypeDmServiceRpc().findWorkTypeDmList(workTypeDmQuery);

        List<WikiWorkType> wikiWorkTypeList = new ArrayList<>();
        for (WorkTypeDm workTypeDm : workTypeDmList) {
            WikiWorkType wikiWorkType = new WikiWorkType();
            wikiWorkType.setProjectId(workTypeDm.getProjectId());
            wikiWorkType.setName(workTypeDm.getWorkType().getName());
            wikiWorkType.setId(workTypeDm.getWorkType().getId());

            wikiWorkTypeList.add(wikiWorkType);
        }

        return wikiWorkTypeList;
    }

    @Override
    public List<WikiDmUser> findDmUserList(WikiWorkItemQuery wikiWorkItemQuery) {
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(wikiWorkItemQuery.getProjectId());

        List<DmUser> dmUserList = dmUserServiceRpc().findDmUserList(dmUserQuery);

        List<WikiDmUser> wikiDmUserList = new ArrayList<>();
        for (DmUser dmUser : dmUserList) {
            WikiDmUser wikiDmUser = new WikiDmUser();
            wikiDmUser.setProjectId(dmUser.getDomainId());
            wikiDmUser.setName(dmUser.getUser().getName());
            wikiDmUser.setId(dmUser.getUser().getId());

            wikiDmUserList.add(wikiDmUser);
        }

        return wikiDmUserList;
    }

    @Override
    public WikiWorkItem findWorkItem(String workItemId) {
        WorkItem workItem = workItemServiceRpc().findWorkItem(workItemId);

        WikiWorkItem wikiWorkItem = new WikiWorkItem();
        wikiWorkItem.setId(workItem.getId());
        wikiWorkItem.setTitle(workItem.getTitle());
        wikiWorkItem.setProjectName(workItem.getProject().getProjectName());
        wikiWorkItem.setProjectId(workItem.getProject().getId());
        wikiWorkItem.setWorkTypeName(workItem.getWorkTypeSys().getName());
        wikiWorkItem.setWorkStatusName(workItem.getWorkStatusNode().getName());
        return wikiWorkItem;
    }


}
