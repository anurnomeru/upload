package com.raythonsoft.upload.service.impl;

import com.raythonsoft.upload.enums.FileTypeEnum;
import com.raythonsoft.upload.enums.UploadTypeEnum;
import com.raythonsoft.upload.exception.AuthorizedException;
import com.raythonsoft.upload.pojo.model.UploadToken;
import com.raythonsoft.upload.pool.UploadTokenPool;
import com.raythonsoft.upload.repository.UploadTokenRepository;
import com.raythonsoft.upload.service.UploadTokenService;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

/**
 * Created by Anur IjuoKaruKas on 2017/11/17.
 * Description :
 */
@Service
public class UploadTokenServiceImpl implements UploadTokenService {

    @Autowired
    UploadTokenRepository uploadTokenRepository;

    private static Logger logger = Logger.getLogger(UploadTokenServiceImpl.class);

    @Override
    public void validateToken(String token, String type, String project, String ip, Long size) {
        ObjectId id;
        logger.info("validateToken: " + token);
        try {
            id = new ObjectId(token);
        } catch (IllegalArgumentException e) {
            throw new AuthorizedException(null);
        }

        UploadToken uploadTokenExample = new UploadToken();
        uploadTokenExample.set_id(id);
        UploadToken uploadToken = uploadTokenRepository.findOne(Example.of(uploadTokenExample));
        if (null == uploadToken) {
            throw new AuthorizedException(null);
        }

        if (1 > uploadToken.getEffectTimes()) {
            uploadTokenRepository.delete(uploadToken);
            throw new AuthorizedException(null);
        }

        uploadToken.setEffectTimes(uploadToken.getEffectTimes() - 1);
        if (0 == uploadToken.getEffectTimes()) {
            uploadTokenRepository.delete(uploadToken);
        } else {
            uploadTokenRepository.save(uploadToken);
        }

        if (!ip.equals(uploadToken.getIp())) {
            throw new AuthorizedException("ip不正确");
        }

        if (!project.equals(uploadToken.getProject())) {
            throw new AuthorizedException("项目错误");
        }

        if (size > uploadToken.getSize()) {
            throw new AuthorizedException("文件过大");
        }

        boolean validate = false;

        try {
            FileTypeEnum fileTypeEnum = FileTypeEnum.valueOf(type.toUpperCase());// 上传文件的类型

            List<UploadTypeEnum> uploadTypeEnumList = uploadToken.getUploadTypeEnumList();// 这里token生成时，使用一类文件类型
            if (uploadTypeEnumList != null) {
                if (uploadTypeEnumList.size() != 0) {
                    for (Iterator<UploadTypeEnum> iterator = uploadTypeEnumList.iterator(); iterator.hasNext(); ) {
                        UploadTypeEnum next = iterator.next();
                        if (next.equals(fileTypeEnum.getUploadTypeEnum())) {// 当前文件格式被包含于uploadTypeEnumList的uploadTypeEnum中
                            validate = true;
                            break;
                        }
                    }
                }
            }

            List<FileTypeEnum> fileTypeEnumList = uploadToken.getFileTypeEnumList();// 这里token生成时，使用某些文件类型
            if (fileTypeEnumList != null) {
                if (fileTypeEnumList.size() != 0) {
                    for (Iterator<FileTypeEnum> iterator = fileTypeEnumList.iterator(); iterator.hasNext(); ) {
                        FileTypeEnum next = iterator.next();
                        if (next.equals(fileTypeEnum)) {
                            validate = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new AuthorizedException("文件类型不正确");
        } finally {
            if (!validate) {
                throw new AuthorizedException("文件类型不正确");
            }
        }
    }

    @Override
    public void delete(String objectId) {
        ObjectId id = new ObjectId(objectId);
        UploadToken uploadTokenExample = new UploadToken();
        uploadTokenExample.set_id(id);
        uploadTokenRepository.delete(uploadTokenExample);
    }

    @Override
    public String saveAndInit(UploadToken uploadToken, Integer validTime) {
        uploadToken = uploadTokenRepository.save(uploadToken);

        String token = uploadToken.get_id().toHexString();

//        try {
//            UploadTokenPool.getPool().submit(new Thread(() -> {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " : " + token);
//                    Thread.sleep(validTime * 1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                this.delete(token);
//            }));
//        } catch (RejectedExecutionException e) {
//            return null;
//        }

        return token;
    }

}
