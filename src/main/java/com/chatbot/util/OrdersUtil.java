package com.chatbot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.Set;

import com.chatbot.model.Orders;
import com.chatbot.model.ProductOrdered;
import com.chatbot.service.ApiService;

public class OrdersUtil {
	public static Orders order(int userId, Map<String,String> attribute,String totalAmount,ApiService apiService) {
		Orders order = new Orders();
		order.setUser_id(userId);
		Set<String> attributeSet = attribute.keySet();
		List<ProductOrdered>  productslist =new ArrayList<>(); 
		for(String attributeId:attributeSet) {
			ProductOrdered productOrdered =new ProductOrdered();
			productOrdered.setAttribute_id(Integer.parseInt(attributeId));
			productOrdered.setWeight(apiService.getWeightByAttributeId(Integer.parseInt(attributeId)));
			productOrdered.setQuantitySelected(Integer.parseInt(attribute.get(attributeId)));
			productOrdered.setProduct_id(apiService.getProductIdByAttributeId(Integer.parseInt(attributeId)));
			productOrdered.setOrders(order);
			productslist.add(productOrdered);
		}
		order.setTotalAmount(Float.parseFloat(totalAmount));
		order.setProduct_ordered(productslist);
		return order;
	}
}
