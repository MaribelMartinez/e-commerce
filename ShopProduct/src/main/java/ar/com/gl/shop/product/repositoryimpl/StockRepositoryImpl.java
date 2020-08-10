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
import ar.com.gl.shop.product.services.datasource.StockDatasource;

public class StockRepositoryImpl implements Serializable,StockRepository {

	private static final long serialVersionUID = 3876426318410983253L;
	private static StockRepositoryImpl INSTANCE;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private  List<Stock> list;
	
	private StockRepositoryImpl() {
		list = new ArrayList<Stock>();
	}
	
	public StockRepositoryImpl getInstance() {
		if(Objects.isNull(INSTANCE)) {
			INSTANCE = new StockRepositoryImpl();
		}
		return INSTANCE;
	}
	
	@Override
	public Stock save(Stock stock) {
		list.add(stock);
		return stock;
	}

	@Override
	public List<Stock> getAll() {
		return list;
	}
	
	@Override
	public void delete(Stock stock) {
		list.remove(stock);
	}
	
	@Override
	public Stock getStock(final Long id) throws SQLException {
		final String query = "SELECT * FROM stock;";
		Stock stock= null;
		try {
			con = StockDatasource.getDataSource().getConnection();
			st = con.createStatement();
			rs = st.executeQuery(query);
			if(!rs.next()) {
				return stock;
			}else
			{
				stock = new Stock();
				stock.setId(rs.getLong(""));
				stock.setQuantity(rs.getInt(""));
				stock.setLocationCode(rs.getString(""));
				stock.setEnabled(rs.getBoolean(""));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
			st.close();
			rs.close();
		}
		return stock;
	}
}
