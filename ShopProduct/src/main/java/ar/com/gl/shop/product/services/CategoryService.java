package ar.com.gl.shop.product.services;
import ar.com.gl.shop.product.model.Category;

interface CategoryService {

	public void createCategory();
	public Category readCategory(Long id);
	public boolean updateCategory(Long id, String name, String description);
	public boolean deleteCategory(Long id);
}
