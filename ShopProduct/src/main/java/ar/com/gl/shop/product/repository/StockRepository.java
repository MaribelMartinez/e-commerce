package ar.com.gl.shop.product.repository;

import java.util.List;

import ar.com.gl.shop.product.model.Stock;

public interface StockRepository {
	public Stock save(Stock stock);
	public Stock findById(Long id);
	public Stock delete(Stock stock);
	public List<Stock> getAll();
	public Stock update(Stock stock);
	public Stock softDeleteStock(Stock stock);
	
}
