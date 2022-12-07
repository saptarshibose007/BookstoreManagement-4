package com.bootcamp.bookstoremanagement.exception;

public class CredentialMismatchException extends RuntimeException {
	public CredentialMismatchException(String msg) {
		super(msg);
	}
}
