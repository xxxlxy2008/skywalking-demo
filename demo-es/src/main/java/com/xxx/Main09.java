package com.xxx;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author xujunming
 * Created on 2020-03-22
 */
public class Main09 {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder( // 指定 ElasticSearch 集群各个节点的地址和端口号
                        new HttpHost("localhost", 9200, "http")));
        // 该 Listener可以监听每个 BulkRequest请求相关事件
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            public void beforeBulk(long executionId, BulkRequest request) {
                // 在 BulkRequest请求发送之前，会触发该方法
                System.out.println("beforeBulk");
            }

            public void afterBulk(long executionId, BulkRequest request,
                                  BulkResponse response) {
                // 在收到 BulkResponse响应时触发该方法，这里可以通过
                // response.hasFailures()方法判断请求是否失败
                System.out.println("afterBulk");
            }

            public void afterBulk(long executionId, BulkRequest request,
                                  Throwable failure) {
                // 在 BulkRequest请求抛出异常的时候，会触发该方法
                System.out.println("afterBulk2");
            }
        };
        // 创建 BulkProcessor
        BulkProcessor bulkProcessor = BulkProcessor.builder(client::bulkAsync,
                listener).setBulkActions(10)
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
                .setFlushInterval(TimeValue.timeValueSeconds(10))
                .setConcurrentRequests(1)
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(
                        TimeValue.timeValueMillis(100), 3)).build();

        // 创建 IndexRequest请求，这里需要指定 Index名称
        IndexRequest request = new IndexRequest("skywalking");
        request.type("type");
        request.id("2");  // Document Id，不指定的话，ElasticSearch 为其自动分配
        // Document的具体内容
        String jsonString = "{" +
                "\"user\":\"kim\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"age\":29," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);
        bulkProcessor.add(request);
        DeleteRequest request2 = new DeleteRequest("skywalking", "type", "1");
        bulkProcessor.add(request2);
    }
}