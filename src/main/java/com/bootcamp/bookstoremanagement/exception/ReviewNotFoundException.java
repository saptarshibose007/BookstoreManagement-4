package com.bootcamp.bookstoremanagement.exception;

public class ReviewNotFoundException extends RuntimeException {
	public ReviewNotFoundException(String msg) {
		super(msg);
	}
}
