package com.bootcamp.bookstoremanagement.exception;

public class CustomerNotFoundException extends RuntimeException{
	public CustomerNotFoundException(String msg) {
		super(msg);
	}
}
