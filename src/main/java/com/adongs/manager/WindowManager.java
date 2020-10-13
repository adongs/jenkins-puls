package com.adongs.manager;

import java.util.HashMap;
import java.util.Map;

/**
 * 窗口管理器
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 3:18 下午
 * @modified By
 */
public class WindowManager {

 private final static Map<String,Object> map = new HashMap<>();


 /**
  * 注册窗口
  * @param object
  */
 public static void registered(Object object){
  map.put(object.getClass().getTypeName(), object);
 }

 /**
  * 获取窗口
  * @param clazz
  * @param <T>
  * @return
  */
 public static  <T>T get(Class<T> clazz){
  return map.containsKey(clazz.getTypeName())?(T)map.get(clazz.getTypeName()):null;
 }



}
