package com.example.serviceutils.utils;

import com.jarry.serviceutils.utils.LRUAbstractMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.example.serviceutils.utils
 * @Author: Jarry.Chang
 * @CreateTime: 2020-01-19 14:10
 */
@Slf4j
public class AbstractMapTest {
    @Test
    public void test() throws InterruptedException {
        LRUAbstractMap map = new LRUAbstractMap();
        for (int i = 0; i < 1025; i++) {
            map.put(i,i);
        }

        log.debug(map.get(5120).toString());
        log.info(map.size()+"Size");
        while (!LRUAbstractMap.checkTimePool.isTerminated()){
            Thread.sleep(5000);
        };
//        System.out.println(map.get(1));
    }
}
