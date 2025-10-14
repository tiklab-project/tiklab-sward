package io.tiklab.sward.repository.service;


import io.tiklab.sward.support.support.model.kanassProject;
import io.tiklab.sward.support.support.service.SystemUrlService;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.sward.support.support.model.SystemUrl;
import io.tiklab.sward.support.support.model.SystemUrlQuery;
import io.tiklab.sward.support.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @pi.protocol: http
 * @pi.groupName: 项目管理， 弃用
 */
@Service
public class KanassServiceImpl implements KanassService {
    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    SystemUrlService systemUrlService;
    @Autowired
    HttpRequestUtil httpRequestUtil;

    String getSystemUrl(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        // systemUrlQuery.setName("kanass");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return url;
    }

    @Override
    public List<kanassProject> findAllProject() {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<kanassProject> kanassProjectList = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/project/findAllProject", null, kanassProject.class);

        return kanassProjectList;
    }
}
