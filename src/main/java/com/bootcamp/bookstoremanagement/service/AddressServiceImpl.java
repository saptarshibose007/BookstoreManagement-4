package com.bootcamp.bookstoremanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.bookstoremanagement.entity.Address;
import com.bootcamp.bookstoremanagement.exception.AddressNotFoundException;
import com.bootcamp.bookstoremanagement.exception.DuplicateIdException;
import com.bootcamp.bookstoremanagement.repository.IAddressRepository;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
    private IAddressRepository addressRepository;
    @Override
    public Address addAddress(Address address){
    	List<Address> addressList = addressRepository.findAll();    	
    	for(Address ad : addressList) {
    		if(ad.getAddressId()==address.getAddressId()) {
    			throw new DuplicateIdException("This addressId is already taken, Please change the id");
    		}
    	}
    	return addressRepository.save(address);
    }
    @Override
    public Address viewAddress(int id) {
        return addressRepository.findById(id).orElseThrow(()-> new AddressNotFoundException("This address is not present"));
    }
    @Override
    public Address editAddress(Address address){
        addressRepository.findById(address.getAddressId()).orElseThrow(()-> new AddressNotFoundException("This address is not present"));
        return addressRepository.save(address);
    }
    @Override
    public Address deleteAddress(int id){
        addressRepository.findById(id).orElseThrow(()-> new AddressNotFoundException("This address is not present"));
    	addressRepository.deleteById(id);
        return null;
    }
    @Override
    public List<Address> viewAllAddresses(){
        return addressRepository.findAll();
    }
}
