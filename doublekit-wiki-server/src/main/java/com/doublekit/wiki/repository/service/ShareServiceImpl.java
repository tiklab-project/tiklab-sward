package com.doublekit.wiki.repository.service;

import com.doublekit.beans.BeanMapper;
import com.doublekit.common.Pagination;
import com.doublekit.join.JoinTemplate;
import com.doublekit.wiki.repository.dao.ShareDao;
import com.doublekit.wiki.repository.entity.SharePo;
import com.doublekit.wiki.repository.model.Share;
import com.doublekit.wiki.repository.model.ShareQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
* ShareServiceImpl
*/
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    ShareDao shareDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createShare(@NotNull @Valid Share share) {
        SharePo sharePo = BeanMapper.map(share, SharePo.class);

        return shareDao.createShare(sharePo);
    }

    @Override
    public void updateShare(@NotNull @Valid Share share) {
        SharePo sharePo = BeanMapper.map(share, SharePo.class);

        shareDao.updateShare(sharePo);
    }

    @Override
    public void deleteShare(@NotNull String id) {
        shareDao.deleteShare(id);
    }

    @Override
    public Share findOne(String id) {
        SharePo sharePo = shareDao.findShare(id);

        Share share = BeanMapper.map(sharePo, Share.class);
        return share;
    }

    @Override
    public List<Share> findList(List<String> idList) {
        List<SharePo> sharePoList = shareDao.findShareList(idList);

        List<Share> shareList = BeanMapper.mapList(sharePoList, Share.class);
        return shareList;
    }

    @Override
    public Share findShare(@NotNull String id) {
        Share share = findOne(id);

        joinTemplate.queryOne(share);
        return share;
    }

    @Override
    public List<Share> findAllShare() {
        List<SharePo> sharePoList = shareDao.findAllShare();

        List<Share> shareList = BeanMapper.mapList(sharePoList, Share.class);

        joinTemplate.queryList(shareList);
        return shareList;
    }

    @Override
    public List<Share> findShareList(ShareQuery shareQuery) {
        List<SharePo> sharePoList = shareDao.findShareList(shareQuery);

        List<Share> shareList = BeanMapper.mapList(sharePoList, Share.class);

        joinTemplate.queryList(shareList);

        return shareList;
    }

    @Override
    public Pagination<Share> findSharePage(ShareQuery shareQuery) {
        Pagination<Share> pg = new Pagination<>();

        Pagination<SharePo> pagination = shareDao.findSharePage(shareQuery);
        BeanUtils.copyProperties(pagination, pg);

        List<Share> shareList = BeanMapper.mapList(pagination.getDataList(), Share.class);

        joinTemplate.queryList(shareList);

        pg.setDataList(shareList);
        return pg;
    }

    @Override
    public Share addShare(Share share) {
        if (share.getWhetherAuthCode()) {
            //随机生成验证码
            int authCode = ThreadLocalRandom.current().nextInt(1000,10000);
            share.setAuthCode(String.valueOf(authCode));
        }
        //根据时间搓和文档id生成分享id
        long l = System.currentTimeMillis();
        String time = String.valueOf(l);
        String shareLink = time + "?xt:" + share.getDocumentId();
        share.setShareLink(shareLink);
        share.setCreateTime(new Date());
        SharePo sharePo = BeanMapper.map(share, SharePo.class);

        shareDao.createShare(sharePo);
        return share;
    }

    @Override
    public Share cutHaveOrNotAuthCode(ShareQuery shareQuery) {
        List<SharePo> shareList = shareDao.findShareList(new ShareQuery().setShareLink(shareQuery.getShareLink()));
        //切换验证码到有
        if (shareQuery.getWhetherAuthCode()) {
            //验证码
            String authCode = shareList.get(0).getAuthCode();
            if (StringUtils.isEmpty(authCode)) {
                //随机生成验证码
                int newAuthCode = ThreadLocalRandom.current().nextInt(1000,10000);
                shareList.get(0).setAuthCode(String.valueOf(newAuthCode));
                shareDao.updateShare(shareList.get(0));
                Share share = BeanMapper.map(shareList.get(0), Share.class);
                return share;
            } else {
                return null;
            }
        } else {
            String authCode = shareList.get(0).getAuthCode();
            if (!StringUtils.isEmpty(authCode)) {
                shareList.get(0).setAuthCode("");
                shareDao.updateShare(shareList.get(0));
                Share share = BeanMapper.map(shareList.get(0), Share.class);
                return share;
            }else {
                return null;
            }
        }
    }

    @Override
    public String verifyAuthCode(ShareQuery shareQuery) {
        List<SharePo> shareList = shareDao.findShareList(new ShareQuery().setShareLink(shareQuery.getShareLink()));
        if (CollectionUtils.isNotEmpty(shareList)){
            SharePo sharePo = shareList.get(0);
            if (shareQuery.getAuthCode().equals(sharePo.getAuthCode())){
                return "true";
            }else {
                return "请输入正确的验证码";
            }
        }else {
            return "你来晚了,该链接内容已被删除";
        }
    }

    @Override
    public String judgeAuthCode(String shareLink) {
        List<SharePo> shareList = shareDao.findShareList(new ShareQuery().setShareLink(shareLink));
        if (CollectionUtils.isNotEmpty(shareList)){
            SharePo sharePo = shareList.get(0);
            if (StringUtils.isEmpty(sharePo.getAuthCode())){
                return "false";
            }else {
                return "true";
            }
        }else {
            return "你来晚了,该链接内容已被删除";
        }
    }
}