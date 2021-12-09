package com.darwin.prototype.base;

/**
 * 顶级的校验接口
 */
public interface Verifier<K,V> {
    public boolean verify(K k,V v);
    public void delete(K k);
    default boolean verifyAndDelete(K k,V v){
        try{
            return verify(k, v);
        }finally {
            delete(k);
        }
    }
}
