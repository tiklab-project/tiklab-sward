
package io.tiklab.sward.support.support.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.sward.support.support.model.*;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;

import java.util.List;

/**
* 图标服务接口
*/
public interface WikiProjectService {

    Pagination<kanassProject> findKanassProjectPage(kanassProjectQuery  kanassProjectQuery);

    List<kanassProject> findKanassProjectList(kanassProjectQuery  kanassProjectQuery);

    Pagination<KanassWorkItem> findKanassWorkItemPage(KanassWorkItemQuery kanassWorkItemQuery);

    List<KanassWorkItem> findKanassWorkItemList(KanassWorkItemQuery kanassWorkItemQuery);

    List<KanassItemType> findKanassWorkTypeList(KanassWorkTypeDmQuery kanassWorkTypeDmQuery);

    Pagination<DmUser> findDmUserPage(DmUserQuery dmUserQuery);

    List<DmUser> findDmUserList(DmUserQuery dmUserQuery);

    KanassWorkItem findKanassWorkItem(String workItemId);
}