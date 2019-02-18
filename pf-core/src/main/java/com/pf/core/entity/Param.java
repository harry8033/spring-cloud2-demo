package com.pf.core.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Author: Ru He
 * Date: Created in 2019/2/1.
 * Description:
 */
public class Param extends JSONObject {

    /*public Integer getInteger(String key){
        Object o = get(key);
        return o == null ? null : Integer.parseInt(o.toString());
    }
    public Integer getIntValue(String key){
        Object o = get(key);
        return o == null ? 0 : Integer.parseInt(o.toString());
    }

    public String getString(String key){
        Object o = get(key);
        return o == null ? null : o.toString();
    }

    public Double getDouble(String key){
        Object o = get(key);
        return o == null ? null : Double.parseDouble(o.toString());
    }

    public Double getDoubleValue(String key){
        Object o = get(key);
        return o == null ? 0.0D : Double.parseDouble(o.toString());
    }

    public Float getFloat(String key){
        Object o = get(key);
        return o == null ? null : Float.parseFloat(o.toString());
    }

    public Float getFloatValue(String key){
        Object o = get(key);
        return o == null ? 0.0F : Float.parseFloat(o.toString());
    }

    public Long getLong(String key){
        Object o = get(key);
        return o == null ? null : Long.parseLong(o.toString());
    }

    public Long getLongValue(String key){
        Object o = get(key);
        return o == null ? 0L : Long.parseLong(o.toString());
    }*/

    public <T> List<T> getList(String key, Class<T> c){
        return getJSONArray(key).toJavaList(c);
    }
}
