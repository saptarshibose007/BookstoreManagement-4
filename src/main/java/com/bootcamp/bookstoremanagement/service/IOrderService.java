package com.bootcamp.bookstoremanagement.service;

import java.util.List;

import com.bootcamp.bookstoremanagement.entity.Book;
import com.bootcamp.bookstoremanagement.entity.Customer;
import com.bootcamp.bookstoremanagement.entity.OrderDetails;

public interface IOrderService {
	
	public List<OrderDetails> listAllOrders(); //admin
	public OrderDetails getOrder(int id);
	public OrderDetails viewOrderFromCustomer(int id); 
	public OrderDetails viewOrderFromAdmin(int id); 
	public OrderDetails cancelOrder(int id);
	public OrderDetails addOrder(OrderDetails orderDetails);
	public OrderDetails updateOrder(OrderDetails orderDetails);
	public List<OrderDetails> listOrderByCustomer(Customer customer); 
	public List<OrderDetails> listOrderByBook(Book book);

}
