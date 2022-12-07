package com.bootcamp.bookstoremanagement.service;

import java.util.List;

import com.bootcamp.bookstoremanagement.entity.Book;
import com.bootcamp.bookstoremanagement.entity.Customer;
import com.bootcamp.bookstoremanagement.entity.Review;

public interface IReviewService {
	
	public List<Review> listAllReviews();  //done both
	public Review addReview(Review review); //done user
	public Review updateReview(Review review); // done user
	public Review deleteReview(int id); // user
	public Review viewReview(int id); // both
	public List<Review> listAllReviewsByBook(Book book); //both
	public List<Review> listAllReviewByCustomer(Customer customer); //both
	

}
