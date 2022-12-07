package com.bootcamp.bookstoremanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.bookstoremanagement.entity.Book;
import com.bootcamp.bookstoremanagement.exception.BookNotFoundException;
import com.bootcamp.bookstoremanagement.exception.DuplicateIdException;
import com.bootcamp.bookstoremanagement.repository.IBookRepository;
import com.bootcamp.bookstoremanagement.repository.ICategoryRepository;

@Service
public class BookServiceImpl implements IBookSerivce {
	
	@Autowired
	IBookRepository bookRepository;
	@Autowired
	ICategoryRepository categoryRepository;

	@Override
	public Book createBook(Book book) {
		List<Book> bookList = bookRepository.findAll();
		for(Book b : bookList) {
			if(b.getBookId()==book.getBookId()) {
				throw new DuplicateIdException("This bookId is already taken, Please change the id");
			}
		}
		return bookRepository.save(book);
	}

	@Override
	public List<Book> listAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book deleteBook(int id) {		
		bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("The book is not present"));
		bookRepository.deleteById(id);		
		return null;
	}

	@Override
	public Book editBook(Book book) {	
		bookRepository.findById(book.getBookId()).orElseThrow(()-> new BookNotFoundException("The book is not present"));
		return bookRepository.save(book);
	}

	@Override
	public Book viewBook(int id) {
		return bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("The book is not present"));
	}

	@Override
	public List<Book> listBookByCategory(String category) {		
		List<Book> bookList = bookRepository.findAll();
		List<Book>listBookByCategory = new ArrayList<>();
		for(Book b : bookList){
			if(category.equals(b.getCategory().getCategoryName())){
				listBookByCategory.add(b);
			}
		}
		return listBookByCategory;	
//		List<Book> bookList = bookRepository.findAll();
//		bookList.forEach(b->category.equals(b.getCategory().getCategoryName()));
//		return bookList;
	}


}
