package com.chatbot.dao;

import java.util.List;

import com.chatbot.model.Orders;
import com.chatbot.model.Product;
import com.chatbot.model.ProductImages;
import com.chatbot.model.User;

public interface OrderDao {
	public List<Orders> listOrders();
	public Product getProductById(int id);
	public String getUserNameById(int id);
	public String getUserEmailById(int id);
	public String getProductNameById(int id);
	public float getWeightByAttributeId(int id);
	public boolean updateOrderStatus(int id,int orderStatus);
	public List<Orders> listPerPage(int start,int limit);
	public long totalPageCount();
	public List<Orders> searchFilter(String orderIdNameOrderDate);
	public boolean orderStatus(String value,int orderId);
	public float getPrice(int productId, float weight);
}
