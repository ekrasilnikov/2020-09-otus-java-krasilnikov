package ru.otus.cachehw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.WeakHashMap;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V>, HwListener<K, V> {
    //Надо реализовать эти методы
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);

    private final WeakHashMap<K, V> weakHashMap;
    private final HashSet<HwListener<K, V>> hwListenerHashSet;

    public MyCache() {
        this.weakHashMap = new WeakHashMap<>();
        this.hwListenerHashSet = new HashSet<>();
    }

    @Override
    public void put(K key, V value) {
        weakHashMap.put(key, value);
        notify(key, value, "put");
        logger.info("Working with the cache: action - put");
    }

    @Override
    public void remove(K key) {
        V value = weakHashMap.get(key);
        weakHashMap.remove(key);
        notify(key, value, "remove");
        logger.info("Working with the cache: action - remove");
    }

    @Override
    public V get(K key) {
        return weakHashMap.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        hwListenerHashSet.add(listener);
        logger.info("Working with the cache: action - addListener");
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        hwListenerHashSet.remove(listener);
        logger.info("Working with the cache: action - removeListener");
    }

    @Override
    public void notify(K key, V value, String action) {
        hwListenerHashSet.forEach(kvHwListener -> kvHwListener.notify(key, value, action));
    }
}
