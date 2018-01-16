package com.raythonsoft.upload.service.impl;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.raythonsoft.upload.service.GridFsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

/**
 * Created by Anur IjuoKaruKas on 2017/11/20.
 * Description :
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GridFsServiceImpl implements GridFsService {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Override
    public String save(InputStream inputStream, String filename) {
        GridFSFile gridFSFile = gridFsTemplate.store(inputStream, filename);
        return gridFSFile.getId().toString();
    }

    @Override
    public GridFSDBFile findById(String id) {
        return gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is(id)));
    }
}
