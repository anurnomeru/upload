package com.raythonsoft.upload.util;

import com.raythonsoft.upload.exception.AuthorizedException;
import com.raythonsoft.upload.pojo.model.Resources;
import com.raythonsoft.upload.service.ProjectItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Anur IjuoKaruKas on 2017/11/23.
 * Description :
 */
@Component
public class AuthUtil {

    @Autowired
    ProjectItemService projectItemServicePostConstruct;

    static ProjectItemService projectItemService;

    @PostConstruct
    private void init() {
        projectItemService = projectItemServicePostConstruct;
    }

//    public static boolean auth(String project, Map<String, String[]> paramMap) {
//        return projectItemService.authentication(project, paramMap);
//    }

    public static boolean auth(String token, String ip, Resources resources) {
        try {
            return projectItemService.authentication(resources.getProject(), deCode(token, ip, resources.get_id().toHexString()));
        } catch (Exception e) {
            throw new AuthorizedException(null);
        }
    }

    public static String enCode(String str, String ip, String resourcesId) {
        String firstEncode = SymmetricEncoder.AESEncode(resourcesId, str);
        String secondEncode = SymmetricEncoder.AESEncode(ip, firstEncode);
        return secondEncode.substring(1, secondEncode.length()) + secondEncode.substring(0, 1);
    }

    public static String deCode(String token, String ip, String resourcesId) {
        String result = token.substring(token.length() - 1, token.length()) + token.substring(0, token.length() - 1);
        String firstDecode = SymmetricEncoder.AESDecode(ip, result);
        String secondDecode = SymmetricEncoder.AESDecode(resourcesId, firstDecode);
        return secondDecode;
    }


}
