package com.ar.gl.feign.shop.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ar.gl.feign.dto.OrderDTO;
import com.ar.gl.feign.shop.product.fallback.HystrixOrderFallbackFactory;

@FeignClient(name="Order-Shop", fallback = HystrixOrderFallbackFactory.class)
public interface FeignOrder {
	
	@PostMapping(value = "/orders/v1/orders")
	public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO);
	
	@GetMapping(value = "/orders/v1/orders")
	public ResponseEntity<List<OrderDTO>> getAllOrders();
	
	@GetMapping(value = "/orders/v1/orders/{id}")
	public ResponseEntity<OrderDTO> get(@PathVariable("id") Long id);
	
	@GetMapping(value = "/orders/v1/orders/customer/{id}")
	public ResponseEntity<List<OrderDTO>> getOrdersByCustomer(@PathVariable("id") Long id);
	
	@GetMapping(value = "/orders/v1/orders/product/{id}")
	public ResponseEntity<List<OrderDTO>> getOrdersByProduct(@PathVariable("id") Long id);
	
	@PutMapping(value = "/orders/v1/orders/{id}")
	public ResponseEntity<OrderDTO> update(@PathVariable("id") Long id, @RequestBody OrderDTO orderDTO);
	
	@DeleteMapping(value = "/orders/v1/orders/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id);
	
	@PatchMapping(value = "/orders/v1/orders/{id}")
	public ResponseEntity<OrderDTO> softDelete(@PathVariable("id") Long id);

}
