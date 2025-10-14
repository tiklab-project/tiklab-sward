package io.tiklab.sward.support.support.controller;

import io.tiklab.core.Result;
import io.tiklab.sward.support.support.model.SwardFile;
import io.tiklab.sward.support.support.service.FileLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sward/limit")
public class FileLimitController {


    @Autowired
    FileLimitService fileLimitService;

    @RequestMapping(path="/findFileLimit",method = RequestMethod.POST)
    public Result<SwardFile> findFileLimit(){
        SwardFile swardFile = fileLimitService.findFileLimit();

        return Result.ok(swardFile);
    }



}
