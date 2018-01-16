package com.raythonsoft.upload.exception;

/**
 * Created by Anur IjuoKaruKas on 2017/10/19.
 * Description :
 */
public class NoSuchProjectException extends RuntimeException {

    public NoSuchProjectException() {
        super("项目不存在");
    }
}
