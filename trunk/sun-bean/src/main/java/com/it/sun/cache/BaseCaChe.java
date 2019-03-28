package com.it.sun.cache;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BaseCaChe {
    private static JSONArray primaryCache;//初级缓存【单例】





    public static JSONArray getPrimaryCache() {
        return primaryCache;
    }

    public static synchronized void setPrimaryCache(JSONArray primaryCache) {//加上同步锁，同时设置的时候会阻塞，上一个完成之后才会继续下一个
        BaseCaChe.primaryCache = primaryCache;
    }

    public static synchronized boolean addChildJSONObject(JSONObject child){//在初级缓存中添加子项
        return BaseCaChe.primaryCache.add(child);
    }
}
