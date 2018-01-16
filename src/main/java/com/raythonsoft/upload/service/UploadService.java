package com.raythonsoft.upload.service;

import com.raythonsoft.upload.enums.FileTypeEnum;
import com.raythonsoft.upload.pojo.model.PathModel;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by Anur IjuoKaruKas on 2017/11/9.
 * Description :
 */
public interface UploadService {

    /**
     * 构建参数model
     *
     * @param project
     * @param lock
     * @param customPath
     * @param typePath
     * @param originFilename
     * @param realPath
     * @return
     */
    PathModel buildPathModel(String project, boolean lock, String customPath, String typePath, String originFilename, String realPath);

    /**
     * 验证类型
     *
     * @param files
     * @return
     */
    FileTypeEnum validateType(MultipartFile files);
}
