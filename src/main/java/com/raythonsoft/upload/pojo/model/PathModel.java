package com.raythonsoft.upload.pojo.model;

/**
 * Created by Anur IjuoKaruKas on 2017/10/19.
 * Description :
 */
public class PathModel {
    String realPath;// 存放路径（不带文件名）
    String completeFileName;// 真正的文件名
    String accessPath;// 资源获取路径
//    String accessKey;

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getCompleteFileName() {
        return completeFileName;
    }

    public void setCompleteFileName(String completeFileName) {
        this.completeFileName = completeFileName;
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }
}
