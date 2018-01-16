package com.raythonsoft.upload.controller.project;

import com.raythonsoft.upload.core.Result;
import com.raythonsoft.upload.core.ResultGenerator;
import com.raythonsoft.upload.exception.ProjectExistException;
import com.raythonsoft.upload.pojo.model.ProjectItem;
import com.raythonsoft.upload.service.ProjectItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * Created by Anur IjuoKaruKas on 2017/11/2.
 * Description : 新建项目
 */
@Api(produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectItemService projectItemService;

    @ApiOperation(value = "新建项目")
    @PostMapping
    public Result genProject(@RequestParam String project, @RequestParam String authUrl, @RequestParam List<String> paramList, @RequestParam String test) {
        if (!test.equals("anurnomeru")) {
            return ResultGenerator.genFailResult("调皮！", "???");
        }

        if (projectItemService.findByProject(project) != null) {
            throw new ProjectExistException();
        }

        ProjectItem projectItem = new ProjectItem();
        projectItem.setAuthUrl(authUrl);
        projectItem.setProject(project);
        projectItem.setPramList(paramList);
        return ResultGenerator.genSuccessResult(projectItemService.save(projectItem));
    }
}
