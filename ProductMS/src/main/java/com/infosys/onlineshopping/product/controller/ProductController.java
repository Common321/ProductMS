package com.infosys.onlineshopping.product.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.infosys.onlineshopping.product.dto.ProductDTO;
import com.infosys.onlineshopping.product.dto.SubscribedProductDTO;
import com.infosys.onlineshopping.product.entity.Product;
//import com.infosys.onlineshopping.product.repository.ProductRepository;
import com.infosys.onlineshopping.product.service.ProductService;
import com.infosys.onlineshopping.product.service.SubscribedProductService;

@RestController
@CrossOrigin
public class ProductController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productservice;
	
	@Autowired
	SubscribedProductService subproser;
	
	@Autowired
	Environment environment;
	
	// Fetches all product details
	@GetMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProduct() {
		logger.info("Fetching all products");
		return productservice.getAllProduct();
	}
	
	
	// Fetches specific subscription based on buyerId
	@RequestMapping(value = "/api/subscriptions/{buyerid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SubscribedProductDTO> getByBuyerid(@PathVariable String buyerid) {
		logger.info("productname request for product {}", buyerid);
		return subproser.getByBuyerid(buyerid);
	}
	
	
	//Add new subscription
		@PostMapping(value = "/api/subscriptions/add", consumes = MediaType.APPLICATION_JSON_VALUE)
		public void createSubscription(@RequestBody SubscribedProductDTO subscribedproductDTO) {
			logger.info("Creation of request for subscribedproduct{}", subscribedproductDTO);
			subproser.createSubscription(subscribedproductDTO);
		}
		
	
	//Add a new product
	@PostMapping(value = "/api/product", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createProduct(@RequestBody ProductDTO productDTO) {
		logger.info("Creation of request for product{}", productDTO);
		productservice.createProduct(productDTO);
		return "Product added successfully";
	}
	
	/*	
		//@PostMapping(value = "/api/products/add",  consumes = MediaType.APPLICATION_JSON_VALUE)
		@RequestMapping(value = "/api/products/add", method = RequestMethod.POST)
		public void saveproduct(@PathVariable Integer prodid, @RequestBody ProductDTO productDTO) {
			logger.info("Creation request for product {} with data {}", prodid, productDTO);
			productservice.saveproduct(prodid, productDTO);
		}
		
		*/
		
		
	
	// Fetches specific product by product name
	@RequestMapping(value = "/api/products/{productname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductByName(@PathVariable String productname) {
		logger.info("productname request for product {}", productname);
		return productservice.getProductByName(productname);
	}
	
	
	// Fetches specific product by category
	@RequestMapping(value = "/api/products/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductBycategory(@PathVariable String category) {
		logger.info("Category request for product {}",category);
		return productservice.getProductBycategory(category);
	}
	
	
	// Update specific product by product ID
	@RequestMapping(value = "/api/products/{prodid}", method = RequestMethod.PUT)
    public Product updateProductStock(@RequestBody Product product, @PathVariable Integer prodid) {
        return productservice.updateProductStock(product, prodid);
		
    }
	
	
	// Fetches all subscription details
	@GetMapping(value = "/api/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SubscribedProductDTO> getAllSubProduct() {
		logger.info("Fetching all products");
		return subproser.getAllSubProduct();
	}
	
	
	//Delete product
	@DeleteMapping(value = "/api/delete/{prodid}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer prodid) throws Exception{
		productservice.deleteProduct(prodid);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
		
	}
	
}
