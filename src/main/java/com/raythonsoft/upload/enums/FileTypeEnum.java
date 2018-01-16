package com.raythonsoft.upload.enums;

/**
 * Created by Anur IjuoKaruKas on 2017/11/9.
 * Description :
 */
public enum FileTypeEnum {

    /**
     * JEPG.
     */
    JPEG("FFD8FF", UploadTypeEnum.IMG),

    /**
     * PNG.
     */
    PNG("89504E47", UploadTypeEnum.IMG),

    /**
     * GIF.
     */
    GIF("47494638", UploadTypeEnum.IMG),

    /**
     * Windows Bitmap.
     */
    BMP("424D", UploadTypeEnum.IMG),

    /**
     * CAD.
     */
    DWG("41433130", UploadTypeEnum.FILE),

    /**
     * TIFF.
     */
    TIFF("49492A00", UploadTypeEnum.FILE),

    /**
     * Adobe Photoshop.
     */
    PSD("38425053", UploadTypeEnum.FILE),

    /**
     * Rich Text Format.
     */
    RTF("7B5C727466", UploadTypeEnum.FILE),

    /**
     * XML.
     */
    XML("3C3F786D6C", UploadTypeEnum.FILE),

    /**
     * HTML.
     */
    HTML("68746D6C3E", UploadTypeEnum.FILE),

    /**
     * Email [thorough only].
     */
    EML("44656C69766572792D646174653A", UploadTypeEnum.FILE),

    /**
     * Outlook Express.
     */
    DBX("CFAD12FEC5FD746F", UploadTypeEnum.FILE),

    /**
     * Outlook (pst).
     */
    PST("2142444E", UploadTypeEnum.FILE),

    /**
     * MS Word/Excel.
     */
    XLS_DOC("D0CF11E0", UploadTypeEnum.FILE),

    /**
     * MS Access.
     */
    MDB("5374616E64617264204A", UploadTypeEnum.FILE),

    /**
     * WordPerfect.
     */
    WPD("FF575043", UploadTypeEnum.FILE),

    /**
     * Postscript.
     */
    EPS("252150532D41646F6265", UploadTypeEnum.FILE),

    /**
     * Adobe Acrobat.
     */
    PDF("255044462D312E", UploadTypeEnum.FILE),

//    /**
//     * Quicken.
//     */
//    QDF("AC9EBD8F"),

//    /**
//     * Windows Password.
//     */
//    PWL("E3828596"),

    /**
     * ZIP Archive.
     */
    ZIP("504B0304", UploadTypeEnum.FILE),

    /**
     * RAR Archive.
     */
    RAR("52617221", UploadTypeEnum.FILE),

    /**
     * MP3.
     */
    MP3("49443303", UploadTypeEnum.MEDIA),

    /**
     * Wave.
     */
    WAV("57415645", UploadTypeEnum.MEDIA),

    /**
     * MP4.
     */
    MP4("00000020", UploadTypeEnum.MEDIA),

    /**
     * AVI.
     */
    AVI("41564920", UploadTypeEnum.MEDIA),

    /**
     * Real Audio.
     */
    RAM("2E7261FD", UploadTypeEnum.MEDIA),

    /**
     * Real UploadTypeEnum.MEDIA.
     */
    RM("2E524D46", UploadTypeEnum.MEDIA),

    /**
     * MPEG (mpg).
     */
    MPG("000001BA", UploadTypeEnum.MEDIA),

    /**
     * Quicktime.
     */
    MOV("6D6F6F76", UploadTypeEnum.MEDIA),

    /**
     * Windows UploadTypeEnum.MEDIA.
     */
    ASF("3026B2758E66CF11", UploadTypeEnum.MEDIA),

    /**
     * MIDI.
     */
    MID("4D546864", UploadTypeEnum.MEDIA);

    private String value;

    private UploadTypeEnum uploadTypeEnum;

    /**
     * Constructor.
     *
     * @param value
     */
    FileTypeEnum(String value, UploadTypeEnum uploadTypeEnum) {
        this.value = value;
        this.uploadTypeEnum = uploadTypeEnum;
    }

    public UploadTypeEnum getUploadTypeEnum() {
        return uploadTypeEnum;
    }

    public void setUploadTypeEnum(UploadTypeEnum uploadTypeEnum) {
        this.uploadTypeEnum = uploadTypeEnum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
