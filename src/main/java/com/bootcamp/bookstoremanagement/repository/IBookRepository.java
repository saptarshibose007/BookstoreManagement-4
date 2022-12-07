package com.bootcamp.bookstoremanagement.repository;

import com.bootcamp.bookstoremanagement.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book,Integer> {

}
