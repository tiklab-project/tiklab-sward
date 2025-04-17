package io.tiklab.sward.document.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.sward.document.model.DocumentVersion;
import io.tiklab.sward.document.model.DocumentVersionQuery;
import io.tiklab.sward.document.service.DocumentVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DocumentVersionController
 */
@RestController
@RequestMapping("/documentVersion")
@Api(name = "DocumentVersionController",desc = "文档版本管理")
public class DocumentVersionController {


    @Autowired
    private DocumentVersionService versionService;

    @RequestMapping(path="/createDocumentVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "createDocumentVersion",desc = "创建版本")
    //@ApiParam(name = "documentVersion",desc = "documentVersion",required = true)
    public Result<String> createDocumentVersion(@RequestBody @NotNull @Valid DocumentVersion documentVersion){
        String id = versionService.createDocumentVersion(documentVersion);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateDocumentVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "updateDocumentVersion",desc = "更新版本")
    //@ApiParam(name = "documentVersion",desc = "documentVersion",required = true)
    public Result<Void> updateDocumentVersion(@RequestBody @NotNull @Valid DocumentVersion documentVersion){
        versionService.updateDocumentVersion(documentVersion);

        return Result.ok();
    }

    @RequestMapping(path="/deleteDocumentVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteDocumentVersion",desc = "删除版本")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteDocumentVersion(@NotNull String id){
        versionService.deleteDocumentVersion(id);

        return Result.ok();
    }

    @RequestMapping(path="/findDocumentVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "findDocumentVersion",desc = "通过id查询")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<DocumentVersion> findDocumentVersion(@NotNull String id){
        DocumentVersion documentVersion = versionService.findDocumentVersion(id);

        return Result.ok(documentVersion);
    }

    @RequestMapping(path="/callbackDocument",method = RequestMethod.POST)
    //@ApiMethod(name = "findDocumentVersion",desc = "回滚版本")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> callbackDocument(@NotNull String id){
         versionService.callbackDocument(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAllDocumentVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllDocumentVersion",desc = "查询所有版本")
    public Result<List<DocumentVersion>> findAllDocumentVersion(){
        List<DocumentVersion> documentVersionList = versionService.findAllDocumentVersion();

        return Result.ok(documentVersionList);
    }

    @RequestMapping(path = "/findDocumentVersionList",method = RequestMethod.POST)
    //@ApiMethod(name = "findDocumentVersionList",desc = "根据查询对象查询版本")
    //@ApiParam(name = "documentVersionQuery",desc = "documentVersionQuery",required = true)
    public Result<List<DocumentVersion>> findDocumentVersionList(@RequestBody @Valid @NotNull DocumentVersionQuery documentVersionQuery){
        List<DocumentVersion> documentVersionList = versionService.findDocumentVersionList(documentVersionQuery);

        return Result.ok(documentVersionList);
    }


    @RequestMapping(path = "/findDocumentVersionPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findDocumentVersionPage",desc = "根据查询对象分页查询版本")
    //@ApiParam(name = "documentVersionQuery",desc = "documentVersionQuery",required = true)
    public Result<Pagination<DocumentVersion>> findDocumentVersionPage(@RequestBody @Valid @NotNull DocumentVersionQuery documentVersionQuery){
        Pagination<DocumentVersion> pagination = versionService.findDocumentVersionPage(documentVersionQuery);

        return Result.ok(pagination);
    }

}
