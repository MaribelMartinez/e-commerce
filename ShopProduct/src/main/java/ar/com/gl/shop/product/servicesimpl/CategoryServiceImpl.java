package ar.com.gl.shop.product.servicesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.exceptions.ItemNotFound;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Resources;
import ar.com.gl.shop.product.repository.Repository;
import ar.com.gl.shop.product.repositoryimpl.RepositoryImpl;
import ar.com.gl.shop.product.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {	
	
	
	private Repository repositoryImpl;
	
	private Category theCategory;
	
	private static CategoryServiceImpl INSTANCE; 
	
	
	private CategoryServiceImpl() {
		
		repositoryImpl = new RepositoryImpl();
		
		theCategory = new Category();
	}
	
	public static CategoryServiceImpl getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new CategoryServiceImpl();
		}
		return INSTANCE;
	}

	@Override
	public Category create(String name, String description) {	
		
		
		theCategory = new Category(name,description);
		
		return repositoryImpl.saveCategory(theCategory);
		
		
	}
	
	@Override
	public List<Category> findAll() {	
		
		List<Category> theCategoriesEnabled = new ArrayList<>();

		for (Resources category : repositoryImpl.findAllCategory()) {
			
			if (((Category) category).getEnabled()) {
				theCategoriesEnabled.add((Category)category);
			}
		}	
		
		return theCategoriesEnabled;
	}
	
	@Override
	public List<Category> findAllDisabled(){		
		
		return repositoryImpl.findAllCategory();
		
	}

	
	@Override
	public Category findById(Long id, Boolean searchEnable){	
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
	public Category update(Category category){	
		
		return repositoryImpl.updateCategory(category);	
		
	}

	@Override
	public Category deleteById(Category theCategory){
		
		return repositoryImpl.deleteCategory(theCategory);
		
	}
	
	@Override
	public Category  forceDeleteById(Category theCategory){
		
		return repositoryImpl.forceDeleteCategory(theCategory);
			
	}
	




}
