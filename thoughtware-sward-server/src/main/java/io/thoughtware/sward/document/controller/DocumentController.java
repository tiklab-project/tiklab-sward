package io.thoughtware.sward.document.controller;

import io.thoughtware.sward.support.model.RecentQuery;
import io.thoughtware.sward.document.model.WikiDocument;
import io.thoughtware.sward.document.model.DocumentQuery;
import io.tiklab.postin.annotation.Api;
import io.thoughtware.sward.document.service.DocumentService;
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
 * @pi.protocol: http
 * @pi.groupName: 文档控制器
 */
@RestController
@RequestMapping("/document")
@Api(name = "DocumentController",desc = "文档管理")
public class DocumentController {

    private static Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;

    /**
     * @pi.name:创建文档
     * @pi.path:/document/createDocument
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WikiDocument
     */
    @RequestMapping(path="/createDocument",method = RequestMethod.POST)
    @ApiMethod(name = "createDocument",desc = "创建文档")
    @ApiParam(name = "document",desc = "document",required = true)
    public Result<String> createDocument(@RequestBody @NotNull @Valid WikiDocument wikiDocument){
        String id = documentService.createDocument(wikiDocument);

        return Result.ok(id);
    }

    /**
     * @pi.name:创建文档
     * @pi.path:/document/updateDocument
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=WikiDocument
     */
    @RequestMapping(path="/updateDocument",method = RequestMethod.POST)
    @ApiMethod(name = "updateDocument",desc = "修改文档")
    @ApiParam(name = "document",desc = "document",required = true)
    public Result<Void> updateDocument(@RequestBody @NotNull @Valid WikiDocument wikiDocument){
        documentService.updateDocument(wikiDocument);

        return Result.ok();
    }


    /**
     * @pi.name:通过id删除文档
     * @pi.path:/document/deleteDocument
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/deleteDocument",method = RequestMethod.POST)
    @ApiMethod(name = "deleteDocument",desc = "通过id删除文档")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteDocument(@NotNull String id){
        documentService.deleteDocument(id);

        return Result.ok();
    }

    /**
     * @pi.name:通过id删除文档
     * @pi.path:/document/deleteDocumentAndSort
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: model=WikiDocument
     */
    @RequestMapping(path="/deleteDocumentAndSort",method = RequestMethod.POST)
    @ApiMethod(name = "deleteDocument",desc = "通过id删除文档")
    @ApiParam(name = "wikiDocument",desc = "wikiDocument",required = true)
    public Result<Void> deleteDocumentAndSort(@RequestBody @Valid @NotNull WikiDocument wikiDocument){
        documentService.deleteDocumentAndSort(wikiDocument);
        return Result.ok();
    }

    /**
     * @pi.name:通过id查询文档
     * @pi.path:/document/findDocument
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/findDocument",method = RequestMethod.POST)
    @ApiMethod(name = "findDocument",desc = "通过id查询文档")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiDocument> findDocument(@NotNull String id){
        WikiDocument wikiDocument = documentService.findDocument(id);

        return Result.ok(wikiDocument);
    }

    /**
     * @pi.name:通过id删除文档
     * @pi.path:/document/findAllDocument
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllDocument",method = RequestMethod.POST)
    @ApiMethod(name = "findAllDocument",desc = "findAllDocument")
    public Result<List<WikiDocument>> findAllDocument(){
        List<WikiDocument> wikiDocumentList = documentService.findAllDocument();

        return Result.ok(wikiDocumentList);
    }


    /**
     * @pi.name:查找文档列表
     * @pi.path:/document/findDocumentList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=DocumentQuery
     */
    @RequestMapping(path = "/findDocumentList",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentList",desc = "查找文档列表")
    @ApiParam(name = "documentQuery",desc = "documentQuery",required = true)
    public Result<List<WikiDocument>> findDocumentList(@RequestBody @Valid @NotNull DocumentQuery documentQuery){
        List<WikiDocument> wikiDocumentList = documentService.findDocumentList(documentQuery);

        return Result.ok(wikiDocumentList);
    }

    /**
     * @pi.name:根据分页查找文档
     * @pi.path:/document/findDocumentPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=DocumentQuery
     */
    @RequestMapping(path = "/findDocumentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentPage",desc = "根据分页查找文档")
    @ApiParam(name = "documentQuery",desc = "documentQuery",required = true)
    public Result<Pagination<WikiDocument>> findDocumentPage(@RequestBody @Valid @NotNull DocumentQuery documentQuery){
        Pagination<WikiDocument> pagination = documentService.findDocumentPage(documentQuery);

        return Result.ok(pagination);
    }

    /**
     * @pi.name:查找文档数量
     * @pi.path:/document/findDocumentCount
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=DocumentQuery
     */
    @RequestMapping(path = "/findDocumentCount",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentCount",desc = "findDocumentPage")
    @ApiParam(name = "documentQuery",desc = "documentQuery",required = true)
    public Result<Integer> findDocumentCount(@RequestBody @Valid @NotNull DocumentQuery documentQuery){
        Integer documentCount = documentService.findDocumentCount(documentQuery);

        return Result.ok(documentCount);
    }

    /**
     * @pi.name:根据关键字查找文档数量
     * @pi.path:/document/findDocuementByKeyWork
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=keyWord;dataType=string;value=keyWord;
     */
    @RequestMapping(path = "/findDocuementByKeyWork",method = RequestMethod.POST)
    @ApiMethod(name = "findDocuementByKeyWork",desc = "findDocuementByKeyWork")
    @ApiParam(name = "keyWord",desc = "keyWord",required = true)
    public Result<List<WikiDocument>> findDocuementByKeyWork(@NotNull String keyWord){
        List<WikiDocument> docuementByKeyWork = documentService.findDocuementByKeyWork(keyWord);

        return Result.ok(docuementByKeyWork);
    }

    /**
     * @pi.name:通过id查询
     * @pi.path:/document/findDocumentById
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/findDocumentById",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentById",desc = "通过id查询")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiDocument> findDocumentById(@NotNull String id){
        WikiDocument wikiDocument = documentService.findDocumentById(id);

        return Result.ok(wikiDocument);
    }

    /**
     * @pi.name:最近查看的文档
     * @pi.path:/document/findRecentDocumentList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=RecentQuery
     */
    @RequestMapping(path="/findRecentDocumentList",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentDocumentList",desc = "最近查看的文档")
    @ApiParam(name = "recentQuery",desc = "recentQuery",required = true)
    public Result<WikiDocument> findRecentDocumentList(@RequestBody @Valid @NotNull RecentQuery recentQuery){
        List<WikiDocument> recentDocumentList = documentService.findRecentDocumentList(recentQuery);

        return Result.ok(recentDocumentList);
    }

    /**
     * @pi.name:分享的文档
     * @pi.path:/document/view
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/view",method = RequestMethod.POST)
    @ApiMethod(name = "view",desc = "通过id查询")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WikiDocument> view(@NotNull String id){
        WikiDocument wikiDocument = documentService.findDocumentById(id);

        return Result.ok(wikiDocument);
    }

}
