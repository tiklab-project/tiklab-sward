package io.tiklab.kanass.repository.controller;

import io.tiklab.kanass.repository.service.TeamWireService;
import io.tiklab.kanass.support.model.Project;
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
@Api(name = "TeamWireController",desc = "项目管理")
public class TeamWireController {

    private static Logger logger = LoggerFactory.getLogger(TeamWireController.class);

    @Autowired
    TeamWireService teamWireService;
    /**
     * @pi.name:查找所有项目
     * @pi.path:/project/findAllProject
     * @pi.methodType:post
     * @pi.request-type:formdata
     */
    @RequestMapping(path="/findAllProject",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProject",desc = "查找所有项目")
    public Result<List<Project>> findAllProject(){
        List<Project> projectList = teamWireService.findAllProject();

        return Result.ok(projectList);
    }


}
