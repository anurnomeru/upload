package com.raythonsoft.upload.repository;


import com.raythonsoft.upload.pojo.model.Resources;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by Anur IjuoKaruKas on 2017/11/15.
 * Description :
 */
public interface ResourcesRepository extends MongoRepository<Resources, String> {

    List<Resources> findAllByProjectAndParamMapIsContaining(String project, Map<String, String> paramMap);

    List<Resources> findAllByProjectAndParamMapLike(String project, Map<String, String> paramMap);

    List<Resources> findAllByParamMapLike(Map<String, String> paramMap);

    Resources findByAccessPath(String accessPath);

}
