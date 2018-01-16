package com.raythonsoft.upload.controller.auth;

import com.raythonsoft.upload.core.Result;
import com.raythonsoft.upload.core.ResultGenerator;
import com.raythonsoft.upload.helper.NetworkHelper;
import com.raythonsoft.upload.util.AuthUtil;
import com.raythonsoft.upload.util.NetworkUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Anur IjuoKaruKas on 2017/11/23.
 * Description : 模拟认证
 */
@Api(produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @ApiOperation(value = "模拟鉴权")
    @GetMapping("/auth")
    public Result get(String id, String name) {
        logger.info("id: {} = name: {}", id, name);
        if (1 == 1) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("FAIL", "FAIL");
        }
    }

    @ApiOperation(value = "获取token")
    @GetMapping("/gen/token")
    public String get(String resourcesId) {
        String s = "?id=122&name=neko";
        String ip = NetworkHelper.getIpAddress();
        String result = AuthUtil.enCode(s, ip, resourcesId);
        System.out.println(AuthUtil.deCode(result, ip, resourcesId));
        return result;
    }
}