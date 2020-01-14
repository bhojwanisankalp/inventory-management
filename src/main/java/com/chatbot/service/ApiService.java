package com.chatbot.service;

import java.util.List;

import com.chatbot.model.Orders;
import com.chatbot.model.Product;
import com.chatbot.model.ProductAttribute;
import com.chatbot.model.ProductCategory;
import com.chatbot.model.ProductImages;
import com.chatbot.model.User;

public interface ApiService {
	public List<ProductCategory> listCategories();
	public List<Product> listProducts(int category_id);
	public List<ProductAttribute> getAttribute(int product_id);
	public List<ProductImages> getImages(int product_id);
	public String getProductDescriptionById(int id);
	public void addUser(User p);
	public boolean isEmailExist(String email);
	public void addOrder(Orders order);
	public int getUserId(String email);
	public int getProductIdByAttributeId(int attributeId);
	public float getWeightByAttributeId(int id);
	public Orders getOrderById(int id);
	public User getUserById(int id);
	public String getProductNameById(int id);
	public float getPrice(int productId, float weight);
	public ProductAttribute getProductQuantityByProductId(int productId,float weight);
	public boolean updateProductQuantity(ProductAttribute productAttribute);
	public long getTotalQuantity(int product_id);
	public boolean updateProductStatus(int id,int isActive);
}
