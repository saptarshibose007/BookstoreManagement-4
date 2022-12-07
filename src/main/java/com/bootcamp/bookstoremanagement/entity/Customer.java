package com.bootcamp.bookstoremanagement.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Customer {
	
	@Id
	private int customerId;	
	private String email; 
	private String mobileNumber;
	private String fullName;
	@OneToOne
	@JoinColumn(name="addressId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Address address;
	private LocalDate registerOn;
	
	public Customer() {
		this.mobileNumber = "Not provided";
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public LocalDate getRegisterOn() {
		return registerOn;
	}
	public void setRegisterOn(LocalDate registerOn) {
		this.registerOn = registerOn;
	}
	
	//user id, and password.  email. fname,lname, location. 

}
