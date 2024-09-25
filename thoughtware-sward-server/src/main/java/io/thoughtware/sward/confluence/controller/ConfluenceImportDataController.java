package io.thoughtware.sward.confluence.controller;

import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.core.Result;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.sward.confluence.service.ConfluenceImportData719Service;
import io.thoughtware.sward.confluence.service.ConfluenceImportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * jira 数据导入
 */

@RestController
@RequestMapping("/importData")
    @Api(name = "ConfluenceImportDataController",desc = "ConfluenceImportDataController")
public class ConfluenceImportDataController {

    @Autowired
    ConfluenceImportDataService confluenceImportDataService;

    @Autowired
    ConfluenceImportData719Service confluenceImportData719Service;

    @RequestMapping(path="/importConfluenceData",method = RequestMethod.POST)
    public Result<Void> importConfluenceData(@RequestParam("uploadFile")MultipartFile uploadFile){
        if (uploadFile == null) {
            throw new ApplicationException("文件不能为空");
        }else {
            try {
                InputStream inputStream = uploadFile.getInputStream();
                confluenceImportDataService.importJiraData(inputStream);
                return Result.ok();
            } catch (IOException e) {
                throw new ApplicationException(e);
            }
        }
    }

    @RequestMapping(path="/findCfInputSchedule",method = RequestMethod.POST)
    @ApiMethod(name = "findCfInputSchedule",desc = "导入jire数据")
    public Result<Map<String, Object>> findJiraInputSchedule(){
        Map<String, Object> jiraInputSchedule = confluenceImportDataService.findCfInputSchedule();
        return Result.ok(jiraInputSchedule);
    }

}
