package com.tiklab.kanass.document.service;

import com.tiklab.kanass.document.model.DocumentRecent;
import com.tiklab.postlink.client.mock.JMockit;
import com.tiklab.kanass.config.TestConfig;
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
public class DocumentRecentServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(DocumentRecentServiceImplTest.class);

    @Autowired
    DocumentRecentService documentRecentService;

    static String id;

    @Test
    public void test01ForSaveDocumentRecent() {
        DocumentRecent documentRecent = JMockit.mock(DocumentRecent.class);

        id = documentRecentService.createDocumentRecent(documentRecent);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateDocumentRecent(){
        DocumentRecent documentRecent = JMockit.mock(DocumentRecent.class);
        documentRecent.setId(id);

        documentRecentService.updateDocumentRecent(documentRecent);
    }

    @Test
    public void test03ForFindDocumentRecent() {
        DocumentRecent documentRecent = documentRecentService.findDocumentRecent(id);

        assertNotNull(documentRecent);
    }

    @Test
    public void test04ForFindAllDocumentRecent() {
        List<DocumentRecent> documentRecentList = documentRecentService.findAllDocumentRecent();

        assertNotNull(documentRecentList);
    }

    @Test
    public void test05ForDeleteDocumentRecent(){
        documentRecentService.deleteDocumentRecent(id);
    }
}