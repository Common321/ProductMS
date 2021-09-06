package com.infosys.onlineshopping.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import com.infosys.onlineshopping.product.dto.ProductDTO;
import com.infosys.onlineshopping.product.entity.Product;
import com.infosys.onlineshopping.product.repository.ProductRepository;
import com.infosys.onlineshopping.product.repository.SubscribedProductRepository;



@Service
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductRepository productrepo;
	
	@Autowired
	SubscribedProductRepository subscribedprodrepo;


//Add new products
	public void createProduct(ProductDTO productDTO) {
		logger.info("Creation of request for product {},", productDTO);
		Product product = productDTO.createEntity();
		productrepo.save(product);
		
	}

	/*
	//Add new products
	public void saveproduct(Integer prodid, ProductDTO productDTO) {
		logger.info("Creation request for product {} with data {}", prodid, productDTO);
		productDTO.setProdid(prodid);
		Product product = productDTO.createEntity();
		productrepo.save(product);
	}
	*/
	
	
	// List product by name 
	public List<ProductDTO> getProductByName(@PathVariable String productname) {

		logger.info("Productname request for product {}", productname);

		List<Product> product = productrepo.findByproductname(productname);
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();

		for (Product Pro : product) {
			productDTO.add(ProductDTO.valueOf(Pro));
		}
		logger.info("Productname for product : {}", product);

		return productDTO;
	}
	//List product by category
	public List<ProductDTO> getProductBycategory(@PathVariable String category) {

		logger.info("Category request for product {}", category);

		List<Product> product = productrepo.findBycategory(category);
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();

		for (Product Prod : product) {
			productDTO.add(ProductDTO.valueOf(Prod));
		}
		logger.info("Category for product : {}", product);

		return productDTO;
	}
	//List all products
	public List<ProductDTO> getAllProduct() {

		List<Product> products = productrepo.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Product product : products) {
			ProductDTO productDTO = ProductDTO.valueOf(product);
			productDTOs.add(productDTO);
		}

		logger.info("Product Details : {}", productDTOs);
		return productDTOs;
	}
	
	
	//Update stock
	 public Product updateProductStock(Product product, Integer prodid) {
	        Product existingProduct = productrepo.findById(prodid).orElse(null);
	        if(existingProduct != null) {
	            existingProduct.setStock(product.getStock());
	            return productrepo.save(existingProduct); 
	        }
	        return existingProduct;
	    }
	 
	 // Delete product
	 public void deleteProduct(Integer prodid) throws Exception {
		Optional<Product> product = productrepo.findById(prodid);
		product.orElseThrow(() -> new Exception("Service.Seller_NOT_FOUND"));
		productrepo.deleteById(prodid);
	 }
	
}
