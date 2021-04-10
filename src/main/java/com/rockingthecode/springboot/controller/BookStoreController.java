package com.rockingthecode.springboot.controller;

import com.rockingthecode.springboot.model.Book;
import com.rockingthecode.springboot.repository.BookStoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookStoreController {

    private final BookStoreRepository bookStoreRepository;

    public BookStoreController(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @GetMapping(value = "books")
    public Iterable<Book> getBooks() {
        return bookStoreRepository.findAll();
    }

    @GetMapping(value = "books/{id}")
    public Optional<Book> getById(@PathVariable Long id) {
        return bookStoreRepository.findById(id);
    }

    @PostMapping(value = "/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookStoreRepository.save(book), HttpStatus.CREATED);
    }

    @PutMapping(value = "books/{id}")
    public ResponseEntity<Book> updatePrice(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> existingBook = bookStoreRepository.findById(id);
        if (existingBook.isPresent()) {
            existingBook.get().setPrice(book.getPrice());
            return new ResponseEntity<>(bookStoreRepository.save(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "books/{id}")
    public void delete(@PathVariable Long id) {
        bookStoreRepository.deleteById(id);
    }
}
