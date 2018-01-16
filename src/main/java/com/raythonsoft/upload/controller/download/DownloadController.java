package com.raythonsoft.upload.controller.download;

import com.mongodb.gridfs.GridFSDBFile;
import com.raythonsoft.upload.exception.NoSuchResourcesException;
import com.raythonsoft.upload.exception.AuthorizedException;
import com.raythonsoft.upload.helper.NetworkHelper;
import com.raythonsoft.upload.pojo.model.Resources;
import com.raythonsoft.upload.service.GridFsService;
import com.raythonsoft.upload.service.ResourcesService;
import com.raythonsoft.upload.util.AuthUtil;
import com.raythonsoft.upload.util.ParameterMapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

import static com.raythonsoft.upload.common.ProjectConstruct.FILES_BASE_PATH;
import static com.raythonsoft.upload.common.ProjectConstruct.TOKEN;

/**
 * Created by Anur IjuoKaruKas on 2017/11/2.
 * Description : 下载
 */
@Api(produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    ResourcesService resourcesService;

    @Autowired
    GridFsService gridFsService;

    @ApiOperation(value = "取文件")
    @GetMapping("/{resourcesId}")
    public void get(@PathVariable String resourcesId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Resources resources = resourcesService.findById(resourcesId);

        if (resources == null) {
            throw new NoSuchResourcesException();
        }

        String token = request.getHeader(TOKEN);
        String ip = NetworkHelper.getIpAddress();

        if (resources.isLock()) {
            if (!AuthUtil.auth(token, ip, resources)) {
                throw new AuthorizedException(null);
            }
        }

        OutputStream outputStream = response.getOutputStream();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + resources.getOriginFileName() + "\"");

        try {
            if (resources.isUseGridFS()) {// 使用gridFs进行存储的文件
                GridFSDBFile gridFSDBFile = gridFsService.findById(resources.getResourcesId());
                gridFSDBFile.writeTo(outputStream);
            } else {// 普通存储的文件
                File file = new File(FILES_BASE_PATH + resources.getAccessPath());//fixme
                InputStream is = new FileInputStream(file);
                byte[] body = new byte[is.available()];

                int n;
                while ((n = is.read(body)) != -1) {
                    outputStream.write(body, 0, n);
                }
            }

        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
}
