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
public class RepositoryCategroyTreePathInit implements ApplicationRunner {

    @Autowired
    WikiRepositoryService wikiRepositoryService;

    @Autowired
    WikiCategoryService wikiCategoryService;

    @Autowired
    DocumentService wikiDocumentService;

    private static Logger logger = LoggerFactory.getLogger(RepositoryCategroyTreePathInit.class);
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        initTreePath();
    }

    public void initTreePath(){
        List<WikiRepository> repositoryList = wikiRepositoryService.findAllRepository();
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
            updateTreePath(categoryList, documentList, null);
        }
    }

    public void updateTreePath(List<WikiCategory> categoryList, List<WikiDocument> documentList, String treePath){
        if(categoryList.size() == 0 && documentList.size() == 0){
            return;
        }
        Integer size = new Integer(0);
        if(categoryList.size() > 0){
            for (WikiCategory wikiCategory : categoryList) {
                String id = wikiCategory.getId();
                wikiCategory.setTreePath(treePath);
                wikiCategoryService.updateCategoryInit(wikiCategory);

                WikiCategoryQuery wikiCategoryQuery = new WikiCategoryQuery();
                wikiCategoryQuery.setParentWikiCategory(id);
                List<WikiCategory> categoryList1 = wikiCategoryService.findCategoryList(wikiCategoryQuery);

                DocumentQuery documentQuery = new DocumentQuery();
                documentQuery.setCategoryId(id);
                List<WikiDocument> documentList1 = wikiDocumentService.findDocumentList(documentQuery);

                String path = new String();
                if(treePath != null){
                    path = treePath + id + ";";
                }else {
                    path = id + ";";
                }

                updateTreePath(categoryList1, documentList1, path);
            }
        }
        if(documentList.size() >0){
            for (WikiDocument wikiDocument : documentList) {
                wikiDocument.setTreePath(treePath);
                wikiDocumentService.updateDocumentInit(wikiDocument);
                ++size;
            }
        }

    }
}