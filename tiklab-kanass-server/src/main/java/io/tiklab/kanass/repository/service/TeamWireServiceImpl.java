package io.tiklab.kanass.repository.service;


import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.kanass.repository.controller.TeamWireController;
import io.tiklab.kanass.support.model.Project;
import io.tiklab.kanass.support.model.SystemUrl;
import io.tiklab.kanass.support.model.SystemUrlQuery;
import io.tiklab.kanass.support.service.SystemUrlService;
import io.tiklab.kanass.support.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @pi.protocol: http
 * @pi.groupName: 项目管理
 */
@Service
public class TeamWireServiceImpl implements TeamWireService {
    @Autowired
    JpaTemplate jpaTemplate;

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
        List<Project> projectList = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/project/findAllProject", null, Project.class);

        return projectList;
    }
}
