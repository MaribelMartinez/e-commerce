package ar.com.gl.shop.product.repositoryimpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.repository.ProductRepository;
import ar.com.gl.shop.product.services.datasource.ProductDatasource;

public class ProductRepositoryImpl implements Serializable,ProductRepository{


	private static final long serialVersionUID = -2918045200095824049L;

	private static ProductRepositoryImpl instance;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public ProductRepository getInstance()
	{
		if(Objects.isNull(instance)) {
			instance = new ProductRepositoryImpl();
		}
		return instance;
	}
	
	public Product getCategory(final long id) throws SQLException{
		
		final String query = "SELECT * FROM product;";
		Product product = null;
		try {
			con = ProductDatasource.getDataSource().getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);
			if(!rs.next()) {
				return product;
			}else
			{
				product = new Product();
				product.setId(rs.getLong("CA_ID"));
				product.setName(rs.getString("CA_NAME"));
				product.setDescription(rs.getString("CA_DESCRIPTION"));
				product.setEnabled(rs.getBoolean("CA_ESTADO"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
			st.close();
			rs.close();
		}
		return product;
	}
	
	
}
