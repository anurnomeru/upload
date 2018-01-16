package com.raythonsoft.upload.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Anur IjuoKaruKas on 2017/11/13.
 * Description :
 */
public class MapUtil {

    public static Map<String, String> getMapListIntersection(List<Map> mapList) {
        mapList.sort(((o1, o2) -> o1.size() > o2.size() ? 1 : -1));
        int size = mapList.size();
        if (size < 2) {
            return size == 0 ? null : mapList.get(0);
        }

        Map resultMap = mapList.get(0);
        for (int i = 0; i < size - 1; i++) {
            if (resultMap.size() == 0) {
                return resultMap;
            }
            resultMap = getIntersection(resultMap, mapList.get(i + 1));
        }
        return resultMap;
    }

    private static Map<String, String> getIntersection(Map map1, Map map2) {
        Map<String, String> resultMap = new HashMap<>();
        Iterator<String> stringIterator = map1.keySet().iterator();
        while (stringIterator.hasNext()) {
            String next = stringIterator.next();
            if (map2.get(next) != null) {
                resultMap.put(next, (String) map1.get(next));
            }
        }
        return resultMap;
    }
}
