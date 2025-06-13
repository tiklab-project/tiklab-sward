package io.tiklab.sward.support.service;


import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.model.WorkTypeDm;
import io.tiklab.kanass.workitem.model.WorkTypeDmQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.kanass.workitem.service.WorkTypeDmService;
import io.tiklab.sward.support.model.*;
import io.tiklab.sward.support.util.HttpRequestUtil;
import io.tiklab.core.page.Pagination;
import io.tiklab.sward.support.util.SwardRpc;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * kanass 的项目与事项接口查找
 */
@Service
public class WikiProjectServiceImpl implements WikiProjectService {
    @Autowired
    SystemUrlService systemUrlService;

    @Autowired
    HttpRequestUtil httpRequestUtil;

    String findSystemUrl(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("kanass");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);

        if (Objects.isNull(systemUrlList) || systemUrlList.isEmpty()){
            throw new ApplicationException("当前未配置kanass系统地址，请在系统设置-->集成与开发-->服务集成中添加Kanass系统地址! ");
        }
        return systemUrlList.get(0).getSystemUrl();
    }

    @Override
    public Pagination<kanassProject> findKanassProjectPage(kanassProjectQuery  kanassProjectQuery) {
        SwardRpc swardRpc = SwardRpc.instance();
        ProjectService serviceRpc = swardRpc.findServiceRpc(ProjectService.class, findSystemUrl());
        ProjectQuery projectQuery = copyFields(kanassProjectQuery, new ProjectQuery());
        Pagination<Project> projectPage = serviceRpc.findProjectPage(projectQuery);
        List<Project> dataList = projectPage.getDataList();
        List<kanassProject> kanassProjectList = new ArrayList<>();
        for (Project project : dataList) {
            kanassProject kanassProject = copyFields(project, new kanassProject());
            kanassProjectList.add(kanassProject);
        }
        return PaginationBuilder.build(projectPage,kanassProjectList);
    }


    @Override
    public List<kanassProject> findKanassProjectList(kanassProjectQuery  kanassProjectQuery) {
        SwardRpc swardRpc = SwardRpc.instance();
        ProjectService serviceRpc = swardRpc.findServiceRpc(ProjectService.class, findSystemUrl());
        ProjectQuery projectQuery = copyFields(kanassProjectQuery, new ProjectQuery());
        List<Project> dataList = serviceRpc.findProjectList(projectQuery);
        List<kanassProject> kanassProjectList = new ArrayList<>();
        for (Project project : dataList) {
            kanassProject kanassProject = copyFields(project, new kanassProject());
            kanassProjectList.add(kanassProject);
        }
        return kanassProjectList;
    }

    @Override
    public Pagination<KanassWorkItem> findKanassWorkItemPage(KanassWorkItemQuery kanassWorkItemQuery) {
        SwardRpc swardRpc = SwardRpc.instance();
        WorkItemService serviceRpc = swardRpc.findServiceRpc(WorkItemService.class, findSystemUrl());
        WorkItemQuery workItemQuery = copyFields(kanassWorkItemQuery, new WorkItemQuery());
        Pagination<WorkItem> workItemPage = serviceRpc.findWorkItemPage(workItemQuery);

        List<KanassWorkItem> workItemList = new ArrayList<>();
        List<WorkItem> dataList = workItemPage.getDataList();
        for (WorkItem workItem : dataList) {
            KanassWorkItem kanassWorkItem = copyFields(workItem, new KanassWorkItem());
            workItemList.add(kanassWorkItem);
        }
        return PaginationBuilder.build(workItemPage,workItemList);
    }


    @Override
    public List<KanassWorkItem> findKanassWorkItemList(KanassWorkItemQuery kanassWorkItemQuery) {
        WorkItemService serviceRpc = SwardRpc.instance().findServiceRpc(WorkItemService.class, findSystemUrl());
        WorkItemQuery workItemQuery = copyFields(kanassWorkItemQuery, new WorkItemQuery());
        List<WorkItem> dataList = serviceRpc.findWorkItemList(workItemQuery);
        List<KanassWorkItem> workItemList = new ArrayList<>();
        for (WorkItem workItem : dataList) {
            KanassWorkItem kanassWorkItem = copyFields(workItem, new KanassWorkItem());
            workItemList.add(kanassWorkItem);
        }
        return workItemList;
    }


    @Override
    public List<KanassItemType> findKanassWorkTypeList(KanassWorkTypeDmQuery kanassWorkTypeDmQuery) {
        WorkTypeDmService serviceRpc = SwardRpc.instance().findServiceRpc(WorkTypeDmService.class, findSystemUrl());

        WorkTypeDmQuery workTypeDmQuery = copyFields(kanassWorkTypeDmQuery, new WorkTypeDmQuery());

        List<WorkTypeDm> workTypeDmList = serviceRpc.findWorkTypeDmList(workTypeDmQuery);
        List<KanassItemType> kanassItemTypeList = new ArrayList<>();
        for (WorkTypeDm workTypeDm : workTypeDmList) {
            KanassItemType kanassItemType = copyFields(workTypeDm, new KanassItemType());
            kanassItemTypeList.add(kanassItemType);
        }

        return kanassItemTypeList;
    }

    @Override
    public Pagination<DmUser> findDmUserPage(DmUserQuery dmUserQuery) {
        DmUserService serviceRpc = SwardRpc.instance().findServiceRpc(DmUserService.class, findSystemUrl());
        return serviceRpc.findDmUserPage(dmUserQuery);
    }

    @Override
    public List<DmUser> findDmUserList(DmUserQuery dmUserQuery) {
        DmUserService serviceRpc = SwardRpc.instance().findServiceRpc(DmUserService.class, findSystemUrl());
        return serviceRpc.findDmUserList(dmUserQuery);
    }

    @Override
    public KanassWorkItem findKanassWorkItem(String workItemId) {
        WorkItemService serviceRpc = SwardRpc.instance().findServiceRpc(WorkItemService.class, findSystemUrl());
        WorkItem workItem = serviceRpc.findWorkItem(workItemId);
        return copyFields(workItem, new KanassWorkItem());
    }


    public static <S, T> T copyFields(S source, T target) {
        String jsonString = JSONObject.toJSONString(source);
       return (T) JSONObject.parseObject(jsonString, target.getClass());


        // if (source == null || target == null) {
        //     throw new IllegalArgumentException("source 和 target 不能为空");
        // }
        //
        // // 将 target 的字段缓存起来，提高效率
        // Map<String, Field> targetFieldMap = new HashMap<>();
        // Class<?> targetClass = target.getClass();
        // for (Field field : targetClass.getDeclaredFields()) {
        //     field.setAccessible(true);
        //     targetFieldMap.put(field.getName(), field);
        // }
        //
        // Class<?> sourceClass = source.getClass();
        // for (Field sourceField : sourceClass.getDeclaredFields()) {
        //     sourceField.setAccessible(true);
        //     Object value;
        //     try {
        //         value = sourceField.get(source);
        //         if (value == null) {
        //             continue;
        //         }
        //         Field targetField = targetFieldMap.get(sourceField.getName());
        //         if (targetField != null && targetField.getName().equals(sourceField.getName())) {
        //
        //             Class<?> componentType = targetField.getType();
        //             if (componentType.isAssignableFrom(String.class) || componentType.isAssignableFrom(Integer.class)){
        //                 targetField.set(target, value);
        //             } else {
        //                 targetField.set(target, value);
        //             }
        //
        //             // Class<?> type = sourceField.getType();
        //
        //             targetField.set(target, value);
        //         }
        //
        //         // if (targetField != null && targetField.getType().isAssignableFrom(sourceField.getType())) {
        //         //     targetField.set(target, value);
        //         // }
        //
        //     } catch (IllegalAccessException e) {
        //         throw new RuntimeException("字段访问异常: " + sourceField.getName(), e);
        //     }
        // }
        // return target;
    }


}
