package io.tiklab.sward.support.support.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.sward.support.support.dao.RecentDao;
import io.tiklab.sward.support.support.entity.RecentEntity;
import io.tiklab.sward.support.support.model.Recent;
import io.tiklab.sward.support.support.model.RecentQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* 最近查看接口
*/
@Service
public class RecentServiceImpl implements RecentService {

    @Autowired
    public RecentDao recentDao;

    @Autowired
    public JoinTemplate joinTemplate;

    @Override
    public String createRecent(@NotNull @Valid Recent recent) {
        recent.setRecentTime(new Timestamp(System.currentTimeMillis()));
        User user = new User();
        user.setId(LoginContext.getLoginId());
        recent.setMaster(user);
        String id;
        // 处理之前存储数据多于5条的情况
        String model = recent.getModel();
        id = switch (model) {
            case "repository" -> createRecentByCondition(recent, 5);
            case "document" -> createRecentByCondition(recent, 10);
            default -> createRecentByCondition(recent, 10);
        };
        return id;
    }

    public String createRecentByCondition(Recent recent, int num){
        String id = "";
        RecentQuery recentQuery = new RecentQuery();
        String modelId = recent.getModelId();
        String model = recent.getModel();
        recentQuery.setModel(model);
        recentQuery.setMasterId(LoginContext.getLoginId());
        List<Recent> recentList = findRecentList(recentQuery);
        if(!recentList.isEmpty()){
            List<Recent> recentList2  = new ArrayList<>();
            // 如果大于应该存在的条数，删掉多于的
            if(recentList.size() > num){
                List<Recent> recentList1 = recentList.subList(num, recentList.size());
                if(!recentList1.isEmpty()){
                    String ids = "(" + recentList1.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";
                    recentDao.deleteRecentByIds(ids);
                }
                recentList2 = recentList1.subList(0, num);
            }else {
                // 如果小于等于，则获取应该存在的
                recentList2 = recentList.subList(0, recentList.size());
            }

            int size = recentList2.size();
            if(size > 0){
                List<Recent> recentList3 = recentList2.stream()
                        .filter(recent1 -> recent1.getModelId().equals(modelId))
                        .collect(Collectors.toList());
                if(!recentList3.isEmpty()){
                    Recent recent1 = recentList3.get(0);
                    id = recent1.getId();
                    recent.setId(recent1.getId());
                    updateRecent(recent);
                }else {
                    RecentEntity recentEntity = BeanMapper.map(recent, RecentEntity.class);
                    id = recentDao.createRecent(recentEntity);
                    Recent recent1 = recentList2.get(size - 1);
                    // 如果刚好5条，删除最早的那一条
                    if(size == num){
                        deleteRecent(recent1.getId());
                    }
                }
            }
        }else {
            RecentEntity recentEntity = BeanMapper.map(recent, RecentEntity.class);
            id = recentDao.createRecent(recentEntity);
        }
        return id;
    }

    @Override
    public void updateRecent(@NotNull @Valid Recent recent) {
        RecentEntity recentEntity = BeanMapper.map(recent, RecentEntity.class);

        recentDao.updateRecent(recentEntity);
    }

    @Override
    public void deleteRecent(@NotNull String id) {
        recentDao.deleteRecent(id);
    }

    @Override
    public void deleteRecnetByCondition(RecentQuery recentQuery) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(RecentEntity.class)
                .eq("modelId", recentQuery.getModelId())
                .in("modelId", recentQuery.getModelIds())
                .eq("repositoryId", recentQuery.getRepositoryId())
                .get();
        recentDao.deleteRecnetByCondition(deleteCondition);

    }


    @Override
    public Recent findOne(String id) {
        RecentEntity recentEntity = recentDao.findRecent(id);

        return BeanMapper.map(recentEntity, Recent.class);
    }

    @Override
    public List<Recent> findList(List<String> idList) {
        List<RecentEntity> recentEntityList =  recentDao.findRecentList(idList);

        return BeanMapper.mapList(recentEntityList, Recent.class);
    }

    @Override
    public Recent findRecent(@NotNull String id) {
        Recent recent = findOne(id);

        joinTemplate.joinQuery(recent,new  String[]{"wikiRepository","master","node"});

        return recent;
    }

    @Override
    public List<Recent> findAllRecent() {
        List<RecentEntity> recentEntityList =  recentDao.findAllRecent();

        List<Recent> recentList =  BeanMapper.mapList(recentEntityList, Recent.class);

        joinTemplate.joinQuery(recentList,new  String[]{"wikiRepository","master","node"});

        return recentList;
    }

    @Override
    public List<Recent> findRecentList(RecentQuery recentQuery) {
        List<RecentEntity> recentEntityList = recentDao.findRecentList(recentQuery);

        List<Recent> recentList = BeanMapper.mapList(recentEntityList, Recent.class);

        joinTemplate.joinQuery(recentList,new  String[]{"wikiRepository","master","node"});

        List<Recent> review = recentList.stream()
                .filter(recent -> !Objects.isNull(recent.getNode()))
                .filter(recent -> !Objects.isNull(recent.getNode().getMaster()))
                .sorted(Comparator.comparing(Recent::getRecentTime).reversed())
                // .filter(recent -> recent.getNode().getMaster().getId().equals(recent.getMaster().getId()))
                .collect(Collectors.toList());

        return review;
    }

    @Override
    public Pagination<Recent> findRecentPage(RecentQuery recentQuery) {
        Pagination<RecentEntity>  pagination = recentDao.findRecentPage(recentQuery);

        List<Recent> recentList = BeanMapper.mapList(pagination.getDataList(), Recent.class);

        joinTemplate.joinQuery(recentList,new  String[]{"wikiRepository","master","node"});

        return PaginationBuilder.build(pagination, recentList);
    }
}