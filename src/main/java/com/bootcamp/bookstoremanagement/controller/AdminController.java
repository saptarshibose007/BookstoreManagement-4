package com.bootcamp.bookstoremanagement.controller;

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

import java.util.List;

import com.bootcamp.bookstoremanagement.entity.Address;
import com.bootcamp.bookstoremanagement.entity.Book;
import com.bootcamp.bookstoremanagement.entity.Category;
import com.bootcamp.bookstoremanagement.entity.Customer;
import com.bootcamp.bookstoremanagement.entity.OrderDetails;
import com.bootcamp.bookstoremanagement.entity.Review;
import com.bootcamp.bookstoremanagement.entity.User;
import com.bootcamp.bookstoremanagement.exception.AddressNotFoundException;
import com.bootcamp.bookstoremanagement.exception.BookNotFoundException;
import com.bootcamp.bookstoremanagement.exception.CategoryNotFoundException;
import com.bootcamp.bookstoremanagement.exception.CustomerNotFoundException;
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
@RequestMapping("/admin")
@CrossOrigin(origins="*")
public class AdminController { 
	
	@Autowired
	IBookSerivce bookService;
	@Autowired
	ICategoryService categoryService;
	@Autowired
	ICustomerService customerService;
	@Autowired
	IReviewService reviewService;
	@Autowired
	IOrderService orderService;
	@Autowired
	IAddressService addressService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	ILoginService loginService;
	
	@GetMapping("/index")
	public String home(){
		return "Admin Home Page";
	}
	
	//User Related Operation-->
	
	@PostMapping(path="/register", consumes="application/json")
	public User registerUser(@RequestBody User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_ADMIN");
		return loginService.addUser(user);
	}
	
