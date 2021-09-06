package com.infosys.onlineshopping.product.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import com.infosys.onlineshopping.product.dto.SubscribedProductDTO;

import com.infosys.onlineshopping.product.entity.SubscribedProduct;
import com.infosys.onlineshopping.product.repository.SubscribedProductRepository;

@Service
public class SubscribedProductService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SubscribedProductRepository subscribedprodrepo;
	
	
	// Fetches all subscribed products by buyer id
	public List<SubscribedProductDTO> getByBuyerid(@PathVariable String buyerid) {

		logger.info("Productname request for product {}", buyerid);

		List<SubscribedProduct> sub = subscribedprodrepo.findByBuyerid(buyerid);
		List<SubscribedProductDTO> subscribedprodDTO = new ArrayList<SubscribedProductDTO>();

		for (SubscribedProduct subs : sub) {
			subscribedprodDTO.add(SubscribedProductDTO.valueOf(subs));
		}
		logger.info("Productname for product : {}", sub);

		return subscribedprodDTO;
	}
	
	// Fetches all subscribed products
	public List<SubscribedProductDTO> getAllSubProduct() {

		List<SubscribedProduct> subs = subscribedprodrepo.findAll();
		List<SubscribedProductDTO> subscribedprodDTOs = new ArrayList<>();

		for (SubscribedProduct subpro : subs) {
			SubscribedProductDTO subscribedprodDTO = SubscribedProductDTO.valueOf(subpro);
			subscribedprodDTOs.add(subscribedprodDTO);
		}

		logger.info("Product Details : {}", subscribedprodDTOs);
		return subscribedprodDTOs;
	}
	
	//Add new  subscription
	public void createSubscription(SubscribedProductDTO subscribedproductDTO) {
		logger.info("Creation of request for product {},", subscribedproductDTO);
		SubscribedProduct subscribedproduct = subscribedproductDTO.createEntity();
		subscribedprodrepo.save(subscribedproduct);
		
	}
		
}
