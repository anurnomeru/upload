package com.raythonsoft.upload.exception;

/**
 * Created by Anur IjuoKaruKas on 2017/11/9.
 * Description :
 */
public class CustomParamIllegalException extends RuntimeException {
    public CustomParamIllegalException() {
        super("自定义参数只能为字母，不能有任何符号");
    }
}
