package com.fairy.base.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hll
 * @version 1.0
 * @date  2022/4/20 15:51
 */
public class CacheMap {

    public volatile static Map<String,Object> caches;

    public static Map getInstance(){
        if(caches==null){
            synchronized (CacheMap.class){
                if(caches==null){
                    caches = new HashMap<>();
                }
            }
        }
        return  caches;
    }

}
