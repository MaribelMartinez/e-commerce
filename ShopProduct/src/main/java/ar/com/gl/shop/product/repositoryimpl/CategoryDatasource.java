package ar.com.gl.shop.product.repositoryimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Resources;

public class CategoryDatasource extends DataSource{
	
	private static CategoryDatasource instance;
	
	public static CategoryDatasource getInstance() {
		if(Objects.isNull(instance)) {
			instance = new CategoryDatasource();
		}
		
		return instance;
	}	
	
	public Category getById(final long id){
		
		Resources theCategory = new Category();
		
		final String query = "SELECT * FROM category WHERE id=" + id + ";";
		
		
		return (Category)connection(query, theCategory);
	}	
	
	public List<Category> getAll(){
		
		List<Resources> resourcesList = new ArrayList<>();
		
		Resources theCategory = new Category();
		
		final String query = "SELECT * FROM category";
		
		List<Category> categorylist = new ArrayList<>();		
						
		for (Resources category : connection(query, theCategory, resourcesList)) {
			categorylist.add((Category) category);
		}
		
		return categorylist;
	}
	
	public Category createCategory(Category category) {
		
		String name = category.getName();
		String description = category.getDescription();
		
		final String query = "INSERT INTO category (name, description, enabled) values ('"+ name +"', '" + description +"', '1');";
		
		
		return (Category)connectionCreate(query, category);
	}
	
	public Category updateCategory(Category category) {
		
		Long id = category.getId();
		String name = category.getName();
		String description = category.getDescription();
		
		final String query = "UPDATE category set name ='"+name+"', description='"+description+"' where id="+id+";";
		
		return (Category)connectionCreate(query, category);
	}
	
	public Category deleteCategory(Category category) {
		
		Long id = category.getId();
		
		Integer enabled = category.getEnabled() ? 0 : 1;
		
		final String query = "UPDATE category set enabled = "+enabled+" where id="+id+";";		
		
		return (Category)connectionCreate(query, category);
	}
	
	public Category forceDeleteCategory(Category category) {
		
		Long id = category.getId();
		
		final String query = "DELETE FROM category where id="+id+";";
		
		connectionCreate(query, category);
		
		return category;
	}
	


	@Override
	public Category setAttributesForSqlReturnedObject(ResultSet resultSet) throws SQLException {		
			
		Category theCategory = new Category();
		
		theCategory.setId(resultSet.getLong("id"));
		theCategory.setName(resultSet.getString("name"));
		theCategory.setDescription(resultSet.getString("description"));

		Boolean enabled = resultSet.getInt("enabled") == 1  ? true : false;
		
		theCategory.setEnabled(enabled);
			
		return theCategory;

	}
}