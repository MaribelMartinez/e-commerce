package ar.com.gl.shop.product.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.service.impl.CategoryServiceImpl;

@RestController
public class CatalogController {
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping(value="/categories")
	public ResponseEntity<Collection<Category>> findAll(){
		
		return new ResponseEntity<>(categoryServiceImpl.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(value="/category/{id}")
	public ResponseEntity<Category> getById(@PathVariable(name = "id") Long id){
		
		return new ResponseEntity<>(categoryServiceImpl.getById(id, true),HttpStatus.OK);
	}
	
	@GetMapping(value="/category/{name}")
	public ResponseEntity<Category> getByName(@PathVariable(name = "name") String name){
		
		return new ResponseEntity<>(categoryServiceImpl.getByName(name),HttpStatus.OK);
	}
	
	@PostMapping(value="/create/category")
	public ResponseEntity<Category> create(@Valid @RequestBody Category category){
		
		return new ResponseEntity<>(categoryServiceImpl.create(category),HttpStatus.CREATED);
	}
	
	@PatchMapping(value="/patch/category/{id}/name")
	public ResponseEntity<Category> patchName(@PathVariable(name="id")Long id,
											  @RequestParam(name="description") String name){
		
		Category category = categoryServiceImpl.getById(id, true);
		
		category.setName(name);
		
		return new ResponseEntity<>(categoryServiceImpl.update(category),HttpStatus.OK);
	}

	
	@PatchMapping(value="/patch/category/{id}/description")
	public ResponseEntity<Category> patchDescription(@PathVariable(name="id")Long id,
												     @RequestParam(name="description") String description){
		
		Category category = categoryServiceImpl.getById(id, true);
		
		category.setDescription(description);
		
		return new ResponseEntity<>(categoryServiceImpl.update(category),HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/category/{id}")
	public void delete(@PathVariable(name="id") Long id) {
		categoryServiceImpl.delete(id);
	}

}
