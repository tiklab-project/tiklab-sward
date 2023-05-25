package io.tiklab.kanass.repository.service;

import io.tiklab.kanass.repository.model.WikiRepositoryFocus;
import io.tiklab.kanass.config.TestConfig;
import io.tiklab.postin.client.mock.JMockit;
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
public class WikiWikiWikiRepositoryFocusServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WikiWikiWikiRepositoryFocusServiceImplTest.class);

    @Autowired
    WikiRepositoryFocusService wikiRepositoryFocusService;

    static String id;

    @Test
    public void test01ForSaveRepositoryFocus() {
        WikiRepositoryFocus wikiRepositoryFocus = JMockit.mock(WikiRepositoryFocus.class);

        id = wikiRepositoryFocusService.createRepositoryFocus(wikiRepositoryFocus);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRepositoryFocus(){
        WikiRepositoryFocus wikiRepositoryFocus = JMockit.mock(WikiRepositoryFocus.class);
        wikiRepositoryFocus.setId(id);

        wikiRepositoryFocusService.updateRepositoryFocus(wikiRepositoryFocus);
    }

    @Test
    public void test03ForFindRepositoryFocus() {
        WikiRepositoryFocus wikiRepositoryFocus = wikiRepositoryFocusService.findRepositoryFocus(id);

        assertNotNull(wikiRepositoryFocus);
    }

    @Test
    public void test04ForFindAllRepositoryFocus() {
        List<WikiRepositoryFocus> wikiRepositoryFocusList = wikiRepositoryFocusService.findAllRepositoryFocus();

        assertNotNull(wikiRepositoryFocusList);
    }

    @Test
    public void test05ForDeleteRepositoryFocus(){
        wikiRepositoryFocusService.deleteRepositoryFocus(id);
    }
}