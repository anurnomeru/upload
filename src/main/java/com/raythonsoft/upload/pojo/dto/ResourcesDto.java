package com.raythonsoft.upload.pojo.dto;

import java.util.Map;

/**
 * Created by Anur IjuoKaruKas on 2017/11/20.
 * Description :
 */
public class ResourcesDto {
    private String id;

    private boolean lock;

    private boolean useGridFS;

    private String resourcesId;

    private String originFileName;

    private String accessPath;

    private Map<String, String> paramMap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean isUseGridFS() {
        return useGridFS;
    }

    public void setUseGridFS(boolean useGridFS) {
        this.useGridFS = useGridFS;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
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
