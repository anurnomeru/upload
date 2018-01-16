package com.raythonsoft.upload.service.impl;

import com.raythonsoft.upload.builder.PathModelBuilder;
import com.raythonsoft.upload.enums.FileTypeEnum;
import com.raythonsoft.upload.exception.FileIllegalException;
import com.raythonsoft.upload.exception.FileSizeIllegalException;
import com.raythonsoft.upload.exception.NoSuchProjectException;
import com.raythonsoft.upload.pojo.model.PathModel;
import com.raythonsoft.upload.service.UploadService;
import com.raythonsoft.upload.util.TokenUtil;
import com.raythonsoft.upload.util.ValidateFileTypeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.raythonsoft.upload.common.CatalogConstruct.PROJECT_CATALOG;


/**
 * Created by Anur IjuoKaruKas on 2017/11/9.
 * Description :
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UploadServiceImpl implements UploadService {

    @Override
    public PathModel buildPathModel(String project, boolean lock, String customPath, String typePath, String originFilename, String realPath) {
        PathModelBuilder pathModelBuilder = new PathModelBuilder();// 构建存储路径
        return pathModelBuilder.buildPathModel(project, lock, customPath, typePath, originFilename, realPath);// 储存路径信息存放在pathModel中，这一步会验证传入项目是否存在。//fixme project如何去存储未定义
    }

    @Override
    public FileTypeEnum validateType(MultipartFile files) {
        FileTypeEnum fileTypeEnum = ValidateFileTypeUtil.getFileTypeEnum(files);
        if (null == fileTypeEnum) {
            throw new FileIllegalException();
        }
        return fileTypeEnum;
    }
}
