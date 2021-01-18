package com.gsitm.career.utils;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;

import com.gsitm.career.mybatis.type.CamelCaseMap;

/**
 * <pre>
 * kr.co.gsbill.base.utils
 *
 * </pre>
 * Description :
 *
 * @author : Mercury
 * @version : 1.0
 * @date : 2017-11-02
 */

public class CamelCaseUtils {

    /**
     *
     * @param name
     * @return
     */
    public static String convertUnderscoreNameToPropertyName(String name) {
        StringBuilder result = new StringBuilder();
        String[] names;
        String fristPart;
        String secondPart;
        int i = 0;
        name = name.toLowerCase();
        names = name.split("_");

        if (names.length == 1) {
            return name.toLowerCase();
        }

        for (String word : names) {
            i++;
            if (i == 1) {
                result.append(word.toLowerCase());
                continue;
            }

            fristPart = word.substring(0, 1);
            result.append(fristPart.toUpperCase());
            secondPart = word.substring(1, word.length());
            result.append(secondPart);
        }
        return result.toString();
    }


    public static Object convertObject(CamelCaseMap camelCaseMap, Class<?> clazz) {

        Object object = null;
        Object value;
        try {
            object = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                value = camelCaseMap.get(field.getName());
                if (value == null) {
                    continue;
                }

                try {
                    BeanUtils.setProperty(object, field.getName(), value);
                } catch (Exception e) {

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return object;
    }


    public static CamelCaseMap convertMap(Object object) {

        CamelCaseMap camelCaseMap = new CamelCaseMap();
        Object value = null;
        try {
            Class<?> clazz = object.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                try {
                    value = BeanUtils.getProperty(object,field.getName());
                } catch (NoSuchMethodException e) {
                    continue;
                }

                camelCaseMap.putPlain(field.getName(), value);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return camelCaseMap;
    }
}
