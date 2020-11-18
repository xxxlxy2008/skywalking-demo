package com.xxx;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class AuthorServiceImpl implements AuthorService {

    private AtomicLong idGenerator = new AtomicLong(0L);

    private static List<Author> authors = Lists.newCopyOnWriteArrayList();

    @Override
    public String createAuthor(String firstName, String lastName) {
        String id = "author-" + idGenerator.getAndIncrement();
        Author author = new Author();
        author.setId(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authors.add(author);
        return id;
    }

    @Override
    public Author getAuthorById(String id) {
        return authors.stream().filter(a -> a.getId().equals(id))
                .findFirst().orElse(null);
    }
}