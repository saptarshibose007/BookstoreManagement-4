package com.bootcamp.bookstoremanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.bookstoremanagement.entity.Address;
import com.bootcamp.bookstoremanagement.entity.Book;
import com.bootcamp.bookstoremanagement.entity.Category;
import com.bootcamp.bookstoremanagement.entity.Customer;
import com.bootcamp.bookstoremanagement.entity.OrderDetails;
import com.bootcamp.bookstoremanagement.entity.Review;
import com.bootcamp.bookstoremanagement.entity.User;
import com.bootcamp.bookstoremanagement.exception.BookNotFoundException;
import com.bootcamp.bookstoremanagement.exception.CategoryNotFoundException;
import com.bootcamp.bookstoremanagement.exception.NegativeIdException;
import com.bootcamp.bookstoremanagement.exception.OrderNotFoundException;
import com.bootcamp.bookstoremanagement.exception.ReviewNotFoundException;
import com.bootcamp.bookstoremanagement.exception.UnsuccessfulDeletionException;
import com.bootcamp.bookstoremanagement.service.IAddressService;
import com.bootcamp.bookstoremanagement.service.IBookSerivce;
import com.bootcamp.bookstoremanagement.service.ICategoryService;
import com.bootcamp.bookstoremanagement.service.ICustomerService;
import com.bootcamp.bookstoremanagement.service.ILoginService;
import com.bootcamp.bookstoremanagement.service.IOrderService;
import com.bootcamp.bookstoremanagement.service.IReviewService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	IBookSerivce bookService;
	@Autowired
	ILoginService loginService;
	@Autowired
	IBookSerivce bookservice;
	@Autowired
	IOrderService orderService;
	@Autowired
	IReviewService reviewService;
	@Autowired
	ICategoryService categoryService;
	@Autowired
	ICustomerService customerService;
	@Autowired
	IAddressService addressService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/index")
	public String  home(){
		return "User Home";
	}
	
	//1.Login Related Activities--->
	
	@PostMapping(path="/register", consumes="application/json")
	public User registerUser(@RequestBody User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_NORMAL");
		return loginService.addUser(user);
	}
	@DeleteMapping("/deleteUser/{id}")
	public String deleterUser(@PathVariable int id){
		if(loginService.removeUser(id)!=null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "User Deleted";
	}
	
	//2.Customer Related Activities-->
	
	@PostMapping(path="/saveCustomer", consumes="application/json")
	public String saveCustomer(@RequestBody Customer customer){
		if(customer.getCustomerId() <= 0) {
			throw new NegativeIdException("Id is invalid (zero/negative/null) , Please enter a valid id");
		}
		customerService.createCustomer(customer); 
		return "Customer Added";
	}	
	@DeleteMapping("/deleteCustomer/{id}")
	public String deleterCustomer(@PathVariable Integer id){
		if(customerService.deleteCustomer(id) != null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "Customer Deleted";
	}
	@GetMapping("/getCustomer/{id}")
	public Customer viewCustomer(@PathVariable int id){
		return customerService.viewCustomer(id);
	}
	@PutMapping(path="/editCustomer",consumes="application/json")
	public Customer updateCustomer(@RequestBody Customer customer){
		return customerService.updateCustomer(customer);
	}
	
	//3.Book Related Activities-->
	
	@GetMapping("/getBook/{id}")
	public Book getBook(@PathVariable Integer id) {	
		return bookService.viewBook(id);	
	}	
	@GetMapping("/getAllBooks")
	public List<Book>getAllBooks(){
		if(bookService.listAllBooks().isEmpty()) {
			throw new BookNotFoundException("No Books in the database");
		}
		return bookService.listAllBooks();
	}
	@GetMapping("/listBookByCategory/{category}")
	public List<Book> listBookByCategory(@PathVariable String category){
		if(bookService.listAllBooks().isEmpty()) {
			throw new BookNotFoundException("No Books of this category is available");
		}
		return bookService.listBookByCategory(category);
	}
	
	//4.Category Related Activity -->
	
	@GetMapping("/getCategory/{id}")
	public Category viewCategory(@PathVariable int id) {
		if(categoryService.viewCategory(id) != null) {
			throw new CategoryNotFoundException("No such category exists");
		}
		return categoryService.viewCategory(id);
	}
	@GetMapping("/getAllCategories")
	public List<Category> viewAllCategory(){
		if(categoryService.viewAllCategories().isEmpty()) {
			throw new CategoryNotFoundException("No such category exists");
		}
		return categoryService.viewAllCategories();
	}
	
	//5.Review Related Activities-->
	
	@PutMapping(path="/editReview",consumes="application/json")
	public Review updateReview(@RequestBody Review review){
		return reviewService.updateReview(review);
	}	
	@PostMapping(path="/saveReview",consumes="application/json")
	public String addReview(@RequestBody Review review){
		if(review.getReviewId() <= 0) {
			throw new NegativeIdException("Id is invalid (zero/negative/null) , Please enter a valid id");
		}
		reviewService.addReview(review);
		return  "Review Added";
	}
	@GetMapping("/listAllReviews")
	public List<Review> listAllReviews(){
		if(reviewService.listAllReviews().isEmpty()) {
			throw new ReviewNotFoundException("No entry of Reviews in the database");
		}
		return reviewService.listAllReviews();
	}	
	@DeleteMapping("/deleteReview/{id}")
	public String deleteReview(@PathVariable int id){
		if(reviewService.deleteReview(id) != null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "Review Deleted";
	}
	@GetMapping(path="/getReview/{id}",consumes="application/json")
	public Address viewAddress(@PathVariable int id){
		return addressService.viewAddress(id);
	}
	@GetMapping("/listAllReviewsByBook")
	public List<Review> listAllReviewsByBook(@RequestBody Book book){
		if(reviewService.listAllReviewsByBook(book).isEmpty()) {
			throw new ReviewNotFoundException("No records of review for this book is available");
		}
		return reviewService.listAllReviewsByBook(book);
	}
	
	//6.Address Related Activities-->
	
	@GetMapping("/getAddress/{id}")
	public Address viewaddress(@PathVariable int id){
		return addressService.viewAddress(id);
	}
	@PostMapping(path="/saveAddress",consumes="application/json")
	public String addAddress(@RequestBody Address address){
		if(address.getAddressId() <= 0) {
			throw new NegativeIdException("Id is invalid (zero/negative/null) , Please enter a valid id");
		}
		addressService.addAddress(address);
		return "Address Added";
	}
	@PutMapping(path="/editAddress",consumes="application/json")
	public Address editAddress(@RequestBody Address address){
		return addressService.editAddress(address);	 
	}	 
	@DeleteMapping("/deleteAddress/{id}")
	public String deleteAddress(@PathVariable int id){
		if(addressService.deleteAddress(id) != null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "Address Deleted";
	}
	
	//7.Order Related Activities -->
	
	@PostMapping(path="/saveOrder",consumes ="application/json")
	public String addOrder(@RequestBody OrderDetails orderDetails) {
		if(orderDetails.getOrderId() <= 0) {
			throw new NegativeIdException("Id is invalid (zero/negative/null) , Please enter a valid id");
		}
		orderService.addOrder(orderDetails);
		return "Order Added";
	}
	@PutMapping(path="/editOrder",consumes ="application/json")
	public OrderDetails editOrder(@RequestBody OrderDetails orderDetails) {
		return orderService.updateOrder(orderDetails);
	}
	@DeleteMapping("/deleteOrder/{id}")
	public String deleteOrder(@PathVariable int id){
		if(orderService.cancelOrder(id) != null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "Order Deleted";
	}	
	@GetMapping("/getOrder/{id}")
	public OrderDetails getOrder(@PathVariable int id) {
		return orderService.getOrder(id);
	}	
}
