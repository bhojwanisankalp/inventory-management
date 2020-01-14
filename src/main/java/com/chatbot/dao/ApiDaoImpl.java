package com.chatbot.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chatbot.model.Orders;
import com.chatbot.model.Product;
import com.chatbot.model.ProductAttribute;
import com.chatbot.model.ProductCategory;
import com.chatbot.model.ProductImages;
import com.chatbot.model.ProductOrdered;
import com.chatbot.model.User;

public class ApiDaoImpl implements ApiDao {
	private static final Logger logger = LoggerFactory.getLogger(ApiDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCategory> listCategories() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ProductCategory> productsCategoryList = session.createQuery("from ProductCategory").list();
		for (ProductCategory p : productsCategoryList) {
			logger.info("Products category List::" + p);
		}
		return productsCategoryList;
	}

	@Override
	public List<Product> listProducts(int category_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query productListQuery = session.createQuery("from Product p where p.productId=:category_id");
		productListQuery.setParameter("category_id", category_id);
		List<Product> productsList = productListQuery.list();
		for (Product p : productsList) {
			logger.info("Person List::" + p);
		}
		return productsList;
	}

	@Override
	public List<ProductAttribute> getAttribute(int product_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query productListQuery = session.createQuery("from ProductAttribute p where p.product.id=:product_id"+" and p.product.isActive=0");
		productListQuery.setParameter("product_id", product_id);
		List<ProductAttribute> productsList = productListQuery.list();
		return productsList;

	}

	@Override
	public List<ProductImages> getImages(int product_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query productImagesListQuery = session.createQuery("from ProductImages p where p.product.id=:product_id");
		productImagesListQuery.setParameter("product_id", product_id);
		List<ProductImages> images = productImagesListQuery.list();
		return images;

	}

	@Override
	public String getProductDescriptionById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query descriptionQuery = session.createQuery("SELECT p.description FROM Product p WHERE p.id=:id");
		descriptionQuery.setParameter("id", id);
		String description = (String) descriptionQuery.uniqueResult();

		return description;
	}

	@Override
	public void addUser(User p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("User saved successfully, User Details=" + p);
	}

	@Override
	public boolean isEmailExist(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User c WHERE c.email=:email");
		query.setParameter("email", email);
		User p = (User) query.uniqueResult();
		if (p != null) {

			return true;

		} else {
			return false;
		}
	}
	
	@Override
	public void addOrder(Orders order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(order);
		logger.info("Order saved successfully, Order Details="+order);
	}
	
	@Override
	public int getUserId(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		Query userIdQuery = session.createQuery("SELECT p.id FROM User p WHERE p.email=:email");
		userIdQuery.setParameter("email", email);
		int id = (Integer)userIdQuery.uniqueResult();
		return id;
	}
	@Override
	public int getProductIdByAttributeId(int attributeId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query userIdQuery = session.createQuery("SELECT p.product.id FROM ProductAttribute p WHERE p.id=:attributeId");
		userIdQuery.setParameter("attributeId", attributeId);
		int id = (Integer)userIdQuery.uniqueResult();
		return id;
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
	public Orders getOrderById(int id) {
		Session session = this.sessionFactory.getCurrentSession();	
		return (Orders) session.createCriteria(Orders.class)
				.add(Restrictions.eq("id", id))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.uniqueResult();
	}
	@Override
	public User getUserById(int id) {
		User user = new User();
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query userIdQuery = session.createQuery("FROM User c WHERE c.id=:id");
			userIdQuery.setParameter("id", id);
			user = (User)userIdQuery.uniqueResult();
			return user;
		}catch(Exception e){
			e.printStackTrace();
			return user;
		}
	}
	@Override
	public String getProductNameById(int id) {
		try{
			Session session = this.sessionFactory.getCurrentSession();		
			Query productNameQuery = session.createQuery("SELECT c.name FROM Product c WHERE c.id=:id");
			productNameQuery.setParameter("id",id);
			String productName= (String)productNameQuery.uniqueResult();
			return productName;
		}catch(Exception e){
			e.printStackTrace();
			return null;
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
	@Override
	public ProductAttribute getProductQuantityByProductId(int productId,float weight) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query productNameQuery = session.createQuery("SELECT FROM ProductAttribute c WHERE c.product.id=:id"+" and c.weight=:weight");
			productNameQuery.setParameter("id", productId);
			productNameQuery.setParameter("weight", weight);
			return (ProductAttribute)productNameQuery.uniqueResult();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateProductQuantity(ProductAttribute productAttribute) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(productAttribute);
			logger.info("Product updated successfully, Product Details=" + productAttribute);
			return true;
		}catch(Exception e) {
		    e.printStackTrace();
			return false;
		}
	}

	@Override
	public long getTotalQuantity(int product_id) {
			Session session = this.sessionFactory.getCurrentSession();
			Query query=session.createQuery("SELECT sum(productQuantity) FROM ProductAttribute WHERE product_id=:id");
			query.setParameter("id", product_id);
			long totalQuantity=(long)query.uniqueResult();
			return totalQuantity;
	}
	@Override
	public boolean updateProductStatus(int id,int isActive) {
		Session session = this.sessionFactory.getCurrentSession();		
		Query orderStatusQuery = session.createQuery("UPDATE Product set isActive=:isActive"+" WHERE id=:id");
		orderStatusQuery.setParameter("id",id);
		orderStatusQuery.setParameter("isActive",isActive);
		int i=0;
		 i= (int)orderStatusQuery.executeUpdate();
		if(i >0) {
		return true;
		}else {
			return false;
		}
	}
}
