package com.xxx.impl;

import com.xxx.Log;

public class Logback implements Log {
    @Override
    public void log(String info) {
        System.out.println("Logback:" + info);
    }
}