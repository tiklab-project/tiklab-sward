package io.tiklab.sward.document.document.controller;

import io.tiklab.sward.document.document.model.DocumentTemplate;
import io.tiklab.sward.document.document.model.DocumentTemplateQuery;
import io.tiklab.sward.document.document.service.DocumentTemplateService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
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
 * DocumentTemplateController
 */
@RestController
@RequestMapping("/documentTemplate")
public class DocumentTemplateController {

    @Autowired
    private DocumentTemplateService documentTemplateService;

    @RequestMapping(path="/createDocumentTemplate",method = RequestMethod.POST)
    public Result<String> createDocumentTemplate(@RequestBody @NotNull @Valid DocumentTemplate documentTemplate){
        String id = documentTemplateService.createDocumentTemplate(documentTemplate);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateDocumentTemplate",method = RequestMethod.POST)
    public Result<Void> updateDocumentTemplate(@RequestBody @NotNull @Valid DocumentTemplate documentTemplate){
        documentTemplateService.updateDocumentTemplate(documentTemplate);

        return Result.ok();
    }

    @RequestMapping(path="/deleteDocumentTemplate",method = RequestMethod.POST)
    public Result<Void> deleteDocumentTemplate(@NotNull String id){
        documentTemplateService.deleteDocumentTemplate(id);

        return Result.ok();
    }

    @RequestMapping(path="/findDocumentTemplate",method = RequestMethod.POST)
    public Result<DocumentTemplate> findDocumentTemplate(@NotNull String id){
        DocumentTemplate documentTemplate = documentTemplateService.findDocumentTemplate(id);

        return Result.ok(documentTemplate);
    }

    @RequestMapping(path="/findAllDocumentTemplate",method = RequestMethod.POST)
    public Result<List<DocumentTemplate>> findAllDocumentTemplate(){
        List<DocumentTemplate> documentTemplateList = documentTemplateService.findAllDocumentTemplate();

        return Result.ok(documentTemplateList);
    }

    @RequestMapping(path = "/findDocumentTemplateList",method = RequestMethod.POST)
    public Result<List<DocumentTemplate>> findDocumentTemplateList(@RequestBody @Valid @NotNull DocumentTemplateQuery documentTemplateQuery){
        List<DocumentTemplate> documentTemplateList = documentTemplateService.findDocumentTemplateList(documentTemplateQuery);

        return Result.ok(documentTemplateList);
    }

    @RequestMapping(path = "/findDocumentTemplatePage",method = RequestMethod.POST)
    public Result<Pagination<DocumentTemplate>> findDocumentTemplatePage(@RequestBody @Valid @NotNull DocumentTemplateQuery documentTemplateQuery){
        Pagination<DocumentTemplate> pagination = documentTemplateService.findDocumentTemplatePage(documentTemplateQuery);

        return Result.ok(pagination);
    }

}
