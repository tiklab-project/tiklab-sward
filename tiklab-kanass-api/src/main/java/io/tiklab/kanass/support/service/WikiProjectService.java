
package io.tiklab.kanass.support.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.support.model.*;

import java.util.List;

/**
* 图标服务接口
*/
public interface WikiProjectService {

    List<WikiProject> findAllProject();

    Pagination<WikiWorkItem> findWorkItemPage(WikiWorkItemQuery wikiWorkItemQuery);

    List<WikiWorkType> findWorkTypeList(WikiWorkItemQuery wikiWorkItemQuery);

    List<WikiDmUser> findDmUserList(WikiWorkItemQuery wikiWorkItemQuery);

    WikiWorkItem findWorkItem(String workItemId);
}