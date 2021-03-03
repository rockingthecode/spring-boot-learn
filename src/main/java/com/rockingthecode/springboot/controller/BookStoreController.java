package com.rockingthecode.springboot.controller;

import com.rockingthecode.springboot.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.util.comparator.Comparators;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BookStoreController {

    private final List<Book> books = new ArrayList<>();

    public BookStoreController() {
        Book daVinciCode = Book.builder()
                .id(1L)
                .author("Dan Brown")
                .title("Da Vinci Code")
                .publishDate(new Date())
                .price(50.5)
                .build();

        Book harryPotter = Book.builder()
                .id(2L)
                .author("J.K Rowling")
                .title("Harry Potter and the Philosopher's Stone")
                .price(48.5)
                .publishDate(new Date())
                .build();

        this.books.add(harryPotter);
        this.books.add(daVinciCode);
    }

    @GetMapping(value = "books")
    public List<Book> getBooks() {
        return books;
    }

    @GetMapping(value = "books/{id}")
    public Optional<Book> getBooks(@PathVariable Long id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    @PostMapping(value = "books")
    public Book createBook(@RequestBody Book book) {
        //finding maximum id of existing books
        long maxId = books.stream()
                .map(Book::getId)
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        //setting id for new book
        book.setId(maxId + 1);
        books.add(book);
        return book;
    }

    @PutMapping(value = "books/{id}")
    public Optional<Book> updatePrice(@PathVariable Long id, @RequestBody Book book) {
        //filtering out new book
        Optional<Book> existingBook = books.stream().filter(b -> b.getId().equals(id)).findFirst();
        //setting id for new book
        existingBook.ifPresent(value -> value.setPrice(book.getPrice()));
        return existingBook;
    }

    @DeleteMapping(value = "books/{id}")
    public void delete(@PathVariable Long id) {
        //setting id for new book
        books.removeIf(b -> b.equals(id));
    }
}
