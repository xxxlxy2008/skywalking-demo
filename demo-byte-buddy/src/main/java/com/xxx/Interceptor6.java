package com.xxx;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

/**
 * @author xujunming
 * Created on 2020-03-15
 */
public class Interceptor6 {
    @RuntimeType
    public void intercept(@This Object obj,
                          @AllArguments Object[] allArguments) {
        System.out.println("after constructor!");
    }
}
