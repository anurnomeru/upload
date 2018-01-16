package com.raythonsoft.upload.exception;

/**
 * Created by Anur IjuoKaruKas on 2017/11/9.
 * Description :
 */
public class FileSizeIllegalException extends RuntimeException {
    public FileSizeIllegalException() {
        super("文件大小超过限制");
    }
}
