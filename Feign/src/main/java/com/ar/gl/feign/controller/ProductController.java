package com.ar.gl.feign.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ar.gl.feign.dto.ProductDTO;
import com.ar.gl.feign.model.Product;
import com.ar.gl.feign.shop.product.FeignProduct;

@RestController
public class ProductController {
	
	private FeignProduct product;
	
	public ProductController(FeignProduct product) {
		this.product = product;
	}
	
	@GetMapping(value = "/products")
	public ResponseEntity<Product> findAll(){
		return product.findAll();
	}
	
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product> getById(@PathVariable(name = "id") Long id){
		return product.getById(id);
	}
	
	@GetMapping(value = "/products/name/{name}")
	public ResponseEntity<Product> getByName(@PathVariable(name = "name") String name){
		return product.getByName(name);
	}
	
	@GetMapping(value = "/products/category/{id}")
	public ResponseEntity<Product> getByCategoryId(@PathVariable(name = "id") Long id){
		return product.getByCategoryId(id);
	}
	
	@PostMapping(value = "/products")
	public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO){
		return product.create(productDTO);
	}
	
	@PutMapping(value = "/products/{id}")
	public ResponseEntity<Product> update(@PathVariable(name = "id") Long id,
			@RequestBody Object object){
		return product.update(id, object);
	}
	
	@PatchMapping(value = "/products/{id}")
	public ResponseEntity<Product> patchDescription(@PathVariable(name = "id") Long id){
		return product.patchDescription(id);
	}
	
	@DeleteMapping(value = "/products/{id}")
	public ResponseEntity<String> delete(@PathVariable(name = "id") Long id){
		return product.delete(id);
	}
}
