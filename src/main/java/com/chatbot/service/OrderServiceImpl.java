package com.chatbot.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chatbot.dao.OrderDao;
import com.chatbot.model.Orders;
import com.chatbot.model.Product;
import com.chatbot.model.User;

public class OrderServiceImpl implements OrderService{
	
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@Override
	@Transactional
	public List<Orders> listOrders(){
		return this.orderDao.listOrders();
	}
	
	@Override
	@Transactional
	public Product getProductById(int id){
		return this.orderDao.getProductById(id);
	}

	@Override
	@Transactional
	public String getUserNameById(int id) {
		return this.orderDao.getUserNameById(id);
	}
	
	@Override
	@Transactional
	public String getUserEmailById(int id) {
	 return this.orderDao.getUserEmailById(id);	
	}
	
	@Override
	@Transactional
	public String getProductNameById(int id) {
		return this.orderDao.getProductNameById(id);
	}
	@Override
	@Transactional
	public float getWeightByAttributeId(int id) {
		return this.orderDao.getWeightByAttributeId(id);	
	}
	@Override
	@Transactional
	public boolean updateOrderStatus(int id,int orderStatus) {
		return this.orderDao.updateOrderStatus(id, orderStatus);
	}
	@Override
	@Transactional
	public List<Orders> listPerPage(int start,int limit){
		return this.orderDao.listPerPage(start, limit);
	}
	
	@Override
	@Transactional
	public long totalPageCount() {
		return this.orderDao.totalPageCount();
	}
	
	@Override
	@Transactional
	public List<Orders> searchFilter(String orderIdNameOrderDate){
		return this.orderDao.searchFilter(orderIdNameOrderDate);
	}
	
	@Override
	@Transactional
	public boolean orderStatus(String value,int orderId){
		return this.orderDao.orderStatus(value, orderId);
	}

	@Override
	@Transactional
	public float getPrice(int productId, float weight) {
		return this.orderDao.getPrice(productId, weight);
	}
}
