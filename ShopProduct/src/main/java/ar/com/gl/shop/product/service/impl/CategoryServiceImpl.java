package ar.com.gl.shop.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.exceptions.CannotDelete;
import ar.com.gl.shop.product.exceptions.ItemNotFound;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.repository.Repository;
import ar.com.gl.shop.product.repository.impl.RepositoryImpl;
import ar.com.gl.shop.product.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {	
	
	
	private Repository repositoryImpl;
	
	private Category theCategory;
	
	private static CategoryServiceImpl INSTANCE; 
	
	
	private CategoryServiceImpl() {
		
		repositoryImpl = new RepositoryImpl();
		
		theCategory = new Category();
	}
	
	public static CategoryServiceImpl getInstance() {
		
		if (Objects.isNull(INSTANCE)) {
			INSTANCE = new CategoryServiceImpl();
		}
		return INSTANCE;
	}

	@Override
	public Category create(String name, String description) {	
		
		
		theCategory = new Category(name,description);
		
		return repositoryImpl.createCategory(theCategory);
		
		
	}
	
	@Override
	public Category getById(Long id, Boolean searchEnable){	
		Category category = repositoryImpl.findCategoryById(id);	
		try {
			if(Objects.isNull(category)) {
				throw new ItemNotFound("No se encontró categoria con este id");
			}
			if(searchEnable) {
				category = category.getEnabled() ? category : null;
			}			
		}catch (ItemNotFound e) {
			System.out.println(e.getMessage());	
		}
		return category;		
	}
	
	@Override
	public List<Category> findAll() {	
		
		List<Category> theCategoriesEnabled = new ArrayList<>();

		for (Category category : repositoryImpl.findAllCategory()) {
			
			if (category.getEnabled()) {
				theCategoriesEnabled.add(category);
			}
		}	
		
		return theCategoriesEnabled;
	}
	
	@Override
	public List<Category> findAllDisabled(){		
		
		return repositoryImpl.findAllCategory();
		
	}
	

	@Override
	public Category update(Category category){	
		
		return repositoryImpl.updateCategory(category);	
		
	}

	@Override
	public Category softDelete(Category theCategory){
		
		for (Product product : repositoryImpl.findAllProduct()) {
			try {
				if (product.getCategory().getId().equals(theCategory.getId())) {
					
					throw new CannotDelete("No se puede eliminar una Categoria asociada a un producto");
					
				}
			} catch (CannotDelete e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		
		return repositoryImpl.softDeleteCategory(theCategory);
		
	}
	
	@Override
	public Category delete(Category theCategory){
		
		for (Product product : repositoryImpl.findAllProduct()) {
			try {
				if (product.getCategory().getId().equals(theCategory.getId())) {
					
					throw new CannotDelete("No se puede eliminar una Categoria asociada a un producto");
					
				}
			} catch (CannotDelete e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		
		return repositoryImpl.deleteCategory(theCategory);
			
	}
	




}
