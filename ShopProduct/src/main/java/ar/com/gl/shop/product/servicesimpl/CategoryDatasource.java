package ar.com.gl.shop.product.servicesimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Resources;
import ar.com.gl.shop.product.services.DataSource;

public class CategoryDatasource extends DataSource{
	
	private static CategoryDatasource instance;
	
	public static CategoryDatasource getInstance() {
		if(Objects.isNull(instance)) {
			instance = new CategoryDatasource();
		}
		
		return instance;
	}	
	
	
	public Category create(Category category) {
		
		String name = category.getName();
		String description = category.getDescription();
		
		final String query = "INSERT INTO category (name, description, enabled) values ('"+ name +"', '" + description +"', '1');";
		
		
		return (Category)connectionPost(query, category);
	}
	
	public Category findById(final long id){
		
		final String query = "SELECT * FROM category WHERE id=" + id + ";";
		
		
		return (Category)connectionGet(query);
	}	
	
	public List<Category> findAll(){
		
				
		final String query = "SELECT * FROM category";
		
		List<Resources> resourcesList = connectionGetAll(query);
		
		List<Category> categorylist = new ArrayList<>();		
						
		for (Resources category : resourcesList) {
			categorylist.add((Category) category);
		}
		
		return categorylist;
	}
	

	
	public Category update(Category category) {
		
		Long id = category.getId();
		String name = category.getName();
		String description = category.getDescription();
		
		final String query = "UPDATE category set name ='"+name+"', description='"+description+"' where id="+id+";";
		
		return (Category)connectionPost(query, category);
	}
	
	public Category softDelete(Category category) {
		
		Long id = category.getId();
		
		Integer enabled = category.getEnabled() ? 0 : 1;
		
		final String query = "UPDATE category set enabled = "+enabled+" where id="+id+";";		
		
		return (Category)connectionPost(query, category);
	}
	
	public Category delete(Category category) {
		
		Long id = category.getId();
		
		final String query = "DELETE FROM category where id="+id+";";		

		connectionPost(query, category);
		
		return category;
	}
	


	@Override
	public Category setAttributesFromQueryResult(ResultSet resultSet) throws SQLException {		
			
		Category theCategory = new Category();
		
		theCategory.setId(resultSet.getLong("id"));
		theCategory.setName(resultSet.getString("name"));
		theCategory.setDescription(resultSet.getString("description"));

		Boolean enabled = resultSet.getInt("enabled") == 1  ? true : false;
		
		theCategory.setEnabled(enabled);
			
		return theCategory;

	}
}