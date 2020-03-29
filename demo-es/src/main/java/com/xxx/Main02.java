package com.xxx;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author xujunming
 * Created on 2020-03-22
 */
public class Main02 {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder( // 指定 ElasticSearch 集群各个节点的地址和端口号
                        new HttpHost("localhost", 9200, "http")));
        // 创建 IndexRequest请求，这里需要指定 Index名称
        IndexRequest request = new IndexRequest("skywalking");
        request.type("type");
        request.id("1");  // Document Id，不指定的话，ElasticSearch 为其自动分配
        // Document的具体内容
        String jsonString = "{" +
                "\"user\":\"kim\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"age\":29," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);
        // 发送 IndexRequest请求，不抛异常，就是创建成了
        IndexResponse response = client.index(request);
        System.out.println(response);
    }
}