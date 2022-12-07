package com.bootcamp.bookstoremanagement.exception;

public class DuplicateIdException extends RuntimeException {
	public DuplicateIdException(String msg) {
		super(msg);
	}
}
