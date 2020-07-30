package ar.com.gl.shop.product.services;
import ar.com.gl.shop.product.model.Category;

public interface CategoryService {

	public boolean createCategory(Long id, String name, String description);
	public Category readCategory(Long id);
	public boolean updateCategory(Long id, String name, String description);
	public boolean deleteCategory(Long id);
	public boolean deleteCategoryLogically(Long id);
	public boolean restore(Long id);
}
