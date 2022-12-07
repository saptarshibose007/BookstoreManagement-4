package com.bootcamp.bookstoremanagement.repository;

import com.bootcamp.bookstoremanagement.entity.OrderDetails;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderDetails,Integer> {

}
