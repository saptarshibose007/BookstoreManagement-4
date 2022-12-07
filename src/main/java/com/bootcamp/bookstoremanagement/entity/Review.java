package com.bootcamp.bookstoremanagement.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Review {
	
	@Id
	private int reviewId;
	@OneToOne
	@JoinColumn(name="bookId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Book book;	
	@OneToOne
	@JoinColumn(name="customerId" )
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;
	private String headLine;
	private String comment;
	private String rating;
	private LocalDate reviewOn;
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getHeadLine() {
		return headLine;
	}
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public LocalDate getReviewOn() {
		return reviewOn;
	}
	public void setReviewOn(LocalDate reviewOn) {
		this.reviewOn = reviewOn;
	}
	
}
