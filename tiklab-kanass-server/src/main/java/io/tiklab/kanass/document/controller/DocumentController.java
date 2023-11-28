package io.tiklab.kanass.document.controller;

import io.tiklab.kanass.support.model.RecentQuery;
import io.tiklab.kanass.document.model.WikiDocument;
import io.tiklab.kanass.document.model.DocumentQuery;
import io.tiklab.postin.annotation.Api;
import io.tiklab.kanass.document.service.DocumentService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DocumentController
 */
@RestController
@RequestMapping("/document")
@Api(name = "DocumentController",desc = "文档管理")
public class DocumentController {

    private static Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;


    @RequestMapping(path="/createDocument",method = RequestMethod.POST)
    @ApiMethod(name = "createDocument",desc = "创建文档")
    @ApiParam(name = "document",desc = "document",required = true)
    public Result<String> createDocument(@RequestBody @NotNull @Valid WikiDocument wikiDocument){
        String id = documentService.createDocument(wikiDocument);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateDocument",method = RequestMethod.POST)
    @ApiMethod(name = "updateDocument",desc = "修改文档")
    @ApiParam(name = "document",desc = "document",required = true)
    public Result<Void> updateDocument(@RequestBody @NotNull @Valid WikiDocument wikiDocument){
        documentService.updateDocument(wikiDocument);

        return Result.ok();
    }


    @RequestMapping(path="/deleteDocument",method = RequestMethod.POST)
    @ApiMethod(name = "deleteDocument",desc = "通过id删除文档")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteDocument(@NotNull String id){
        documentService.deleteDocument(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteDocumentAndSort",method = RequestMethod.POST)
    @ApiMethod(name = "deleteDocument",desc = "通过id删除文档")
    @ApiParam(name = "wikiDocument",desc = "wikiDocument",required = true)
    public Result<Void> deleteDocumentAndSort(@RequestBody @Valid @NotNull WikiDocument wikiDocument){
        documentService.deleteDocumentAndSort(wikiDocument);
        return Result.ok();
    }


    @RequestMapping(path="/findDocument",method = RequestMethod.POST)
    @ApiMethod(name = "findDocument",desc = "通过id查询文档")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiDocument> findDocument(@NotNull String id){
        WikiDocument wikiDocument = documentService.findDocument(id);

        return Result.ok(wikiDocument);
    }

    @RequestMapping(path="/findAllDocument",method = RequestMethod.POST)
    @ApiMethod(name = "findAllDocument",desc = "findAllDocument")
    public Result<List<WikiDocument>> findAllDocument(){
        List<WikiDocument> wikiDocumentList = documentService.findAllDocument();

        return Result.ok(wikiDocumentList);
    }


    @RequestMapping(path = "/findDocumentList",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentList",desc = "findDocumentList")
    @ApiParam(name = "documentQuery",desc = "documentQuery",required = true)
    public Result<List<WikiDocument>> findDocumentList(@RequestBody @Valid @NotNull DocumentQuery documentQuery){
        List<WikiDocument> wikiDocumentList = documentService.findDocumentList(documentQuery);

        return Result.ok(wikiDocumentList);
    }


    @RequestMapping(path = "/findDocumentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentPage",desc = "findDocumentPage")
    @ApiParam(name = "documentQuery",desc = "documentQuery",required = true)
    public Result<Pagination<WikiDocument>> findDocumentPage(@RequestBody @Valid @NotNull DocumentQuery documentQuery){
        Pagination<WikiDocument> pagination = documentService.findDocumentPage(documentQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findDocumentCount",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentCount",desc = "findDocumentPage")
    @ApiParam(name = "documentQuery",desc = "documentQuery",required = true)
    public Result<Integer> findDocumentCount(@RequestBody @Valid @NotNull DocumentQuery documentQuery){
        Integer documentCount = documentService.findDocumentCount(documentQuery);

        return Result.ok(documentCount);
    }

    @RequestMapping(path = "/findDocuementByKeyWork",method = RequestMethod.POST)
    @ApiMethod(name = "findDocuementByKeyWork",desc = "findDocuementByKeyWork")
    @ApiParam(name = "keyWord",desc = "keyWord",required = true)
    public Result<List<WikiDocument>> findDocuementByKeyWork(@NotNull String keyWord){
        List<WikiDocument> docuementByKeyWork = documentService.findDocuementByKeyWork(keyWord);

        return Result.ok(docuementByKeyWork);
    }


    @RequestMapping(path="/findDocumentById",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentById",desc = "通过id查询")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiDocument> findDocumentById(@NotNull String id){
        WikiDocument wikiDocument = documentService.findDocumentById(id);

        return Result.ok(wikiDocument);
    }

    @RequestMapping(path="/findRecentDocumentList",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentDocumentList",desc = "通过id查询")
    @ApiParam(name = "recentQuery",desc = "recentQuery",required = true)
    public Result<WikiDocument> findRecentDocumentList(@RequestBody @Valid @NotNull RecentQuery recentQuery){
        List<WikiDocument> recentDocumentList = documentService.findRecentDocumentList(recentQuery);

        return Result.ok(recentDocumentList);
    }
    @RequestMapping(path="/view",method = RequestMethod.POST)
    @ApiMethod(name = "view",desc = "通过id查询")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiDocument> view(@NotNull String id){
        WikiDocument wikiDocument = documentService.findDocumentById(id);

        return Result.ok(wikiDocument);
    }

}
