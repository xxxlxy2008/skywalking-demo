package com.xxx;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author xujunming
 * Created on 2020-03-22
 */
public class Main08 {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder( // 指定 ElasticSearch 集群各个节点的地址和端口号
                        new HttpHost("localhost", 9200, "http")));
        // 创建 SearchRequest请求，查询的Index为skywalking
        SearchRequest searchRequest = new SearchRequest("skywalking");
        // 通过 SearchSourceBuilder,用来构造检索条件
        SearchSourceBuilder sourceBuilder =
                SearchSourceBuilder.searchSource();
        // 创建 BoolQueryBuilder
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 符合条件的 Document中 age字段的值必须位于[10,40]这个范围
        List<QueryBuilder> mustQueryList = boolQueryBuilder.must();
        mustQueryList.add(QueryBuilders.rangeQuery("age").gte(10));
        mustQueryList.add(QueryBuilders.rangeQuery("age").lte(40));
        // 符合条件的 Document中 user字段的值必须为kim
        boolQueryBuilder.must().add(
                QueryBuilders.termQuery("user", "kim"));
        sourceBuilder.query(boolQueryBuilder);
        searchRequest.source(sourceBuilder);
        // 发送 SearchRequest 请求
        SearchResponse searchResponse = client.search(searchRequest);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getId() + ":" +
                    searchHit.getSourceAsMap());
        }
    }
}