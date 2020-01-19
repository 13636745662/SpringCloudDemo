package com.jarry.serviceutils.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.AbstractMap;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.serviceutils.utils
 * @Author: Jarry.Chang
 * @CreateTime: 2020-01-19 11:07
 */
@Slf4j
public class LRUAbstractMap extends AbstractMap {

    private ExecutorService checkTimePool;

    private final static int MAX_SIZE = 1024;

    private final static ArrayBlockingQueue<Node> QUEUE = new ArrayBlockingQueue<>(MAX_SIZE);

    private final static int DEFAULT_ARRAY_SIZE = 1024;

    private int arraySize;

    private Object[] arrays;

    private volatile boolean flag = true;

    private final static Long EXPIRE_TIME = 60 * 60 * 1000L;
    /**
     * 整个 Map 的大小
     */
    private volatile AtomicInteger size  ;

    public LRUAbstractMap(){

        arraySize = DEFAULT_ARRAY_SIZE;
        arrays = new Object[arraySize];

        executeCheckTime();
    }

    private void executeCheckTime() {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("check-thread-%d")
                .setDaemon(true)
                .build();

        checkTimePool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        checkTimePool.execute(new CheckTimeThread());
    }

    @Override
    public Set<Entry> entrySet() {
        return super.keySet();
    }

    @Override
    public Object put(Object key, Object value){
        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = (Node) arrays[index];
        
        if (currentNode == null){
            arrays[index] = new Node(null,null,key,value);
            QUEUE.offer((Node) arrays[index]);
            sizeUp();
        }else {
            Node cNode = currentNode;
            Node nNode = cNode;
            if (nNode.key == key)cNode.val = value;
            while (nNode.next != null){
                if (nNode.key == key){
                    nNode.val = value;
                    break;
                }else {
                    sizeUp();
                    Node node = new Node(nNode,null,key,value);

                    QUEUE.offer(node);
                    cNode.next = node;
                }
                nNode = nNode.next;
            }
        }
        return null;
    }
    @Override
    public Object get(Object key) {

        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = (Node) arrays[index];

        if (currentNode == null) {
            return null;
        }
        if (currentNode.next == null){
            currentNode.setUpdateTime(System.currentTimeMillis());

            return currentNode;
        }
        Node node = currentNode;
        while (node.next != null){
            if (node.key == key){
                currentNode.setUpdateTime(System.currentTimeMillis());
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public Object remove(Object key){

        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = (Node) arrays[index];

        if (currentNode == null) return null;

        if (currentNode.key == key){
            sizeDown();
            arrays[index] = null;

            QUEUE.poll();
            return currentNode;
        }
        Node node = currentNode;
        while (node.next != null) {
            if (node.key == key){

                sizeDown();

                node.pre.next = node.next;
                node = null;
                QUEUE.poll();

                return node;
            }
            node = node.next;
        }
        return null;
    }

    private void sizeDown() {
        if (QUEUE.size() == 0)
            flag = false;

        this.size.decrementAndGet();
    }

    private void sizeUp() {

        //在put值时候认为里边已经有数据了
        flag = true;
        if (size == null) size = new AtomicInteger();

        int size = this.size.incrementAndGet();

        if (size >= MAX_SIZE){
            Node node = QUEUE.poll();
            if (node == null) throw new RuntimeException("data Error");

            Object key = node.key;
            remove(key);
            lruCallback();
        }
    }

    private void lruCallback() {
        log.debug("lruCallback");
    }

    public int hash(Object key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode())^(h >>> 16);
    }
    private  class Node {
        private Object key ;
        private Long updateTime;
        private Node next ;
        private Node pre;
        private Object val;

        public Node(Node pre,Node next, Object key, Object val) {
            this.pre = pre ;
            this.next = next;
            this.key = key;
            this.val = val;
            this.updateTime = System.currentTimeMillis() ;
        }
        public void setUpdateTime(Long updateTime){
            this.updateTime = updateTime;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    private class CheckTimeThread implements Runnable {

        @Override
        public void run() {
            while (flag){
                try {
                    Node node = QUEUE.poll();
                    if (node == null){
                    log.info("I 'm polling");
                        continue;
                    }
                    Long updateTime = node.getUpdateTime();

                    if ((updateTime - System.currentTimeMillis()) >= EXPIRE_TIME){
                        remove(node.key);
                    }
                }catch (Exception e){
                    log.info("InterruptedException");
                }
            }
        }
    }
}
