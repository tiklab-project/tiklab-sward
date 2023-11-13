package io.tiklab.kanass.support.support;

import io.tiklab.kanass.category.model.WikiCategory;
import io.tiklab.kanass.category.model.WikiCategoryQuery;
import io.tiklab.kanass.category.service.WikiCategoryService;
import io.tiklab.kanass.document.model.DocumentQuery;
import io.tiklab.kanass.document.model.WikiDocument;
import io.tiklab.kanass.document.service.DocumentService;
import io.tiklab.kanass.repository.model.WikiRepository;
import io.tiklab.kanass.repository.service.WikiRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
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
        initWorkTypeEpicForProject();
    }

    public void initWorkTypeEpicForProject(){
        List<WikiRepository> repositoryList = wikiRepositoryService.findRepositoryList("2023-10-27");
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
