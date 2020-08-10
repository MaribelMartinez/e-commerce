package ar.com.gl.shop.product.repositoryimpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.repository.CategoryRepository;
import ar.com.gl.shop.product.services.datasource.CategoryDatasource;


public class CategoryRepositoryImpl implements Serializable,CategoryRepository{

	private static final long serialVersionUID = 2719297530448134615L;

	private static CategoryRepositoryImpl instance;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public CategoryRepository getInstance()
	{
		if(Objects.isNull(instance)) {
			instance = new CategoryRepositoryImpl();
		}
		return instance;
	}
	
	public Category getCategory(final long id) throws SQLException{
		
		final String query = "SELECT * FROM category;";
		Category category = null;
		try {
			con = CategoryDatasource.getDataSource().getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);
			if(!rs.next()) {
				return category;
			}else
			{
				category = new Category();
				category.setId(rs.getLong("CA_ID"));
				category.setName(rs.getString("CA_NAME"));
				category.setDescription(rs.getString("CA_DESCRIPTION"));
				category.setEnabled(rs.getBoolean("CA_ESTADO"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
			st.close();
			rs.close();
		}
		return category;
	}
	
		
}

