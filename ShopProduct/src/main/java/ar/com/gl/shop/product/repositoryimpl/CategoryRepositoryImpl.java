package ar.com.gl.shop.product.repositoryimpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.repository.CategoryRepository;
import ar.com.gl.shop.product.services.datasource.CategoryDatasource;


public class CategoryRepositoryImpl implements Serializable,CategoryRepository{

	private static final long serialVersionUID = 2719297530448134615L;

	private static CategoryRepositoryImpl instance;
	private Statement st;
	private ResultSet rs;
	
	public static CategoryRepository getInstance()
	{
		if(Objects.isNull(instance)) {
			instance = new CategoryRepositoryImpl();
		}
		return instance;
	}
	@Override
	public Category getCategory(final long id){
		
		final String query = "SELECT * FROM category WHERE id = " + id + ";";
		Category category = null;
		try(Connection	con = CategoryDatasource.getDataSource().getConnection())
		{
			st = con.createStatement();
			rs = st.executeQuery(query);
			if(!rs.next()) {
				return category;
			}else
			{
				category = new Category();
				category.setId(rs.getLong("id"));
				category.setName(rs.getString("name"));
				category.setDescription(rs.getString("description"));
				category.setEnabled(rs.getBoolean("enabled"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public Category save(Category category){
		final String query = "INSERT INTO category(category.id, category.name, category.description, category.enabled)"
							+ "VALUES (" + category.getId() + ",\"" + category.getName() + "\",\"" + category.getDescription() + "\"," + category.getEnabled() + ");";
		try(Connection con = CategoryDatasource.getDataSource().getConnection())
		{
		st = con.createStatement();
		st.executeUpdate(query);
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void delete(Category category){
		
		final String query = "DELETE FROM category WHERE id = " + category.getId() + ";";
		try(Connection con = CategoryDatasource.getDataSource().getConnection()) 
		{
		st = con.createStatement();
		st.executeUpdate(query);
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<Category> getAll(){
		
		final String query = "SELECT * FROM category;";
		Category category = null;
		ArrayList<Category> categoryList = new ArrayList<Category>();
		try(Connection con = CategoryDatasource.getDataSource().getConnection())
		{
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				category = new Category();
				category.setId(rs.getLong("id"));
				category.setName(rs.getString("name"));
				category.setDescription(rs.getString("description"));
				category.setEnabled(rs.getBoolean("enabled"));
				categoryList.add(category);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
}

