package com.xxx;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

public class TimeInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method,
          @SuperCall Callable<?> callable) throws Exception {
        long start = System.currentTimeMillis();
        try {
            return callable.call(); // 执行原函数
        } finally {
            System.out.println(method.getName() + ":"
                    + (System.currentTimeMillis() - start) + "ms");
        }
    }
}