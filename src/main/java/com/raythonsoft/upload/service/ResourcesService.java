package com.raythonsoft.upload.service;

import com.raythonsoft.upload.pojo.model.Resources;

import java.util.List;

/**
 * Created by Anur IjuoKaruKas on 2017/11/20.
 * Description :
 */
public interface ResourcesService {
    /**
     * 普通保存
     *
     * @param resources
     * @return
     */
    Resources save(Resources resources);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Resources findById(String id);

    /**
     * 根据resources实体类查找
     *
     * @param resources
     * @return
     */
    List<Resources> findByResources(Resources resources);

    /**
     * 根据accessPath找一个
     *
     * @param accessPath
     * @return
     */
    Resources findByAccessPath(String accessPath);
}
