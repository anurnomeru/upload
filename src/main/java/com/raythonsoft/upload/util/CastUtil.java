package com.raythonsoft.upload.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stack on 2017/8/12.
 * Description :
 */
public class CastUtil {
/**通用**/

    /**
     * 将一个类强制赋值给另一个类
     *
     * @param modelWantToCast
     * @param modelCastToClazz
     * @return
     * @throws Exception
     */
    public static <T> T castModelToAnotherModel(Object modelWantToCast, Class modelCastToClazz) {
        Method[] dtoMethod = modelWantToCast.getClass().getDeclaredMethods();
        String getMethodName;

        Class modelClazz = modelCastToClazz;
        Object modelCastTo = null;

        try {
            modelCastTo = modelCastToClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (Method getMethod : dtoMethod) {
            getMethodName = getMethod.getName();
            if (getMethodName.indexOf("get", 0) == -1) {
                continue;
            }
            String setMethodName = "set" + getMethodName.substring(3, getMethodName.length());

            Object value = null;// 取值

            try {
                value = getMethod.invoke(modelWantToCast);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            Class parameterTypes = getMethod.getReturnType();// 取参类

            Method setMethod;

            try {
                setMethod = modelClazz.getDeclaredMethod(setMethodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                continue;
            } catch (SecurityException e) {
                continue;
            }

            try {
                setMethod.invoke(modelCastTo, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return (T) modelCastTo;
    }

    public static String castListToStringArr(List list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Object item : list) {
            stringBuffer.append(item.toString());
            stringBuffer.append(",");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    //将数据库查询
    public static void castSqlToDto(Object sql, Object dto) {
        // 获取dto所有方法，除了集合框架类型，其所有的get方法sql都应有匹配的set方法
        Method[] dtoMethods = dto.getClass().getDeclaredMethods();
        // 获取sql所有方法，做成map
        Method[] sqlMethods = sql.getClass().getDeclaredMethods();
        Map<String, Method> getMethodMap = new HashMap<>();
        for (Method sqlMethod : sqlMethods) {
            getMethodMap.put(sqlMethod.getName().substring(3), sqlMethod);
        }
        // 遍历，设置
        for (Method dtoMethod : dtoMethods) {
            // 判断是否为set方法，且返回参数类型不是map和list
            if (dtoMethod.getName().startsWith("set") && !"Map-List".contains(dtoMethod.getReturnType().getSimpleName())) {
                try {
                    dtoMethod.invoke(dto, getMethodMap.get(dtoMethod.getName().substring(3)));
                } catch (Exception e) {
//                    throw new ServiceException("反射方法异常");
                }
            }
        }
    }
}
