package com.bootcamp.bookstoremanagement.repository;

import com.bootcamp.bookstoremanagement.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address,Integer> {

}
