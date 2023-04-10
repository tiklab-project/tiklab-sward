package io.tiklab.kanass.document.service;

import io.tiklab.kanass.category.model.Category;
import io.tiklab.kanass.category.service.CategoryService;
import io.tiklab.kanass.document.entity.ShareEntity;
import io.tiklab.kanass.document.model.*;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.kanass.document.dao.ShareDao;
import io.tiklab.user.dmUser.model.DmUser;
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
    CategoryService categoryService;

    @Autowired
    DocumentService documentService;
    @Autowired
    JoinTemplate joinTemplate;

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
        shareRelation.setCategoryIds(share.getCategoryIds());
        shareRelation.setDocumentIds(share.getDocumentIds());

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
    public ArrayList<Object> findShareCategory(String shareId) {
        ArrayList<Object> objects = new ArrayList<>();
        List<Document> documentList = new ArrayList<>();
        List<Document> childrenDocuments = new ArrayList<>();
        List<Category> categoryFirsts = new ArrayList<Category>();
        List<Category> childrenCategorys = new ArrayList<Category>();

        ShareRelationQuery shareRelationQuery = new ShareRelationQuery();

        // 查找分享的所有目录
        shareRelationQuery.setType("category");
        shareRelationQuery.setShareId(shareId);
        List<ShareRelation> shareRelationList = shareRelationService.findShareRelationList(shareRelationQuery);



        // 查找分享的所有文档
        shareRelationQuery.setType("document");
        List<ShareRelation> shareDocumentList = shareRelationService.findShareRelationList(shareRelationQuery);
        if(shareDocumentList.size() > 0){
            List<Document> allDocument = shareDocumentList.stream().map(ShareRelation::getDocument).collect(Collectors.toList());
            List<String> allDocumentIds = allDocument.stream().map(document -> document.getId()).collect(Collectors.toList());
            documentList = documentService.findList(allDocumentIds);
        }


        if(shareRelationList.size() > 0){
            List<Category> categories = shareRelationList.stream().map(ShareRelation::getCategory).collect(Collectors.toList());
            List<String> allCategoryIds = categories.stream().map(Category::getId).collect(Collectors.toList());
            List<Category> categoryList = categoryService.findList(allCategoryIds);
            // 若目录大于一个
            if(shareRelationList.size() >1){
                categoryFirsts = categoryList.stream().filter(category -> (ObjectUtils.isEmpty(category.getParentCategory()) || !allCategoryIds.contains(category.getParentCategory().getId()))).collect(Collectors.toList());
                List<String> categoryFirstIds = categoryFirsts.stream().map(category -> category.getId()).collect(Collectors.toList());
                childrenCategorys = categoryList.stream().filter(category -> (!categoryFirstIds.contains(category.getId()))).collect(Collectors.toList());
                objects.addAll(categoryFirsts);
            }else {
                categoryFirsts = categoryList;
                objects.addAll(categoryFirsts);
            }
            // 查找无父级目录的文档
            List<Document> documentFirsts = documentList.stream().filter(document -> (ObjectUtils.isEmpty(document.getCategory()) || !allCategoryIds.contains(document.getCategory().getId()))).collect(Collectors.toList());
            List<String> documentFirstIds = documentFirsts.stream().map(category -> category.getId()).collect(Collectors.toList());
            childrenDocuments = documentList.stream().filter(document -> (!documentFirstIds.contains(document.getId()))).collect(Collectors.toList());
            objects.addAll(documentFirsts);

            setCategoryChildren(categoryFirsts, childrenCategorys, childrenDocuments);
        }else {
            objects.addAll(documentList);
        }

        return  objects;

    }

    void setCategoryChildren(List<Category> categoryFirsts,List<Category> childrenCategorys, List<Document> documentList){
        List<Category> childrenForCategory = new ArrayList<>();
        List<Category> surChildrenForCategory = new ArrayList<>();
        for (Category category : categoryFirsts) {
            ArrayList<Object> objects1 = new ArrayList<>();
            if(childrenCategorys.size() >0){
                childrenForCategory = childrenCategorys.stream().filter(childrenCategory -> childrenCategory.getParentCategory().getId().equals(category.getId())).collect(Collectors.toList());
                objects1.addAll(childrenForCategory);
                List<String> collect = childrenForCategory.stream().map(category1 -> category1.getId()).collect(Collectors.toList());
                surChildrenForCategory = childrenForCategory.stream().filter(category1 -> (!collect.contains(category1.getId()))).collect(Collectors.toList());
            }
            List<Document> surChildrenDocument = new ArrayList<Document>();
            if(documentList.size() >0){
                List<Document> documentForCategory = documentList.stream().filter(childrenDocument -> childrenDocument.getCategory().getId().equals(category.getId())).collect(Collectors.toList());
                List<String> collect1 = documentForCategory.stream().map(document -> document.getId()).collect(Collectors.toList());
                surChildrenDocument = documentList.stream().filter(document -> (!collect1.contains(document.getId()))).collect(Collectors.toList());
                objects1.addAll(documentForCategory);
                category.setChildren(objects1);
            }


            if(surChildrenForCategory.size() > 0 || surChildrenDocument.size() >0){
                setCategoryChildren(childrenForCategory, surChildrenForCategory, surChildrenDocument);
            }
        }
    }
}