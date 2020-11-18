package com.xxx.service;

import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.stereotype.Service;

/**
 * @author xujunming
 * Created on 2020-04-07
 */
@Service // Spring的@Service注解
public class DemoService {
    // 添加@Trace注解，使用该注解需要引入apm-toolkit-trace依赖，
    // 在搭建demo-webapp项目时已经介绍过了，pom文件不再展示
    @Trace(operationName = "default-trace-method")
    public void traceMethod() throws Exception {
        Thread.sleep(1000);
        ActiveSpan.tag("trace-method",
                String.valueOf(System.currentTimeMillis()));
        ActiveSpan.info("traceMethod info Message");
        System.out.println(TraceContext.traceId()); // 打印Trace ID
    }
}