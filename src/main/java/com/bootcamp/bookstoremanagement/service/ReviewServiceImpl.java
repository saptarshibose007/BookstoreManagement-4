package com.bootcamp.bookstoremanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.bookstoremanagement.entity.Book;
import com.bootcamp.bookstoremanagement.entity.Customer;
import com.bootcamp.bookstoremanagement.entity.Review;
import com.bootcamp.bookstoremanagement.exception.DuplicateIdException;
import com.bootcamp.bookstoremanagement.exception.ReviewNotFoundException;
import com.bootcamp.bookstoremanagement.repository.IReviewRepository;

@Service
public class ReviewServiceImpl implements IReviewService {
	
	@Autowired
	private IReviewRepository reviewRepository;
	
	@Override
	public List<Review> listAllReviews() {
		return reviewRepository.findAll();
	}
	@Override
	public Review addReview(Review review) {	
		List<Review> ReviewList = reviewRepository.findAll();
		for(Review c : ReviewList) {
			if(c.getReviewId()==review.getReviewId()) {
				throw new DuplicateIdException("This categoryId is already taken, Please change the id");
		}
	}
		return reviewRepository.save(review);
	}
	@Override
	public Review updateReview(Review review) {
		reviewRepository.findById(review.getReviewId()).orElseThrow(()-> new ReviewNotFoundException("No such reviews exist"));
		return reviewRepository.save(review);
	}
	@Override
	public Review deleteReview(int id) {
		reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFoundException("No such reviews exist"));
		reviewRepository.deleteById(id);;
		return null ;
	}
	@Override
	public Review viewReview(int id) {
		return reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFoundException("No such reviews exist")); 
	}
	@Override
    public List<Review> listAllReviewsByBook(Book book) {
        List<Review> reviewList = reviewRepository.findAll();
        List<Review> reviewByBook = new ArrayList<>();
        for(Review r : reviewList) {
            if(r.getBook().getBookId()==book.getBookId()) {
                reviewByBook.add(r);
            }
        }
        return reviewByBook;
    }
	  @Override
	    public List<Review> listAllReviewByCustomer(Customer customer) {
	        List<Review> reviewList = reviewRepository.findAll();
	        List<Review> reviewByCustomerList = new ArrayList<>();
	        for(Review r : reviewList) {
	            if(r.getCustomer().getCustomerId()==customer.getCustomerId()) {
	                reviewByCustomerList.add(r);
	            }
	        }
	        return reviewByCustomerList;
	    }

	

}
