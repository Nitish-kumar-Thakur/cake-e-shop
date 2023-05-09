package com.onlinebackery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebackery.entity.Category;
import com.onlinebackery.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository crepo;
	
	public void insert(Category cat) {
		crepo.save(cat);
	}
	
	public List<Category> getAll(){
		return crepo.findAll();
	}
	
	public Category getCategoryById(Integer id) {
		return crepo.findById(id).get();
	}
	
	public void delete(Integer id) {
		crepo.deleteById(id);
	}

}
