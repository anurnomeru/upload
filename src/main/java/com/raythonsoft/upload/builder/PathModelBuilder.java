package com.raythonsoft.upload.builder;

import com.raythonsoft.upload.pojo.model.PathModel;
import com.raythonsoft.upload.util.PathUtil;
import com.raythonsoft.upload.util.TokenUtil;

import java.util.Date;

/**
 * Created by Anur IjuoKaruKas on 2017/10/19.
 * Description : 构建文件储存路径、文件MD5名、以及可获取路径
 */
public class PathModelBuilder {

    private PathModel pathModel = new PathModel();

    public PathModel buildPathModel(String project, boolean lock, String customPath, String typePath, String originalFilename, String realPath) {
        String type = PathUtil.getType(originalFilename);

        String dynamicPath = PathUtil.getDynamicPath(project, lock, customPath, typePath);// 动态路径(由项目名和文件类型目录拼接而成 如 project/img/)
        String completeFileName = TokenUtil.hex(originalFilename + new Date()) + "." + type;// 带格式的真正文件名

        pathModel.setRealPath(realPath + ("/") + dynamicPath);// 真实电脑路径 + 由项目名和文件类型目录拼接而成 如 project/img/
//        pathModel.setAccessKey(project  + ":" + typePath + ":" + TokenUtil.hex(accessKey + originalFilename + project + new Date())); // 储存的key为 项目 => 类型 两级目录
        pathModel.setCompleteFileName(completeFileName);
        pathModel.setAccessPath(dynamicPath + completeFileName);
        return pathModel;
    }

}
