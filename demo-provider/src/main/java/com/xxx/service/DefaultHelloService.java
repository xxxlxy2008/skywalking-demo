package com.xxx.service;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.xxx.HelloService;

/**
 * @author xujunming
 * Created on 2020-03-03
 */
@Service
@Component
public class DefaultHelloService implements HelloService {
    @Override
    public String say(String name) throws Exception {
        Thread.sleep(2000);
        return "hello" + name;
    }
}
