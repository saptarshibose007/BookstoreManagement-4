package com.bootcamp.bookstoremanagement.repository;

import com.bootcamp.bookstoremanagement.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoginRepository extends JpaRepository<User,Integer> {
	public User findByEmail(String email);
}
