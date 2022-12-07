package com.bootcamp.bookstoremanagement.repository;

import com.bootcamp.bookstoremanagement.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Integer> {

}
