package io.thoughtware.sward.document.service;

import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.document.dao.ShareDao;
import io.thoughtware.sward.category.service.WikiCategoryService;
import io.thoughtware.sward.document.entity.ShareEntity;
import io.thoughtware.sward.document.model.*;
import io.thoughtware.sward.node.model.Node;
import io.thoughtware.sward.node.model.NodeQuery;
import io.thoughtware.sward.node.service.NodeService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.rpc.annotation.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
* ShareServiceImpl
*/
@Service
@Exporter
public class ShareServiceImpl implements ShareService {

    @Autowired
    ShareDao shareDao;

    @Autowired
    ShareRelationService shareRelationService;

    @Autowired
    WikiCategoryService wikiCategoryService;

    @Autowired
    DocumentService documentService;
    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    NodeService nodeService;

    @Override
    public String createShare(@NotNull @Valid Share share) {
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);
        String shareId = shareDao.createShare(shareEntity);

        return shareId;
    }

    @Override
    public void updateShare(@NotNull @Valid Share share) {
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);

        shareDao.updateShare(shareEntity);
    }

    @Override
    public void deleteShare(@NotNull String id) {
        shareDao.deleteShare(id);
    }

    @Override
    public Share findOne(String id) {
        ShareEntity shareEntity = shareDao.findShare(id);

        Share share = BeanMapper.map(shareEntity, Share.class);
        return share;
    }

    @Override
    public List<Share> findList(List<String> idList) {
        List<ShareEntity> shareEntityList = shareDao.findShareList(idList);

        List<Share> shareList = BeanMapper.mapList(shareEntityList, Share.class);
        return shareList;
    }

    @Override
    public Share findShare(@NotNull String id) {
        Share share = findOne(id);

        joinTemplate.joinQuery(share);
        return share;
    }

    @Override
    public List<Share> findAllShare() {
        List<ShareEntity> shareEntityList = shareDao.findAllShare();

        List<Share> shareList = BeanMapper.mapList(shareEntityList, Share.class);

        joinTemplate.joinQuery(shareList);
        return shareList;
    }

    @Override
    public List<Share> findShareList(ShareQuery shareQuery) {
        List<ShareEntity> shareEntityList = shareDao.findShareList(shareQuery);

        List<Share> shareList = BeanMapper.mapList(shareEntityList, Share.class);

        joinTemplate.joinQuery(shareList);

        return shareList;
    }

    @Override
    public Pagination<Share> findSharePage(ShareQuery shareQuery) {

        Pagination<ShareEntity> pagination = shareDao.findSharePage(shareQuery);

        List<Share> shareList = BeanMapper.mapList(pagination.getDataList(), Share.class);

        joinTemplate.joinQuery(shareList);

        return PaginationBuilder.build(pagination,shareList);
    }

    @Override
    public Share addShare(Share share) {
        if (share.getLimits().equals("private")) {
            //随机生成验证码
            int authCode = ThreadLocalRandom.current().nextInt(1000,10000);
            share.setAuthCode(String.valueOf(authCode));
        }
        share.setCreateTime(new Date());
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
        if (shareQuery.getLimits().equals("private")) {
            //验证码
            String authCode = share.getAuthCode();
            if (StringUtils.isEmpty(authCode)) {
                //随机生成验证码
                int newAuthCode = ThreadLocalRandom.current().nextInt(1000,10000);
                share.setAuthCode(String.valueOf(newAuthCode));
                shareDao.updateShare(share);
                Share share1 = BeanMapper.map(share, Share.class);
                return share1;
            } else {
                return null;
            }
        } else {
            String authCode = share.getAuthCode();
            if (!StringUtils.isEmpty(authCode)) {
                share.setAuthCode("");
                shareDao.updateShare(share);
                Share share1 = BeanMapper.map(share, Share.class);
                return share1;
            }else {
                return null;
            }
        }
    }

    @Override
    public String verifyAuthCode(ShareQuery shareQuery) {
        List<ShareEntity> shareList = shareDao.findShareList(shareQuery);
        if (shareList.size() > 0){
            if (shareQuery.getAuthCode().equals(shareList.get(0).getAuthCode())){
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
        ShareEntity share = shareDao.findShare(shareLink);
        if (!ObjectUtils.isEmpty(share)){
            if (StringUtils.isEmpty(share.getAuthCode())){
                return "false";
            }else {
                return "true";
            }
        }else {
            return "你来晚了,该链接内容已被删除";
        }
    }

    @Override
    public List<Node> findShareCategory(NodeQuery nodeQuery) {
        String shareId = nodeQuery.getShareId();
        ShareRelationQuery shareRelationQuery = new ShareRelationQuery();
        shareRelationQuery.setShareId(shareId);
        List<ShareRelation> shareRelationList = shareRelationService.findShareRelationList(shareRelationQuery);
        Object[] Ids = shareRelationList.stream().map(item -> item.getNode().getId()).toArray();
        nodeQuery.setIds(Ids);
        List<Node> nodePageTree = nodeService.findNodePageTree(nodeQuery);

        return nodePageTree;

    }

    void setCategoryChildren(List<WikiCategory> wikiCategoryFirsts, List<WikiCategory> childrenWikiCategories, List<WikiDocument> wikiDocumentList){
        List<WikiCategory> childrenForWikiCategory = new ArrayList<>();
        List<WikiCategory> surChildrenForWikiCategory = new ArrayList<>();
        for (WikiCategory wikiCategory : wikiCategoryFirsts) {
            ArrayList<Object> objects1 = new ArrayList<>();
//            if(childrenWikiCategories.size() >0){
//                childrenForWikiCategory = childrenWikiCategories.stream().filter(childrenCategory -> childrenCategory.getParentWikiCategory().getId().equals(wikiCategory.getId())).collect(Collectors.toList());
//                objects1.addAll(childrenForWikiCategory);
//                List<String> collect = childrenForWikiCategory.stream().map(category1 -> category1.getId()).collect(Collectors.toList());
//                surChildrenForWikiCategory = childrenForWikiCategory.stream().filter(category1 -> (!collect.contains(category1.getId()))).collect(Collectors.toList());
//            }
//            List<WikiDocument> surChildrenWikiDocument = new ArrayList<WikiDocument>();
//            if(wikiDocumentList.size() >0){
//                List<WikiDocument> wikiDocumentForCategory = wikiDocumentList.stream().filter(childrenDocument -> childrenDocument.getWikiCategory().getId().equals(wikiCategory.getId())).collect(Collectors.toList());
//                List<String> collect1 = wikiDocumentForCategory.stream().map(document -> document.getId()).collect(Collectors.toList());
//                surChildrenWikiDocument = wikiDocumentList.stream().filter(document -> (!collect1.contains(document.getId()))).collect(Collectors.toList());
//                objects1.addAll(wikiDocumentForCategory);
//                wikiCategory.setChildren(objects1);
//            }


//            if(surChildrenForWikiCategory.size() > 0 || surChildrenWikiDocument.size() >0){
//                setCategoryChildren(childrenForWikiCategory, surChildrenForWikiCategory, surChildrenWikiDocument);
//            }
        }
    }
}