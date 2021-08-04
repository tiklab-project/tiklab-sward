package com.doublekit.wiki.repository.service;

import com.doublekit.wiki.repository.model.RepositoryPage;
import com.doublekit.apibox.client.mock.JMockit;
import com.doublekit.wiki.config.TestConfig;
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
public class RepositoryPageServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RepositoryPageServiceImplTest.class);

    @Autowired
    RepositoryPageService repositoryPageService;

    static String id;

    @Test
    public void test01ForSaveRepositoryPage() {
        RepositoryPage repositoryPage = JMockit.mock(RepositoryPage.class);

        id = repositoryPageService.createRepositoryPage(repositoryPage);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRepositoryPage(){
        RepositoryPage repositoryPage = JMockit.mock(RepositoryPage.class);
        repositoryPage.setId(id);

        repositoryPageService.updateRepositoryPage(repositoryPage);
    }

    @Test
    public void test03ForFindRepositoryPage() {
        RepositoryPage repositoryPage = repositoryPageService.findRepositoryPage(id);

        assertNotNull(repositoryPage);
    }

    @Test
    public void test04ForFindAllRepositoryPage() {
        List<RepositoryPage> repositoryPageList = repositoryPageService.findAllRepositoryPage();

        assertNotNull(repositoryPageList);
    }

    @Test
    public void test05ForDeleteRepositoryPage(){
        repositoryPageService.deleteRepositoryPage(id);
    }
}