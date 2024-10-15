package io.tiklab.sward.document.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.sward.document.dao.ShareRelationDao;
import io.tiklab.sward.node.model.Node;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.sward.document.entity.ShareRelationEntity;
import io.tiklab.sward.document.model.ShareRelation;
import io.tiklab.sward.document.model.ShareRelationQuery;
import io.tiklab.rpc.annotation.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
        String[] nodeIds = shareRelation.getNodeIds();
        if(!ObjectUtils.isEmpty(nodeIds)){
            for (String nodeId : nodeIds) {
                ShareRelation shareRelation1 = new ShareRelation();
                Node node = new Node();
                node.setId(nodeId);
                shareRelation1.setNode(node);
                shareRelation1.setType("category");
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
    public void deleteShareRelationCondition(ShareRelationQuery shareRelationQuery){
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(ShareRelationEntity.class)
                .eq("nodeId", shareRelationQuery.getNodeId())
                .in("nodeId", shareRelationQuery.getNodeIds())
                .get();
        shareRelationDao.deleteShareRelation(deleteCondition);
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