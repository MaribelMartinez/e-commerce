package com.ar.gl.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
	
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Boolean enabled;
	private Long categoryId;
	private String categoryName;
	private String categoryDescription;
	private Boolean categoryEnabled; 
	private Long stockId;
	private Integer stockQuantity;
	private String stockLocationCode;	

}
