package com.xxx;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class BookServiceImpl implements BookService {
    // 使用递增方式生成 id后缀
    private AtomicLong idGenerator = new AtomicLong(0L);
    // 这里并没有使用持久化存储，而是使用该 List将图书信息保存在内存中
    private static List<Book> books = Lists.newCopyOnWriteArrayList();

    @Override
    public Book getBookById(String id) {
        return books.stream().filter(b -> b.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<Book> list() {
        return books;
    }

    @Override
    public Book createBook(BookInput input) {
        String id = "book-" + idGenerator.getAndIncrement();
        Book book = new Book();
        book.setId(id);
        book.setName(input.getName());
        book.setPageCount(input.getPageCount());
        book.setAuthorId(input.getAuthorId());
        books.add(book);
        return book;
    }
}
