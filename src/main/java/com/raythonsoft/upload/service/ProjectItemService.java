package com.raythonsoft.upload.service;

import com.raythonsoft.upload.pojo.model.ProjectItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Anur IjuoKaruKas on 2017/11/22.
 * Description :
 */
public interface ProjectItemService {

    /**
     * 通过project查找
     *
     * @param project
     * @return
     */
    ProjectItem findByProject(String project);

    /**
     * 保存
     *
     * @param projectItem
     * @return
     */
    ProjectItem save(ProjectItem projectItem);

    /**
     * 认证
     *
     * @param project
     * @param param
     * @return
     */
    boolean authentication(String project, String param);
}
