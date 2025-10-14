package io.tiklab.sward.support.task.service;

import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.sward.node.node.model.Node;
import io.tiklab.sward.node.node.service.NodeService;
import io.tiklab.sward.repository.service.WikiRepositoryService;
import io.tiklab.dss.client.DssClient;
import io.tiklab.sward.document.document.model.WikiDocument;
import io.tiklab.sward.document.document.service.DocumentService;
import io.tiklab.sward.repository.model.WikiRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 重建索引
 */
@Component
public class DssInitIndex implements DsmProcessTask {


    @Autowired
    DssClient dssClient;

    @Autowired
    WikiRepositoryService wikiRepositoryService;

    @Autowired
    DocumentService documentService;

    @Autowired
    NodeService nodeService;

    /**
     * 重建索引，需要重建，就在DssInitAutoConfiguration 中加个新版本
     */
    @Override
    public void execute() {
        dssClient.deleteAll(WikiRepository.class);
        dssClient.deleteAll(WikiDocument.class);

        List<WikiRepository> allRepository = wikiRepositoryService.findAllRepositoryNoQuery();
        for (WikiRepository wikiRepository : allRepository) {
            dssClient.save(wikiRepository);
        }

        List<WikiDocument> allDocument = documentService.findAllDocument();
        for (WikiDocument wikiDocument : allDocument) {
            String versionId = wikiDocument.getVersionId();
            if (!StringUtils.isEmpty(versionId)){
                continue;
            }
            Node node = nodeService.findNodeNoQuery(wikiDocument.getId());
            if (!Objects.isNull(node)){
                node.setRpyId(node.getWikiRepository().getId());
                wikiDocument.setRepositoryId(node.getWikiRepository().getId());
                dssClient.save(node);
            }
            dssClient.save(wikiDocument);
        }
    }
}
