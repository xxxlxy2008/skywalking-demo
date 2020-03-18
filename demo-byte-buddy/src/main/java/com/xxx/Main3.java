package com.xxx;

import static net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.INJECTION;
import static net.bytebuddy.matcher.ElementMatchers.named;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

/**
 * @author xujunming
 * Created on 2020-03-15
 */
public class Main3 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        String helloWorld = new ByteBuddy()
                .subclass(DB.class)
                .method(named("hello"))
                // 拦截DB.hello()方法，并委托给 Interceptor中的静态方法处理
                .intercept(MethodDelegation.to(Interceptor.class))
                .make()
                .load(ClassLoader.getSystemClassLoader(), INJECTION)
                .getLoaded()
                .newInstance()
                .hello("World");
        System.out.println(helloWorld);
    }
}
