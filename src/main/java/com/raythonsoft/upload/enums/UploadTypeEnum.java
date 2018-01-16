package com.raythonsoft.upload.enums;

import com.raythonsoft.upload.common.PathConstruct;

public enum UploadTypeEnum {

    FILE(PathConstruct.FILE), MEDIA(PathConstruct.MEDIA), IMG(PathConstruct.IMG);

    private String path = "";

    /**
     * Constructor.
     *
     * @param path
     */
    UploadTypeEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
