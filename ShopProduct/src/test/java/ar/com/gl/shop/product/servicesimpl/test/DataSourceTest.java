package ar.com.gl.shop.product.servicesimpl.test;

import org.junit.jupiter.api.Test;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.servicesimpl.CategoryDatasource;

public class DataSourceTest {
	
	CategoryDatasource categoryDatasource;
	
	@Test
	public void createCategory() {
		Category category = new Category("Nueva categoria", "esto es una descripcion nueva");
		categoryDatasource.create(category);
	}

}
