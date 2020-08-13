package ar.com.gl.shop.product.repositoryimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ar.com.gl.shop.product.model.Resources;
import ar.com.gl.shop.product.utils.DButils;

public abstract class DataSource {
		
	public abstract Resources setAttributesForSqlReturnedObject(ResultSet resultSet) throws SQLException;
	
	public abstract Resources getById(final long id);
	
	public Resources connection(String query, Resources resources) {
		
		try(
			Connection connection = DButils.getDataSource().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
																){			
			if (!resultSet.next()) {
				return null;
			}else {
				resources = setAttributesForSqlReturnedObject(resultSet);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		return resources;
		
	}
	
	public List<Resources> connection(String query, Resources resources, List<Resources> resourcesList) {
		
		try(
			Connection connection = DButils.getDataSource().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
																){			
			if (!resultSet.next()) {
				return null;
			}else {		
				
				do {					
					resourcesList.add(setAttributesForSqlReturnedObject(resultSet));
					
				}				
				while (resultSet.next());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		return resourcesList;
		
	}
	
	public Resources connectionCreate(String query, Resources resources) {
		
		int succesfullUpdate;
		
		try(
				Connection connection = DButils.getDataSource().getConnection();
				Statement statement = connection.createStatement();
				
																	){	
			succesfullUpdate = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				
				if (succesfullUpdate == 1) {
					 ResultSet resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						return getById(resultSet.getLong(1));
					}else {
						return getById(resources.getId());
					}
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return null;
	}

}
