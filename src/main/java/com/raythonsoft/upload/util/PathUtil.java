package com.raythonsoft.upload.util;

import com.raythonsoft.upload.exception.FileFormatException;
import com.raythonsoft.upload.exception.NoSuchProjectException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.raythonsoft.upload.common.CatalogConstruct.PARAM_CATALOG;

/**
 * Created by Anur IjuoKaruKas on 2017/10/19.
 * Description :
 */
@Component
public class PathUtil {

    private static String path = null;
    private static final String projectDefault = "default";

    /**
     * 获取动态地址
     *
     * @param typePath
     * @param project
     * @return
     * @throws NoSuchProjectException
     * @throws FileFormatException
     */
    public static String getDynamicPath(String project, boolean lock, String customPath, String typePath) throws NoSuchProjectException, FileFormatException {
        path = String
                .format("%s/" +
                                "%s/" +
                                "%s/" +
                                "%s/" +
                                "%s/",
                        "resources",                    // 1
                        project,                        // 2
                        (lock ? "private" : "public"),  // 3
                        customPath                      // 4
                        , typePath);                    // 5
        return path;
    }

    public static String getType(String fileName) {
        return fileName.indexOf(".") != -1 ? (fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())).toLowerCase() : null;// 获取文件类型
    }


    // 以下代码为redis时期用，已作废
    public static String getPath(HttpServletRequest request) {
        return "http://" + request.getServerName() // 服务器地址
                + ":"
                + request.getServerPort()                       // 端口号
                + "/";
    }

    public static String genRedisParamKey(String project, String paramName, String paramValue) {
        return project + ":" + PARAM_CATALOG + paramName.substring(2, paramName.length()) + ":" + paramValue;
    }

    public static String genParamKey(String project, String paramName) {
        return project + ":" + PARAM_CATALOG + paramName.substring(2, paramName.length());
    }
}
