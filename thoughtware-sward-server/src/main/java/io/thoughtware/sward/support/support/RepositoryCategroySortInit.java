package io.thoughtware.sward.support.support;

import io.thoughtware.sward.category.model.WikiCategory;
import io.thoughtware.sward.repository.service.WikiRepositoryService;
import io.thoughtware.sward.category.model.WikiCategoryQuery;
import io.thoughtware.sward.category.service.WikiCategoryService;
import io.thoughtware.sward.document.model.DocumentQuery;
import io.thoughtware.sward.document.model.WikiDocument;
import io.thoughtware.sward.document.service.DocumentService;
import io.thoughtware.sward.repository.model.WikiRepository;
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
        List<WikiRepository> repositoryList = wikiRepositoryService.findRepositoryList("2023-11-10");
        for (WikiRepository wikiRepository : repositoryList) {
            String id = wikiRepository.getId();
            WikiCategoryQuery wikiCategoryQuery = new WikiCategoryQuery();
            wikiCategoryQuery.setRepositoryId(id);
            wikiCategoryQuery.setParentWikiCategoryIsNull(true);
            List<WikiCategory> categoryList = wikiCategoryService.findCategoryList(wikiCategoryQuery);

            DocumentQuery documentQuery = new DocumentQuery();
            documentQuery.setRepositoryId(id);
            documentQuery.setParentWikiCategoryIsNull(true);
            List<WikiDocument> documentList = wikiDocumentService.findDocumentList(documentQuery);
            updateSort(categoryList, documentList, 1);
        }
    }

    public void updateSort(List<WikiCategory> categoryList, List<WikiDocument> documentList, Integer dimension){
        if(categoryList.size() == 0 && documentList.size() == 0){
            return;
        }
        Integer size = new Integer(0);
        if(categoryList.size() > 0){
            for (WikiCategory wikiCategory : categoryList) {
                String id = wikiCategory.getId();
                wikiCategory.setSort(size);
                wikiCategory.setDimension(dimension);
                wikiCategoryService.updateCategoryInit(wikiCategory);
                ++size;

                WikiCategoryQuery wikiCategoryQuery = new WikiCategoryQuery();
                wikiCategoryQuery.setParentWikiCategory(id);
                List<WikiCategory> categoryList1 = wikiCategoryService.findCategoryList(wikiCategoryQuery);

                DocumentQuery documentQuery = new DocumentQuery();
                documentQuery.setCategoryId(id);
                List<WikiDocument> documentList1 = wikiDocumentService.findDocumentList(documentQuery);
                updateSort(categoryList1, documentList1, dimension+1);
            }
        }
        if(documentList.size() >0){
            for (WikiDocument wikiDocument : documentList) {
                wikiDocument.setSort(size);
                wikiDocument.setDimension(dimension);
                wikiDocumentService.updateDocumentInit(wikiDocument);
                ++size;
            }
        }

    }
}
