package io.tiklab.kanass.support.support;

import io.tiklab.dss.client.DssClient;
import io.tiklab.dss.client.initdata.executer.IndexInitCallback;
import io.tiklab.kanass.document.model.WikiDocument;
import io.tiklab.kanass.document.service.DocumentService;
import io.tiklab.kanass.repository.model.WikiRepository;
import io.tiklab.kanass.repository.service.WikiRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
