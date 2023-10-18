package com.example.WebFormHandling.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.WebFormHandling.Models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
