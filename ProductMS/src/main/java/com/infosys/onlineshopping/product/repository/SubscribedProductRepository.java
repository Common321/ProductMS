package com.infosys.onlineshopping.product.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.onlineshopping.product.entity.SubscribedProduct;

	public interface SubscribedProductRepository extends JpaRepository<SubscribedProduct, String>{
		List<SubscribedProduct> findByBuyerid(String buyerid);
	}

