package com.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @desc:         判空工具类
 * @author:       Leo
 * @date:         2016/10/26
 */
public class EmptyUtils
{
    private EmptyUtils() {
        throw new UnsupportedOperationException("error...");
    }

    /**
     * 只传入对象，不适合简单数据类型 String int double float....
     * 某类型对象是否为空
     * @param obj 传入对象名
     * @param <T> 传入对象类型
     * @return <true 对象为空； false 对象非空>
     */
    public static <T> boolean isNull(T obj) {
        return obj == null;
    }

    /**
     * 只传入对象，不适合简单数据类型 String int double float....
     * 某类型对象是否为空
     * @param obj 传入对象名
     * @param <T> 传入对象类型
     * @return <false 对象为空； true 对象非空>
     */
    public static <T> boolean isNotNull(T obj) {
        return !isNull(obj);
    }

    /**
     * 某类型对象是否为空
     * @param obj 传入对象名
     * @param <T> 传入对象类型
     * @return <true 对象为空； false 对象非空>
     */
    public static <T> boolean isEmpty(T obj) {
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        return isNull(obj);
    }

    /**
     * 某类型对象是否为空
     * @param obj 传入对象名
     * @param <T> 传入对象类型
     * @return <false 对象为空； true 对象非空>
     */
    public static <T> boolean isNotEmpty(T obj) {
        return !isEmpty(obj);
    }

    /**
     * 集合是否为空
     * @param collection 传入集合名
     * @param <T> 传入集合内容类型
     * @return <true 集合为空/集合内容为空； false 集合非空>
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return isNull(collection) || collection.size() == 0;
    }

    /**
     * 集合是否为空
     * @param collection 传入集合名
     * @param <T> 传入集合内容类型
     * @return <false 集合为空/集合内容为空； true 集合非空>
     */
    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    /**
     * list是否为空
     * @param collection 传入list名
     * @param <T> 传入list内容类型
     * @return <true list为空/list内容为空； false list非空>
     */
    public static <T> boolean isEmpty(ArrayList<T> collection) {
        return isNull(collection) || collection.size() == 0;
    }

    /**
     * list是否为空
     * @param collection 传入list名
     * @param <T> 传入list内容类型
     * @return <false list为空/list内容为空； true list非空>
     */
    public static <T> boolean isNotEmpty(ArrayList<T> collection) {
        return !isEmpty(collection);
    }

    /**
     * Map是否为空
     * @param map 传入map名
     * @param <K> 传入map key类型
     * @param <V> 传入map value类型
     * @return <true map为空/map内容为空； false map非空>
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return isNull(map) || map.size() == 0;
    }

    /**
     * Map是否为空
     * @param map 传入map名
     * @param <K> 传入map key类型
     * @param <V> 传入map value类型
     * @return <false map为空/map内容为空； true map非空>
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    /**
     * 数组是否为空
     * @param arr 传入数组名
     * @param <T> 传入数组类型
     * @return <true 数组为空/数组内容为空； false 数组非空>
     */
    public static <T> boolean isEmpty(T[] arr) {
        return isNull(arr) || arr.length == 0;
    }

    /**
     * 数组是否为空
     * @param arr 传入数组名
     * @param <T> 传入数组类型
     * @return <false 数组为空/数组内容为空； true 数组非空>
     */
    public static <T> boolean isNotEmpty(T[] arr) {
        return !isEmpty(arr);
    }
}
