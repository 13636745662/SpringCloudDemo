package com.example.serviceutils.utils;

import com.jarry.serviceutils.utils.LRUAbstractMap;
import org.junit.jupiter.api.Test;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.example.serviceutils.utils
 * @Author: Jarry.Chang
 * @CreateTime: 2020-01-19 14:10
 */
public class AbstractMapTest {
    @Test
    public void test(){
        LRUAbstractMap map = new LRUAbstractMap();
        map.put(1,1);
        map.put(2,2);

        System.out.println(map.get(1));
    }
}
