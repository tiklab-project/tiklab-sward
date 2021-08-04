package com.doublekit.wiki.repository.service;

import com.doublekit.wiki.repository.model.RepositoryDetails;
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
public class RepositoryDetailsServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RepositoryDetailsServiceImplTest.class);

    @Autowired
    RepositoryDetailsService repositoryDetailsService;

    static String id;

    @Test
    public void test01ForSaveRepositoryDetails() {
        RepositoryDetails repositoryDetails = JMockit.mock(RepositoryDetails.class);

        id = repositoryDetailsService.createRepositoryDetails(repositoryDetails);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRepositoryDetails(){
        RepositoryDetails repositoryDetails = JMockit.mock(RepositoryDetails.class);
        repositoryDetails.setId(id);

        repositoryDetailsService.updateRepositoryDetails(repositoryDetails);
    }

    @Test
    public void test03ForFindRepositoryDetails() {
        RepositoryDetails repositoryDetails = repositoryDetailsService.findRepositoryDetails(id);

        assertNotNull(repositoryDetails);
    }

    @Test
    public void test04ForFindAllRepositoryDetails() {
        List<RepositoryDetails> repositoryDetailsList = repositoryDetailsService.findAllRepositoryDetails();

        assertNotNull(repositoryDetailsList);
    }

    @Test
    public void test05ForDeleteRepositoryDetails(){
        repositoryDetailsService.deleteRepositoryDetails(id);
    }
}