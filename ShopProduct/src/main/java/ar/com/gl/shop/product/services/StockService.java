package ar.com.gl.shop.product.services;

import ar.com.gl.shop.product.model.Stock;

public interface StockService {

	public Stock create(Stock stock);

	public Boolean update(Stock stock);

	public Boolean delete(Long id);

	public Stock findStockById(Long id);

	public Boolean existsStockById(Long id);
}
