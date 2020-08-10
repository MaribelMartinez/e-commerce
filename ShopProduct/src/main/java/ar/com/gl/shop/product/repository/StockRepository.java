package ar.com.gl.shop.product.repository;

import java.sql.SQLException;
import java.util.List;

import ar.com.gl.shop.product.model.Stock;

public interface StockRepository {
	public Stock save(Stock stock);
	public Stock getStock(final Long id) throws SQLException;
	public void delete(Stock stock);
	public List<Stock> getAll();

}
