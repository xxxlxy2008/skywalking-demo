package com.xxx;

import static net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.INJECTION;
import static net.bytebuddy.matcher.ElementMatchers.named;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Morph;

/**
 * @author xujunming
 * Created on 2020-03-15
 */
public class Main5 {
    public static void main(String[] args) throws Exception {
        String hello = new ByteBuddy()
                .subclass(DB.class)
                .method(named("hello"))
                .intercept(MethodDelegation.withDefaultConfiguration()
                        .withBinders(
                                // 要用@Morph注解之前，需要通过 Morph.Binder 告诉 Byte Buddy
                                // 要注入的参数是什么类型
                                Morph.Binder.install(OverrideCallable.class)
                        )
                        .to(new Interceptor5()))
                .make()
                .load(Main5.class.getClassLoader(), INJECTION)
                .getLoaded()
                .newInstance()
                .hello("World");
    }
}
