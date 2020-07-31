package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.model.Category;

public interface CategoryService {

	public Boolean create(Category category);

	public List<String> findAllCategory();
	
	public List<String> findEnabledCategories();

	public Boolean update(Category category);

	public Boolean delete(Long id);

	public Category findCategoryById(Long id);

	public Boolean existCategoryById(Long id);

	public Boolean disable(Long id);
}
