package com.raythonsoft.upload.controller.search;

import com.raythonsoft.upload.builder.ResourcesBuilder;
import com.raythonsoft.upload.core.Result;
import com.raythonsoft.upload.core.ResultGenerator;
import com.raythonsoft.upload.helper.NetworkHelper;
import com.raythonsoft.upload.pojo.model.Resources;
import com.raythonsoft.upload.service.ResourcesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Anur IjuoKaruKas on 2017/11/10.
 * Description : 搜索resources
 */
@Api(produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ResourcesService resourcesService;

    @ApiOperation(value = "搜索")
    @GetMapping
    public Result search(@RequestParam String project, HttpServletRequest request) {
        //FIXME 需要一个分页
        Resources resources = new ResourcesBuilder(project).setParamMapWithParameterMap(request.getParameterMap()).build();
        return ResultGenerator.genSuccessResult(resourcesService.findByResources(resources));
    }
}
