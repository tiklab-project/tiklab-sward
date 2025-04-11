package io.tiklab.sward.support.support;

import io.tiklab.dss.init.executer.IndexInitCallback;
import io.tiklab.sward.node.model.Node;
import io.tiklab.sward.node.service.NodeService;
import io.tiklab.sward.repository.service.WikiRepositoryService;
import io.tiklab.dss.client.DssClient;
import io.tiklab.sward.document.model.WikiDocument;
import io.tiklab.sward.document.service.DocumentService;
import io.tiklab.sward.repository.model.WikiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 重建索引
 */
@Component
public class DssInitIndex implements IndexInitCallback {
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

        List<WikiRepository> allRepository = wikiRepositoryService.findAllRepository();
        for (WikiRepository wikiRepository : allRepository) {
            dssClient.save(wikiRepository);
        }

        List<WikiDocument> allDocument = documentService.findAllDocument();
        for (WikiDocument wikiDocument : allDocument) {
            Node node = nodeService.findNode(wikiDocument.getId());
            node.setRpyId(node.getWikiRepository().getId());
            dssClient.save(node);
            wikiDocument.setRepositoryId(node.getWikiRepository().getId());
            dssClient.save(wikiDocument);
        }
    }
}
