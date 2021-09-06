package com.infosys.onlineshopping.product.dto;


import com.infosys.onlineshopping.product.entity.Product;
import com.infosys.onlineshopping.product.entity.SubscribedProduct;

public class SubscribedProductDTO {

	
	String buyerid;
	Integer prodid;
	Integer quantity;
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public Integer getProdid() {
		return prodid;
	}
	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public SubscribedProductDTO() {
		super();

	}
	public SubscribedProductDTO(String buyerid, Integer prodid, Integer quantity) {
		this();
		this.buyerid = buyerid;
		this.prodid = prodid;
		this.quantity = quantity;
	}
	// Converts Entity into DTO
		public static SubscribedProductDTO valueOf(SubscribedProduct subscribedprod) {
			SubscribedProductDTO subscribedprodDTO = new SubscribedProductDTO();
			subscribedprodDTO.setBuyerid(subscribedprod.getBuyerid());
			subscribedprodDTO.setProdid(subscribedprod.getProdid());
			subscribedprodDTO.setQuantity(subscribedprod.getQuantity());
			return subscribedprodDTO;
		}
		
		// Converts DTO to Entity
		
		public SubscribedProduct createEntity() {
			SubscribedProduct subscribedprod = new SubscribedProduct();
			subscribedprod.setBuyerid(this.getBuyerid());
			subscribedprod.setProdid(this.getProdid());
			subscribedprod.setQuantity(this.getQuantity());
			return subscribedprod;
			
			
		}
	@Override
	public String toString() {
		return "SubscribedProdDTO [buyerid=" + buyerid + ", prodid=" + prodid + ", quantity=" + quantity + "]";
	}
	
}
