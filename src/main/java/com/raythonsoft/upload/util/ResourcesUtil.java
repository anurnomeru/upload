package com.raythonsoft.upload.util;

import com.raythonsoft.upload.pojo.model.Resources;
import com.raythonsoft.upload.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Anur IjuoKaruKas on 2017/11/26.
 * Description :
 */
@Component
public class ResourcesUtil {

    static ResourcesService resourcesService;

    @Autowired
    ResourcesService resourcesServicePostConstruct;

    @PostConstruct
    private void init() {
        resourcesService = resourcesServicePostConstruct;
    }

    public static Resources findBy(String accessPath) {
        return resourcesService.findByAccessPath(accessPath);
    }
}
