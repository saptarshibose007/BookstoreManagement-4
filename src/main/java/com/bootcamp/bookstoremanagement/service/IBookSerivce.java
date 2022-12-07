package com.bootcamp.bookstoremanagement.service;

import java.util.List;

import com.bootcamp.bookstoremanagement.entity.Book;

public interface IBookSerivce {
	
	public Book createBook(Book book); 
	public List<Book>listAllBooks(); 
	public Book deleteBook(int id); 
	public Book editBook(Book book); 
	public Book viewBook(int id);
	public List<Book> listBookByCategory(String category);
	
	
	
}
