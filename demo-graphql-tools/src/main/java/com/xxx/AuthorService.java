package com.xxx;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public interface AuthorService extends GraphQLQueryResolver, GraphQLMutationResolver {
    String createAuthor(String firstName, String lastName);

    Author getAuthorById(String id);
}