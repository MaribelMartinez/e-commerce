package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.model.Category;

public interface CategoryService {

	public Boolean create(Category category);
	public List<Category> findAllCategory();
	public Boolean update(Category category);
	public Boolean delete(Category category);
	public Category findCategoryById(Long id);
	public Boolean existCategoryById(Long id);
}
