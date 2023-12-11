package io.thoughtware.sward.document.controller;

import io.thoughtware.sward.document.model.DocumentFocus;
import io.thoughtware.sward.document.model.DocumentFocusQuery;
import io.thoughtware.sward.document.service.DocumentFocusService;
import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * DocumentFocusController
 */
@RestController
@RequestMapping("/documentFocus")
@Api(name = "DocumentFocusController",desc = "DocumentFocusController")
public class DocumentFocusController {

    private static Logger logger = LoggerFactory.getLogger(DocumentFocusController.class);

    @Autowired
    private DocumentFocusService documentFocusService;

    @RequestMapping(path="/createDocumentFocus",method = RequestMethod.POST)
    @ApiMethod(name = "createDocumentFocus",desc = "createDocumentFocus")
    @ApiParam(name = "documentFocus",desc = "documentFocus",required = true)
    public Result<String> createDocumentFocus(@RequestBody @NotNull @Valid DocumentFocus documentFocus){
        String id = documentFocusService.createDocumentFocus(documentFocus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateDocumentFocus",method = RequestMethod.POST)
    @ApiMethod(name = "updateDocumentFocus",desc = "updateDocumentFocus")
    @ApiParam(name = "documentFocus",desc = "documentFocus",required = true)
    public Result<Void> updateDocumentFocus(@RequestBody @NotNull @Valid DocumentFocus documentFocus){
        documentFocusService.updateDocumentFocus(documentFocus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteDocumentFocus",method = RequestMethod.POST)
    @ApiMethod(name = "deleteDocumentFocus",desc = "deleteDocumentFocus")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteDocumentFocus(@NotNull String id){
        documentFocusService.deleteDocumentFocus(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteDocumentFocusByCondition",method = RequestMethod.POST)
    @ApiMethod(name = "deleteDocumentFocusByCondition",desc = "deleteDocumentFocusByCondition")
    @ApiParam(name = "documentFocusQuery",desc = "documentFocusQuery",required = true)
    public Result<Void> deleteDocumentFocusByCondition(@RequestBody @Valid @NotNull DocumentFocusQuery documentFocusQuery){
        documentFocusService.deleteDocumentFocusByCondition(documentFocusQuery);

        return Result.ok();
    }

    @RequestMapping(path="/findDocumentFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentFocus",desc = "findDocumentFocus")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<DocumentFocus> findDocumentFocus(@NotNull String id){
        DocumentFocus documentFocus = documentFocusService.findDocumentFocus(id);

        return Result.ok(documentFocus);
    }

    @RequestMapping(path="/findAllDocumentFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findAllDocumentFocus",desc = "findAllDocumentFocus")
    public Result<List<DocumentFocus>> findAllDocumentFocus(){
        List<DocumentFocus> documentFocusList = documentFocusService.findAllDocumentFocus();

        return Result.ok(documentFocusList);
    }

    @RequestMapping(path = "/findDocumentFocusList",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentFocusList",desc = "findDocumentFocusList")
    @ApiParam(name = "documentFocusQuery",desc = "documentFocusQuery",required = true)
    public Result<List<DocumentFocus>> findDocumentFocusList(@RequestBody @Valid @NotNull DocumentFocusQuery documentFocusQuery){
        List<DocumentFocus> documentFocusList = documentFocusService.findDocumentFocusList(documentFocusQuery);

        return Result.ok(documentFocusList);
    }

    @RequestMapping(path = "/findDocumentFocusPage",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentFocusPage",desc = "findDocumentFocusPage")
    @ApiParam(name = "documentFocusQuery",desc = "documentFocusQuery",required = true)
    public Result<Pagination<DocumentFocus>> findDocumentFocusPage(@RequestBody @Valid @NotNull DocumentFocusQuery documentFocusQuery){
        Pagination<DocumentFocus> pagination = documentFocusService.findDocumentFocusPage(documentFocusQuery);

        return Result.ok(pagination);
    }

}
