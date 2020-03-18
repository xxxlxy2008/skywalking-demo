package com.xxx;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author xujunming
 * Created on 2020-03-15
 */
public class Main {
    public static void main(String[] args)throws Exception {
        String str = new ByteBuddy() // 创建ByteBuddy对象
                .subclass(Object.class) // subclass增强方式
                .name("com.xxx.Type") // 新类型的类名
                // 拦截其中的toString()方法
                .method(ElementMatchers.named("toString"))
                // 让toString()方法返回固定值
                .intercept(FixedValue.value("Hello World!"))
                .make()
                // 加载新类型，默认WRAPPER策略
                .load(ByteBuddy.class.getClassLoader())
                .getLoaded()
                .newInstance() // 通过 Java反射创建 com.xxx.Type实例
                .toString(); // 调用 toString()方法
        System.out.println(str);
    }
}
