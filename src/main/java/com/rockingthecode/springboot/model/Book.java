package com.rockingthecode.springboot.model;

import com.rockingthecode.springboot.configuration.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private Integer totalPageNumber;
    private Double price;
    private Date publishDate;
    private String publisherWebSite;
}
