package com.raythonsoft.upload.exception;

/**
 * Created by Anur IjuoKaruKas on 2017/11/23.
 * Description :
 */
public class NoSuchResourcesException extends RuntimeException {

    public NoSuchResourcesException() {
        super("资源不存在");
    }
}
