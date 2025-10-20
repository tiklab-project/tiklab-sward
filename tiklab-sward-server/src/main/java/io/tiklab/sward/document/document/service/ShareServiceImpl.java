package io.tiklab.sward.document.document.service;

import io.tiklab.core.exception.SystemException;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.privilege.permission.service.PermissionService;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.sward.document.document.dao.ShareDao;
import io.tiklab.sward.document.document.entity.ShareEntity;
import io.tiklab.sward.document.document.model.Share;
import io.tiklab.sward.document.document.model.SharePermissions;
import io.tiklab.sward.document.document.model.ShareQuery;
import io.tiklab.sward.node.node.model.Node;
import io.tiklab.sward.node.node.model.NodeQuery;
import io.tiklab.sward.node.node.service.NodeService;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;
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
    JoinTemplate joinTemplate;

    @Autowired
    NodeService nodeService;

    @Autowired
    PermissionService permissionService;


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
    public void deleteShare(@NotNull String id) {
        shareDao.deleteShare(id);
    }

    @Override
    public List<Share> findList(List<String> idList) {
        List<ShareEntity> shareEntityList = shareDao.findShareList(idList);

        return BeanMapper.mapList(shareEntityList, Share.class);
    }

    @Override
    public Share findShare(@NotNull String id) {
        ShareEntity shareEntity = shareDao.findShare(id);

        Share share = BeanMapper.map(shareEntity, Share.class);

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

        List<String> stringList = shareList.stream().map(share -> share.getWikiRepository().getId())
                .collect(Collectors.toList());

        Map<String, Set<String>> permissions = permissionService.findDomainListPermissions(LoginContext.getLoginId(), stringList);

        shareList.forEach(share -> {
            String id = share.getWikiRepository().getId();
            SharePermissions sharePermissions = new SharePermissions();
            Set<String> strings = permissions.get(id);

            if(!Objects.isNull(strings)){
                String join = String.join(",", strings);
                sharePermissions.addPipelinePermission(sharePermissions,join);
            }
            share.setPermissions(sharePermissions);
        });

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
        share.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ShareEntity shareEntity = BeanMapper.map(share, ShareEntity.class);
        String shareId = shareDao.createShare(shareEntity);
        share.setId(shareId);
        return share;
    }

    @Override
    public List<Node> findShareCategory(String id) {

        Share share = findShare(id);

        List<Node> nodeList = new ArrayList<>();
        String shareIds = share.getShareIds();
        String[] split = shareIds.split(",");
        for (String docId : split) {
            if (StringUtils.isEmpty(docId)) {
                continue;
            }
            Node node = nodeService.findNode(docId);
            if (Objects.isNull(node)) {
                continue;
            }
            NodeQuery nodeQuery = new NodeQuery();
            nodeQuery.setParentId(node.getId());
            List<Node> childNodeList = nodeService.findNodeList(nodeQuery);
            node.setChildren(childNodeList);
            setChildrenNode(childNodeList);

            nodeList.add(node);
        }

        return nodeList;
    }

    private void setChildrenNode(List<Node> allNodeList){
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