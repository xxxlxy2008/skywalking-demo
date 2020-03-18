package com.xxx;

import static net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.INJECTION;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

/**
 * @author xujunming
 * Created on 2020-03-15
 */
public class Main2 {
    public static void main(String[] args) throws Exception {
        Foo dynamicFoo = new ByteBuddy()
                .subclass(Foo.class)
                .method(isDeclaredBy(Foo.class)) // 匹配 Foo中所有的方法
                .intercept(FixedValue.value("One!"))
                .method(named("foo")) // 匹配名为 foo的方法
                .intercept(FixedValue.value("Two!"))
                .method(named("foo").and(takesArguments(1))) // 匹配名为foo且只有一个
                // 参数的方法
                .intercept(FixedValue.value("Three!"))
                .make()
                .load(Main2.class.getClassLoader(), INJECTION)
                .getLoaded()
                .newInstance();
        System.out.println(dynamicFoo.bar());
        System.out.println(dynamicFoo.foo());
        System.out.println(dynamicFoo.foo(null));
    }
}
