package com.bootcamp.bookstoremanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.bookstoremanagement.entity.Book;
import com.bootcamp.bookstoremanagement.entity.Customer;
import com.bootcamp.bookstoremanagement.entity.OrderDetails;
import com.bootcamp.bookstoremanagement.exception.DuplicateIdException;
import com.bootcamp.bookstoremanagement.exception.OrderNotFoundException;
import com.bootcamp.bookstoremanagement.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	IOrderRepository orderRepository;
	@Override
	public OrderDetails addOrder(OrderDetails orderDetails){
		List<OrderDetails> orderList = orderRepository.findAll();
		for(OrderDetails od : orderList) {
			if(od.getOrderId()==orderDetails.getOrderId()) {
				throw new DuplicateIdException("This orderId is taken , Please change it");
			}
		}
		return orderRepository.save(orderDetails);
	}
	@Override
	public List<OrderDetails> listAllOrders(){
		return orderRepository.findAll();
	}
	@Override
	public OrderDetails cancelOrder(int id){
		orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("this order does not exists"));
		orderRepository.deleteById(id);
		return null;
	}
	@Override
	public OrderDetails updateOrder(OrderDetails orderDetails){
		orderRepository.findById(orderDetails.getOrderId()).orElseThrow(()-> new OrderNotFoundException("this order does not exists"));
		return orderRepository.save(orderDetails);
	}
	@Override
	public OrderDetails viewOrderFromCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OrderDetails viewOrderFromAdmin(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<OrderDetails> listOrderByCustomer(Customer customer){
		List<OrderDetails> orderList = orderRepository.findAll();
		List<OrderDetails> listOrderByCustomer = new ArrayList<>();
		for(OrderDetails od : orderList) {
			if(customer.getCustomerId()==(od.getCustomer().getCustomerId())) {
				listOrderByCustomer.add(od);
			}
		}
		return listOrderByCustomer;
	}
	@Override
	public List<OrderDetails> listOrderByBook(Book book) {
		List<OrderDetails> orderList = orderRepository.findAll();
		List<OrderDetails> listOrderByBook = new ArrayList<>();
		for(OrderDetails od : orderList) {
			if(book.getBookId()==od.getBook().getBookId() && book.getCategory().getCategoryId()==od.getBook().getCategory().getCategoryId()) {
				listOrderByBook.add(od);
			}
		}
		return listOrderByBook;
	}
	@Override
	public OrderDetails getOrder(int id) {
		return orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("this order does not exists"));
	}
}