package io.thoughtware.sward.support.support;

import io.thoughtware.sward.repository.service.WikiRepositoryService;
import io.thoughtware.dss.client.DssClient;
import io.thoughtware.dss.client.initdata.executer.IndexInitCallback;
import io.thoughtware.sward.document.model.WikiDocument;
import io.thoughtware.sward.document.service.DocumentService;
import io.thoughtware.sward.repository.model.WikiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DssInitIndex implements IndexInitCallback {
    @Autowired
    DssClient dssClient;

    @Autowired
    WikiRepositoryService wikiRepositoryService;

    @Autowired
    DocumentService documentService;

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
            dssClient.save(wikiDocument);
        }
    }
}
