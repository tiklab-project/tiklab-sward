package io.tiklab.kanass.doument.service;

import io.tiklab.kanass.document.model.Document;
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
public class DocumentServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(DocumentServiceImplTest.class);

    @Autowired
    DocumentService documentService;

    static String id;

    @Test
    public void test01ForSaveDocument() {
        Document document = JMockit.mock(Document.class);

        id = documentService.createDocument(document);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateDocument(){
        Document document = JMockit.mock(Document.class);
        document.setId(id);

        documentService.updateDocument(document);
    }

    @Test
    public void test03ForFindDocument() {
        Document document = documentService.findDocument(id);

        assertNotNull(document);
    }

    @Test
    public void test04ForFindAllDocument() {
        List<Document> documentList = documentService.findAllDocument();

        assertNotNull(documentList);
    }

    @Test
    public void test05ForDeleteDocument(){
        documentService.deleteDocument(id);
    }
}