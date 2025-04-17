package io.tiklab.sward.document.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.dss.client.DssClient;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.sward.document.dao.DocumentVersionDao;
import io.tiklab.sward.document.entity.DocumentVersionEntity;
import io.tiklab.sward.document.model.DocumentVersion;
import io.tiklab.sward.document.model.DocumentVersionQuery;
import io.tiklab.sward.document.model.WikiDocument;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DocumentVersionServiceImpl implements DocumentVersionService {

    @Autowired
    DocumentVersionDao versionDao;

    @Autowired
    DocumentService documentService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createDocumentVersion(DocumentVersion version) {
        String documentId = version.getDocumentId();
        WikiDocument document = documentService.findDocument(documentId);
        if (Objects.isNull(document)){
            throw new ApplicationException("创建失败，无法找到原文档。");
        }
        document.setId(UuidGenerator.getRandomIdByUUID(12));
        String versionDocumentId = documentService.createOneDocument(document);
        document.setId(versionDocumentId);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        version.setCreateTime(format);
        version.setDocumentId(documentId);
        version.setWikiDocument(document);
        DocumentVersionEntity versionEntity = BeanMapper.map(version, DocumentVersionEntity.class);
        String documentVersionId = versionDao.createDocumentVersion(versionEntity);

        document.setVersionId(documentVersionId);
        documentService.updateOneDocument(document);
        return documentVersionId;
    }

    @Override
    public void updateDocumentVersion(DocumentVersion version) {
        String id = version.getId();
        DocumentVersion documentVersion = findDocumentVersion(id);
        documentVersion.setVersionName(version.getVersionName());
        DocumentVersionEntity versionEntity = BeanMapper.map(documentVersion, DocumentVersionEntity.class);
        versionDao.updateDocumentVersion(versionEntity);
    }

    @Override
    public void deleteDocumentVersion(String id) {
        DocumentVersion documentVersion = findDocumentVersion(id);
        String documentId = documentVersion.getWikiDocument().getId();
        documentService.deleteDocument(documentId);
        versionDao.deleteDocumentVersion(id);
    }

    @Override
    public void callbackDocument(String id){

        DocumentVersion documentVersion = findDocumentVersion(id);
        String documentId = documentVersion.getDocumentId();
        WikiDocument document = documentService.findDocument(documentId);
        if (Objects.isNull(document)){
            throw new ApplicationException("恢复版本失败，无法找到原文档。");
        }

        // 创建新的版本信息

        DocumentVersion newDocumentVersion = new DocumentVersion();
        newDocumentVersion.setVersionName("恢复至 "+ documentVersion.getCreateTime()+" 修改的版本");
        newDocumentVersion.setUser(new User(LoginContext.getLoginId()));
        newDocumentVersion.setDocumentId(documentId);
        createDocumentVersion(newDocumentVersion);

        // 更新文档
        WikiDocument wikiDocument = documentVersion.getWikiDocument();
        document.setDetails(wikiDocument.getDetails());
        document.setDetailText(wikiDocument.getDetailText());
        documentService.updateDocument(document);

    }

    @Override
    public DocumentVersion findDocumentVersion(String id) {
        DocumentVersionEntity documentVersionEntity = versionDao.findDocumentVersion(id);
        DocumentVersion documentVersion = BeanMapper.map(documentVersionEntity, DocumentVersion.class);
        joinTemplate.joinQuery(documentVersion);
        return documentVersion;
    }

    @Override
    public List<DocumentVersion> findDocumentVersionList(List<String> idList) {
        List<DocumentVersionEntity> versionEntityList = versionDao.findDocumentVersionList(idList);
        return BeanMapper.mapList(versionEntityList, DocumentVersion.class);
    }

    @Override
    public List<DocumentVersion> findDocumentVersionList(DocumentVersionQuery versionQuery) {
        List<DocumentVersionEntity> versionEntityList = versionDao.findDocumentVersionList(versionQuery);
        return BeanMapper.mapList(versionEntityList, DocumentVersion.class);
    }

    @Override
    public List<DocumentVersion> findAllDocumentVersion() {
        List<DocumentVersionEntity> versionEntityList = versionDao.findAllDocumentVersion();
        return BeanMapper.mapList(versionEntityList, DocumentVersion.class);
    }

    @Override
    public Pagination<DocumentVersion> findDocumentVersionPage(DocumentVersionQuery versionQuery) {
        Pagination<DocumentVersionEntity> versionEntityPagination = versionDao.findDocumentVersionPage(versionQuery);
        List<DocumentVersionEntity> versionEntityList = versionEntityPagination.getDataList();
        if (versionEntityList.isEmpty()) {
            return PaginationBuilder.build(versionEntityPagination, Collections.emptyList());
        }
        List<DocumentVersion> documentVersionList = BeanMapper.mapList(versionEntityList, DocumentVersion.class);
        joinTemplate.joinQuery(documentVersionList);
        return PaginationBuilder.build(versionEntityPagination, documentVersionList);
    }
}
