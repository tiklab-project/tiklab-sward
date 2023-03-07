package io.tiklab.kanass.repository.service;

import io.tiklab.kanass.repository.model.RepositoryFocus;
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
public class RepositoryFocusServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RepositoryFocusServiceImplTest.class);

    @Autowired
    RepositoryFocusService repositoryFocusService;

    static String id;

    @Test
    public void test01ForSaveRepositoryFocus() {
        RepositoryFocus repositoryFocus = JMockit.mock(RepositoryFocus.class);

        id = repositoryFocusService.createRepositoryFocus(repositoryFocus);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRepositoryFocus(){
        RepositoryFocus repositoryFocus = JMockit.mock(RepositoryFocus.class);
        repositoryFocus.setId(id);

        repositoryFocusService.updateRepositoryFocus(repositoryFocus);
    }

    @Test
    public void test03ForFindRepositoryFocus() {
        RepositoryFocus repositoryFocus = repositoryFocusService.findRepositoryFocus(id);

        assertNotNull(repositoryFocus);
    }

    @Test
    public void test04ForFindAllRepositoryFocus() {
        List<RepositoryFocus> repositoryFocusList = repositoryFocusService.findAllRepositoryFocus();

        assertNotNull(repositoryFocusList);
    }

    @Test
    public void test05ForDeleteRepositoryFocus(){
        repositoryFocusService.deleteRepositoryFocus(id);
    }
}