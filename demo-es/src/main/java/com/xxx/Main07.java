package com.xxx;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author xujunming
 * Created on 2020-03-22
 */
public class Main07 {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder( // 指定 ElasticSearch 集群各个节点的地址和端口号
                        new HttpHost("localhost", 9200, "http")));
        // 创建 SearchRequest请求
        SearchRequest searchRequest = new SearchRequest("skywalking");
        // 通过 SearchSourceBuilder,用来构造检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 通过id进行检索，这里查询 Id为1和2的两个Document
        searchSourceBuilder.query(QueryBuilders.idsQuery().addIds("1", "2"));
        searchRequest.source(searchSourceBuilder);
        // 发送 SearchRequest 请求
        SearchResponse searchResponse = client.search(searchRequest);
        // 遍历 SearchHit
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getId() + ":" +
                    searchHit.getSourceAsMap());
        }
    }
}