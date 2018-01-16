package com.raythonsoft.upload.repository;

import com.raythonsoft.upload.pojo.model.UploadToken;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Anur IjuoKaruKas on 2017/11/17.
 * Description :
 */
public interface UploadTokenRepository extends MongoRepository<UploadToken, Long> {
}
