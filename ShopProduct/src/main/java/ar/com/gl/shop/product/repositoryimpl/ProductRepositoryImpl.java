package ar.com.gl.shop.product.repositoryimpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.repository.ProductRepository;
import ar.com.gl.shop.product.services.datasource.ProductDatasource;
import ar.com.gl.shop.product.services.datasource.StockDatasource;

public class ProductRepositoryImpl implements Serializable,ProductRepository{


	private static final long serialVersionUID = -2918045200095824049L;

	private static ProductRepositoryImpl instance;
	private Statement st;
	private ResultSet rs;
	
	public static ProductRepository getInstance()
	{
		if(Objects.isNull(instance)) {
			instance = new ProductRepositoryImpl();
		}
		return instance;
	}
	@Override
	public Product getProduct(final Long id)
	{
		final String query = "SELECT * FROM product WHERE id = "+ id +";";
		Product product = null;
		try(Connection con = ProductDatasource.getDataSource().getConnection()) 
		{
		
			st = con.createStatement();
			rs = st.executeQuery(query);
			if(!rs.next()) {
				return product;
			}else
			{
				product = new Product();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(StockRepositoryImpl.getInstance().getStock((long)rs.getInt("stock")));
				product.setCategory(CategoryRepositoryImpl.getInstance().getCategory(rs.getInt("category")));
				product.setEnabled(rs.getBoolean("enabled"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Product save(Product product)
	{
		final String query = "INSERT INTO product (id, name, description, price, stock, category, enabled) \n"
				+ "VALUES (" + product.getId() + ",\"" + product.getName() + "\",\"" + product.getDescription()+ "\"," + product.getPrice() + "," + product.getStock().getId()+ "," + product.getCategory().getId() + "," + product.getEnabled() + ");";
		try(Connection con = ProductDatasource.getDataSource().getConnection())
		{
		st = con.createStatement();
		st.executeUpdate(query);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void delete(Product product) 
	{
		final String query = "DELETE FROM product WHERE id = " + product.getId() + ";";
		try(Connection con = ProductDatasource.getDataSource().getConnection()) 
		{	
		st = con.createStatement();
		st.executeUpdate(query);
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Product> getAll() {
		final String query = "SELECT * FROM product;";
		Product product = null;
		ArrayList<Product> productList = new ArrayList<Product>();
		try(Connection con = StockDatasource.getDataSource().getConnection())
		{
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				product = new Product();
				product.setId(rs.getLong("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(StockRepositoryImpl.getInstance().getStock((long)rs.getInt("stock")));
				product.setCategory(CategoryRepositoryImpl.getInstance().getCategory(rs.getInt("category")));
				product.setEnabled(rs.getBoolean("enabled"));
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	
}
