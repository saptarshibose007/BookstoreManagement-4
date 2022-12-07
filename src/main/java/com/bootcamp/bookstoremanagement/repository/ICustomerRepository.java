package com.bootcamp.bookstoremanagement.repository;

import com.bootcamp.bookstoremanagement.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Integer> {

}
