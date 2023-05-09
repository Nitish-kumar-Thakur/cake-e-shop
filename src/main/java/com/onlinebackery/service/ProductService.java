package com.onlinebackery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlinebackery.entity.Product;
import com.onlinebackery.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository prepo;
	
	public void insert(Product prod) {
		prepo.save(prod);
	}
	
	public List<Product> getAll(){
		return prepo.findAll();
	}
	
	public Product getProductById(Long id) {
		return prepo.findById(id).get();
	}
	
	public void delete(Long id) {
		prepo.deleteById(id);
	}
	
	public List<Product> getAllByCategorisId(int id) {
		
		return prepo.findAllByCategory_Id(id);
	}
	

}
