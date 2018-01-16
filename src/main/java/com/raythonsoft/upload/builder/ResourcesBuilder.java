package com.raythonsoft.upload.builder;


import com.raythonsoft.upload.common.ProjectConstruct;
import com.raythonsoft.upload.exception.CustomParamIllegalException;
import com.raythonsoft.upload.exception.WrongParamException;
import com.raythonsoft.upload.pojo.model.Resources;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Anur IjuoKaruKas on 2017/11/17.
 * Description : 构建resources存储对象
 */
public class ResourcesBuilder {
    private boolean lock = false;
    private boolean useGridFS = false;
    private String originFileName;
    private String accessPath;
    private Map<String, String> paramMap;
    private String resourcesId;
    private String project;

    public ResourcesBuilder(String project) {
        this.project = project;
    }

    public ResourcesBuilder isLock(boolean lock) {
        this.lock = lock;
        return this;
    }

    public ResourcesBuilder isUseGridFs(boolean useGridFS) {
        this.useGridFS = useGridFS;
        return this;
    }

    public ResourcesBuilder setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
        return this;
    }

    public ResourcesBuilder setAccessPath(String accessPath) {
        this.accessPath = accessPath;
        return this;
    }

    public ResourcesBuilder setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
        return this;
    }

    public ResourcesBuilder setParamMapWithParameterMap(Map<String, String[]> parameterMap) {
        Map paramMap = new HashMap();
        Iterator<Map.Entry<String, String[]>> it = parameterMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> entry = it.next();
            if (entry.getKey().indexOf(ProjectConstruct.PARAM_SIGN) != -1) {
                if (entry.getValue().length > 1) {
                    throw new WrongParamException();
                }
                paramMap.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        this.paramMap = paramMap;
        return this;
    }

    public Resources build() {
        return build(this);
    }

    private static Resources build(ResourcesBuilder builder) {
        final boolean lock = builder.lock;
        final boolean useGridFS = builder.useGridFS;
        final String originFileName = builder.originFileName;
        final String accessPath = builder.accessPath;
        final Map<String, String> paramMap = builder.paramMap;
        final String resourceId = builder.resourcesId;
        final String project = builder.project;

        Resources resources = new Resources();
        resources.setLock(lock);
        resources.setUseGridFS(useGridFS);
        resources.setProject(project);// 必须设置 否则报错
        if (originFileName != null) {
            resources.setOriginFileName(originFileName);
        }
        if (accessPath != null) {
            resources.setAccessPath(accessPath);
        }
        if (paramMap != null) {
            resources.setParamMap(paramMap);
        }
        if (resourceId != null) {
            resources.setResourcesId(resourceId);
        }
        return resources;
    }
}
