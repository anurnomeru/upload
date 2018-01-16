package com.raythonsoft.upload.util;

import com.raythonsoft.upload.exception.WrongParamException;

import java.util.Map;

/**
 * Created by Anur IjuoKaruKas on 2017/11/24.
 * Description :
 */
public class ParameterMapUtil {

    public static String get(Map<String, String[]> parameterMap, String paramName) {
        String[] params = parameterMap.get(paramName);
        if (params == null) {
            return null;
        }
        if (params.length > 1) {
            throw new WrongParamException();
        }
        return params[0];
    }
}
