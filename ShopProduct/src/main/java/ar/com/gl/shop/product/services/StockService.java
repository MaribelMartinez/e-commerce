package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.model.Stock;

public interface StockService {

	public Boolean create(Stock stock);
	public List<Stock> findAllStock();
	public Boolean update(Stock stock);
	public Boolean delete(Stock stock);
	public Stock findStockById(Long id);
	public Boolean existStockById(Long id);
}
