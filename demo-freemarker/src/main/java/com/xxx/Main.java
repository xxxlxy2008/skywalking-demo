package com.xxx;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author xujunming
 * Created on 2020-05-02
 */
public class Main {
    public static void main(String[] args) throws Exception {
        //1.初始化并配置Configuration对象
        Configuration configuration =
                new Configuration(Configuration.getVersion());
        //2.设置模板文件所在的目录
        configuration.setClassForTemplateLoading(Main.class, "/template");
        //3.设置字符集
        configuration.setDefaultEncoding("utf-8");
        //4.加载模板文件
        Template template = configuration.getTemplate("test.ftl");
        //5.创建数据模型
        Map<String, Object> result = createData();
        //6.创建Writer对象
        FileWriter writer = new FileWriter(
                new File("/Users/xxx/Documents/log/test.html"));
        //7.输出数据模型到文件中
        template.process(result, writer);
        //8.关闭Writer对象
        writer.close();
    }

    private static Map<String, Object> createData() {
        Map<String, Object> result = new HashMap<>();
        result.put("animals", createAnimals());
        result.put("user", "freemarker-user");
        result.put("message", "It is a test!");
        return result;
    }

    private static List<Animal> createAnimals() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Mouse());
        animals.add(new Elephant());
        animals.add(new Python());
        return animals;
    }
}