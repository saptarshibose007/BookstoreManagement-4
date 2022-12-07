package com.bootcamp.bookstoremanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.bookstoremanagement.entity.Book;
import com.bootcamp.bookstoremanagement.entity.Category;
import com.bootcamp.bookstoremanagement.entity.Customer;
import com.bootcamp.bookstoremanagement.entity.OrderDetails;
import com.bootcamp.bookstoremanagement.exception.CustomerNotFoundException;
import com.bootcamp.bookstoremanagement.exception.DuplicateIdException;
import com.bootcamp.bookstoremanagement.repository.IBookRepository;
import com.bootcamp.bookstoremanagement.repository.ICustomerRepository;
import com.bootcamp.bookstoremanagement.repository.IOrderRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepository;
	@Autowired
	IBookRepository bookRepository;
	@Autowired
	IOrderRepository orderRepository;
	@Override
	public Customer createCustomer(Customer customer) {
		List<Customer> customerList = customerRepository.findAll();
		for(Customer c : customerList) {
			if(c.getCustomerId()==customer.getCustomerId()) {
				throw new DuplicateIdException("This customerId is already taken, Please change the id");
		}
	}
		return customerRepository.save(customer);		
	}
	@Override
	public List<Customer> listCustomers() {
		return customerRepository.findAll();	
	}
	@Override
	public Customer deleteCustomer(int id) {
		customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("This customer does not exist"));
		customerRepository.deleteById(id);
		return null;
	}
	@Override
	public Customer viewCustomer(int id) {
		return customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("This customer does not exist"));	
	}
	@Override
	public Customer updateCustomer(Customer customer) {
		customerRepository.findById(customer.getCustomerId()).orElseThrow(()-> new CustomerNotFoundException("This customer does not exist"));
		return customerRepository.save(customer);	
	}
	@Override
	public List<Customer> listAllCustomersByBook(Book book) {
		List<OrderDetails> orderList = orderRepository.findAll();
		List<Customer> customerList = new ArrayList<>();
		for(OrderDetails od : orderList) {
			if(od.getBook().getBookId() == book.getBookId()) {
				customerList.add(od.getCustomer());
			}
		}
		return customerList;
	}

}
