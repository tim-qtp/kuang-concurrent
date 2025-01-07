package com.qtp.map;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        //map 是这样用的吗？  不是，工作中不使用这个
        //默认等价什么？ new HashMap<>(16,0.75);
        /**
         * 解决方案
         * 1. Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
         *  Map<String, String> map = new ConcurrentHashMap<>();
         */
        Map<String, String> map = new ConcurrentHashMap<>();
//        HashMap<String, String> map = new HashMap<>();
        //加载因子、初始化容量
        for (int i = 1; i < 100; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
