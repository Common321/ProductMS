package com.infosys.onlineshopping.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.onlineshopping.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByproductname(String productname);
	List<Product> findBycategory(String category);

}
