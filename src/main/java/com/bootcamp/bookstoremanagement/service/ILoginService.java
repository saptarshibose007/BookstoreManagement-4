package com.bootcamp.bookstoremanagement.service;

import com.bootcamp.bookstoremanagement.entity.User;

public interface ILoginService {
	
	public User addUser(User user);
	public User removeUser(int id);
	public User validateUser(User user);

}
