package com.xxx;

import static net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.INJECTION;
import static net.bytebuddy.matcher.ElementMatchers.any;

import java.lang.reflect.Constructor;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;

/**
 * @author xujunming
 * Created on 2020-03-15
 */
public class Main6 {
    public static void main(String[] args) throws Exception {
        Constructor<? extends DB> constructor = new ByteBuddy()
                .subclass(DB.class)
                .constructor(any()) // 通过constructor()方法拦截所有构造方法
                // 拦截的操作：首先调用目标对象的构造方法，根据前面自动匹配，
                // 这里直接匹配到参数为String.class的构造方法
                .intercept(SuperMethodCall.INSTANCE.andThen(
                        // 执行完原始构造方法，再开始执行interceptor的代码
                        MethodDelegation.withDefaultConfiguration()
                                .to(new Interceptor6())
                )).make().load(Main.class.getClassLoader(), INJECTION)
                .getLoaded()
                .getConstructor(String.class);
        // 下面通过反射创建生成类型的对象
        constructor.newInstance("MySQL");
    }
}
