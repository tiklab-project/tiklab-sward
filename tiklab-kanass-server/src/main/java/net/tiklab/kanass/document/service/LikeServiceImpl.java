package net.tiklab.kanass.document.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.rpc.annotation.Exporter;
import net.tiklab.kanass.document.dao.LikeDao;
import net.tiklab.kanass.document.entity.LikeEntity;
import net.tiklab.kanass.document.model.Like;
import net.tiklab.kanass.document.model.LikeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* LikeServiceImpl
*/
@Service
@Exporter
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeDao likeDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createLike(@NotNull @Valid Like like) {
        LikeQuery likeQuery = new LikeQuery();
        likeQuery.setLikeType(like.getLikeType());
        likeQuery.setToWhomId(like.getToWhomId());
//        likeQuery.setLikeUser(findCreatUser());
        List<LikeEntity> likeList = likeDao.findLikeList(likeQuery);
        if (!likeList.isEmpty()){
            return "已经点过赞了";
        }
        LikeEntity likeEntity = BeanMapper.map(like, LikeEntity.class);
        //添加点赞人
//        likeEntity.setLikeUser(findCreatUser());
        return likeDao.createLike(likeEntity);
    }

    @Override
    public void updateLike(@NotNull @Valid Like like) {
        LikeEntity likeEntity = BeanMapper.map(like, LikeEntity.class);

        likeDao.updateLike(likeEntity);
    }

    @Override
    public void deleteLike(@NotNull String id) {
        likeDao.deleteLike(id);
    }

    @Override
    public Like findOne(String id) {
        LikeEntity likeEntity = likeDao.findLike(id);

        Like like = BeanMapper.map(likeEntity, Like.class);
        return like;
    }

    @Override
    public List<Like> findList(List<String> idList) {
        List<LikeEntity> likeEntityList =  likeDao.findLikeList(idList);

        List<Like> likeList =  BeanMapper.mapList(likeEntityList,Like.class);
        return likeList;
    }

    @Override
    public Like findLike(@NotNull String id) {
        Like like = findOne(id);

        joinTemplate.joinQuery(like);
        return like;
    }

    @Override
    public List<Like> findAllLike() {
        List<LikeEntity> likeEntityList =  likeDao.findAllLike();

        List<Like> likeList =  BeanMapper.mapList(likeEntityList,Like.class);

        joinTemplate.joinQuery(likeList);
        return likeList;
    }

    @Override
    public List<Like> findLikeList(LikeQuery likeQuery) {
        List<LikeEntity> likeEntityList = likeDao.findLikeList(likeQuery);

        List<Like> likeList = BeanMapper.mapList(likeEntityList,Like.class);

        joinTemplate.joinQuery(likeList);

        return likeList;
    }

    @Override
    public Pagination<Like> findLikePage(LikeQuery likeQuery) {

        Pagination<LikeEntity>  pagination = likeDao.findLikePage(likeQuery);

        List<Like> likeList = BeanMapper.mapList(pagination.getDataList(),Like.class);

        joinTemplate.joinQuery(likeList);

        return PaginationBuilder.build(pagination,likeList);
    }

}