package com.chatbot.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Customer implements Serializable {
	Map<String,String> user;
	List<String> productId;
	Map<String,String> attribute;
	List<String> attributeId;
	String totalamount;
	String quantity;
	
	public Map<String, String> getUser() {
		return user;
	}
	public void setUser(Map<String, String> user) {
		this.user = user;
	}
	public List<String> getProductId() {
		return productId;
	}
	public void setProductId(List<String> productId) {
		this.productId = productId;
	}
	
	public Map<String, String> getAttribute() {
		return attribute;
	}
	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}
	public List<String> getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(List<String> attributeId) {
		this.attributeId = attributeId;
	}

	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	

	
	
}
