package com.xxx;

/**
 * @author xujunming
 * Created on 2020-03-15
 */
class Interceptor {
//    public static String intercept(String name) {
//        return "String";
//    }

    public static String intercept(int i) {
        return "int";
    }

    public static String intercept(Object o) {
        return "Object";
    }
}