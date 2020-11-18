package com.xxx;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetchers {

    private static List<ImmutableMap<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1", "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223", "authorId", "author-1")
    );
    private static List<ImmutableMap<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1", "firstName", "Joanne",
                    "lastName", "Rowling")
    );

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            // 获取 id参数，然后根据id查找 books集合并返回相应的 Book信息
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books.stream().filter(book -> book.get("id").equals(bookId))
                    .findFirst().orElse(null);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            // DataFetcher 会按照 GraphQL Schema定义从外层向内层调用
            // 这里可以直接通过 DataFetchingEnvironment获取外层 DataFetcher查找到的数据(即关联的Book）
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");  // 根据 authorId查找作者信息
            return authors.stream().filter(author -> author.get("id").equals(authorId))
                    .findFirst().orElse(null);
        };
    }

    public DataFetcher listDataFetcher() {
        return dataFetchingEnvironment -> books;
    }
}