package io.tiklab.sward.repository.controller;

import io.tiklab.sward.repository.service.KanassService;
import io.tiklab.sward.support.model.Project;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @pi.protocol: http
 * @pi.groupName: 项目管理
 */
@RestController
@RequestMapping("/project")
@Api(name = "KanassController",desc = "项目管理")
public class KanassController {

    private static Logger logger = LoggerFactory.getLogger(KanassController.class);

    @Autowired
    KanassService kanassService;
    /**
     * @pi.name:查找所有项目
     * @pi.path:/project/findAllProject
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllProject",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProject",desc = "查找所有项目")
    public Result<List<Project>> findAllProject(){
        List<Project> projectList = kanassService.findAllProject();

        return Result.ok(projectList);
    }


}
