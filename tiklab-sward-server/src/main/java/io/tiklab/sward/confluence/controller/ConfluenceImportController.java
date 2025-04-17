package io.tiklab.sward.confluence.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.core.Result;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.sward.confluence.model.ConfluenceSpace;
import io.tiklab.sward.confluence.model.ConfluenceVersion;
import io.tiklab.sward.confluence.model.ImportStatus;
import io.tiklab.sward.confluence.service.ConfluenceImportData719Service;
import io.tiklab.sward.confluence.service.ConfluenceImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Confluence数据导入
 */

@RestController
@RequestMapping("/import/confluence")
@Api(name = "ConfluenceImportController",desc = "Confluence数据导入")
public class ConfluenceImportController {

    @Autowired
    ConfluenceImportService confluenceImportService;

    @Autowired
    ConfluenceImportData719Service confluenceImportData719Service;

    @Value("${unzip.path}")
    String unzipAddress;

    @RequestMapping(path="/uploadFile",method = RequestMethod.POST)
    public Result<String> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile){

        if (uploadFile == null || uploadFile.isEmpty()) {
            throw new ApplicationException("上传文件不能为空");
        }

        // 2. 校验后缀
        String originalFilename = uploadFile.getOriginalFilename();
        if (originalFilename == null || !originalFilename.toLowerCase().endsWith(".zip")) {
            throw new ApplicationException("仅支持上传 .zip 格式的文件");
        }

        // 3. 设定保存目录
        File saveDir = new File(unzipAddress);
        if (!saveDir.exists()) {
            saveDir.mkdirs(); // 创建目录
        }

        // 4. 构建目标文件路径
        File destFile = new File(saveDir, originalFilename);

        try {
            // 5. 保存文件
            uploadFile.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException("文件保存失败!");
        }
        return Result.ok(destFile.getAbsolutePath());
    }

    @RequestMapping(path="/validConfluenceVersion",method = RequestMethod.POST)
    public Result<ConfluenceVersion> validConfluenceVersion(String confluenceFileAddress){
        ConfluenceVersion confluenceVersion = confluenceImportService.validConfluenceVersion(confluenceFileAddress);
        return Result.ok(confluenceVersion);
    }

    @RequestMapping(path="/importJiraData",method = RequestMethod.POST)
    public Result<Void> importJiraData(String confluenceFileAddress){
        confluenceImportService.importJiraData(confluenceFileAddress);
        return Result.ok();
    }

    @RequestMapping(path="/analysisEntityXml",method = RequestMethod.POST)
    public Result<List<ConfluenceSpace>> analysisEntityXml(String confluenceAddress){
        List<ConfluenceSpace> spaceList = confluenceImportService.analysisEntityXml(confluenceAddress);
        return Result.ok(spaceList);
    }

    @RequestMapping(path="/findCfInputSchedule",method = RequestMethod.POST)
    public Result<ImportStatus> findJiraInputSchedule(){
        ImportStatus status = confluenceImportService.findCfInputSchedule();
        return Result.ok(status);
    }

}
