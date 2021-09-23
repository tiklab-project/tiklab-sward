package com.doublekit.wiki.repository.service;

import com.doublekit.beans.BeanMapper;
import com.doublekit.common.Pagination;
import com.doublekit.eam.common.Ticket;
import com.doublekit.eam.common.TicketContext;
import com.doublekit.eam.common.TicketHolder;
import com.doublekit.join.JoinTemplate;
import com.doublekit.wiki.repository.dao.LikeDao;
import com.doublekit.wiki.repository.entity.LikePo;
import com.doublekit.wiki.repository.model.Like;
import com.doublekit.wiki.repository.model.LikeQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* LikeServiceImpl
*/
@Service
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
        likeQuery.setLikeUser(findCreatUser());
        List<LikePo> likeList = likeDao.findLikeList(likeQuery);
        if (CollectionUtils.isNotEmpty(likeList)){
            return "已经点过赞了";
        }
        LikePo likePo = BeanMapper.map(like, LikePo.class);
        //添加点赞人
        likePo.setLikeUser(findCreatUser());
        return likeDao.createLike(likePo);
    }

    @Override
    public void updateLike(@NotNull @Valid Like like) {
        LikePo likePo = BeanMapper.map(like, LikePo.class);

        likeDao.updateLike(likePo);
    }

    @Override
    public void deleteLike(@NotNull String id) {
        likeDao.deleteLike(id);
    }

    @Override
    public Like findOne(String id) {
        LikePo likePo = likeDao.findLike(id);

        Like like = BeanMapper.map(likePo, Like.class);
        return like;
    }

    @Override
    public List<Like> findList(List<String> idList) {
        List<LikePo> likePoList =  likeDao.findLikeList(idList);

        List<Like> likeList =  BeanMapper.mapList(likePoList,Like.class);
        return likeList;
    }

    @Override
    public Like findLike(@NotNull String id) {
        Like like = findOne(id);

        joinTemplate.queryOne(like);
        return like;
    }

    @Override
    public List<Like> findAllLike() {
        List<LikePo> likePoList =  likeDao.findAllLike();

        List<Like> likeList =  BeanMapper.mapList(likePoList,Like.class);

        joinTemplate.queryList(likeList);
        return likeList;
    }

    @Override
    public List<Like> findLikeList(LikeQuery likeQuery) {
        List<LikePo> likePoList = likeDao.findLikeList(likeQuery);

        List<Like> likeList = BeanMapper.mapList(likePoList,Like.class);

        joinTemplate.queryList(likeList);

        return likeList;
    }

    @Override
    public Pagination<Like> findLikePage(LikeQuery likeQuery) {
        Pagination<Like> pg = new Pagination<>();

        Pagination<LikePo>  pagination = likeDao.findLikePage(likeQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Like> likeList = BeanMapper.mapList(pagination.getDataList(),Like.class);

        joinTemplate.queryList(likeList);

        pg.setDataList(likeList);
        return pg;
    }


    /**
     * 查询用户（创建人）id
     * @param
     */
    public String findCreatUser(){
        String ticketId = TicketHolder.get();
        Ticket ticket = TicketContext.get(ticketId);
        return ticket.getUserId();
    }

}