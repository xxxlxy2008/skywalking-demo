package com.xxx;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

/**
 * @author xujunming
 * Created on 2020-03-22
 */
public class Main06 {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder( // 指定 ElasticSearch 集群各个节点的地址和端口号
                        new HttpHost("localhost", 9200, "http")));
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("age", 30);
            builder.field("message", "update Test");
        }
        builder.endObject();
        // 创建 UpdateReques请求
        UpdateRequest request = new UpdateRequest("skywalking",
                "type","1").doc(builder);
        // 发送请求
        UpdateResponse updateResponse = client.update(request);
        System.out.println(updateResponse);
    }
}