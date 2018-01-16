package com.raythonsoft.upload.pojo.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Anur IjuoKaruKas on 2017/11/1.
 * Description :
 */
public class Resources implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private ObjectId _id;

    private boolean lock;

    private boolean useGridFS;

    private String resourcesId;

    private String originFileName;

    private String accessPath;

    private String project;

    private Map<String, String> paramMap;

    public Resources() {
    }

    public Resources(ObjectId _id, boolean lock, boolean useGridFS, String resourcesId, String originFileName, String accessPath, String project, Map<String, String> paramMap) {
        this._id = _id;
        this.lock = lock;
        this.useGridFS = useGridFS;
        this.resourcesId = resourcesId;
        this.originFileName = originFileName;
        this.accessPath = accessPath;
        this.project = project;
        this.paramMap = paramMap;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public boolean isUseGridFS() {
        return useGridFS;
    }

    public void setUseGridFS(boolean useGridFS) {
        this.useGridFS = useGridFS;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }
}
