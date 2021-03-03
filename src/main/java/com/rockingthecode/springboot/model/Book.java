package com.rockingthecode.springboot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer totalPageNumber;
    private Double price;
    private Date publishDate;
}
