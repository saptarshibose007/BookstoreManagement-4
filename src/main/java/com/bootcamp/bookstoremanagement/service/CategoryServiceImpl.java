package com.bootcamp.bookstoremanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.bookstoremanagement.entity.Category;
import com.bootcamp.bookstoremanagement.exception.CategoryNotFoundException;
import com.bootcamp.bookstoremanagement.exception.DuplicateIdException;
import com.bootcamp.bookstoremanagement.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category){
    	List<Category> categoryList = categoryRepository.findAll();
    	for(Category c : categoryList) {
    		if(c.getCategoryId()==category.getCategoryId()) {
    			throw new DuplicateIdException("This categoryId is already taken, Please change the id");
    		}
    	}
        return categoryRepository.save(category);
    }
    @Override
    public Category editCategory(Category category){
    	categoryRepository.findById(category.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("This category does not exist"));
        return categoryRepository.save(category);
    }
    @Override
    public List<Category> viewAllCategories(){
        return categoryRepository.findAll();
    }
    @Override
    public Category removeCategory(int id){      
    	categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("This category does not exist"));
        categoryRepository.deleteById(id);;
        return null;
    }
	@Override
	public Category viewCategory(int id) {
		return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("This category does not exist"));
	}

}
