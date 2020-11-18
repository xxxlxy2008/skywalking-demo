package com.xxx;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQLProvider {

    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        // 读取 GraphQL Schema文件并创建 GraphQL实例，
        // 该GraphQL实例会通过上面的 graphQL()方法暴露给Spring，
        // 默认情况下，请求到"/graphql"这个path上的请求都会由该GraphQL实例处理
        URL url = Resources.getResource("book.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        // GraphQL Schema文件被解析之后，就是这里的 TypeDefinitionRegistry对象
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        // 注册DataFetcher，DataFetcher的介绍以及buildWiring()方法实现在后面马上会进行介绍
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        // 将GraphQL Schema中定义的与 DataFetcher关联起来
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                // 将Query.getById与getBookByIdDataFetcher()方法返回的DataFetcher实现关联
                .type(newTypeWiring("Query").dataFetcher("getById",
                        graphQLDataFetchers.getBookByIdDataFetcher())
                        .dataFetcher("list", graphQLDataFetchers.listDataFetcher()))
                // 将Book.author字段与getBookByIdDataFetcher()方法返回的DataFetcher实现关联
                .type(newTypeWiring("Book").dataFetcher("author",
                        graphQLDataFetchers.getAuthorDataFetcher()))
                .build();
    }
}