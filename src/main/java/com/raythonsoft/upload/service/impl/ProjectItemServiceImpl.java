package com.raythonsoft.upload.service.impl;

import com.alibaba.fastjson.JSON;
import com.raythonsoft.upload.core.Result;
import com.raythonsoft.upload.exception.ProjectException;
import com.raythonsoft.upload.pojo.model.ProjectItem;
import com.raythonsoft.upload.repository.ProjectItemRepository;
import com.raythonsoft.upload.service.ProjectItemService;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Anur IjuoKaruKas on 2017/11/22.
 * Description :
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectItemServiceImpl implements ProjectItemService {

    @Autowired
    ProjectItemRepository projectItemRepository;

    private static Integer SUCCESS = 200;

    @Override
    public ProjectItem findByProject(String project) {
        List<ProjectItem> projectItemList = projectItemRepository.findByProject(project);
        int size = projectItemList.size();
        if (size == 1) {
            return projectItemList.get(0);
        }
        if (size > 1) {
            throw new ProjectException();
        }
        return null;
    }

    @Override
    public ProjectItem save(ProjectItem projectItem) {
        return projectItemRepository.save(projectItem);
    }

    @Override
    public boolean authentication(String project, String param) {
        ProjectItem projectItem = this.findByProject(project);

        if (projectItem == null) {// 是否有这个项目
            return false;
        }

        String uri = projectItem.getAuthUrl() + param;
        System.out.println(uri);

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000).build();

        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String entityStr = EntityUtils.toString(entity, "UTF-8");
                Result result = JSON.parseObject(entityStr, Result.class);
                if (SUCCESS.equals(result.getCode())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
