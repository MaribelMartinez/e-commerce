package ar.com.gl.shop.product.services;

import ar.com.gl.shop.product.model.Stock;

public interface StockService {

	public boolean createStock(Long id, Integer quantity, String locationCode);
	public Stock readStock(Long id);
	public boolean updateStock(Long id, Integer quantity, String locationCode);
	public boolean deleteStock(Long id);
	public boolean deleteStockLogically(Long id);
	public boolean restore(Long id);
	
}
