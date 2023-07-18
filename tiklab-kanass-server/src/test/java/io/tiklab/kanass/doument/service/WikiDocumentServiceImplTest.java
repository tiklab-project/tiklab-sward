package io.tiklab.kanass.doument.service;

import io.tiklab.kanass.document.model.WikiDocument;
import io.tiklab.postin.client.mock.JMockit;
import io.tiklab.kanass.config.TestConfig;
import io.tiklab.kanass.document.service.DocumentService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Transactional
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WikiDocumentServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WikiDocumentServiceImplTest.class);

    @Autowired
    DocumentService documentService;

    static String id;

    @Test
    public void test01ForSaveDocument() {
        WikiDocument wikiDocument = JMockit.mock(WikiDocument.class);

        id = documentService.createDocument(wikiDocument);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateDocument(){
        WikiDocument wikiDocument = JMockit.mock(WikiDocument.class);
        wikiDocument.setId(id);

        documentService.updateDocument(wikiDocument);
    }

    @Test
    public void test03ForFindDocument() {
        WikiDocument wikiDocument = documentService.findDocument(id);

        assertNotNull(wikiDocument);
    }

    @Test
    public void test04ForFindAllDocument() {
        List<WikiDocument> wikiDocumentList = documentService.findAllDocument();

        assertNotNull(wikiDocumentList);
    }

    @Test
    public void test05ForDeleteDocument(){
        documentService.deleteDocument(id);
    }
}