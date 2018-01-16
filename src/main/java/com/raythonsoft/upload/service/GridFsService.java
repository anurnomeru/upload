package com.raythonsoft.upload.service;

import com.mongodb.gridfs.GridFSDBFile;

import java.io.InputStream;

/**
 * Created by Anur IjuoKaruKas on 2017/11/20.
 * Description :
 */
public interface GridFsService {

    /**
     * 普通保存
     *
     * @param inputStream
     * @param filename
     * @return
     */
    String save(InputStream inputStream, String filename);

    /**
     * 根据id搜索
     *
     * @param id
     * @return
     */
    GridFSDBFile findById(String id);
}
