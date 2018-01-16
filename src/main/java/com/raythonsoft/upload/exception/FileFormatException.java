package com.raythonsoft.upload.exception;

/**
 * Created by Anur IjuoKaruKas on 2017/10/19.
 * Description :
 */
public class FileFormatException extends RuntimeException {
    public FileFormatException() {
        super("文件格式不正确");
    }
}
