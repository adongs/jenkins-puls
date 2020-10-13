package com.adongs.constant;

import com.google.common.collect.ImmutableMap;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/10/13 2:46 下午
 * @modified By
 */
public interface Constant {

    public ImmutableMap<Integer,String> number = ImmutableMap.<Integer,String>builder()
            .put(1,"①")
            .put(2,"②")
            .put(3,"③")
            .put(4,"④")
            .put(5,"⑤")
            .put(6,"⑥")
            .put(7,"⑦")
            .put(8,"⑧")
            .put(9,"⑨")
            .put(10,"⑩").build();
}
