package com.raythonsoft.upload.controller.upload;

import com.raythonsoft.upload.builder.ResourcesBuilder;
import com.raythonsoft.upload.core.Result;
import com.raythonsoft.upload.core.ResultGenerator;
import com.raythonsoft.upload.enums.FileTypeEnum;
import com.raythonsoft.upload.helper.NetworkHelper;
import com.raythonsoft.upload.pojo.dto.ResourcesDto;
import com.raythonsoft.upload.pojo.model.PathModel;
import com.raythonsoft.upload.pojo.model.Resources;
import com.raythonsoft.upload.service.*;
import com.raythonsoft.upload.util.CastUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.raythonsoft.upload.common.ProjectConstruct.FILES_BASE_PATH;
import static com.raythonsoft.upload.common.ProjectConstruct.GRIDFS_SIZE;

/**
 * Created by Anur IjuoKaruKas on 2017/10/18.
 * Description : 上传
 */
@Api(produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/file")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @Autowired
    UploadTokenService uploadTokenService;

    @Autowired
    ResourcesService resourcesService;

    @Autowired
    GridFsService gridFsService;

    @ApiOperation(value = "上传")
    @PostMapping
    public Result UploadImg(@RequestParam MultipartFile files,
                            @RequestParam(required = false) String token,
                            @RequestParam(required = false, defaultValue = "bari") String project,
                            @RequestParam(required = false, defaultValue = "false") boolean lock,
                            @RequestParam(required = false, defaultValue = "default") String customPath,
                            @RequestParam(required = false, defaultValue = "false") boolean useGridFS, HttpServletRequest request) throws IOException {

        FileTypeEnum fileTypeEnum = uploadService.validateType(files);// 验证文件类型
        String ip = NetworkHelper.getIpAddress();// ip
        String type = fileTypeEnum.name().toLowerCase();// 类型 (小写)
        Long filesSize = files.getSize();// 大小

        uploadTokenService.validateToken(token, type, project, ip, filesSize);// 验证token

        ResourcesBuilder resourcesBuilder = new ResourcesBuilder(project)// 构建存储resources
                .isLock(lock)
                .setOriginFileName(files.getOriginalFilename())
                .setParamMapWithParameterMap(request.getParameterMap());

        if (useGridFS || filesSize > GRIDFS_SIZE) {// 使用gridFs进行存储
            InputStream inputStream = null;
            String resourcesId;
            try {
                inputStream = files.getInputStream();
                resourcesId = gridFsService.save(inputStream, files.getOriginalFilename());
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }

            resourcesBuilder.isUseGridFs(true).setResourcesId(resourcesId);
        } else {// 不使用gridFs
            PathModel pathModel = uploadService.buildPathModel(project, lock, customPath, fileTypeEnum.getUploadTypeEnum().getPath(), files.getOriginalFilename(), FILES_BASE_PATH);
            // 开始存储
            File file = new File(pathModel.getRealPath());
            if (!file.exists()) {
                file.mkdirs();
            }
            files.transferTo(new File(file.getPath() // 项目名/类型/
                    + ("/")
                    + pathModel.getCompleteFileName())); // 文件名

            resourcesBuilder.isUseGridFs(false).setAccessPath(pathModel.getAccessPath());
        }

        Resources resources = resourcesBuilder.build();

        resources = resourcesService.save(resources);// 不使用mongodb
        ResourcesDto resourcesDto = CastUtil.castModelToAnotherModel(resources, ResourcesDto.class);
        resourcesDto.setId(resources.get_id().toHexString());

        return ResultGenerator.genSuccessResult(resourcesDto);
    }
}
