package com.bootcamp.bookstoremanagement.repository;

import com.bootcamp.bookstoremanagement.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review,Integer> {

}
