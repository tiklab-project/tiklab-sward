package net.tiklab.kanass.document.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.Result;
import net.tiklab.core.page.Pagination;
import net.tiklab.kanass.document.model.DocumentRecent;
import net.tiklab.kanass.document.model.DocumentRecentQuery;
import net.tiklab.kanass.document.service.DocumentRecentService;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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
 * DocumentRecentController
 */
@RestController
@RequestMapping("/documentRecent")
@Api(name = "DocumentRecentController",desc = "DocumentRecentController")
public class DocumentRecentController {

    private static Logger logger = LoggerFactory.getLogger(DocumentRecentController.class);

    @Autowired
    private DocumentRecentService documentRecentService;

    @RequestMapping(path="/createDocumentRecent",method = RequestMethod.POST)
    @ApiMethod(name = "createDocumentRecent",desc = "createDocumentRecent")
    @ApiParam(name = "documentRecent",desc = "documentRecent",required = true)
    public Result<String> createDocumentRecent(@RequestBody @NotNull @Valid DocumentRecent documentRecent){
        String id = documentRecentService.createDocumentRecent(documentRecent);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateDocumentRecent",method = RequestMethod.POST)
    @ApiMethod(name = "updateDocumentRecent",desc = "updateDocumentRecent")
    @ApiParam(name = "documentRecent",desc = "documentRecent",required = true)
    public Result<Void> updateDocumentRecent(@RequestBody @NotNull @Valid DocumentRecent documentRecent){
        documentRecentService.updateDocumentRecent(documentRecent);

        return Result.ok();
    }

    @RequestMapping(path="/deleteDocumentRecent",method = RequestMethod.POST)
    @ApiMethod(name = "deleteDocumentRecent",desc = "deleteDocumentRecent")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteDocumentRecent(@NotNull String id){
        documentRecentService.deleteDocumentRecent(id);

        return Result.ok();
    }

    @RequestMapping(path="/findDocumentRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentRecent",desc = "findDocumentRecent")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<DocumentRecent> findDocumentRecent(@NotNull String id){
        DocumentRecent documentRecent = documentRecentService.findDocumentRecent(id);

        return Result.ok(documentRecent);
    }

    @RequestMapping(path="/findAllDocumentRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findAllDocumentRecent",desc = "findAllDocumentRecent")
    public Result<List<DocumentRecent>> findAllDocumentRecent(){
        List<DocumentRecent> documentRecentList = documentRecentService.findAllDocumentRecent();

        return Result.ok(documentRecentList);
    }

    @RequestMapping(path = "/findDocumentRecentList",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentRecentList",desc = "findDocumentRecentList")
    @ApiParam(name = "documentRecentQuery",desc = "documentRecentQuery",required = true)
    public Result<List<DocumentRecent>> findDocumentRecentList(@RequestBody @Valid @NotNull DocumentRecentQuery documentRecentQuery){
        List<DocumentRecent> documentRecentList = documentRecentService.findDocumentRecentList(documentRecentQuery);

        return Result.ok(documentRecentList);
    }

    @RequestMapping(path = "/findDocumentRecentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentRecentPage",desc = "findDocumentRecentPage")
    @ApiParam(name = "documentRecentQuery",desc = "documentRecentQuery",required = true)
    public Result<Pagination<DocumentRecent>> findDocumentRecentPage(@RequestBody @Valid @NotNull DocumentRecentQuery documentRecentQuery){
        Pagination<DocumentRecent> pagination = documentRecentService.findDocumentRecentPage(documentRecentQuery);

        return Result.ok(pagination);
    }

}
