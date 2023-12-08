
package io.thoughtware.sward.support.service;

import io.thoughtware.sward.support.model.*;
import io.tiklab.core.page.Pagination;
import io.tiklab.sward.support.model.*;

import java.util.List;

/**
* 图标服务接口
*/
public interface WikiProjectService {

    List<Project> findAllProject();

    Pagination<WorkItem> findWorkItemPage(WorkItemQuery workItemQuery);

    List<WikiWorkType> findWorkTypeList(WikiWorkItemQuery wikiWorkItemQuery);

    List<WikiDmUser> findDmUserList(WikiWorkItemQuery wikiWorkItemQuery);

    WorkItem findWorkItem(String workItemId);
}