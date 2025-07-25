package io.tiklab.sward.document.document.service;

import io.tiklab.core.exception.SystemException;
import io.tiklab.sward.document.document.dao.ShareDao;
import io.tiklab.sward.document.document.model.Share;
import io.tiklab.sward.document.document.model.ShareQuery;
import io.tiklab.sward.document.document.model.ShareRelation;
import io.tiklab.sward.document.document.model.ShareRelationQuery;
import io.tiklab.sward.document.document.entity.ShareEntity;
import io.tiklab.sward.node.model.Node;
import io.tiklab.sward.node.model.NodeQuery;
import io.tiklab.sward.node.service.NodeService;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.user.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
* 分享接口
*/
@Service
@Exporter
public class ShareServiceImpl implements ShareService {

    @Autowired
    ShareDao shareDao;

    @Autowired
    ShareRelationService shareRelationService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    NodeService nodeService;

    @Override
    public String createShare(@NotNull @Valid Share share) {
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);

        return shareDao.createShare(shareEntity);
    }

    @Override
    public void updateShare(@NotNull @Valid Share share) {
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);

        shareDao.updateShare(shareEntity);
    }

    @Override
    @Transactional
    public void deleteShare(@NotNull String id) {
        shareDao.deleteShare(id);

        ShareRelationQuery shareRelationQuery = new ShareRelationQuery();
        shareRelationQuery.setShareId(id);
        List<ShareRelation> shareRelationList = shareRelationService.findShareRelationList(shareRelationQuery);
        for (ShareRelation relation : shareRelationList) {
            shareRelationService.deleteShareRelation(relation.getId());
        }
    }

    @Override
    public Share findOne(String id) {
        ShareEntity shareEntity = shareDao.findShare(id);

        return BeanMapper.map(shareEntity, Share.class);
    }

    @Override
    public List<Share> findList(List<String> idList) {
        List<ShareEntity> shareEntityList = shareDao.findShareList(idList);

        return BeanMapper.mapList(shareEntityList, Share.class);
    }

    @Override
    public Share findShare(@NotNull String id) {
        Share share = findOne(id);

        joinTemplate.joinQuery(share,new String[]{"wikiRepository","user"});
        return share;
    }

    @Override
    public List<Share> findAllShare() {
        List<ShareEntity> shareEntityList = shareDao.findAllShare();

        List<Share> shareList = BeanMapper.mapList(shareEntityList, Share.class);

        joinTemplate.joinQuery(shareList,new String[]{"wikiRepository","user"});
        return shareList;
    }

    @Override
    public List<Share> findShareList(ShareQuery shareQuery) {
        List<ShareEntity> shareEntityList = shareDao.findShareList(shareQuery);

        List<Share> shareList = BeanMapper.mapList(shareEntityList, Share.class);

        joinTemplate.joinQuery(shareList,new String[]{"wikiRepository","user"});

        return shareList;
    }

    @Override
    public Pagination<Share> findSharePage(ShareQuery shareQuery) {

        Pagination<ShareEntity> pagination = shareDao.findSharePage(shareQuery);

        List<Share> shareList = BeanMapper.mapList(pagination.getDataList(), Share.class);

        joinTemplate.joinQuery(shareList,new String[]{"wikiRepository","user"});

        return PaginationBuilder.build(pagination,shareList);
    }

    @Override
    public Share addShare(Share share) {

        WikiRepository wikiRepository = share.getWikiRepository();
        if (Objects.isNull(wikiRepository)){
            throw new SystemException("仓库不能为空!");
        }

        User user = share.getUser();
        if (Objects.isNull(user)){
            throw new SystemException("用户不能为空!");
        }

        String name = share.getName();
        if (StringUtils.isEmpty(name)){
            throw new SystemException("分享名称不能为空!");
        }

        if (share.getLimits().equals("private") && StringUtils.isEmpty(share.getAuthCode())) {
            //随机生成验证码
            int authCode = ThreadLocalRandom.current().nextInt(1000,10000);
            share.setAuthCode(String.valueOf(authCode));
        }
        share.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);
        ShareRelation shareRelation = new ShareRelation();
        shareRelation.setNodeIds(share.getNodeIds());

        String shareId = shareDao.createShare(shareEntity);
        share.setId(shareId);
        shareRelation.setShareId(shareId);
        shareRelationService.createShareDocumentCategory(shareRelation);
        return share;
    }

    @Override
    public Share cutHaveOrNotAuthCode(ShareQuery shareQuery) {
        ShareEntity share = shareDao.findShare(shareQuery.getId());
        //切换验证码到有
        String authCode = share.getAuthCode();
        if (shareQuery.getLimits().equals("private")) {
            //验证码
            if (StringUtils.isEmpty(authCode)) {
                //随机生成验证码
                int newAuthCode = ThreadLocalRandom.current().nextInt(1000,10000);
                share.setAuthCode(String.valueOf(newAuthCode));
                shareDao.updateShare(share);
                return BeanMapper.map(share, Share.class);
            } else {
                return null;
            }
        } else {
            if (!StringUtils.isEmpty(authCode)) {
                share.setAuthCode("");
                shareDao.updateShare(share);
                return BeanMapper.map(share, Share.class);
            }else {
                return null;
            }
        }
    }

    @Override
    public String verifyAuthCode(ShareQuery shareQuery) {

        String id = shareQuery.getShareLink();
        Share share = findOne(id);
        if (Objects.isNull(share)){
            throw new SystemException("该分享不存在！");
        }

        if (!share.getAuthCode().equals(shareQuery.getAuthCode())){
            throw new SystemException("密码错误！");
        }
        return "true";
    }

    @Override
    public String judgeAuthCode(String shareLink) {
        ShareEntity share = shareDao.findShare(shareLink);
        if (Objects.isNull(share)){
            throw new SystemException("该分享不存在！");
        }
        if (share.getLimits().equals("private")){
            return "true";
        }
        return "false";
    }

    @Override
    public List<Node> findShareCategory(NodeQuery nodeQuery) {
        List<Node> firstNodeList = new ArrayList<>();
        String shareId = nodeQuery.getShareId();
        ShareRelationQuery shareRelationQuery = new ShareRelationQuery();
        shareRelationQuery.setShareId(shareId);
        List<ShareRelation> shareRelationList = shareRelationService.findShareRelationList(shareRelationQuery);
        if(shareRelationList.isEmpty()){
           return new ArrayList<>();
        }

        ShareRelation relation = shareRelationList.get(0);
        Share share = findOne(shareId);

        Node node = nodeService.findNode(relation.getNode().getId());
        firstNodeList.add(node);
        if (share.getType().equals("document")){
            return firstNodeList;
        }

        // nodeQuery.setParentId(relation.getNode().getId());
        // List<Node> nodeList = nodeService.findNodeList(nodeQuery);
        // firstNodeList = nodeList.stream().filter(item -> item.getDimension().equals(1)).collect(Collectors.toList());
        // if(!nodeList.isEmpty()){
        //     setChildrenNode(firstNodeList);
        // }
        setChildrenNode(firstNodeList);


        return firstNodeList;

    }

    void setChildrenNode(List<Node> allNodeList){
        for (Node node : allNodeList) {
            NodeQuery nodeQuery = new NodeQuery();
            nodeQuery.setParentId(node.getId());
            List<Node> childNodeList = nodeService.findNodeList(nodeQuery);
            node.setChildren(childNodeList);
            if (Objects.isNull(childNodeList) || childNodeList.isEmpty() ){
                continue;
            }
            setChildrenNode(childNodeList);
        }
    }
}