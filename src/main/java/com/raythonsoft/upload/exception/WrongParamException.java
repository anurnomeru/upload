package com.raythonsoft.upload.exception;

/**
 * Created by Anur IjuoKaruKas on 2017/10/19.
 * Description :
 */
public class WrongParamException extends RuntimeException {
    public WrongParamException() {
        super("参数错误");
    }
}
