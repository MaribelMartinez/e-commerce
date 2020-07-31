package ar.com.gl.shop.product.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Product {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private Stock stock;
	private Category category;
	private Date disabledDate;
	 private static final AtomicLong count = new AtomicLong(0); 
	 
	 public Product() {
		 id = count.incrementAndGet();
	 }
	
	public Date getDisabledDate() {
		return disabledDate;
	}
	public void setDisabledDate(Date disabledDate) {
		this.disabledDate = disabledDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
