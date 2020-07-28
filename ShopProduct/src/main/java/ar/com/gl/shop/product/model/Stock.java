package ar.com.gl.shop.product.model;

public class Stock {

	Long id;
	Integer quantity;
	String locationCode;
	Boolean isActive;
	
	public Stock(Long id, Integer quantity, String locationCode)
	{
		this.id = id;
		this.quantity = quantity;
		this.locationCode = locationCode;
		this.isActive = true;
	}
	
	public Stock() 
	{
		this.isActive = true;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
}