	//1.Book Related Operations---->
	@PostMapping(path="/saveBook", consumes="application/json")
	public String addBook(@RequestBody Book book){
		if(book.getBookId() <= 0) {
			throw new NegativeIdException("Id is invalid (zero/negative/null) , Please enter a valid id");
		}
		bookService.createBook(book); 
		return "Book Added";
	}
	@GetMapping("/getAllBooks")
	public List<Book>getAllBooks(){
		if(bookService.listAllBooks().isEmpty()) {
			throw new BookNotFoundException("No Books in the database");
		}
		return bookService.listAllBooks();	
	}
	@GetMapping("/getBook/{id}")
	public Book getBook(@PathVariable Integer id) { 	
		return bookService.viewBook(id);	
	}
	@PutMapping(path="/editBook", consumes="application/json")
	public Book editBook(@RequestBody Book book){
		return bookService.editBook(book);
	}
	@DeleteMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable int id){
		if(bookService.deleteBook(id)!=null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "Book Deleted";
	}
	@GetMapping("/listBookByCategory/{cat}")
	public List<Book> listBookByCategory(@PathVariable String cat){
		if(bookService.listBookByCategory(cat).isEmpty()) {
			throw new BookNotFoundException("No Books of this category is available");
		}
		return bookService.listBookByCategory(cat);
	}
	
	//2.Customer Related Operations-->
	
	@GetMapping("/getCustomer/{id}")
	public Customer getCustomer(@PathVariable Integer id) { 	
		return customerService.viewCustomer(id);	
	}
	@GetMapping("/getAllCustomers")
	public List<Customer> listCustomers(){
		if(customerService.listCustomers().isEmpty()){
			throw new CustomerNotFoundException("No Entry of Customers in Data Base");
		}
		return customerService.listCustomers();
	}
	@DeleteMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable int id){
		if(customerService.deleteCustomer(id)!=null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "Customer Deleted";
	}
	@GetMapping(path="/listCustomerByBook",consumes ="application/json")
	public List<Customer> listBookByCustomer(@RequestBody Book book){
		if(customerService.listAllCustomersByBook(book).isEmpty()) {
			throw new CustomerNotFoundException("No customer records for this book is available");
		}
		return customerService.listAllCustomersByBook(book);
	}
	
	//3.Category Related Operations--->
	
	@PostMapping(path="/saveCategory", consumes="application/json")
	public String addCategory(@RequestBody Category category){
		if(category.getCategoryId() <= 0) {
			throw new NegativeIdException("Id is invalid (zero/negative/null) , Please enter a valid id");
		}
		categoryService.addCategory(category);
		return "Category Added";
	}
	@GetMapping("/getCategory/{id}")
	public Category viewCategory(@PathVariable int id) {
		/*
		if(categoryService.viewCategory(id) != null) {
			throw new CategoryNotFoundException("No such category exists");
		}
		*/
		return categoryService.viewCategory(id);
	}
	@GetMapping("/getAllCategories")
	public List<Category> viewAllCategory(){
		if(categoryService.viewAllCategories().isEmpty()) {
			throw new CategoryNotFoundException("No such category exists");
		}
		return categoryService.viewAllCategories();
	}
	@PutMapping(path="/editCategory",consumes="application/json")
	public Category editCategory(@RequestBody Category category){
		return categoryService.editCategory(category);
	}
	@DeleteMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable int id){
		if(categoryService.removeCategory(id)!=null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "Category Deleted";
	}
	//4.Order Related Operations--->
	
	@GetMapping("/getAllOrders")//rename it to get
	public List<OrderDetails> getAllOrders(){
		if(orderService.listAllOrders().isEmpty()) {
			throw new OrderNotFoundException("No entry of orders in database");
		}
		return orderService.listAllOrders();
	}
	@GetMapping("/getOrder/{id}")
	public OrderDetails getOrder(@PathVariable int id) {
		return orderService.getOrder(id);
	}
	@DeleteMapping("/deleteOrder/{id}")
	public String deleteOrder(@PathVariable int id){
		if(orderService.cancelOrder(id)!=null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "Order Deleted";
	}
	@GetMapping(path="/listOrdersByCustomer",consumes="application/json")
	public List<OrderDetails> listOrderByCustomer(@RequestBody Customer customer){
		if(orderService.listOrderByCustomer(customer).isEmpty()) {
			throw new OrderNotFoundException("This customer has no order records");
		}
		return orderService.listOrderByCustomer(customer);
	}
	@GetMapping(path="/listOrdersByBook",consumes="application/json")
	public List<OrderDetails> listOrderByBook(@RequestBody Book book){
		if(orderService.listOrderByBook(book).isEmpty()) {
			throw new OrderNotFoundException("This book has no order records");
		}
		return orderService.listOrderByBook(book);
	}
	
	//5.Review Related Operations--->
	
	@GetMapping("/listAllReviews")
	public List<Review> listAllReviews(){
		if(reviewService.listAllReviews().isEmpty()) {
			throw new ReviewNotFoundException("No Reviews exist in the database");
		}
		return reviewService.listAllReviews();
	}
	@GetMapping(path="/getReview/{id}",consumes="application/json")
	public Address viewAddress(@PathVariable int id){
		return addressService.viewAddress(id);
	}
	
	@GetMapping("/listAllReviewsByBook")
	public List<Review> listAllReviewsByBook(@RequestBody Book book){
		if(reviewService.listAllReviewsByBook(book).isEmpty()) {
			throw new ReviewNotFoundException("No reviews for this book is available");
		}
		return reviewService.listAllReviewsByBook(book);
	}
	@GetMapping("/listAllReviewByCustomer")
	public List<Review> listAllReviewBy(@RequestBody Customer customer){
		if(reviewService.listAllReviewByCustomer(customer).isEmpty()) {
			throw new ReviewNotFoundException("No reviews by this customer is available");
		}
		return reviewService.listAllReviewByCustomer(customer);
	}
	
	//6.Address Related Operations --->
	@GetMapping("/getAddress/{id}")
	public Address viewaddress(@PathVariable int id){
		return addressService.viewAddress(id);
	}
	@GetMapping("/getAllAddresses")
	public List<Address> listAllAddresses(){
		if(addressService.viewAllAddresses().isEmpty()) {
			throw new AddressNotFoundException("No such address exists in the database");
		}
		return addressService.viewAllAddresses();
	}
}

