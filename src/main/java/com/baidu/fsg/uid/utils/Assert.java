package com.baidu.fsg.uid.utils;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2021/7/23 13:35
 * @email kfyty725@hotmail.com
 */
public abstract class Assert {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
}
