package com.zj.web.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static String getResultStatusJson(String resultCode,String resultMsg){
		JSONObject jo = new JSONObject();
		jo.put("result",resultCode);
		jo.put("message",resultMsg);
		return jo.toString();
	}
	/** 
     * 对象转换成JSON字符串 
     *  
     * @param obj 
     *            需要转换的对象 
     * @return 对象的string字符 
     */  
    public static String toJson(Object obj) {  
        JSONObject jSONObject = JSONObject.fromObject(obj);  
        return jSONObject.toString();  
    }  
  
    /** 
     * JSON字符串转换成对象 
     *  
     * @param jsonString 
     *            需要转换的字符串 
     * @param type 
     *            需要转换的对象类型 
     * @return 对象 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T fromJson(String jsonString, Class<T> type) {  
        JSONObject jsonObject = JSONObject.fromObject(jsonString);  
        return (T) JSONObject.toBean(jsonObject, type);  
    }  
  
    /** 
     * 将JSONArray对象转换成list集合 
     *  
     * @param jsonArr 
     * @return 
     */  
    public static List<Object> jsonToList(JSONArray jsonArr) {  
        List<Object> list = new ArrayList<Object>();  
        for (Object obj : jsonArr) {  
            if (obj instanceof JSONArray) {  
                list.add(jsonToList((JSONArray) obj));  
            } else if (obj instanceof JSONObject) {  
                list.add(jsonToMap((JSONObject) obj));  
            } else {  
                list.add(obj);  
            }  
        }  
        return list;  
    }  
  
    /** 
     * 将json字符串转换成map对象 
     *  
     * @param json 
     * @return 
     */  
    public static Map<String, Object> jsonToMap(String json) {  
        JSONObject obj = JSONObject.fromObject(json);  
        return jsonToMap(obj);  
    }  
  
    /** 
     * 将JSONObject转换成map对象 
     *  
     * @param json 
     * @return 
     */  
    public static Map<String, Object> jsonToMap(JSONObject obj) {  
        Set<?> set = obj.keySet();  
        Map<String, Object> map = new HashMap<String, Object>(set.size());  
        for (Object key : obj.keySet()) {  
            Object value = obj.get(key);  
            if (value instanceof JSONArray) {  
                map.put(key.toString(), jsonToList((JSONArray) value));  
            } else if (value instanceof JSONObject) {  
                map.put(key.toString(), jsonToMap((JSONObject) value));  
            } else {  
                map.put(key.toString(), obj.get(key));  
            }  
  
        }  
        return map;  
    }  
}
