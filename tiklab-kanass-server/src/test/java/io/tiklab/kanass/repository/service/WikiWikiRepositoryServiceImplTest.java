package io.tiklab.kanass.repository.service;

import io.tiklab.kanass.repository.model.WikiRepository;
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
public class WikiWikiRepositoryServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WikiWikiRepositoryServiceImplTest.class);

    @Autowired
    WikiRepositoryService wikiRepositoryService;

    static String id;

    @Test
    public void test01ForSaveRepository() {
        WikiRepository wikiRepository = JMockit.mock(WikiRepository.class);

        id = wikiRepositoryService.createRepository(wikiRepository);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRepository(){
        WikiRepository wikiRepository = JMockit.mock(WikiRepository.class);
        wikiRepository.setId(id);

        wikiRepositoryService.updateRepository(wikiRepository);
    }

    @Test
    public void test03ForFindRepository() {
        WikiRepository wikiRepository = wikiRepositoryService.findRepository(id);

        assertNotNull(wikiRepository);
    }

    @Test
    public void test04ForFindAllRepository() {
        List<WikiRepository> wikiRepositoryList = wikiRepositoryService.findAllRepository();

        assertNotNull(wikiRepositoryList);
    }

    @Test
    public void test05ForDeleteRepository(){
        wikiRepositoryService.deleteRepository(id);
    }
}