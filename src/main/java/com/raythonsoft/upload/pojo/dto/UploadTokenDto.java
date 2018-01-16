package com.raythonsoft.upload.pojo.dto;

import com.raythonsoft.upload.enums.FileTypeEnum;
import com.raythonsoft.upload.enums.UploadTypeEnum;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Anur IjuoKaruKas on 2017/11/17.
 * Description :
 */
public class UploadTokenDto {
    @NotNull(message = "?????????")
    private String project;

    @NotNull(message = "?????????")
    private Long size;

    @NotNull(message = "?????????")
    @Max(value = 20, message = "?????????")
    private Integer effectTimes;// 有效次数

    @NotNull(message = "?????????")
    @Max(value = 120, message = "?????????")
    private Integer validTime;// 有效时间

    @NotNull(message = "?????????")
    private String ip;

    private List<FileTypeEnum> fileTypeEnumList;

    private List<UploadTypeEnum> uploadTypeEnumList;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getEffectTimes() {
        return effectTimes;
    }

    public void setEffectTimes(Integer effectTimes) {
        this.effectTimes = effectTimes;
    }

    public List<FileTypeEnum> getFileTypeEnumList() {
        return fileTypeEnumList;
    }

    public void setFileTypeEnumList(List<FileTypeEnum> fileTypeEnumList) {
        this.fileTypeEnumList = fileTypeEnumList;
    }

    public List<UploadTypeEnum> getUploadTypeEnumList() {
        return uploadTypeEnumList;
    }

    public void setUploadTypeEnumList(List<UploadTypeEnum> uploadTypeEnumList) {
        this.uploadTypeEnumList = uploadTypeEnumList;
    }
}
