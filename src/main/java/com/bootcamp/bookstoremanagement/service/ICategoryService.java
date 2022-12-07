package com.bootcamp.bookstoremanagement.service;

import java.util.List;

import com.bootcamp.bookstoremanagement.entity.Category;

public interface ICategoryService {
	
	public Category addCategory(Category category); //done
	public Category editCategory(Category category); //done 
	public List<Category> viewAllCategories(); //done
	public Category removeCategory(int id);
	public Category viewCategory(int id); 

}
