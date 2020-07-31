package ar.com.gl.shop.product.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Category {

	private String description;
	private String name;
	private Long id;
	 private static final AtomicLong count = new AtomicLong(0); 
	private Date disabledDate;
	
	public Category() {
		id = count.incrementAndGet();
	}
	
	public Date getDisabledDate() {
		return disabledDate;
	}

	public void setDisabledDate(Date disabledDate) {
		this.disabledDate = disabledDate;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString() {
		return "Name: "+name+"\nDescription: "+description;
	}
}
