package com.raythonsoft.upload.util;

import com.raythonsoft.upload.enums.FileTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Anur IjuoKaruKas on 2017/11/9.
 * Description :
 */
public class ValidateFileTypeUtil {

    /**
     * 将文件头转换成16进制字符串
     *
     * @param src 原生byte
     * @return 16进制字符串
     */
    private static String bytesToHexString(byte[] src) {

        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 得到文件头
     *
     * @param file 文件
     * @return 文件头
     * @throws IOException
     */
    private static String getFileContent(MultipartFile file) {

        byte[] b = new byte[28];

        InputStream inputStream = null;

        try {
            inputStream = file.getInputStream();
            inputStream.read(b, 0, 28);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("解析文件错误");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("解析文件错误");
                }
            }
        }
        return bytesToHexString(b);
    }

    /**
     * 判断文件类型
     *
     * @param file 文件路径
     * @return 文件类型
     */
    public static FileTypeEnum getFileTypeEnum(MultipartFile file) {

        String fileHead = getFileContent(file);

        if (fileHead == null || fileHead.length() == 0) {
            return null;
        }

        fileHead = fileHead.toUpperCase();

        FileTypeEnum[] fileTypes = FileTypeEnum.values();

        for (FileTypeEnum type : fileTypes) {
            if (fileHead.startsWith(type.getValue())) {
                return type;
            }
        }

        return null;
    }
}
