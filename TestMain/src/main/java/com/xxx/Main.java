package com.xxx;

/**
 * @author xujunming
 * Created on 2020-03-09
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(new TestClass().getNumber());
        while (true) {
            Thread.sleep(1000); // 注意，这里是新建TestClass对象
            System.out.println(new TestClass().getNumber());
        }
    }
}
