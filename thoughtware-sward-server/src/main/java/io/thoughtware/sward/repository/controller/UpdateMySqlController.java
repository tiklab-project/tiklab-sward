package io.thoughtware.sward.repository.controller;

import io.thoughtware.sward.repository.service.UpdateMySqlService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 最近访问的控制器
 */
@RestController
@RequestMapping("/updateMySql")
@Api(name = "UpdateMySqlController",desc = "UpdateMySqlController")
public class UpdateMySqlController {

    private static Logger logger = LoggerFactory.getLogger(UpdateMySqlController.class);

    @Autowired
    private UpdateMySqlService updateMySqlService;

    @RequestMapping(path="/updateAllData",method = RequestMethod.POST)
    @ApiMethod(name = "updateAllData")
    public void updateAllData(){
        updateMySqlService.updateAllData();
    }


}
