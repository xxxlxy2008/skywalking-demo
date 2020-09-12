package com.xxx;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;

/**
 * @author xujunming
 * Created on 2020-03-22
 */
public class Main01 {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder( // 指定 ElasticSearch 集群各个节点的地址和端口号
                        new HttpHost("localhost", 9200, "http")));
        // 创建 CreateIndexRequest请求，该请求会创建一个名为"skywalking"的 Index，
        // 注意，Index 的名称必须是小写
        CreateIndexRequest request = new CreateIndexRequest("skywalking");
        // 在 CreateIndexRequest请求中设置 Index的 setting信息
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)   // 设置分片数量
                .put("index.number_of_replicas", 2)  // 设置副本数量
        );
        // 在 CreateIndexRequest请求中设置 Index的 Mapping信息，新建的 Index里有
        // 个user和message两个字段，都为text类型，还有一个 age字段，为 integer类型
        request.mapping("type", "user", "type=text", "age", "type=integer",
                "message", "type=text");
        // 设置请求的超时时间
        request.timeout(TimeValue.timeValueSeconds(5));
        // 发送 CreateIndex请求
        CreateIndexResponse response = client.indices().create(request);
        // 这里关心 CreateIndexResponse响应的 isAcknowledged字段值
        // 该字段为 true则表示 ElasticSearch已处理该请求
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);
    }
}