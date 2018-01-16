package com.raythonsoft.upload.exception;

/**
 * Created by Anur IjuoKaruKas on 2017/10/19.
 * Description :
 */
public class ProjectExistException extends RuntimeException {

    public ProjectExistException() {
        super("项目已经存在");
    }
}
