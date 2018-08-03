package com.distributed.transaction.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author ssk www.8win.com
 * @date 2018/05/17 上午 11:20
 * @since v1.0
 **/

public enum SingletonGsonEnum {
    /**
     * 单例
     */
    instences;

    private Gson gson = null;

    SingletonGsonEnum() {

        gson = new Gson();
    }

    public Gson getGson() {

        return gson;
    }

    public  <T>T fromJson(String json,Class<T> type){
        Gson gson = new Gson();
        return gson.fromJson(json,type);

    }

    public  <T> List<T> listFromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
    }
}
