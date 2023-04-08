package io.tiklab.kanass.document.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.kanass.category.model.Category;
import io.tiklab.kanass.document.dao.ShareRelationDao;
import io.tiklab.kanass.document.entity.ShareRelationEntity;
import io.tiklab.kanass.document.model.Document;
import io.tiklab.kanass.document.model.ShareRelation;
import io.tiklab.kanass.document.model.ShareRelationQuery;
import io.tiklab.rpc.annotation.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
* ShareRelationServiceImpl
*/
@Service
@Exporter
public class ShareRelationServiceImpl implements ShareRelationService {

    @Autowired
    ShareRelationDao shareRelationDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createShareRelation(@NotNull @Valid ShareRelation shareRelation) {
        ShareRelationEntity shareRelationEntity = BeanMapper.map(shareRelation, ShareRelationEntity.class);

        return shareRelationDao.createShareRelation(shareRelationEntity);
    }

    public void createShareDocumentCategory(@NotNull @Valid ShareRelation shareRelation){
        String shareId = shareRelation.getShareId();
        String[] categoryIds = shareRelation.getCategoryIds();
        String[] documentIds = shareRelation.getDocumentIds();
        if(!ObjectUtils.isEmpty(categoryIds)){
            for (String categoryId : categoryIds) {
                ShareRelation shareRelation1 = new ShareRelation();
                Category category = new Category();
                category.setId(categoryId);
                shareRelation1.setCategory(category);
                shareRelation1.setType("category");
                shareRelation1.setShareId(shareId);
                createShareRelation(shareRelation1);
            }
        }

        if(!ObjectUtils.isEmpty(documentIds)){
            for (String documentId : documentIds) {
                ShareRelation shareRelation1 = new ShareRelation();
                Document document = new Document();
                document.setId(documentId);
                shareRelation1.setDocument(document);
                shareRelation1.setType("document");
                shareRelation1.setShareId(shareId);
                createShareRelation(shareRelation1);
            }
        }

    }

    @Override
    public void updateShareRelation(@NotNull @Valid ShareRelation shareRelation) {
        ShareRelationEntity shareRelationEntity = BeanMapper.map(shareRelation, ShareRelationEntity.class);

        shareRelationDao.updateShareRelation(shareRelationEntity);
    }

    @Override
    public void deleteShareRelation(@NotNull String id) {
        shareRelationDao.deleteShareRelation(id);
    }

    @Override
    public ShareRelation findOne(String id) {
        ShareRelationEntity shareRelationEntity = shareRelationDao.findShareRelation(id);

        ShareRelation shareRelation = BeanMapper.map(shareRelationEntity, ShareRelation.class);
        return shareRelation;
    }

    @Override
    public List<ShareRelation> findList(List<String> idList) {
        List<ShareRelationEntity> shareRelationEntityList = shareRelationDao.findShareRelationList(idList);

        List<ShareRelation> shareRelationList = BeanMapper.mapList(shareRelationEntityList, ShareRelation.class);
        return shareRelationList;
    }

    @Override
    public ShareRelation findShareRelation(@NotNull String id) {
        ShareRelation shareRelation = findOne(id);

        joinTemplate.joinQuery(shareRelation);
        return shareRelation;
    }

    @Override
    public List<ShareRelation> findAllShareRelation() {
        List<ShareRelationEntity> shareRelationEntityList = shareRelationDao.findAllShareRelation();

        List<ShareRelation> shareRelationList = BeanMapper.mapList(shareRelationEntityList, ShareRelation.class);

        joinTemplate.joinQuery(shareRelationList);
        return shareRelationList;
    }

    @Override
    public List<ShareRelation> findShareRelationList(ShareRelationQuery shareRelationQuery) {
        List<ShareRelationEntity> shareRelationEntityList = shareRelationDao.findShareRelationList(shareRelationQuery);

        List<ShareRelation> shareRelationList = BeanMapper.mapList(shareRelationEntityList, ShareRelation.class);

        joinTemplate.joinQuery(shareRelationList);

        return shareRelationList;
    }

    @Override
    public Pagination<ShareRelation> findShareRelationPage(ShareRelationQuery shareRelationQuery) {

        Pagination<ShareRelationEntity> pagination = shareRelationDao.findShareRelationPage(shareRelationQuery);

        List<ShareRelation> shareRelationList = BeanMapper.mapList(pagination.getDataList(), ShareRelation.class);

        joinTemplate.joinQuery(shareRelationList);

        return PaginationBuilder.build(pagination,shareRelationList);
    }

}