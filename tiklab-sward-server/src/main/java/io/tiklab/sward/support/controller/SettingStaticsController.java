package io.tiklab.sward.support.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.sward.support.service.SettingStaticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/setting")
@Api(name = "SettingStaticsController",desc = "SettingStaticsController")
public class SettingStaticsController {
    private static Logger logger = LoggerFactory.getLogger(SettingStaticsController.class);

    @Autowired
    private SettingStaticsService settingStaticsService;

    @RequestMapping(path="/findOrgaNum",method = RequestMethod.POST)
    //@ApiMethod(name = "findOrgaNum",desc = "设置首页统计各个模块数量")
    public Result<HashMap<String, Object>> findOrgaNum(){
        HashMap<String, Object> orgaNum = settingStaticsService.findOrgaNum();

        return Result.ok(orgaNum);
    }
}
