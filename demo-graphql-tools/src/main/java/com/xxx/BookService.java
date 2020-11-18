package com.xxx;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public interface BookService extends GraphQLQueryResolver, GraphQLMutationResolver {
    Book getBookById(String id);

    List<Book> list();

    Book createBook(BookInput input);
}