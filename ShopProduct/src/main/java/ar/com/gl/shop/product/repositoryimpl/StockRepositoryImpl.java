package ar.com.gl.shop.product.repositoryimpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.StockRepository;
import ar.com.gl.shop.product.repository.datasource.CategoryDatasource;
import ar.com.gl.shop.product.repository.datasource.StockDatasource;

public class StockRepositoryImpl implements Serializable,StockRepository {

	private static final long serialVersionUID = 3876426318410983253L;
	private static StockRepositoryImpl INSTANCE;
	private Statement st;
	private ResultSet rs;
	
	
	public static StockRepositoryImpl getInstance() {
		if(Objects.isNull(INSTANCE)) {
			INSTANCE = new StockRepositoryImpl();
		}
		return INSTANCE;
	}
	
	@Override
	public Stock save(Stock stock){
		final String query = "INSERT INTO stock (id, quantity, locationCode, enabled) \n"
				+ "VALUES (" + stock.getId() + "," + stock.getQuantity() + ",\"" + stock.getLocationCode() + "\"," + stock.getEnabled() + ");";
		try(Connection con = CategoryDatasource.getDataSource().getConnection())
		{
		
		st = con.createStatement();
		st.executeUpdate(query);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return stock;
	}

	@Override
	public List<Stock> getAll() {
		final String query = "SELECT * FROM stock;";
		Stock stock = null;
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		try(Connection con = StockDatasource.getDataSource().getConnection()) 
		{
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) 
			{
				stock = new Stock();
				stock.setId(rs.getLong("id"));
				stock.setQuantity(rs.getInt("quantity"));
				stock.setLocationCode(rs.getString("locationCode"));
				stock.setEnabled(rs.getBoolean("enabled"));
				stockList.add(stock);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return stockList;
	}
	
	@Override
	public void delete(Stock stock){
		final String query = "DELETE FROM stock WHERE id = " + stock.getId() + ";";
		try(Connection con = StockDatasource.getDataSource().getConnection()) 
		{
		st = con.createStatement();
		st.executeUpdate(query);
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public Stock getStock(final Long id){
		final String query = "SELECT * FROM stock;";
		Stock stock= null;
		try(Connection con = StockDatasource.getDataSource().getConnection()) 
		{
			st = con.createStatement();
			rs = st.executeQuery(query);
			if(!rs.next()) {
				return stock;
			}else
			{
				stock = new Stock();
				stock.setId(rs.getLong("id"));
				stock.setQuantity(rs.getInt("quantity"));
				stock.setLocationCode(rs.getString("locationCode"));
				stock.setEnabled(rs.getBoolean("enabled"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}
}
