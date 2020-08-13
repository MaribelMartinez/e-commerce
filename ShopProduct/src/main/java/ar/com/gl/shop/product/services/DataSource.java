package ar.com.gl.shop.product.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.gl.shop.product.model.Resources;
import ar.com.gl.shop.product.utils.DButils;

public abstract class DataSource {
		
	public abstract Resources setAttributesFromQueryResult(ResultSet resultSet) throws SQLException;
	
	public abstract Resources findById(final long id);
	
	public Resources connectionGet(String query) {
		
		Resources resources = null;
		
		try(
			Connection connection = DButils.getDataSource().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
																){			
			if (!resultSet.next()) {
				return null;
			}else {
				resources = setAttributesFromQueryResult(resultSet);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		return resources;
		
	}
	
	public List<Resources> connectionGetAll(String query) {
		
		List<Resources> resourcesList = new ArrayList<>();
		
		try(
			Connection connection = DButils.getDataSource().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
																){			
			if (!resultSet.next()) {
				return null;
			}else {		
				
				do {					
					resourcesList.add(setAttributesFromQueryResult(resultSet));
					
				}				
				while (resultSet.next());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		return resourcesList;
		
	}
	
	public Resources connectionPost(String query, Resources resources) {
		
		int succesfullUpdate;
		
		try(
				Connection connection = DButils.getDataSource().getConnection();
				Statement statement = connection.createStatement();
				
																	){	
			succesfullUpdate = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				
				if (succesfullUpdate == 1) {
					 ResultSet resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						return findById(resultSet.getLong(1));
					}else {
						return findById(resources.getId());
					}
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return null;
	}

}
