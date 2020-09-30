package com.example.demo.util;

import java.util.UUID;

public class Utils {
    /**
     * 获取32位随机字符串，作为用户的id
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
