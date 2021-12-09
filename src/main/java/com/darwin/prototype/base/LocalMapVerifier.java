package com.darwin.prototype.base;

import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 本地 Map 校验器
 *
 * @param <T>
 */
public class LocalMapVerifier<K,V> implements Verifier<K,V> {
    public ConcurrentMap<K,V> localMap = null;

    public LocalMapVerifier(){
        this(new ConcurrentHashMap<>());
    }

    public LocalMapVerifier(ConcurrentMap<K,V> localMap){
        this.localMap = localMap;
    }

    @Override
    public boolean verify(K k,V v) {
        if(localMap.containsKey(k)){
            return localMap.get(k).equals(v);
        }
        return false;
    }

    @Override
    public void delete(K k) {
        localMap.remove(k);
    }
}
