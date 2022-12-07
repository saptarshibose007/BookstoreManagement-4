package com.bootcamp.bookstoremanagement.exception;

public class CategoryNotFoundException extends RuntimeException {

	public CategoryNotFoundException(String message) {
		super(message);
	}

}
