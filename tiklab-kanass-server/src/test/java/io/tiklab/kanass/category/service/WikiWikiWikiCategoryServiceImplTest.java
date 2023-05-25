package io.tiklab.kanass.category.service;

import io.tiklab.kanass.category.model.WikiCategory;
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
public class WikiWikiWikiCategoryServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WikiWikiWikiCategoryServiceImplTest.class);

    @Autowired
    WikiCategoryService wikiCategoryService;

    static String id;

    @Test
    public void test01ForSaveCategory() {
        WikiCategory wikiCategory = JMockit.mock(WikiCategory.class);

        id = wikiCategoryService.createCategory(wikiCategory);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateCategory(){
        WikiCategory wikiCategory = JMockit.mock(WikiCategory.class);
        wikiCategory.setId(id);

        wikiCategoryService.updateCategory(wikiCategory);
    }

    @Test
    public void test03ForFindCategory() {
        WikiCategory wikiCategory = wikiCategoryService.findCategory(id);

        assertNotNull(wikiCategory);
    }

    @Test
    public void test04ForFindAllCategory() {
        List<WikiCategory> wikiCategoryList = wikiCategoryService.findAllCategory();

        assertNotNull(wikiCategoryList);
    }

    @Test
    public void test05ForDeleteCategory(){
        wikiCategoryService.deleteCategory(id);
    }
}