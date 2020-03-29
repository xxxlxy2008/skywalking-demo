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

/**
 * @author xujunming
 * Created on 2020-03-22
 */
public class Main03 {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder( // 指定 ElasticSearch 集群各个节点的地址和端口号
                        new HttpHost("localhost", 9200, "http")));
        try {
            // 创建 GetRequest请求，这里指定 Index、type以及 Document Id，
            // 在高版本中，type参数已经消失
            GetRequest request = new GetRequest("skywalking", "type", "1");
            // 发送 GetRequest请求
            GetResponse response = client.get(request);
            // 从 GetResponse响应中可以拿到相应的 Document以及相关信息
            String index = response.getIndex(); // 获取 Index名称
            String id = response.getId();// 获取 Document Id
            if (response.isExists()) { // 检查 Document是否存在
                long version = response.getVersion(); // Document版本
                System.out.println("version:" + version);
                // 获取不同格式的 Document内容
                String sourceAsString = response.getSourceAsString();
                System.out.println("sourceAsString:" + sourceAsString);
                Map<String, Object> sourceAsMap = response.getSourceAsMap();
                System.out.println("sourceAsMap:" + sourceAsMap);
                byte[] sourceAsBytes = response.getSourceAsBytes();
                // 按照字段进行遍历
                Map<String, DocumentField> fields = response.getFields();
                for (Map.Entry<String, DocumentField> entry :
                        fields.entrySet()) {
                    DocumentField documentField = entry.getValue();
                    String name = documentField.getName();
                    Object value = documentField.getValue();
                    System.out.println(name + ":" + value);
                }
            } else {
                System.out.println("Document Not Exist!");
            }
        } catch (ElasticsearchParseException e) { // Index不存在的异常
            if (e.status() == RestStatus.NOT_FOUND) {
                System.err.println("Can find index");
            }
        }
    }
}