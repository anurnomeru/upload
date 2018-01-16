package com.raythonsoft.upload.service;

import com.raythonsoft.upload.pojo.model.UploadToken;

/**
 * Created by Anur IjuoKaruKas on 2017/11/17.
 * Description :
 */
public interface UploadTokenService {

    /**
     * 根据主键和相关参数验证并请求可行性，并减少一次可用次数
     *
     * @param token
     * @param type
     * @param project
     * @param ip
     */
    void validateToken(String token, String type, String project, String ip, Long size);

    /**
     * 删除某token
     *
     * @param objectId
     */
    void delete(String objectId);

    /**
     * 保存基本，定时销毁
     *
     * @param uploadToken
     * @param validTime
     * @return
     */
    String saveAndInit(UploadToken uploadToken, Integer validTime);
}
