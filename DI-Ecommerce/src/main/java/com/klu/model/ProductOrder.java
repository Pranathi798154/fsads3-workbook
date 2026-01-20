package com.klu.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class ProductOrder {
	private int orderid;
	private String customerName;
	private String productName;
	private int quantity;
	
	public ProductOrder(@Value("404")int orderId,@Value("Pranathi")String customerName) {
		this.orderid=orderId;
		this.customerName=customerName;
	}
	@Value("mobile")
	public void setProductName(String productName) {
		this.productName=productName;
	}
	@Value("5")
	public void setQuantity(int quantity ) {
		this.quantity=quantity;
	}
	public void display() {
		System.out.println("Following the order details.....");
		System.out.println("orderId:"+orderid);
		System.out.println("customerName:"+customerName);
		System.out.println("productName:"+productName);
		System.out.println("quantity:"+quantity);
		
	}

}