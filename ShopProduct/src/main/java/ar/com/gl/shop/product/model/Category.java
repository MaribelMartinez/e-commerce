package ar.com.gl.shop.product.model;

public class Category {

	String description;
	String name;
	Long id;
	Boolean isActive;
	
	public Category(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.isActive = true;
	}
	
	

	public Category() {
		this.isActive = true;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
