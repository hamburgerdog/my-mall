package org.xjosiah.mymall.service;

public interface RedisService {
     /**
      * 获取值
      * @param key
      * @return
      */
     String get(String key);

     /**
      * 存储
      * @param key
      * @param value
      */
     void set(String key,String value);

     /**
      * 设置过期时间
      * @param key
      * @param expire
      * @return
      */
     boolean expire(String key,long expire);

     /**
      * 删除数据
      * @param key
      */
     void remove(String key);

     /**
      * 自增
      * @param key
      * @param delta     自增步长
      * @return
      */
     Long increment(String key ,long delta);
}
