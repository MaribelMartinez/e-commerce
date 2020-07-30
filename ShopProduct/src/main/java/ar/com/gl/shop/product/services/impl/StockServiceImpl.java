package ar.com.gl.shop.product.services.impl;

import ar.com.gl.shop.product.exceptions.IdAlreadyExistException;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.RepositoryImpl;
import ar.com.gl.shop.product.services.StockService;

import java.util.ArrayList;

public class StockServiceImpl implements StockService{
	
	private ArrayList<Stock> stocks = RepositoryImpl.getInstance().getListaStock();

	public boolean createStock(Long id, Integer quantity, String locationCode) 
	{
		
		try {
			for(Stock stock : stocks)
			{
				if(stock.getId() == id)
					throw new IdAlreadyExistException();
			}
		return stocks.add(new Stock(id,quantity,locationCode));
		}catch(IdAlreadyExistException exception)
		{
			System.err.println(exception.getMessage());
			return false;
		}
	}
	
	public Stock readStock(Long id) 
	{
		for(int i=0; i < stocks.size(); i++)
		{
			if(stocks.get(i).getId() == id && stocks.get(i).getIsActive())
				return stocks.get(i);
		}
		return null;
	}
	
	public boolean updateStock(Long id, Integer quantity, String locationCode)
	{
		for(int i=0; i < stocks.size(); i++)
		{
			if(stocks.get(i).getId() == id && stocks.get(i).getIsActive())
			{	
				stocks.get(i).setId(id);
				stocks.get(i).setQuantity(quantity);
				stocks.get(i).setLocationCode(locationCode);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteStock(Long id) 
	{
		for(int i=0; i < stocks.size(); i++)
		{
			if(stocks.get(i).getId() == id)
			{	
				stocks.remove(stocks.get(i));
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteStockLogically(Long id) 
	{
		for(int i=0; i < stocks.size(); i++)
		{
			if(stocks.get(i).getId() == id && stocks.get(i).getIsActive())
			{	
				stocks.get(i).setIsActive(false);
				return true;
			}
		}
		return false;
	}
	
	public boolean restore(Long id) 
	{
		for(int i=0; i < stocks.size(); i++)
		{
			if(stocks.get(i).getId() == id && !stocks.get(i).getIsActive())
			{	
				stocks.get(i).setIsActive(true);
				return true;
			}
		}
		return false;
	}
}
