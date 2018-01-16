package com.raythonsoft.upload.service.impl;

import com.raythonsoft.upload.pojo.model.Resources;
import com.raythonsoft.upload.repository.ResourcesRepository;
import com.raythonsoft.upload.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Anur IjuoKaruKas on 2017/11/20.
 * Description :
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    ResourcesRepository resourcesRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Resources save(Resources resources) {
        return resourcesRepository.save(resources);
    }

    @Override
    public Resources findById(String id) {
        return resourcesRepository.findOne(id);
    }

    @Override
    public List<Resources> findByResources(Resources resources) {
        Query query = new Query();

        for (Map.Entry<String, String> entry : resources.getParamMap().entrySet()) {
            query.addCriteria(Criteria.where("paramMap." + entry.getKey()).is(entry.getValue()));
        }

        return mongoTemplate.find(query, Resources.class);
    }

    @Override
    public Resources findByAccessPath(String accessPath) {
        return resourcesRepository.findByAccessPath(accessPath);
    }
}
