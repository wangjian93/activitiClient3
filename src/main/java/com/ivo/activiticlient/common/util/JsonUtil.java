package com.ivo.activiticlient.common.util;

import com.google.gson.*;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/10/16
 */
public class JsonUtil {

    public static String Json2string(String s){
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String toJson(Object object) {
        return toJson(object, "yyyy-MM-dd HH:mm:ss");
    }

    public static String toJson(Object object,
                                boolean excludeFieldsWithoutExposeAnnotation) {
        return toJson(object, excludeFieldsWithoutExposeAnnotation,
                "yyyy-MM-dd HH:mm:ss");
    }

    public static String toJson(Object object, String pattern) {
        return toJson(object, false, pattern);
    }
    public static JsonArray toJsonArray(String json) {

        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(json);
            JsonArray myJsonArray = jsonElement.getAsJsonArray();
            return myJsonArray;
        } catch (Exception e) {
            return null;
        }

    }
    public static String toJson(Object object,
                                boolean excludeFieldsWithoutExposeAnnotation, String pattern) {
        GsonBuilder bulider = new GsonBuilder();
        Gson json;
        if (excludeFieldsWithoutExposeAnnotation)
            bulider.excludeFieldsWithoutExposeAnnotation();
        try {
            json = bulider.setDateFormat(pattern).create();
        } catch (Exception e) {
            json = bulider.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
        return json.toJson(object);
    }
    /**
     * 将java List对象转换成json字符串
     * @param javaObj
     * @return
     */
    public static String getJsonString(List beans) {
        JSONObject json = null;
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < beans.size(); i++) {
            json = JSONObject.fromObject(beans.get(i));
            sb.append(json.toString());
            if (i < beans.size() - 1) sb.append(",");
        }
        sb.append("]");

        return sb.toString();
    }
}
