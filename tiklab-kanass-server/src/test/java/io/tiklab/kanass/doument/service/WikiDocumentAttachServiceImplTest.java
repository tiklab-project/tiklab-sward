package io.tiklab.kanass.doument.service;

import io.tiklab.kanass.document.model.DocumentAttach;
import io.tiklab.kanass.document.service.DocumentAttachService;
import io.tiklab.postin.client.mock.JMockit;
import io.tiklab.kanass.config.TestConfig;
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
public class WikiDocumentAttachServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WikiDocumentAttachServiceImplTest.class);

    @Autowired
    DocumentAttachService documentAttachService;

    static String id;

    @Test
    public void test01ForSaveDocumentAttach() {
        DocumentAttach documentAttach = JMockit.mock(DocumentAttach.class);

        id = documentAttachService.createDocumentAttach(documentAttach);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateDocumentAttach(){
        DocumentAttach documentAttach = JMockit.mock(DocumentAttach.class);
        documentAttach.setId(id);

        documentAttachService.updateDocumentAttach(documentAttach);
    }

    @Test
    public void test03ForFindDocumentAttach() {
        DocumentAttach documentAttach = documentAttachService.findDocumentAttach(id);

        assertNotNull(documentAttach);
    }

    @Test
    public void test04ForFindAllDocumentAttach() {
        List<DocumentAttach> documentAttachList = documentAttachService.findAllDocumentAttach();

        assertNotNull(documentAttachList);
    }

    @Test
    public void test05ForDeleteDocumentAttach(){
        documentAttachService.deleteDocumentAttach(id);
    }
}