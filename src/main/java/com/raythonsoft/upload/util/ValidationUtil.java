package com.raythonsoft.upload.util;

import com.raythonsoft.upload.core.Result;
import com.raythonsoft.upload.core.ResultGenerator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by Stack on 2017/8/8.
 * Description :
 */
public class ValidationUtil {
/**通用**/

    /**
     * @param result
     * @return
     */
    public static Result validate(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            StringBuffer stringBuffer = new StringBuffer();
            for (ObjectError error : errorList) {
                stringBuffer.append(error.getDefaultMessage() + "，");
            }
            return ResultGenerator.genFailResult(stringBuffer.substring(0, stringBuffer.length() - 1), "WRONG_PARAM");
        }
        return null;
    }
}
