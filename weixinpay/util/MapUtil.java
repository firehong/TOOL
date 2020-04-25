package com.cgmcomm.webapi.utils.weixinpay.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapUtil {


    public static Map<String, String> convertToMap(Object obj) {
        Map<String, String> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if(field.get(obj) != null){
                    map.put(field.getName(), field.get(obj).toString());
                }else {
                    map.put(field.getName(), null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
