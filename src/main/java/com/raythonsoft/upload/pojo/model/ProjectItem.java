package com.raythonsoft.upload.pojo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

/**
 * Created by Anur IjuoKaruKas on 2017/11/22.
 * Description :
 */
public class ProjectItem {

    @Id
    private ObjectId _id;

    private String project;

    private String authUrl;

    private List<String> pramList;

    public ProjectItem() {
    }

    public ProjectItem(ObjectId _id, String project, String authUrl, List<String> pramList) {
        this._id = _id;
        this.project = project;
        this.authUrl = authUrl;
        this.pramList = pramList;
    }

    public List<String> getPramList() {
        return pramList;
    }

    public void setPramList(List<String> pramList) {
        this.pramList = pramList;
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

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }
}
