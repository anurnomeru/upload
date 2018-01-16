package com.raythonsoft.upload.helper;

import com.raythonsoft.upload.util.NetworkUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Anur IjuoKaruKas on 2017/11/23.
 * Description :
 */
public class NetworkHelper {

    private static NetworkUtil networkUtil;

    public static void init(HttpServletRequest request) {
        networkUtil = new NetworkUtil();
        networkUtil.initIpAddress(request);
    }

    public static String getIpAddress() {
        return networkUtil.getIpAddress();
    }
}
