package com.chatbot.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.chatbot.dao.ApiDao;
import com.chatbot.model.Orders;
import com.chatbot.model.Product;
import com.chatbot.model.ProductAttribute;
import com.chatbot.model.ProductCategory;
import com.chatbot.model.ProductImages;
import com.chatbot.model.User;

public class ApiServiceImpl implements ApiService {
	
	private ApiDao apiDao;
		
	public void setApiDao(ApiDao apiDao) {
		this.apiDao = apiDao;
	}

	@Override
	@Transactional
	public List<ProductCategory> listCategories(){
		return this.apiDao.listCategories();
	}
	
	@Override
	@Transactional
	public List<Product> listProducts(int category_id){
		return this.apiDao.listProducts(category_id);
	}
	
	@Override
	@Transactional
	public List<ProductAttribute> getAttribute(int product_id){
		return this.apiDao.getAttribute(product_id);
	}
	
	@Override
	@Transactional
	public List<ProductImages> getImages(int product_id){
		return this.apiDao.getImages(product_id);
	}
	
	@Override
	@Transactional
	public String getProductDescriptionById(int id) {
		return this.apiDao.getProductDescriptionById(id);
	}
	
	@Override
	@Transactional
	public void addUser(User p) {
	 this.apiDao.addUser(p);	
	}
	
	@Override
	@Transactional
	public boolean isEmailExist(String email) {
		return this.apiDao.isEmailExist(email);
	}
	
	@Override
	@Transactional
	public void addOrder(Orders order) {
		this.apiDao.addOrder(order);
	}
	
	@Override
	@Transactional
	public int getUserId(String email) {
		return this.apiDao.getUserId(email);
	}
	
	@Override
	@Transactional
	public int getProductIdByAttributeId(int attributeId) {
		return this.apiDao.getProductIdByAttributeId(attributeId);
	}
	@Override
	@Transactional
	public float getWeightByAttributeId(int id) {
		return this.apiDao.getWeightByAttributeId(id);
		
	}
	@Override
	@Transactional
	public Orders getOrderById(int id) {
		return this.apiDao.getOrderById(id);
	}
	@Override
	@Transactional
	public User getUserById(int id) {
		return this.apiDao.getUserById(id);
	}
	@Override
	@Transactional
	public String getProductNameById(int id) {
		return this.apiDao.getProductNameById(id);
	}
	@Override
	@Transactional
	public float getPrice(int productId, float weight) {
		return this.apiDao.getPrice(productId, weight);
	}

	@Override
	@Transactional
	public ProductAttribute getProductQuantityByProductId(int productId, float weight) {
		return this.apiDao.getProductQuantityByProductId(productId, weight);
	}

	@Override
	@Transactional
	public boolean updateProductQuantity(ProductAttribute productAttribute) {
		return this.apiDao.updateProductQuantity(productAttribute);
	}

	@Override
	@Transactional
	public long getTotalQuantity(int product_id) {
		return this.apiDao.getTotalQuantity(product_id);
	}

	@Override
	@Transactional
	public boolean updateProductStatus(int id, int isActive) {
		return this.apiDao.updateProductStatus(id, isActive);
	}
}
