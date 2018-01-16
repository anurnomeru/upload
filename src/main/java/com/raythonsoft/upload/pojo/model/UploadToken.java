package com.raythonsoft.upload.pojo.model;

import com.raythonsoft.upload.enums.FileTypeEnum;
import com.raythonsoft.upload.enums.UploadTypeEnum;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by Anur IjuoKaruKas on 2017/11/17.
 * Description :
 */
public class UploadToken {

    @Id
    private ObjectId _id;

    private String project;

    private String ip;

    private Long size;

    private Integer effectTimes;

    private List<FileTypeEnum> fileTypeEnumList;

    private List<UploadTypeEnum> uploadTypeEnumList;

    public UploadToken() {
    }

    public UploadToken(ObjectId _id, String project, String ip, Long size, Integer effectTimes, List<FileTypeEnum> fileTypeEnumList, List<UploadTypeEnum> uploadTypeEnumList) {
        this._id = _id;
        this.project = project;
        this.ip = ip;
        this.size = size;
        this.effectTimes = effectTimes;
        this.fileTypeEnumList = fileTypeEnumList;
        this.uploadTypeEnumList = uploadTypeEnumList;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
