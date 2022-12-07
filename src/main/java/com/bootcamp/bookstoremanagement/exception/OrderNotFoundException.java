package com.bootcamp.bookstoremanagement.exception;

public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException(String msg) {
		super(msg);
	}
}
