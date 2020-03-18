package com.xxx;

import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @author xujunming
 * Created on 2020-03-03
 */
@RestController
@RequestMapping("/")
public class HelloWorldController {

    @Reference
    private HelloService helloService;

    @GetMapping("/hello/{words}")
    public String hello(@PathVariable("words") String words)throws Exception{
        Thread.sleep(1000);
        System.out.println("traceId:" + TraceContext.traceId());
        ActiveSpan.tag("hello-trace activation", words);
        // Dubbo 方式调用demo-provider
        String result = helloService.say(words);
        Thread.sleep(1000);
        return result;
    }

    @GetMapping("/err")
    public String err() {
        String traceId = TraceContext.traceId();
        System.out.println("traceId:" + TraceContext.traceId());
        ActiveSpan.tag("error-trace activation", "error");
        throw new RuntimeException("err");
    }
}
