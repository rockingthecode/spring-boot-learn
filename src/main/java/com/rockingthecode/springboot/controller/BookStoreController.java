package com.rockingthecode.springboot.controller;

import com.rockingthecode.springboot.configuration.Publisher;
import com.rockingthecode.springboot.exception.BadRequestException;
import com.rockingthecode.springboot.model.Book;
import com.rockingthecode.springboot.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
public class BookStoreController {

    private BookStoreRepository bookStoreRepository;

    private Publisher publisher;

    @Value("${validation.maxPageCount:1000}")
    private Integer maxPageCount;

    @Value("${validation.errorMessage: Book's page count is exceeding allowed limit : ${validation.maxPageCount}}")
    private String errorMessage;

    public BookStoreController(BookStoreRepository bookStoreRepository, Publisher publisher) {
        this.bookStoreRepository = bookStoreRepository;
        this.publisher = publisher;
    }

    @PostMapping(value = "books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Optional.ofNullable(book.getTotalPageNumber())
                .filter(pageNumber ->  pageNumber < maxPageCount)
                .orElseThrow(()->new BadRequestException(errorMessage));
        book.setPublisherWebSite(publisher.getWebSite());
        return new ResponseEntity<Book>(bookStoreRepository.save(book), HttpStatus.CREATED);
    }

    @GetMapping(value = "books")
    public Iterable<Book> getBooks() {
        return bookStoreRepository.findAll();
    }

    @GetMapping(value = "books/{id}")
    public Optional<Book> getBooks(@PathVariable Long id) {
        return bookStoreRepository.findById(id);
    }

    @PutMapping(value = "books/{id}")
    public ResponseEntity<Book> updatePrice(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> existingBook = bookStoreRepository.findById(id);
        if (existingBook.isPresent()) {
            existingBook.get().setPrice(book.getPrice());
            return new ResponseEntity<Book>(bookStoreRepository.save(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "books/{id}")
    public void delete(@PathVariable Long id) {
        bookStoreRepository.deleteById(id);
    }
}
