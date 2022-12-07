package com.bootcamp.bookstoremanagement.exception;

public class NegativeIdException extends RuntimeException {
	public NegativeIdException(String msg) {
		super(msg);
	}
}
