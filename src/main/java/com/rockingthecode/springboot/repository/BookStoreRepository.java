package com.rockingthecode.springboot.repository;

import com.rockingthecode.springboot.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStoreRepository extends CrudRepository<Book, Long> {
}
