package com.doublekit.wiki.integration.cf.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.common.Result;
import com.doublekit.common.exception.DarthException;
import com.doublekit.wiki.integration.cf.service.ImportDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/importDate")
@Api(name = "ImportDateController",desc = "导入第三方数据数据")
public class ImportDateController {
    @Autowired
    ImportDateService importDateService;


    @RequestMapping(path="/importConfluenceDate",method = RequestMethod.POST)
    @ApiMethod(name = "importConfluenceDate",desc = "导入jire数据")
    @ApiParam(name = "uploadFile",desc = "压缩包文件",required = true)
    public Result importConfluenceDate(@RequestParam("uploadFile")MultipartFile uploadFile){
       // importDateService.importConfluenceDate(null);
        if (uploadFile == null) {
            throw new DarthException("uploadFile must not be null.");
        }else {
            try {
                InputStream inputStream = uploadFile.getInputStream();
                String date = importDateService.importConfluenceDate(inputStream);
                return Result.ok(date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.ok();
    }
}
