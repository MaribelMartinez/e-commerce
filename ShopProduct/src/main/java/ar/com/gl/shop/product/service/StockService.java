package ar.com.gl.shop.product.service;

import ar.com.gl.shop.product.model.Stock;

public interface StockService {

    public Stock create(Stock stock);

    public Stock findById(Long id, Boolean searchEnable);

    public void delete(Stock stock);

    public Stock softDelete(Stock stock);

    public Stock update(Stock stock);
}
