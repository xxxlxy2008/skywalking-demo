package com.xxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
class BookResolver implements GraphQLResolver<Book> {
    @Autowired
    private AuthorService authorService;

    public Author author(Book book) {
        return authorService.getAuthorById(book.getAuthorId());
    }
}