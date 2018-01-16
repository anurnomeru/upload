package com.raythonsoft.upload.repository;

import com.raythonsoft.upload.pojo.model.ProjectItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Anur IjuoKaruKas on 2017/11/22.
 * Description :
 */
public interface ProjectItemRepository extends MongoRepository<ProjectItem, String> {

    List<ProjectItem> findByProject(String project);

}
