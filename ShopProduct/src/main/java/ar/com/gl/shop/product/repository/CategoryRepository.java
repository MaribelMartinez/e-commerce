package ar.com.gl.shop.product.repository;

import java.sql.SQLException;
import java.util.List;

import ar.com.gl.shop.product.model.Category;

public interface CategoryRepository {

	public Category save(Category category);
	public Category getCategory(final long id);
	public void delete(Category category);
	public List<Category> getAll();
}
