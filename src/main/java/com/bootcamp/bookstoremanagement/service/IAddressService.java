package com.bootcamp.bookstoremanagement.service;

import java.util.List;

import com.bootcamp.bookstoremanagement.entity.Address;

public interface IAddressService {
	
	public Address addAddress(Address address);
    public Address editAddress(Address address);
    public Address deleteAddress(int id);
    public List<Address> viewAllAddresses();
	public Address viewAddress(int id);

}
