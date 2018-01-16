package com.raythonsoft.upload.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Date;

import static com.raythonsoft.upload.common.CatalogConstruct.GEN_TOKEN_CATALOG;

/**
 * Created by Anur IjuoKaruKas on 2017/10/19.
 * Description :
 */
@Component
public class TokenUtil {

    public static String hex(String string) {
        String result = "";
        result = DigestUtils.md5DigestAsHex(string.getBytes());
        return result;
    }

}
