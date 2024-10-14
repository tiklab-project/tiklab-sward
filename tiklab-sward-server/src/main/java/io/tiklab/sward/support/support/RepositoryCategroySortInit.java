package io.tiklab.sward.support.support;

import io.tiklab.sward.category.model.WikiCategory;
import io.tiklab.sward.repository.service.WikiRepositoryService;
import io.tiklab.sward.category.service.WikiCategoryService;
import io.tiklab.sward.document.model.WikiDocument;
import io.tiklab.sward.document.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RepositoryCategroySortInit implements ApplicationRunner {

    @Autowired
    WikiRepositoryService wikiRepositoryService;

    @Autowired
    WikiCategoryService wikiCategoryService;

    @Autowired
    DocumentService wikiDocumentService;

    private static Logger logger = LoggerFactory.getLogger(RepositoryCategroySortInit.class);
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        System.out.println("");
//        initWorkTypeEpicForProject();
    }

    public void initWorkTypeEpicForProject(){
    }

    public void updateSort(List<WikiCategory> categoryList, List<WikiDocument> documentList, Integer dimension){


    }
}
