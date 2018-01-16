package com.raythonsoft.upload.controller.token;

import com.raythonsoft.upload.core.Result;
import com.raythonsoft.upload.core.ResultGenerator;
import com.raythonsoft.upload.exception.NoSuchProjectException;
import com.raythonsoft.upload.helper.NetworkHelper;
import com.raythonsoft.upload.pojo.dto.UploadTokenDto;
import com.raythonsoft.upload.pojo.model.UploadToken;
import com.raythonsoft.upload.service.ProjectItemService;
import com.raythonsoft.upload.service.UploadTokenService;
import com.raythonsoft.upload.util.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * Created by Anur IjuoKaruKas on 2017/11/2.
 * Description : 生成token
 */
@Api(produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/token")
public class GenUploadTokenController {

    @Autowired
    UploadTokenService uploadTokenService;

    @Autowired
    ProjectItemService projectItemService;

    @ApiOperation(value = "获取上传token")
    @PostMapping
    public Result genUploadToken(@Valid UploadTokenDto uploadTokenDto, BindingResult result) {
        Result validation = ValidationUtil.validate(result);
        if (validation != null) {
            return validation;
        }
        String ip = uploadTokenDto.getIp();
        String project = uploadTokenDto.getProject();

        if (projectItemService.findByProject(project) == null) {
            throw new NoSuchProjectException();
        }

        UploadToken uploadToken = new UploadToken(null, project, ip, uploadTokenDto.getSize(), uploadTokenDto.getEffectTimes(), uploadTokenDto.getFileTypeEnumList(), uploadTokenDto.getUploadTypeEnumList());
        String token = uploadTokenService.saveAndInit(uploadToken, uploadTokenDto.getValidTime());
        if (token == null) {
            return ResultGenerator.genFailResult("上传token队列已满，请稍后再试。", "QUEUE_FULL");
        }
        return ResultGenerator.genSuccessResult(token);
    }
}
