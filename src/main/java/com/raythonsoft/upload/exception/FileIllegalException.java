package com.raythonsoft.upload.exception;

/**
 * Created by Anur IjuoKaruKas on 2017/11/9.
 * Description :
 */
public class FileIllegalException extends RuntimeException {
    public FileIllegalException() {
        super("文件不合法");
    }
}
