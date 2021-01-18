package com.gsitm.career.mybatis.type;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.time.DateUtils;

import com.gsitm.career.utils.CamelCaseUtils;

@SuppressWarnings("serial")
public class CamelCaseMap extends HashMap<String, Object> {
    /**
     * Map의 put을 오버라이드해 key값을 camel 표기법으로 변환하여 put 한다.
     *
     * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public Object put(String key, Object value) {
        if (value instanceof Date) {
            /**
             * 화면에서 자바스크립트로 표현할때 Date 객체 특성에 따라
             * 현지 Time zone을 반영한 시간을 표현하는 문제가 있음.
             *
             * 따라서, Anti-patten 이지만 date 형식의 날짜를 string 으로 변환함.
             *
             */
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                value = dateFormat.format(value);
                //value = value.toString();
            } catch (Exception ex) {
                /**
                 * Exception은 무시한다.
                 */
            }

        }
        return super.put(CamelCaseUtils.convertUnderscoreNameToPropertyName(key), value);
    }

    public Object putPlain(String key, Object value) {
        return super.put(key, value);
    }

    public String getString(String key) {
        return get(key) != null ? get(key).toString() : null;
    }

    public int getInt(String key) {
        return get(key) != null ? Integer.parseInt(get(key).toString()) : 0;
    }

    public long getLong(String key) {
        return get(key) != null ? Long.parseLong(get(key).toString()) : 0;
    }

    public float getFloat(String key) {
        return get(key) != null ? Float.parseFloat(get(key).toString()) : 0f;
    }

    public double getDouble(String key) {
        return get(key) != null ? Double.parseDouble(get(key).toString()) : 0d;
    }

    public String getDate(String key, String format) {
        Date date = getDate(key);
        if (date==null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public Date getDate(String key) {
        try {
            //return get(key) != null ? DateUtils.parseDate(get(key).toString(), Locale.ENGLISH, "E MMM dd HH:mm:ss z yyyy") : null;
            return get(key) != null ? DateUtils.parseDate(get(key).toString(), "yyyy-MM-dd HH:mm:ss") : null;

        } catch (ParseException e) {
            return null;
        }
    }
}