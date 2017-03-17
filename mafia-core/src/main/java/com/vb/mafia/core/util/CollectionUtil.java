package com.vb.mafia.core.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class CollectionUtil {
    public static <T> List<T> newArrayList(){
        return new ArrayList<T>();
    }
    public static <T> List<T> newArrayList(int size){
        return new ArrayList<T>(size);
    }

    public static <T> Set<T> newHashSet(){
        return new HashSet<T>();
    }

    public static <T> Set<T> newHashSet(int size){
        return new HashSet<T>(size);
    }

    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public static <K, V> Map<K, V> newConcurrentHashMap() {
        return new ConcurrentHashMap<K, V>();
    }

    public static <K, V> Map<K, V> newLinkedHashMap() {
        return new LinkedHashMap<K, V>();
    }

    public static <K, V> Map<K, V> newLinkedHashMap(int size) {
        return new LinkedHashMap<K, V>();
    }
}
