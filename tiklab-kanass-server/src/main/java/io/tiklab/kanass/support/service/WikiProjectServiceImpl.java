package io.tiklab.kanass.support.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.support.model.*;
import io.tiklab.kanass.support.util.HttpRequestUtil;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class WikiProjectServiceImpl implements WikiProjectService {
    @Autowired
    SystemUrlService systemUrlService;

    @Autowired
    HttpRequestUtil httpRequestUtil;

    String getSystemUrl(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("teamwire");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return url;
    }


    @Override
    public List<Project> findAllProject() {

        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<Project> allProject = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/project/findAllProject", null, Project.class);


//        List<WikiProject> wikiProjectList = new ArrayList<>();
//        for (Project project : allProject) {
//            WikiProject wikiProject = new WikiProject();
//            wikiProject.setId(project.getId());
//            wikiProject.setProjectName(project.getProjectName());
//            wikiProject.setProjectTypeName(project.getProjectType().getName());
//            wikiProject.setMasterName(project.getMaster().getName());
//            wikiProjectList.add(wikiProject);
//        }

        return allProject;
    }

    @Override
    public Pagination<WorkItem> findWorkItemPage(WorkItemQuery workItemQuery) {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        Pagination<WorkItem> workItemPage = httpRequestUtil.requestPostPage(httpHeaders, systemUrl + "/api/workItem/findWorkItemPage", workItemQuery, WorkItem.class);


        return workItemPage;
    }

    @Override
    public List<WikiWorkType> findWorkTypeList(WikiWorkItemQuery wikiWorkItemQuery) {
        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setProjectId(wikiWorkItemQuery.getProjectId());

        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<WorkTypeDm> workTypeDmList = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/workTypeDm/findWorkTypeDmList", workTypeDmQuery, WorkTypeDm.class);


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

        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<DmUser> dmUserList = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/dmUser/findDmUserList", dmUserQuery, DmUser.class);

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
    public WorkItem findWorkItem(String workItemId) {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        MultiValueMap param = new LinkedMultiValueMap<>();
        param.add("id",workItemId);
        WorkItem workItem = httpRequestUtil.requestPost(httpHeaders, systemUrl + "/api/workItem/findWorkItem", param, WorkItem.class);

//
//        WikiWorkItem wikiWorkItem = new WikiWorkItem();
//        if(!ObjectUtils.isEmpty(workItem)){
//
//            wikiWorkItem.setId(workItem.getId());
//            wikiWorkItem.setTitle(workItem.getTitle());
//            wikiWorkItem.setProjectName(workItem.getProject().getProjectName());
//            wikiWorkItem.setProjectId(workItem.getProject().getId());
//            wikiWorkItem.setWorkTypeName(workItem.getWorkTypeSys().getName());
//            wikiWorkItem.setWorkStatusName(workItem.getWorkStatusNode().getName());
//        }else {
//            wikiWorkItem.setTitle("事项已被删除");
//        }


        return workItem;
    }


}
