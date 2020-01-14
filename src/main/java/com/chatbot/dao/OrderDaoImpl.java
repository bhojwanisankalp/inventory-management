package com.chatbot.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chatbot.model.Orders;
import com.chatbot.model.Product;
import com.chatbot.model.User;

public class OrderDaoImpl implements OrderDao{
	private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	@Override
	public List<Orders> listOrders(){
		Session session = this.sessionFactory.getCurrentSession();
		List<Orders> ordersList = session.createQuery("from Orders").list();
		for(Orders p : ordersList){
			logger.info("Person List::"+p);
		}
		return ordersList;
	}
	@Override
	public Product getProductById(int id){
		Session session = this.sessionFactory.getCurrentSession();		
		Product p = (Product) session.load(Product.class, new Integer(id));
		logger.info("Product loaded successfully, Product details="+p);
		return p; 
	}
	
	@Override
	public String getUserNameById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Query nameQuery = session.createQuery("SELECT c.name FROM User c WHERE c.id=:id");
		nameQuery.setParameter("id", id);
		String userName= (String)nameQuery.uniqueResult();
		return userName;
	}
	
	@Override
	public String getUserEmailById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Query emailQuery = session.createQuery("SELECT c.email FROM User c WHERE c.id=:id");
		emailQuery.setParameter("id", id);
		String userEmail= (String)emailQuery.uniqueResult();
		return userEmail;
	}
	
	@Override
	public String getProductNameById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Query productNameQuery = session.createQuery("SELECT c.name FROM Product c WHERE c.id=:id");
		productNameQuery.setParameter("id",id);
		String productName= (String)productNameQuery.uniqueResult();
		return productName;
	}
	@Override
	public float getWeightByAttributeId(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Query weightQuery = session.createQuery("SELECT c.weight FROM ProductAttribute c WHERE c.id=:id");
		weightQuery.setParameter("id",id);
		float weight= (float)weightQuery.uniqueResult();
		return weight;
	}
	@Override
	public boolean updateOrderStatus(int id,int orderStatus) {
		Session session = this.sessionFactory.getCurrentSession();		
		Query orderStatusQuery = session.createQuery("UPDATE Orders set orderStatus=:orderStatus"+" WHERE id=:id");
		orderStatusQuery.setParameter("id",id);
		orderStatusQuery.setParameter("orderStatus",orderStatus);
		int i=0;
		 i= (int)orderStatusQuery.executeUpdate();
		if(i >0) {
		return true;
		}else {
			return false;
		}
	}
	@Override
	public long totalPageCount() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Orders.class);
		criteriaCount.setProjection(Projections.rowCount());
		return  (Long) criteriaCount.uniqueResult();
	}
	
	@Override
	public List<Orders> listPerPage(int start,int limit){
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Orders.class);
		criteria.addOrder(Order.desc("createdAt"));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		return criteria.list();
	}
	@Override
	public List<Orders> searchFilter(String orderIdNameOrderDate){
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Orders.class);
//		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
//		Date dateobj = new Date();
		int id = 0;
//		try{
//			dateobj = df.parse(orderIdNameOrderDate);
//		}catch(ParseException e) {
//			dateobj = null ;
//		}
		try {
			id = Integer.parseInt(orderIdNameOrderDate);
		}catch(Exception e ) {
			id = 0;
		}
		
		if(id != 0 ) {		
			criteria.add(Restrictions.eq("id", id));
			return criteria.list();
		}else {
			return null;
		}
		//else if(dateobj != null) {
//			criteria.add(Restrictions.like("createdAt", dateobj));
//		}
		
	}
	@Override
	public boolean orderStatus(String value,int orderId) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "UPDATE cart SET order_status ='"+value+"' where order_id ="+orderId;
		SQLQuery query = session.createSQLQuery(sql);
		int i =0 ;
		try {
		i = query.executeUpdate();
		if(i>0) {
			return true;
		}else {
			return false;
		}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public float getPrice(int productId, float weight) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query productNameQuery = session.createQuery("SELECT c.price FROM ProductAttribute c WHERE c.product.id=:id"+" and c.weight=:weight");
			productNameQuery.setParameter("id", productId);
			productNameQuery.setParameter("weight", weight);
			return (float) productNameQuery.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
