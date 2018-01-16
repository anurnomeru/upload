package com.raythonsoft.upload.exception;

/**
 * Created by Anur IjuoKaruKas on 2017/10/19.
 * Description :
 */
public class AuthorizedException extends RuntimeException {

    private static String MSG = "验证失败";
    private static String POINT = "：";

    public AuthorizedException(String content) {
        super(content == null ? MSG : MSG + POINT + content);
    }
}
