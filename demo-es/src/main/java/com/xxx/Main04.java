package com.xxx;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchParseException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

/**
 * @author xujunming
 * Created on 2020-03-22
 */
public class Main04 {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder( // 指定 ElasticSearch 集群各个节点的地址和端口号
                        new HttpHost("localhost", 9200, "http")));
        GetRequest request = new GetRequest("skywalking", "type", "1");
        // 不查询 "_source"字段以及其他字段
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        // 通过exists()方法发送 GetRequest
        boolean exists = client.exists(request);
        System.out.println(exists);
    }
}